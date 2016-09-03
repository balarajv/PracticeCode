package queues;

import myLinkedList.Node;

public class QueuesUsingLInkedList {

	private Node front = null;
	private Node rear  = null;
	
	public void enqueue(Object element){
		Node newElement = new Node(element);
		if(front == null && rear == null){
			front = newElement;
			rear = newElement;
		}else{
			Node temp = rear;
			temp.setNext(newElement);
			rear = newElement;
		}
	}
	
	public Object dequeue(){
		Object element;
		if(isEmpty()){
			throw new IllegalStateException("Stack is empty");
		}else if(front.equals(rear)){
			element = front;
			front = null;
			rear = null;
		}else{
			element = front;
			front = front.getNext();
		}
		return element;
	}
	
	public boolean isEmpty(){
		return (front == null || rear == null);
	}

}

