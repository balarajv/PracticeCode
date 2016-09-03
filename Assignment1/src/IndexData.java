import java.util.List;

import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import org.apache.lucene.codecs.TermVectorsReader;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountRequestBuilder;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.termvectors.TermVectorsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.Queries;
//import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentFactory.*;
import org.elasticsearch.common.xcontent.XContentHelper;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class IndexData {
	
	public static Map<String, String> getBuilder(File file){
		Map<String, String> map = new HashMap<String, String>();
		try {
			String content  = "";
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			String trimLine;
			int count = 0;

			String docNo = "";
		    while ((line = br.readLine()) != null) {
		    	trimLine = line.trim();
		    	if(trimLine.startsWith("<DOC>")){
		    		while((line = br.readLine()) != null
		    				&& ! line.trim().startsWith("</DOC>")){
		    			trimLine = line.trim();
		    			if(trimLine.startsWith("<DOCNO>")){
		    				docNo = trimLine.substring(7, trimLine.indexOf("</DOCNO>")).trim();
		    			}
		    			else if(trimLine.startsWith("<TEXT>")){
				    		while(((line = br.readLine()) != null) 
				    				&& ! line.trim().startsWith("</TEXT>")){
				    			trimLine = line.trim();
				    			content += trimLine+" "; //Faster than stringBuffer verified 
				    		}// end  while(((line = br.readLine()) != null)
				    	}//end else if(trimLine.startsWith("<TEXT>")){
		    		}//end while((line = br.readLine()) != null && line.startsWith("</DOC>"))
		    		map.put(docNo, content);
		    		content = "";
		    		docNo ="";
		    	}//end if(newLine.startsWith("<DOC>")){
		    }// end while ((line = br.readLine()) != null) {
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	public static List<String> indexData() throws Exception{
		Client client = TransportClient.builder().build()
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		List<String> docNos =  new ArrayList<String>();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
        File[] files = new File("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/ap89_collection").listFiles();
        for (File file : files){
        	Map<String, String> map = getBuilder(file);
        	for (Map.Entry<String, String> entry : map.entrySet()) {
        	    String docNo = entry.getKey();
        	    String text  =  entry.getValue();
    			bulkRequest.add(client.prepareIndex("ap_dataset", "document", docNo)
    	                .setSource(XContentFactory.jsonBuilder()
    	                        .startObject()
    	                        .field("docno", docNo)
    	                        .field("text", text)
    	                    .endObject()));
    			docNos.add(docNo);
        	}// for (Map.Entry<String, String> entry : map.entrySet()) {
        }// end for (File file : files){
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        	System.out.println("has errors");
        }
        client.close();
		return docNos;
	}//end public static String IndexData(){
	
	public static List<String> processQueryFile(){
		List<String> queries = new ArrayList<String>();
		try {
			File file = new File("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/query_desc.short.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {	
				queries.add(line.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queries;
	}
	public static float getAverageDocumentLength(Map<String, Integer> docLengths){	
		int totalDocLength =  0;
		for (Integer docNo : docLengths.values()){
			totalDocLength += docNo;
		}
		return ((float)totalDocLength/(docLengths.size()));
	}
	
	public static Map<String, Integer> getDocumentLengths(List<String> docNos){
		Map<String, Integer> docLengths = new HashMap<String, Integer>(); 
		try {
			Client client = TransportClient.builder().build()
				        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			for (String docNo : docNos){	
				docLengths.put(docNo, getDocumentLength(client, docNo));
			}
	        client.close();
		}catch(Exception e){
			
		}
		return docLengths;
	}
	public static int getDocumentLength (Client client, String docNo){
		int docLength = 0;
		Map<String, Integer> termVectors = getTermVectors(client, docNo);
		if(termVectors !=null)
			for(Object termStats : termVectors.values()){
				docLength += (Integer) ((Map)termStats).get("term_freq");
			}
		return docLength;
	}
	public static int getTermFrequency(Map<String, Object> termVectors, String word){
		if(termVectors.containsKey(word) &&
				((Map)termVectors.get(word)).containsKey("term_freq")){
			return (Integer)((Map)termVectors.get(word)).get("term_freq");
		}
		return 0;
	}
	public static Map<String, Integer> getTermVectors(Client client, String docID){
		try{
			TermVectorsResponse response1 = client.prepareTermVectors().setIndex("ap_dataset").setType("document").setId(docID).execute().actionGet();
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			response1.toXContent(builder, ToXContent.EMPTY_PARAMS);
			builder.endObject();
			Map<String, Object> map = XContentHelper.convertToMap(builder.bytes(), false).v2();
			Map<String, Object> termVectors = new HashMap<String, Object>();
			Map<String, Object> text = ((Map)((Map) map.get("term_vectors")).get("text")); 
			if( text != null)
				return processTermVectors((Map)text.get("terms"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private static Map<String, Integer> processTermVectors(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Integer> termFreq = new HashMap<String, Integer>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
    	    Object value  = entry.getValue();
    	    termFreq.put(entry.getKey(), (Integer)((Map)value).get("term_freq"));
    	}
		return termFreq;
	}

	public static Map<String, Integer> getdfwValues(Client client, String str){
		Map<String, Integer> dfwValues = new HashMap<String, Integer>();
		String [] strA = str.split(" ");
		for (int i = 1; i < strA.length; i++){
			dfwValues.put(strA[i], getTermCountInAllDocuments(client, strA[i]));
		}
		return dfwValues;
	}

	public static void processScore(String modal, List<String> queryList, Map<String, Integer> docLengths, List<String> docNos , float alend){
		try {
			Client client = TransportClient.builder().build()
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			List<ScoreDetails> scores = new ArrayList<ScoreDetails>();
			Map<String, Map> termVectorArray = new HashMap<String, Map>(); 
			for (String docNo : docNos){
				termVectorArray.put(docNo, getTermVectors(client, docNo));
			}
			int noDocs = docNos.size();
			System.out.println("term Vectors are built");
			Map<Integer, Map<String, Integer>> dfwArray = new HashMap<Integer, Map<String, Integer>>();
			for(int i = 0; i < queryList.size(); i++){
				dfwArray.put(i, getdfwValues(client, queryList.get(i)));
			}
			System.out.println(dfwArray);
			System.out.println("dfw values are built");
			//if(true) return;
			for (int qNo = 0; qNo < queryList.size(); qNo++){
				String query = queryList.get(qNo);
				String[] words = query.split(" ");
				int queryNo = Integer.parseInt(words[0].substring(0,words[0].length() - 1));
				for (String docNo : docNos){
					Map<String, Integer> termVectors = termVectorArray.get(docNo);
					Double queryScore = (double) 0;
					
						int lend = docLengths.get(docNo);
						for(int i = 1;  i < words.length; i++){
							int tf = 0;
							if(termVectors != null && termVectors.containsKey(words[i]))
								tf = termVectors.get(words[i]); 
							if(modal.equals("okapitf"))
								queryScore += getOKAPItf(tf, lend, alend);
							else if(modal.equals("tfidf")){
								
								int dfw = dfwArray.get(qNo).get(words[i]);
								if (dfw == 0) queryScore = (double) 0;
								else queryScore += gettfIdf(words[i], dfw, tf, lend, alend, noDocs);
							}
							else if(modal.equals("okpitfbm25")){
								int dfw = dfwArray.get(qNo).get(words[i]);
								int tfwq = 0;
								queryScore += getOKAPIBM25(dfw, tf, tfwq, noDocs, 1.2, 0, 0.6, lend, alend);
							}
							else if(modal.equals("lmlaplace")){
								queryScore += p_laplace(tf, lend);
							}
							else {
								int dfw = dfwArray.get(qNo).get(words[i]);
								int tfwddash = dfw-tf;
								int lenddash = 20983860 - lend;
								if (lend == 0) lend = 1; 
								double currentScore = jelinekmercer(docNo, tfwddash, tf, 0.5, lend, lenddash);
								queryScore += currentScore;
							}
						}
					scores.add(new ScoreDetails(queryNo, docNo, queryScore));
					//System.out.println(queryNo+" "+docNo+" "+queryScore);
				}//end for (String docNo : docNos){
				Collections.sort(scores);
				writeRecords(modal+".txt", scores.subList(0, 1000));
				
				scores = new ArrayList<ScoreDetails>();
			}//end for (String query : queryList){ 
	        client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end public static void processOKAPItfScore
	
	public static void writeRecords (String fileName, List<ScoreDetails> scores ){
		try {
			FileWriter fw = new FileWriter("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/"+fileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter writer = new PrintWriter(bw);
		    writer.print("");
		    int rank = 1;
			for (ScoreDetails sd : scores){
				writer.println(sd.queryNo+" "+"Q0 "+sd.docNo+" "+""+rank+" "+sd.score+" "+sd.Exp);
				rank++;
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getTermFrequency(Client client, String docNo, String word){
		int termFrequency = 0;
		try {
			QueryBuilder qb = QueryBuilders
                    .boolQuery()
                    .must(QueryBuilders.termQuery("docno", docNo))
                    .must(QueryBuilders.matchQuery("text", word));
			
			SearchResponse response = client.prepareSearch().setIndices("ap_dataset").setTypes("document")
					.setQuery(qb)
					.setNoFields()
					.setScroll(new TimeValue(60000))
					.setExplain(true)
					.setSize(100)
					.execute().actionGet();
			
			
			String rString = response.toString();
			if(response.toString().indexOf("tf(freq=") != -1){
				int start = rString.indexOf("tf(freq=", response.toString().indexOf("tf(freq=") + 1);
				termFrequency = Integer.parseInt(rString.substring(start+8, rString.indexOf(".", start )));
			}else
				termFrequency = 0;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return termFrequency;
	}
	
	public static int getTermCountInAllDocuments(Client client, String word){
		try {
			CountResponse response = client.prepareCount().setIndices("ap_dataset").setTypes("document")
					.setQuery(QueryBuilders.matchQuery("text", word))
					.execute()
					.actionGet();
			return Math.toIntExact(response.getCount());
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Double p_laplace(int tfwd, int lend){
		int V = 178050; 
		return (double) (Math.log10(((float)(tfwd + 1))/((float)(lend + V))));
	}
	public static double jelinekmercer(String docNo, int tfwddash, int tfwd, double lambda, int lend, int lenddash){
		return (double)(Math.log10(((lambda* ((float)tfwd/lend)) + ((1- lambda)*((float)tfwddash/lenddash)))));
	}
	
	public static Double getOKAPIBM25(int dfw, int tfwd, int tfwq, int D, double k1,float k2, double b, int lend, float alend){
		return (Double) (Math.log((D + 0.5)/(dfw + 0.5))* ((tfwd+ (k1*tfwd))/(tfwd + k1*((1-b)+b* (lend/alend)))));
	}
	public static Double gettfIdf(String word, int dfw, int tf, int lend, float alend, int D){
		return (Double) (((float)tf/(tf + 0.5 + (1.5 *((float)lend / alend))))* Math.log10((float)D/dfw));
	}
	
	public static Double getOKAPItf(int tf, int lend, float alend){
		return (Double)((float)tf/(tf + 0.5 + (1.5 *((float)lend / alend))));
	}
	
	public static void writeDocNos(List<String> docNos){
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/docNoWdocCount.txt", "UTF-8");
			int i = 0;
			for (String sd : docNos){
				writer.println(sd);
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<String> readDocNos(){
		List<String> docNos =  new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/docNoWdocCount.txt"));
			String line = null;
			while ((line = br.readLine()) != null) {
				docNos.add(line.trim());
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docNos;
	}
	public static void writeDocumentLengths(Map<String, Integer> docLengths){
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/docLengths.txt", "UTF-8");
			int i = 0;
			for (Map.Entry<String, Integer> docLength : docLengths.entrySet()) {
				writer.println(docLength.getKey()+" "+docLength.getValue());
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Map<String, Integer> readDocumentLengths(){
		Map<String, Integer> docLengths = new HashMap<String, Integer>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:/Users/Baala/OneDrive/NEU/Summe2016/IR/Assignment1/Data/AP89_DATA/AP_DATA/docLengths.txt"));
			String line = null;
			String []idLength = new String[3];
			while ((line = br.readLine()) != null) {
				idLength = line.trim().split(" ");
				docLengths.put(idLength[0], Integer.parseInt(idLength[1]));
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docLengths;
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//List<String> docNos = new ArrayList<String>();
		//docNos = indexData();
		//writeDocNos(docNos);
		List<String> docNos = readDocNos();
		int docCount = docNos.size();
		List<String> queryList =  processQueryFile();
		//Map<String, Integer> docLengths = getDocumentLengths(docNos);
		//writeDocumentLengths(docLengths);
		Map<String, Integer> docLengths = readDocumentLengths();
		float alend = (float) 247.8077;//getAverageDocumentLength(docLengths);
		System.out.println("average document length "+alend);
		//processScore("okapitf",queryList, docLengths, docNos, alend);
		//processScore("tfidf",queryList, docLengths, docNos, alend);
		//processScore("okpitfbm25",queryList, docLengths, docNos, alend);
		//processScore("lmlaplace",queryList, docLengths, docNos, alend);
		processScore("jelinekmercer",queryList, docLengths, docNos, alend);
	}

}
