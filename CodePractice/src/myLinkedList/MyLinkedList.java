package myLinkedList;

public class MyLinkedList {
	Node head = null;
	int length = 0;
	
	public MyLinkedList(){
		
	}
	public MyLinkedList(Object data){
		head = new Node(data);
	}
	
	public void add(Object data){
		Node givenNode = new Node(data);
		if(head == null) head = givenNode;
		else{
			Node curNode = head;
			while(curNode.next != null){
				curNode = curNode.next;
			}
			curNode.next = givenNode;
			length++;
		}
		
	}
	
	public void add(Object data, int index){
		Node givenNode = new Node(data);

		if(index == 1){
			givenNode.next = head;
			head = givenNode;
			length++;
			return;
		}
		Node prevNode = head;		
		for(int i = 0; i < length; i++){
			if(i == index - 2) break;
			prevNode = prevNode.next;
		}
		Node position = prevNode.next;
		prevNode.next = givenNode;
		givenNode.next = position;
		length++;
	}
	
	public void delete(Object data){
		Node prevNode = null;
		Node curNode = head;
		if(head.data.equals(data)){
			head = head.next;
			return;
		}
		while(curNode.next != null){
			if(curNode.data.equals(data)){
				break;
			}
			prevNode = curNode;
			curNode  = curNode.next;
		}
		prevNode.next = curNode.next; 
	} 
	
	public void deleteAtFront(){
		if(head == null) return;
		head = head.next;
	}
	
	public String toString(){
		StringBuffer strBuff = new StringBuffer("");
		Node curNode = head;
		while(curNode != null){
			strBuff.append(curNode.data.toString()+"->");
			curNode = curNode.next;
		}		
		return strBuff.toString();
	}
	
	
	
}
