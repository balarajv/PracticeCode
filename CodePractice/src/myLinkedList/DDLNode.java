package myLinkedList;

public class DDLNode {
	Object data;
	DDLNode prev;
	DDLNode next;
	public DDLNode(Object data){
		this.data =  data;
		next = null;
		prev = null;
	}
	public DDLNode(Object data, DDLNode prev, DDLNode next){
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	public Object getData(){
		return this.data;
	}
	public void setData(Object data){
		this.data = data;
	}
	public DDLNode getPrev(){
		return this.prev;
	}
	public void setPrev(DDLNode node){
		this.prev = node;
	}
	public DDLNode getNext(){
		return this.next;
	}
	public void setNext(DDLNode node){
		this.next = node;
	}
	public String toString(){
		return this.data.toString();
	}
}
