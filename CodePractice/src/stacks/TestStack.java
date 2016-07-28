package stacks;

public class TestStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyStack mystack = new MyStack(100);
		mystack.push(5);
		mystack.push(2);
		mystack.push(4);
		mystack.push(1);
		mystack.push(7);
		mystack.push(9);
		mystack.push(8);
		
		//GeneralProblems gm  = new GeneralProblems();
		//gm.reverseStack(mystack);
		
		SortStackUsingRecursion sm = new SortStackUsingRecursion();
		sm.sort(mystack);
		
		System.out.println(mystack.toString());
		
		//System.out.println(gm.removeAdjacentDuplicates("careermonkk"));
	}

}
