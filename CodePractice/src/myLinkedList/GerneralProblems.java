package myLinkedList;

import java.util.HashMap;
import java.util.Map;

public class GerneralProblems {

	//===========================================================
	// Find the nth node from the end of a LinkedList
	//===========================================================
	public Object nthNodeFromEndMeth1(MyLinkedList ll, int index){
		Node curNode = ll.head;
		//Assuming there is no length variable present
		//Assuming the index is within bounds
		int count = 1;
		while(curNode.next != null){
			count++;
		}
		int indexFromBeginning = count - index +1;
		count = 1;
		curNode = ll.head;
		while(curNode.next !=  null){
			if(count == indexFromBeginning){
				break;
			}
			curNode = curNode.next;
		}
		return curNode.data;
	}
	
	public Object nthNodeFromEndHash(MyLinkedList ll, int index){
		Map<Integer, Object> tempMap = new HashMap<Integer, Object>();
		Node curNode = ll.head;
		int count = 1;
		while(curNode.next !=  null){
			tempMap.put(count, curNode.data);
			count++;
		}
		int pos = count - index + 1;
		return tempMap.get(pos);
	}
	
	public Object nthNodeFromEndUsingPointers(MyLinkedList ll,  int index){
		Node tempNode = ll.head;
		Node nthNode = ll.head;
		int count = 0;
		while(tempNode.next != null){
			count++;
			tempNode = tempNode.next;
			if(count >= index){
				nthNode = nthNode.next;
			}
		}
		return nthNode.data;
	}
	
	//===========================================================
	// Check whether a given LinkedList is either Null-terminated or ends in cycle
	//===========================================================
	
	//returns true if the LinkedList is null terminated
	//false if it is cycel
	public boolean nullOrCycle(MyLinkedList ll){
		Node curNode = ll.head;
		while(curNode.next != null){
			if(insideLinkedList(ll, curNode.data)){
				return false;
			}
			curNode = curNode.next;
		}
		return true;
	}
	public boolean insideLinkedList(MyLinkedList ll, Object data){
		Node curNode =  ll.head;
		while(curNode.next != null){
			if(curNode.data.equals(data)){
				return true;
			}
			curNode = curNode.next;
		}
		return false;
	}
	//==============Meth2====================================
	public boolean nullOrCycleSlowFast(MyLinkedList ll){
		Node slow = ll.head;
		Node fast = ll.head;
		while(slow.next != null && fast.next != null){
			if(slow.data.equals(fast.data)) {
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}
	
	//==============using hashMaps===========================
	public boolean nullOrCycleHash(MyLinkedList ll){
		Node curNode = ll.head;
		Map<Object, Integer> temp = new HashMap<Object, Integer>();
		while(curNode.next != null){
			if(temp.containsKey(curNode.data)){
				return false;
			}
			temp.put(curNode.data, 1);
			curNode = curNode.next;
		}
		return true;
	}
	//=============Check wheater the given Linked list is either NUll terminated or not. 
	//If there is a cycle find the start ==============================================
	
	public Node findStartInLoop(MyLinkedList ll){
		Node slow = ll.head;
		Node fast = ll.head;
		
		while(slow.next != null && fast.next != null){
			if(slow.data.equals(fast.data)){
				slow = ll.head;
				while(!slow.data.equals(fast.data)){
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return null;
	}
	/*
	 * Check whether a given linkedList is either Null terminated or not
	 * If there is a loop find the length of the loop 
	 */
	public int findLengthInaLoop(MyLinkedList ll){
		Node slow = ll.head;
		Node fast = ll.head;
		
		while(slow.next != null && fast.next != null){
			if(slow.equals(fast)){
				int count = 1;
				while(!fast.next.equals(slow)){
					count++;
					fast = fast.next;
				}
				return count;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return 0;
	}
	
	/*
	 * Insert a node in a sorted Linked list
	 */
	
	public MyLinkedList insertIntoSortedLinkedList(MyLinkedList ll, int num){
		Node prev = ll.head;
		Node cur = ll.head;
		Node temp = new Node(num);
		while(cur != null){
			if((Integer) prev.data < num){
				break;
			} 
			prev = cur;
			cur = cur.next;
		}
		prev.next = temp;
		temp.next = cur;
		return ll;
	}
	
	/*
	 * Reverse a singly LinkedList Iteratively
	 */
	public Node reverALinkedListIterative(Node head){
		Node prev = null;
		Node cur = head;
		Node temp;
		while(cur != null){
			temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		head = prev;
		return head;
	}
	
	/*
	 * Reverse a singly LinkedList using recursion
	 */
	
	public MyLinkedList reverALinkedListRecursion(MyLinkedList ll){
		
		return ll;
	}
	
	/*
	 * Given two Linked Lists, if they merge at some point, find the merging point
	 * Using bruteforce
	 * Using stacks
	 * Using HashMap
	 * Using the best approach copied from the book
	 */
	
	
	
	/*
	 * Find middle of a linked list
	 */
	public Node midleOfLinkedList(MyLinkedList ll){
		Node slow = ll.head;
		Node fast = ll.head;
		while(slow.next != null && fast.next != null && fast.next.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	
	/*
	 * How will you display a linked list from the end Use recursion
	 */
	public void printReverse(Node head){
		if(head == null) return;
		printReverse(head.next);
		System.out.println(head.data);
	}
	
	/*
	 * Check whether the given linked list length is even or odd
	 * returns true if even
	 * odd otherwise
	 */
	public boolean oddOrEven(MyLinkedList ll){
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
	
	/*
	 * Given two sorted linked lists, we need to merge them into the third list in sorted order
	 */

	public MyLinkedList mergeSortedLinkedLists(MyLinkedList l1, MyLinkedList l2){
		Node node1 = l1.head;
		Node node2 = l2.head;
		MyLinkedList tempList = new MyLinkedList();
		while(node1 != null && node2 != null){
			if((Integer) node1.data > (Integer) node2.data){
				tempList.add(node2.data);
			} else{
				tempList.add(node1.data);
			}
			node1 = node1.next;
			node2 = node2.next;
		}
		while(node1 != null){
			tempList.add(node1.data);
			node1 = node1.next;
		}
		while(node2 != null){
			tempList.add(node1.data);
			node2 = node2.next;
		}
		return tempList;
	}
	
	/*
	 * Split a circular Linked list into two equal parts. If the number of nodes in the list are odd then make
	 * the first list one node extra than second list.
	 */
	public CircularLinkedList splitCircularLinkedList(CircularLinkedList cll){
		CircularLinkedList cll2 = new CircularLinkedList();
		Node slow = cll.head;
		Node fast = cll.head;
		
		while(slow.next != null && fast.next != null && fast.next.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		Node temp = slow.next;
		Node end = null;
		if(fast.next == null) end = fast;
		else if(fast.next.next == null) end = fast.next;
		slow.next = cll.head;
		
		cll2.head = temp;
		end.next = temp;
		
		return cll;
	}
	
	/*
	 * Check if a linkedList is palindrome or not
	 */
	
	public boolean isLinkeListPalindrome(MyLinkedList ll){
		Node midNode = midleOfLinkedList(ll);
		System.out.println(midNode);
		midNode = reverALinkedListIterative(midNode);
		System.out.println(midNode);
		Node temp = ll.head;
		while(temp.next != null  && midNode.next != null){
			if(!temp.data.equals(midNode.data)) return false;
			temp = temp.next;
			midNode = midNode.next;
		}
		return true;
	}
	
	public void printFromNode(Node head){
		Node cur = head;
		while(cur!= null){
			System.out.print(cur.data.toString()+"->");
			cur = cur.next;
		}
		System.out.println();
	}
	
	/*
	 * For a given K value K>0 reverse blocks of K nodes in a list
	 * Example Input 12345678
	 * For k =3 : 32145678
	 */
	public MyLinkedList reverseBlockOfKNodes(MyLinkedList ll, int k){
		int count = 0;
		
		Node prev = null;
		Node cur = ll.head;
		Node firstNode = cur;
		Node temp = null;
		while(count <= k){
			temp = cur.next;
			cur.next = prev;
			count++;
			if(count == k) {
				break;
			}
			prev = cur;
			cur = temp;			
		}
		ll.head = cur;
		Node afterK = temp;
		firstNode.next = afterK;
		return ll;
	}
	
	/*
	 * Reverse the linked list in pairs. If yo have a linked list that holds 1->2->3->4->x  then after the 
	 * function has been valled the linked list would hold 2->1->4->3->x
	 */
	public MyLinkedList reverseLinkedListInPairs(MyLinkedList ll){
		
		
		return null;
		
	}
	
	
	/*
	 * JosephusCircle N people have decide to elect a leader by arranging themselves in a circle
	 * and eliminating every Mth person around the circle, closing ranks as each person drops out. Find
	 * which person will be the last one to be eliminated. Rank 1
	 * 
	 */
	public Node josephusCircle(CircularLinkedList cll){
		Node prev  = null;
		Node cur  = cll.head;
		int count = 0;
		while(!prev.equals(cur)){
			if(count++ == 3){
				count = 1;
				prev.next = cur.next;
				cur = cur.next;
			}
			else{
				prev = cur;
				cur = cur.next;
			}
		}
		return null;
	}// end public Node josephusCircle(CircularLinkedList cll){
	
	
	
	/*
	 * Problem 35 Find Modular Node: Given a singly linked list write a function to find the element whose
	 * n%k == 0, where n is the number of elements in a list and k is an integer constant. For example if n=10 and 
	 * k =3 then we  should return 18th node.
	 */
	
	
	/*
	 * Problem 40 Given a linked list, how do you modify it such that all even numbers appear before all the odd 
	 * numbers in the modified linked list
	 * 
	 */
	public Node seperateEvenOdd(MyLinkedList ll){
		
		MyLinkedList ell = new MyLinkedList();
		MyLinkedList oll = new MyLinkedList();
		Node cur = ll.head;
		
		while(cur != null){
			if(((Integer) cur.data)%2 == 0){
				ell.add(cur.data);
			}else{
				oll.add(cur.data);
			}
		}
		Node ecur = ell.head;
		while(ecur.next != null){
			ecur = ecur.next;
		}
		ecur.next = oll.head;
		return ell.head;
	}
	
	
	
	/*
	 * Problem 41: Given two linked lists each list node with one integer digit add these two linked lists. Result should
	 * be stored in third linked list. Also note that the head node contains the most significant digit of the member
	 */
	public MyLinkedList addTwoNumbers(MyLinkedList l1, MyLinkedList l2){
		
		l1.head = reverALinkedListIterative(l1.head);
		l2.head = reverALinkedListIterative(l2.head);
		Node cur1 = l1.head;
		Node cur2 = l2.head;
		
		int carry = 0;
		MyLinkedList l3 = new MyLinkedList();
		while(cur1 != null && cur2 != null){
			int num = (Integer) cur1.data + (Integer) cur2.data;
			l3.add(num%10 + carry);
			carry = num/10;
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		while(cur1 != null){
			l3.add((Integer)cur1.data + carry);
			cur1 = cur1.next;
			carry = 0;
		}
		
		while(cur2 != null){
			l3.add((Integer)cur2.data + carry);
			cur2 = cur2.next;
			carry = 0;
		}
		if(carry != 0) l3.add(carry);
		
		l3.head = reverALinkedListIterative(l3.head);
		return l3;
	}
	
	public int getTheNumber(Node head){
		if(head == null) return 0;
		return (int) ((Integer) getTheNumber(head.next)*10 + ((Integer)head.data));
	}
	
	public String getTheString(Node head, int i){
		if(head == null) return "";
		return (String) getTheString(head.next, i++) + (String)head.data;
	}
	
	/*
	 * Find fractional node: Given a singly linked list write a function to find n/k th element,where n is the number
	 * of elements  in the list.If n = 19 and k =3 should return 6th node 
	 */
	
	
	
	/*
	 * Median in an infinite series of Integers
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
