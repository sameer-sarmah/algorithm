package tree;

public class TreeCreator {

	private static <T extends Comparable<T>>  TreeNode<T> createNode(T value,TreeNode<T> parent){
		TreeNode<T> node=new TreeNode<>(value);
		node.setParent(parent);
		return node;
	}
	
	private static <T extends Comparable<T>>  TreeNode<T> assignLeftRight(TreeNode<T> parent,TreeNode<T> left,TreeNode<T> right){
		parent.setLeft(left);
		parent.setRight(right);
		if(left != null ) {
			left.setParent(parent);
		}
		if(right != null ) {
			right.setParent(parent);
		}
		return parent;
	}
	
	public static TreeNode<Integer> createBinarySearchTree(){
		TreeNode<Integer> root = createNode(20, null);
		TreeNode<Integer> parent = assignLeftRight(root,createNode(10,root),createNode(30,root));
		TreeNode<Integer> left = parent.getLeft();
		TreeNode<Integer> right = parent.getRight();
		assignLeftRight(left,createNode(5,left),createNode(15,left));
		assignLeftRight(right,createNode(25,right),createNode(35,right));
		return root;
	} 
	
	public static TreeNode<Integer> createBinaryTree(){
		TreeNode<Integer> root = createNode(20, null);
		TreeNode<Integer> parent = assignLeftRight(root,createNode(18,root),createNode(30,root));
		TreeNode<Integer> left = parent.getLeft();
		TreeNode<Integer> right = parent.getRight();
		assignLeftRight(left,createNode(15,left),createNode(5,left));
		assignLeftRight(right,createNode(35,right),createNode(25,right));
		return root;
	} 
	
	public static TreeNode<Integer> createSubTree(){
		TreeNode<Integer> root = createNode(30, null);
		assignLeftRight(root,createNode(25,root),createNode(35,root));
		return root;
	}
	
	public static TreeNode<Integer> createBinarySearchTree2(){
		TreeNode<Integer> left = assignLeftRight(createNode(5, null),assignLeftRight(createNode(3, null),createNode(1,null),createNode(4,null)),createNode(6,null));
		TreeNode<Integer> right = assignLeftRight(createNode(8, null),null,createNode(9,null));
		TreeNode<Integer> root = assignLeftRight(createNode(7, null),left,right);
		return root;
	}
	
}
