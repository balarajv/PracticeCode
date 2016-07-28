package sorting;

public class MergeSort {

	static int tempArr[];
	public static void mergeSort(int[] A){
		if(A.length == 1) return;
		int n = A.length;
		int c = n/2;
		int[]B = new int[c];
		int[]C = new int[n - c];
		for (int i = 0;  i < c; i++){
			B[i] = A[i];
		}
		for(int j = c; j < n; j++){
			C[j - c] = A[j];
		}
		 mergeSort(B);
		 mergeSort(C);
		 merge(B,C);
	}
	
	/**
	 * @param A
	 * @param B
	 */
	/**
	 * @param A
	 * @param B
	 */
	public static void merge(int []A, int []B){
		int lenA = A.length;
		int lenB = B.length;
		int i = 0, j = 0, k = 0;
		while(i < lenA && j < lenB){
			if(A[i] < B[j] ){
				tempArr[k] = A[i++];
			}else{
				tempArr[k] = B[j++];
			}
			k++;
		}
		if(i < lenA){
			while(i < lenA){
				tempArr[k++] = A[i++];
			}
		}else{
			while(j < lenB){
				tempArr[k++] = B[j++];
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {3,1,4,2,6,8,5,7};
		tempArr = new int[A.length];
		mergeSort(A);
		System.out.println(tempArr.toString());
		for (int i = 0; i < tempArr.length; i++){
			System.out.println(tempArr[i]);
		}
	}
	

}
