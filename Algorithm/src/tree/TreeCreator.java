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
		return parent;
	}
	
	public static TreeNode<Integer> createTree(){
		TreeNode<Integer> root = createNode(20, null);
		TreeNode<Integer> parent = assignLeftRight(root,createNode(10,root),createNode(30,root));
		TreeNode<Integer> left = parent.getLeft();
		TreeNode<Integer> right = parent.getRight();
		assignLeftRight(left,createNode(5,left),createNode(15,left));
		assignLeftRight(right,createNode(25,right),createNode(35,right));
		return root;
	} 
	
}
