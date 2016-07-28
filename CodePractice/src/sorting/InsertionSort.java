package sorting;

public class InsertionSort {

	public static int[] insertionSort(int[] arr){
		int temp;
		for(int i = 1; i < arr.length; i++){
			for(int j = i; j > 0; j--){
				if(arr[j -1] > arr[j]){
					temp = arr[j - 1];
					arr[j -1] = arr[j];
					arr[j] = temp;
				}
					
			}
		}
		
		return arr;
	}
	
	public static void printArray(int[] items){
		for (int i = 0; i < items.length; i++){
			System.out.print(items[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("inside main");
		int [] items = {45,23,11,89,77,98,4,28,65,43};
		int []arr = insertionSort(items);
		System.out.println("after execution ");
		printArray(arr);
	}

}
