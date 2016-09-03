package queues;

import stacks.MyStack;

public class GeneralQuestions {
	
	/*
	 * Write an algorithm to reverse a Queue, To access the queue you can only use methods
	 * in ADT. 
	 * If you are thinking just exchanging the values of front and rear will work,
	 * No, it will not. The operations we perform with front are very differnt from the kind
	 * of operations we perform on the rear.
	 * 
	 */
	public MyQueue reverseQueue(MyQueue queue){
		MyStack stack = new MyStack(100);
		while(!queue.isEmpty()){
			stack.push(queue.dequeue());
		}
		while(!stack.isStackEmpty()){
			queue.enqueue(stack.pop());
		}
		return queue;
	}
	
	
	
	
}
