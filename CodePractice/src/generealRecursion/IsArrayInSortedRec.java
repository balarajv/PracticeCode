package generealRecursion;

public class IsArrayInSortedRec {

	public static boolean isSorted(int[] arr, int n){
		if(n == 0){
			return true;
		}else{
			return arr[n] > arr[n-1] ? isSorted(arr, n-1): false;
		}
	}
	public static void main(String[] args) {
		int [] a = {3,5,4,1,2};
		int []b = {1,2,3,4,5};
		System.out.println(isSorted(b, b.length -1));
	}

}
