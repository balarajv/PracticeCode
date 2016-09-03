package bInaryTrees;

import queues.MyQueue;

public class BinaryTree {
	
	/*
	 * Basic operations to be implemented
	 * Insert a node
	 * Delete a node
	 * Find a node
	 * 
	 */
	private TreeNode root = null;
	
	public BinaryTree(Object data){
		this.root = new TreeNode(data); 
	}
	
	public BinaryTree(){
		this.root = null;
	}
	
	public boolean findIterative(Object data){
		if(this.root == null) return false;
		TreeNode current =  root;
		while(current != null){
			if((Integer) current.data < (Integer) data){
				current = current.left;
			}else if((Integer) current.data > (Integer) data){
				current = current.right;
			}else{
				return true;
			}
		}
		return false;
	}
	
	public boolean findRecursive(Object data){
		if(this.root == null) return false;
		return findRecursiveHelper(root, data);
	}
	
	public boolean findRecursiveHelper(TreeNode root, Object data){
		if(root == null) return false;
		if((Integer) data < (Integer) root.data){
			findRecursiveHelper(root.left, data);
		}else if((Integer) data < (Integer) root.data){
			findRecursiveHelper(root.right, data);
		}else{
			return true;
		}
		return false;
	}
	
	public void insertIterative(Object data){
		if(this.root == null) this.root = new TreeNode(data);
		TreeNode cur = root;
		
		while(cur != null){
			if((Integer)(data) <= (Integer)root.data){
				if(cur.left == null){
					cur.left = new TreeNode(data);
					return;
				}
				cur = cur.left;
			}else if((Integer)(data) >= (Integer)root.data){
				if(cur.right == null){
					cur.right = new TreeNode(data);
					return;
				}
				cur = cur.right;
			}
		}
	}//end public void insertIterative(Object data){
	
	public void insertRecursive(Object data){
		TreeNode givenNode = new TreeNode(data);
		if (root ==  null){
			root =  givenNode;
		}
		insertRecursiveHelper(root, givenNode);
	}
	
	public void insertRecursiveHelper(TreeNode current, TreeNode givenNode){
		if((Integer) current.data > (Integer) givenNode.data){
			if(current.left == null){
				current.left = givenNode;
				return;
			} insertRecursiveHelper(current.left, givenNode);
		}else{
			if(current.right == null){
				current.right = givenNode;
				return;
			} insertRecursiveHelper(current.right, givenNode);
		}
	}// end public void insertRecursiveHelper(TreeNode current, TreeNode givenNode){
	
	public void delete(TreeNode node,TreeNode parent, int value){
		
		if((Integer)node.data < value){
			parent = node;
			delete(node.left, parent, value);
		}else if((Integer)node.data > value){
			parent = node;
			delete(node.right, parent, value);
		}else{
			if(node.left != null && node.right != null){
				TreeNode successor = findSuccessor(node.right);
				node.data = successor.data;
				delete(node.right, node, (Integer)successor.data);
				parent = node;
			}else if(node.left == null){
				parent.right = node.right;
			}else if(node.right == null){
				parent.left = node.left;
			}else{
				parent.left = null;
				parent.right = null;
			}
		}
	}
	
	public TreeNode findSuccessor(TreeNode node){
		return findMinInBST(node);
	}
	
	public TreeNode findMinInBST(TreeNode node){
		if(node == null) return null;
		while(node.left != null){
			node = node.left;
		}
		return node;
	}
	
	public void TraverseInOrder(TreeNode node){
		if (node == null){
			return;
		}
		TraverseInOrder(node.left);
		System.out.println(node.data);
		TraverseInOrder(node.right);
	}
	
	public void TraversePreOrder(TreeNode node){
		if(node == null){
			return;
		}
		System.out.println(node.data);
		TraversePreOrder(node.left);
		TraversePreOrder(node.right);
	}
	
	public void TravresePostOrder(TreeNode node){
		if(node == null){
			return;
		}
		TraversePreOrder(node.left);
		TraversePreOrder(node.right);
		System.out.println(node.data);
	}
	
	
	public TreeNode findMaxBST(TreeNode node){
		if(node == null) return null;
		while(node.right != null){
			node = node.right;
		}
		return node;
	}
	/*
	 * You can decide if a tree is BST or not using Four approaches
	 * Approach 1 -> Comparing current with smallest in left and largest in right
	 * Approach 2 -> Checking if the current node is greater in left and smallest in right
	 * Approach 3 -> Inorder traversal in sorted order
	 * Approach 4 -> Each time comparing with min value and max value the node can have.
	 */
	
	public boolean isBSTMeth1(TreeNode node){
		if(node == null) return true;
		if(((Integer)node.data < (Integer)findMinInBST(node.left).data) ||
				((Integer)node.data > (Integer)findMaxBST(node.right).data)){
			return false;
		}else if(!isBSTMeth1(node.left) || !isBSTMeth1(node.right))
			return false;
		else return true;
	}
	/*
	 * This approach is the most efficient
	 */
	public boolean isBSTMeth2(TreeNode node){
		return isBSTUtils(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public boolean isBSTUtils(TreeNode node, int min_value, int max_value){
		if(node == null) return true;
		if((Integer)node.data < min_value && (Integer)node.data > max_value){
			return false;
		}
		return isBSTUtils(node.left, min_value, (Integer)node.data) &&
				isBSTUtils(node.right, (Integer)node.data, max_value); 
	}
	/*
	 * By traversing the BST in inOrder we can check to see if the link in sorted order and find it out
	 */
	
	public boolean isBSTMeth3(TreeNode node){
		return isBSTUtilsInOrder(node, Integer.MIN_VALUE); 
	}
	
	public boolean isBSTUtilsInOrder(TreeNode node, int min_value){
		if(node == null) return true;
		if(!isBSTUtilsInOrder(node.left, min_value))
			return false;
		if((Integer)node.data > min_value) return false;
		min_value = (Integer)node.data;
		return isBSTUtilsInOrder(node.right, min_value);
	}//end public boolean isBSTUtilsInOrder(TreeNode node, int min_value){
	
	public void levelOrderTraversal(TreeNode node){
		TreeNode temp = node;
		MyQueue queue = new MyQueue(100);
		queue.enqueue(temp);
		while(temp != null){
			temp = (TreeNode) queue.dequeue();
			if(temp.left != null){
				queue.enqueue(temp.left);
			}else if(temp.right != null){
				queue.enqueue(temp.right);
			}
			
		}
	}
	
	/*
	 * Give an Algorithm for finding maximum element in a Binary Tree
	 */
	
	
	
	
}
















