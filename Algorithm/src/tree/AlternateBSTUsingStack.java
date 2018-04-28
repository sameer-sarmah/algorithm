package tree;

import java.util.Stack;

public class AlternateBSTUsingStack {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createBinarySearchTree();
		Stack<TreeNode<Integer>> rightToLeft=new Stack<>();
		rightToLeft.push(root);
		System.out.printf(root.getValue()+" ");
		alternateBFS(new Stack<TreeNode<Integer>>(),rightToLeft,true);
	}
	
	
	public static void alternateBFS(Stack<TreeNode<Integer>> leftToRight,Stack<TreeNode<Integer>> rightToLeft,boolean isLeftToRight) {
		if(isLeftToRight) {
			while(!rightToLeft.isEmpty()) {
				TreeNode<Integer> node = rightToLeft.pop();
				if(node.getLeft() != null ) {
					leftToRight.push(node.getLeft());
					System.out.printf(node.getLeft().getValue()+" ");
				}
				
				if(node.getRight() != null ) {
					leftToRight.push(node.getRight());
					System.out.printf(node.getRight().getValue()+" ");
				}
				
			}
			if(!leftToRight.isEmpty())
			alternateBFS(leftToRight,rightToLeft,false);
		}
		else {
			while(!leftToRight.isEmpty()) {
				TreeNode<Integer> node = leftToRight.pop();
				if(node.getRight() != null ) {
					rightToLeft.push(node.getRight());
					System.out.printf(node.getRight().getValue()+" ");
				}
				
				if(node.getLeft() != null ) {
					rightToLeft.push(node.getLeft());
					System.out.printf(node.getLeft().getValue()+" ");
				}
				
			}
			if(!rightToLeft.isEmpty())
			alternateBFS(leftToRight,rightToLeft,true);
		}
	}
	
}
