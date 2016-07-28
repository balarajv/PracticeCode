package myLinkedList;

public class CircularLinkedList {
	Node head = null;
	int length = 0;
	
	public CircularLinkedList(){
		head = null;
	}
	public CircularLinkedList(Object data){
		head.data = data;
		head.next = head;
		length++;
	}
	
	public int countNodes(){
		Node curNode = head;
		if (head == null) return 0;
		int count = 1;
		while(curNode.next != head){
			count++;
			curNode = curNode.next;
		}
		return count;
	}
	
	public String toString(){
		StringBuffer strBuf = new StringBuffer("");
		Node curNode = head;
		strBuf.append(head.data.toString());
		while(curNode.next != head){
			curNode = curNode.next;
			strBuf.append(curNode.data.toString()+" ");
		}
		strBuf.append(curNode.data.toString());
		return strBuf.toString();
	}
	
	public void insertAtTheStart(Object data){
		Node newNode = new Node(data);
		newNode.next = head;
		Node curNode = head;
		while(curNode.next != head){
			curNode = curNode.next;
		}
		curNode.next = newNode;
		head = newNode;
	}
	
	public void insertAtTheEnd(Object data){
		 Node newNode = new Node(data);
		 newNode.next = head;
		 Node curNode =  head;
		 while(curNode.next != head){
			 curNode = curNode.next;
		 }
		 curNode.next = newNode;
	}
	
	public void deleteFront(){
		if(head == null) return;
		if(head.next == head){
			head = null;
			return;
		}
		Node curNode = head.next;
		while(curNode.next != head){
			curNode = curNode.next;
		}
		curNode.next = head.next;
		head = head.next;
	}
	
	public void deleteLast(){
		if(head == null) return;
		if(head.next == head){
			head = null;
			return;
		}
		Node curNode = head;
		while(curNode.next.next != head){
			curNode = curNode.next;
		}
		curNode.next = head;
	}
}
