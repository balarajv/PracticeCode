package stacks;

public class TestStackFucntions {
	
	public static void main(){
		MyStack mystack = new MyStack(100);
		mystack.push(1);
		mystack.push(2);
		mystack.push(3);
		GeneralProblems gm  = new GeneralProblems();
		gm.reverseStack(mystack);
		
		
	}
}
