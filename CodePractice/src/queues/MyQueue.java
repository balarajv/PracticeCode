package queues;

public class MyQueue {

	int front = -1;
	int rear = -1;
	int size;
	Object []mystack;
	
	public MyQueue(int m){
		this.size = m;
		mystack = new Object[m];
	}
	
	public void enqueue(Object element){
		if(isFull()){
			throw new IllegalStateException("Queue is full");
		}else if(front == -1 && rear == -1){
			front++;
			rear++;
			mystack[rear] = element;
		}else{
			System.out.println(rear);
			System.out.println((rear + 1)%size);
			System.out.println("====================");
			rear = (rear + 1)%size;
			mystack[rear] = element;
		}
	}
	
	public Object dequeue(){
		Object value;
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
