package stacks;

public class MyStack {
	
	int top = -1;
	int size = 100;
	Object []stack;
	public MyStack(int size){
		stack = new Object[size];
	}
	 
	
	public boolean isStackEmpty(){
		if(top == -1) return true;
		return false;
	}
	
	public boolean isStackFull(){
		if(top == size -1) return false;
		return false;
	}
	
	public void push(Object element){
		if(top == size -1){
			System.out.println("Stack is full");
		}
		else stack[++top] = element;
	}
	
	public Object pop(){
		if(top == -1){
			System.out.println("Stack is empty");
		}
		return stack[top--];
	}
	public Object peek(){
		return stack[top];
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("");
		for(int i = top;i > -1; i--){
			sb.append(stack[i]+"  ");
		}
		return sb.toString();
	}
}
