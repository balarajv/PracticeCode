package sorting;

public class SelectionSort {

	
	public static void printArray(int[] items){
		for (int i = 0; i < items.length; i++){
			System.out.print(items[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	public static int[] selectionSort(int []arr){
		int amin, index, temp;
		for(int j = 0; j < arr.length; j++){
			amin = arr[j];
			index = j;
			for(int i = j + 1; i < arr.length;i++){
				if(arr[i] < amin){
					index = i;
					amin = arr[i];
				}
			}
			if(index != j){
				temp = arr[index];
				arr[index] = arr[j];
				arr[j] = temp;
			}
		}	
		return arr;
	}// public static void selectionSort(int []arr){
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("inside main");
		int [] items = {45,23,11,89,77,98,4,28,65,43};
		int []arr = selectionSort(items);
		System.out.println("after execution ");
		printArray(arr);

	}

}
