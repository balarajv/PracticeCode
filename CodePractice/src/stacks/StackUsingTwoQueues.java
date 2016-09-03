package stacks;

import queues.MyQueue;

public class StackUsingTwoQueues {
	MyQueue queue1 = new MyQueue(100);
	MyQueue queue2 = new MyQueue(100);
	
	public void push(Object element){
		if(queue1.isFull()){
			throw new IllegalStateException("Stack is full");
		}else{
			queue1.enqueue(element);
		}
	}
	
	public void pop(){
		if(queue2.)
	}
	
	public Object peek(){
		
	}
	
	public boolean isEmpty(){
		
	}
	
	public boolean isFull(){
		
	}
}
