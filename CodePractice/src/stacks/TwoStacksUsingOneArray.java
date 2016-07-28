package stacks;

public class TwoStacksUsingOneArray {
	int top1 = -1;
	int top2;
	int size;
	int []stack;
	
	public TwoStacksUsingOneArray(int size){
		this.size = size;
		top2 = size;
		stack = new int[size];
	}
	
	public void pushStack1(int data){
		if(top1 == top2){
			return;
		}
		stack[top1++] = data;
	}
	
	public void pushtStack2(int data){
		if(top1 == top2){
			return;
		}
		stack[top2--] = data;
	}
	
	public int popStack1(){
		if(top1 == -1){
			System.out.println("stack is empty");
			return -1;
		}else{
			return stack[top1--];
		}
	}
	
	public int poptStack2(){
		if(top2 == size){
			System.out.println("stack is empty");
			return -1;
		}else{
			return stack[top2--];
		}
	}
}
