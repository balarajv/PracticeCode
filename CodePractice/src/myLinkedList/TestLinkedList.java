package myLinkedList;

import javax.sound.sampled.ReverbType;

public class TestLinkedList {

	/**
	 * @param args
	 */
	public static boolean oddOrEven(MyLinkedList ll){
		if(ll.head == null){
			return true;
		}
		if(ll.head.next == null) return false;
	
		Node even = ll.head.next;
		Node odd = ll.head;
		while(odd.next.next != null && even.next.next != null){
			even = even.next.next;
			odd = odd.next.next;
		}
		if(odd.next.next == null) return true;
		return false;
	}
	
	public static MyLinkedList revesrALinkedListIterative(MyLinkedList ll){
		Node prev = null;
		Node cur = ll.head;
		Node temp;
		while(cur != null){
			temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		ll.head = prev;
		return ll;
	}
	public static void printReverse(Node head){
		if(head == null) return;
		printReverse(head.next);
		System.out.println(head.data);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList ll = new MyLinkedList("A");
		ll.add("B");
		ll.add("C");
		ll.add("D");
		ll.add("E");
		ll.add("F");
		ll.add("G");
		ll.add("H");
		ll.add("I");
		ll.add("J");
		ll.add("K");
		
		MyLinkedList l1 = new MyLinkedList(7);
		l1.add(4);
		l1.add(5);
		l1.add(1);
		
		MyLinkedList l2 = new MyLinkedList(3);
		l2.add(1);
		l2.add(7);
		l2.add(3);
		l2.add(2);
		
		MyLinkedList l3;
		System.out.println(ll.toString());
		
		GerneralProblems gm = new GerneralProblems();
		System.out.println("This is ");
		//System.out.println(gm.getTheNumber(l1.head, 1));
		System.out.println(gm.getTheString(ll.head, 0));
		
		
		
		l3 = gm.addTwoNumbers(l1, l2);
		
		System.out.println(l3);

		ll = gm.reverseBlockOfKNodes(ll, 5);
		System.out.println(ll.toString());
		
		
		
		
		//System.out.println(gm.isLinkeListPalindrome(ll));
		
		//MyLinkedList rll = revesrALinkedListIterative(ll);
		//System.out.println(rll.toString());
		//System.out.println(oddOrEven(ll));
		//printReverse(ll.head);
	}

}
