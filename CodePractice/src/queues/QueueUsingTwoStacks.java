package queues;

import stacks.MyStack;

public class QueueUsingTwoStacks {
	
	MyStack stack1 =  new MyStack(100);
	MyStack stack2 =  new MyStack(100);
	
	public void enqueue(Object element){
		if(stack1.isStackFull()){
			throw new IllegalStateException("Stack is full");
		}
		stack1.push(element);
	}
	
	public Object dequeue(){
		Object element;
		if(stack2.isStackEmpty()){
			if(stack1.isStackEmpty()){
				throw new IllegalStateException("stack is empty");
			}
			while(!stack1.isStackEmpty()){
				stack2.push(stack1.pop());
			}
			element = stack2.pop();
		}else{
			element = stack2.pop();
		}
		return element;
	}
	
	public boolean isFull(){
		
		return false;
	}
	
	public boolean isEmpty(){
		
		return false;
	}
}
