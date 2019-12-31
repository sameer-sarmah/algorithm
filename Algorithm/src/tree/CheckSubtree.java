package tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CheckSubtree {
	
	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		TreeNode<Integer> subTree = TreeCreator.createSubTree();
		Queue<TreeNode<Integer>> queue =new LinkedBlockingQueue<>();
		TreeNode<Integer> node = checkIfNodeExists(queue,tree,subTree);
		boolean isIdentical = isIdentical(subTree, node);
		System.out.println(isIdentical);
	}
	
	public static TreeNode<Integer> checkIfNodeExists(Queue<TreeNode<Integer>> queue,TreeNode<Integer> currentNode,TreeNode<Integer> searchedNode){
		System.out.println(currentNode);
		if(currentNode.getValue().compareTo(searchedNode.getValue())==0) {
			return currentNode;
		}
		
		if(currentNode.getLeft()!=null) {
		queue.add(currentNode.getLeft());
		}
		
		if(currentNode.getRight()!=null) {
		queue.add(currentNode.getRight());
		}
		
		if(!queue.isEmpty())
			return checkIfNodeExists(queue,queue.poll(),searchedNode);
		else
			return null;
	}
	
	public static boolean isIdentical(TreeNode<Integer> subTree1,TreeNode<Integer> subTree2) {
		
		if(subTree1.getValue().compareTo(subTree2.getValue())==0) {
			boolean isLeftIdentical=false,isRightIdentical=false;
			if(subTree1.getLeft()!=null && subTree2.getLeft()!=null) {
				isLeftIdentical=isIdentical(subTree1.getLeft(),subTree2.getLeft());
			}
			else if(subTree1.getLeft()==null && subTree2.getLeft()==null) {
				isLeftIdentical=true;
			}
			else {
				isLeftIdentical=false;
			}
			
            if(subTree1.getRight()!=null && subTree2.getRight()!=null) {
            	isRightIdentical=isIdentical(subTree1.getRight(),subTree2.getRight());
			}
            else if(subTree1.getRight()==null && subTree2.getRight()==null) {
            	isRightIdentical=true;
			}
			else {
				isRightIdentical=false;
			}
			return 	isLeftIdentical && isRightIdentical;
				
		}
		else {
			return false;
		}
		
	}
}
/*
boolean checkSubTree(TreeNode<T> node,TreeNode<T> subtreeNode)

if (node.getValue().compareTo(subtreeNode.getValue()) == 0){
	if(subtreeNode.getLeft() == null && subtreeNode.getRight() == null){
		return true;
	}
	else if(subtreeNode.getLeft() != null && subtreeNode.getRight() == null && node.getLeft() != null){
		return checkSubTree(node.getLeft(),subtreeNode.getLeft());
	}
	else if(subtreeNode.getLeft() == null && subtreeNode.getRight() != null && && node.getRight() != null){
		return checkSubTree(node.getRight(),subtreeNode.getRight());
	}
	else if(subtreeNode.getLeft() != null && subtreeNode.getRight() != null && node.getLeft() != null && && node.getRight() != null){
		return checkSubTree(node.getLeft(),subtreeNode.getLeft()) || checkSubTree(node.getRight(),subtreeNode.getRight());
	}
	else{
		return false;
	}
} 

	else if(node.getLeft() != null && node.getRight() == null){
		return checkSubTree(node.getLeft(),subtreeNode);
	}
	else if(node.getLeft() == null && node.getRight() != null){
		return checkSubTree(node.getRight(),subtreeNode);
	}
	else if(node.getLeft() != null && node.getRight() != null){
		return checkSubTree(node.getLeft(),subtreeNode) || checkSubTree(node.getRight(),subtreeNode);
	}
*/