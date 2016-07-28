package generealRecursion;

public class TowerOfHanoi {

	public static void solve(int n, String source, String auxillary, String destination){
		if(n == 1){
			System.out.println(source +" to "+ destination);
		}else{
			solve(n-1, source, destination, auxillary);
			System.out.println(Integer.toString(n)+" "+source +" to "+ destination);
			solve(n-1, auxillary, source, destination);
		}
		
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		solve(4, "S","B","C");
	}

}
