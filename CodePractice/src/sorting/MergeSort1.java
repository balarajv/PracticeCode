package sorting;

public class MergeSort1 {

	/**
	 * @param args
	 */
	static int tempArr [];
	static int anotherArr [];
	public static void mergeSort(int []inputArr){
		int length = inputArr.length;
		anotherArr = inputArr;
		tempArr =  new int[length];
		domergeSort(0,length-1);
	}
	
	public static void domergeSort(int lowerBound, int upperBound){
		if(lowerBound < upperBound){
			int middle = lowerBound + (upperBound - lowerBound )/2;
			domergeSort(lowerBound,middle);
			domergeSort(middle +1, upperBound);
			mergeParts(lowerBound, middle, upperBound);
		}
	}
	
	public static void mergeParts(int lowerBound, int middle, int upperBound){
		for (int i = 0; i <= upperBound; i++){
			tempArr[i] = anotherArr[i];
		}
		int i = lowerBound, j = middle + 1, k = lowerBound;
		while( i <= middle && j <= upperBound){
			if(tempArr[i] <= tempArr[j]){
				anotherArr[k] = tempArr[i];
				i++;
			}else{
				anotherArr[k] = tempArr[j];
				j++;
			}
			k++;
		}
		while( i <= middle){
			anotherArr[k++] = tempArr[i++];
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] items = {45,23,11,89,77,98,4,28,65,43};
		mergeSort(items);
		for (int i = 0; i < items.length; i++){
			System.out.print(anotherArr[i]);
			System.out.print(" ");
		}
	}

}
