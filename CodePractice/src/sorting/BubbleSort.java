package sorting;

public class BubbleSort {

	/**
	 * @param args
	 */
	
	public static void printArray(int[] items){
		for (int i = 0; i < items.length; i++){
			System.out.print(items[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static int[] bubbleSort(int []items){
		int temp;
		boolean flag = true;
		while(flag){
			flag = false;
			for(int j =0; j < items.length - 1; j++){
				if(items[j] > items[j +1]){
					temp = items[j];
					items[j] = items[j + 1];
					items[j + 1] = temp;
					flag = true;
				}
			}
		}//end while(flag){ 
		return items;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("inside main");
		int [] items = {45,23,11,89,77,98,4,28,65,43};
		int []arr = bubbleSort(items);
		System.out.println("after execution ");
		printArray(arr);
	}

}
