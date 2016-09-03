package bInaryTrees;

public class TreeNode {
	
	Object data;
	TreeNode left = null;
	TreeNode right = null;
	
	public TreeNode(Object data){
		this.data = data;
		left = null;
		right = null;
	}
	
	public void setLeft(TreeNode left){
		this.left = left;
	}
	
	public TreeNode getLeft(){
		return left;
	}
	
	public void setRight(TreeNode right){
		this.right = right;
	}
	
	public TreeNode getRight(){
		return right;
	}
}
