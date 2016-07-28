package stacks;

public class GeneralProblems {
	
	/*
	 * In this set reversing a stack is one of the elegant approaches I have seen.
	 */

	MyStack mystack = new MyStack(100);
	/*
	 * Stacks for checking the balancing of symbols
	 */
	public boolean checkBraces(String[] braces){
		
		for(int i = 0; i < braces.length; i++){
			if(braces[i].equals("(") ||
				braces[i].equals("{") ||
				braces[i].equals("[")){
				mystack.push(braces[i]);
			}else{
				
				if(mystack.isStackEmpty()) return false;
				else{
					if(!mystack.pop().equals(braces[i])) return false;
				}
			}
		}
		if(!mystack.isStackEmpty()) return false;
		return true;
	}
	
	/*
	 * Discuss infix to postfix conversion algorithm using stack
	 */
	
	
	
	/*
	 * Discuss postfix evaluation using stack
	 */
	
	/*
	 * Can we evaluate the infix expression in 1pass without converting to postfix
	 */
	
	/*
	 * How to design a stack such that GetMinimum() should be 0(1)
	 * Implemented as a  separate class
	 * Eficient appraoch is maintianing two different tops 
	 */
	
	/*
	 * Improve space complexity of above function
	 * Same as above two tops
	 */
	
	/*
	 * For a  give array with n symbols how many stack permutaitons are possible
	 * Will be done in Dyanmic programming
	 */
	
	
	/*
	 * Given an array of characters with X in the middle Check to see if the string is a 
	 * palindrome.
	 */
	public boolean stackPalindrome(String[] strs){
		
		int i = 0;
		while(!strs[i].equals("X")){
			mystack.push(strs[i]);
			i++;
		}
		while(i < strs.length){
			if(! mystack.pop().equals(strs[i])) return false;
		}
		return true;
	}
	
	/*
	 * Given a stack, how to reverse a stack using only stack operations push and pop
	 */
	
	public void reverseStack(MyStack stack){
		if(stack.isStackEmpty()) return;
		int data = (Integer) stack.pop();
		reverseStack(stack);
		insertAtBottom(stack, data);
	}
	
	public void insertAtBottom(MyStack stack, Object data){
		if(stack.isStackEmpty()){
			stack.push(data);
			return;
		}
		Object temp = stack.pop();
		insertAtBottom(stack, data);
		stack.push(temp);
	}
	
	/*
	 * Given a stack of integers, how do check whether each successive pair of numbers in the stack is 
	 * consecutive or not. The pairs can be increasing or decreasing and if the stack has an odd number
	 * of elements the element at the top is left out of pair.
	 * 
	 * 
	 * Will be dealt in Queues leaving for now.
	 */
	
	
	
	
	/*
	 * Recursively remove first character of adjacent duplicates  Given a string of charcters
	 * input: careermonk output:carermonk
	 */
	
	public String removeAdjacentDuplicates(String str){
		return removeDuplicates(str.substring(1), str.charAt(0));
	}
	
	public String removeDuplicates(String str, char lastChar){
		if(str.equals("")) return ""+lastChar;
		if(str.charAt(0) == lastChar) return removeDuplicates(str.substring(1), str.charAt(0));
		else return lastChar + removeDuplicates(str.substring(1), str.charAt(0));
	}
	
	/*
	 * recursively remove all adjacent duplicates: The output should not have any adjacent duplicates
	 * Input: Mississippi output:m
	 * INput: careermonk  output:camonk
	 */
	public String removeDuplicates(String str){
		StringBuilder tempStr = new StringBuilder(str);
		int stackPointer = -1;
		int len = str.length();
		int i = 0;
		while( i < len){
			if(stackPointer == -1 || tempStr.charAt(stackPointer) != tempStr.charAt(i)){
				stackPointer++;
				tempStr.delete(stackPointer, stackPointer + 1);
				//tempStr.
				//i++;
			}
			else{
				while( i < len && (str.charAt(stackPointer) == str.charAt(i))){
					i++;
					stackPointer--;
				}
			}
		}
		return "";
	}
	
	/*
	 * Given an array of elements, replace every element with the nearest greater element on
	 * the right of that element
	 * One simplest approach would involve scanning the array elemets and for each of the element, scan the
	 * remaining elements and find the nearest greater element in that.
	 * 
	 */
	public int[] replaceToNearestGreater(int []arr){
		
		for(int i = 0; i < arr.length; i++){
			for(int j = i + 1; j < arr.length; j++){
				if(arr[i] < arr[j]){
					arr[i] = arr[j];
					break;
				}
			}//end for(int j = i + 1; j < arr.length; j++){
		}
		return arr;
	}
	
	public int[] replaceToNearestGreaterMethod(int[] arr){
		
	}
	
	/*
	 * Sort a stack in increasing order using the operations push, pop, top and empty
	 * IMplemented as a separate class SortStackUsingRecursion
	 */
	
}
