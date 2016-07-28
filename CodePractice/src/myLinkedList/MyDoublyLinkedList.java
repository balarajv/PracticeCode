package myLinkedList;

public class MyDoublyLinkedList {
	private DDLNode head = null;
	int length = 0;
	
	public MyDoublyLinkedList(Object data){
		head = new DDLNode(data);
		length++;
	}
	
	public void insert(Object data){
		DDLNode givenNode =  new DDLNode(data);
		if(head == null){
			head = givenNode;
			return;
		}
		DDLNode curNode = head;
		while(curNode.next != null){
			curNode = curNode.next;	
		}
		givenNode.prev = curNode;
		curNode.next = givenNode;
		length++;
	}
	
	public void insert(Object data, int index){
		DDLNode givenNode =  new DDLNode(data);
		length++;
		if(index == 1){
			givenNode.next = head;
			head.prev = givenNode;
			head =  givenNode;
			return;
		}
		DDLNode curNode = head;
		int count = 0;
		while(curNode.next != null){
			if(count == index - 2) break;
			curNode = curNode.next;
		}
		DDLNode nextNode = curNode.next;
		curNode.next = givenNode;
		givenNode.prev = curNode;
		givenNode.next = nextNode;
		nextNode.prev = curNode;
	}
	public void deleteFirstNode(){
		if(head == null) return;
		if(head.next == null){
			head = null;
			return;
		}
		head = head.next;
		head.prev = null;
	}
	public void deleteLastNode(){
		if(head == null) return;
		if(head.next == null){
			head = null;
			return;
		}
		DDLNode curNode =  head;
		while(curNode.next != null){
			curNode =  curNode.next;
		}
		curNode.prev.next = null;
	}
	public void deleteAtIndex(int index){
		DDLNode curNode =  head;
		if(index == 1) deleteFirstNode();
		if(index == length) deleteLastNode();
		int count = 1;
		while( curNode.next != null ){
			if(index == count) break;
			count++;
			curNode = curNode.next;
		}
		curNode.prev.next = curNode.next;
		curNode.next.prev = curNode.prev; 
	}
}
