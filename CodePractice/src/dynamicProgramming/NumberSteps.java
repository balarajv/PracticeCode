package dynamicProgramming;

public class NumberSteps {

	
	public static int possiblePathsSimple(int n){
		
		int cur = 1, last = 1;
		for (int i = 1; i < n; i++){
			int temp = cur;
			cur = cur + last;
			last = temp;
		}
		return cur;
	}
	
	public static int possiblePathsDynamicUtil(int n, int[] dyn){
		
		if(n < 1){
			return 0;
		}
		if(dyn[n] > 0){
			return dyn[n];
		}
		dyn[n] = 1 + possiblePathsDynamicUtil(n - 1, dyn) +  possiblePathsDynamicUtil(n -2 , dyn);	
		return dyn[n];
	}
	
	public static int possilePathsDynamic(int n){
		int [] dyn = new int[n + 1]; 
		return possiblePathsDynamicUtil(n, dyn);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(possiblePathsSimple(5));
	}

}
