package myLinkedList;

public class StackUsingLinkedList {
	int length = -1;
	MyLinkedList mll = new MyLinkedList();
	public void push(Object data){
		mll.add(data, 1);
	}
	public void pop(){
		if(mll.length == 0) return;
		mll.deleteAtFront();
	}
	public Object top(){
		if(mll.length == 0) return null;
		return mll.head.data;
	}
}
