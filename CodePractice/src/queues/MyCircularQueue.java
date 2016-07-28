package queues;

public class MyCircularQueue {

	private int front = -1;
	private int rear = -1;
	private int size;
	private int []mystack;
	
	public MyCircularQueue(int m){
		this.size = m;
		mystack = new int[m];
	}
	
	public void enqueue(int element){
		if(isFull()){
			throw new IllegalStateException("Queue is full");
		}else if(front == -1 && rear == -1){
			front++;
			rear++;
			mystack[rear] = element;
		}else{
			rear = (rear + 1)%size;
			mystack[rear] = element;
		}
	}
	
	public int dequeue(){
		int value;
		if(isEmpty()){
			throw new IllegalStateException("Queue is empty");
		}else if(front == rear){
			value = mystack[front]; 
			front = -1;
			rear = -1;
		}
		else{
			value = mystack[front];
			front = (front + 1)%size;
		}
		return	value;
	}
	
	public boolean isFull(){
		return (((rear + 1)%size) == front);
	}
	
	public boolean isEmpty(){
		return (front == -1 && rear == -1);
	}

}
