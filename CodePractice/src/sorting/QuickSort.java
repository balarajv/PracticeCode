package sorting;

public class QuickSort {

	public static void printArray(int[] items){
		for (int i = 0; i < items.length; i++){
			System.out.print(items[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static void quickSort(int[] arr, int low, int high){
		if(low < high){
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("inside main");
		int [] items = {45,23,11,89,77,98,4,28,65,43};
		int []arr = quickSort(items);
		System.out.println("after execution ");
		printArray(arr);
	}

}
