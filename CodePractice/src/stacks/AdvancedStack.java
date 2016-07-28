package stacks;

public class AdvancedStack {
	int []stack;
	int []minStack;
	int top = -1;
	int size;
	
	public AdvancedStack(int size){
		this.size = size;
		stack = new int[size];
		minStack = new int[size];
	}
	
	public void push(int element){
		if(top == size -1){
			System.out.println("Stack is full dumbass");
			return;
		}
		if (element < minStack[top]){
			minStack[++top] = element;
		}
		else{
			minStack[++top] = minStack[top];
		}
		stack[top] = element;
	}
	
	public int pop(){
		if(top == -1){
			System.out.println("stack is empty");
			return -1;
		}else{
			
			return stack[top--];
		}
	}
	
	public int getMinumum(){
		return stack[top];
	}
}
