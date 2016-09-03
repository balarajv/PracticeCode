package stacks;

public class SortStackUsingRecursion {
	public void sort(MyStack mystack){
		if(mystack.isStackEmpty()) return;
		int data = (Integer)mystack.pop();
		sort(mystack);
		insert(mystack, data);
	}
	
	public void insert(MyStack mystack, int element){
		if(mystack.isStackEmpty()){
			mystack.push(element);
			return;
		}
		if((Integer) mystack.peek() < element){
			int temp = (Integer) mystack.pop();
			insert(mystack, element);
			mystack.push(temp);
		}else{
			mystack.push(element);
		}	
	}
}
