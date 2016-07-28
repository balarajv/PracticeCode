package myLinkedList;

public class Node {
	Object data;
	Node next;
	public Node(Object data){
		this.data =  data;
		next = null;
	}
	public Node(Object data, Node node){
		this.data = data;
		next = node;
	}
	public Object getData(){
		return this.data;
	}
	public void setData(Object data){
		this.data = data;
	}
	public Node getNext(){
		return this.next;
	}
	public void setNext(Node node){
		this.next = node;
	}
	public String toString(){
		return this.data.toString();
	}
}
