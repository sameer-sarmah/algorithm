package tree;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class BinaryTreeFromPreorderInorder {
	/*
	 * Non duplicate nodes
	 * */
	private static List<Integer> preorder = Arrays.asList(20 ,10, 5, 15, 30, 25, 35);
	private static List<Integer> inorder  = Arrays.asList( 5 ,10, 15, 20 ,25, 30, 35);

	public static void main(String[] args) {
		TreeNode<Integer> root = createTree(preorder,inorder);
		Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<TreeNode<Integer>>();
		queue.add(root);
		bfs(queue);
	}
	
	
	private static TreeNode<Integer> createTree(List<Integer> preorder,List<Integer> inorder){
		if(!preorder.isEmpty() && !inorder.isEmpty()) {
		int root = preorder.get(0);
		TreeNode<Integer> currentNode = new TreeNode<Integer>(root,null,null,null);
		List<Integer> leftInorderSubTree = findLeftNodes(inorder,root);
		List<Integer> rightInorderSubTree = findRightNodes(inorder,root);
	
		List<Integer> leftPreorderSubTree = findPreorderSubList(preorder,leftInorderSubTree);
		List<Integer> rightPreorderSubTree = findPreorderSubList(preorder,rightInorderSubTree);
		
		if(leftInorderSubTree.isEmpty() && rightInorderSubTree.isEmpty()) {
			return currentNode;
		}
		else {
			TreeNode<Integer> leftSubTreeNode = createTree(leftPreorderSubTree,leftInorderSubTree);
			TreeNode<Integer> rightSubTreeNode = createTree(rightPreorderSubTree,rightInorderSubTree);
			currentNode.setLeft(leftSubTreeNode);
			currentNode.setRight(rightSubTreeNode);
			leftSubTreeNode.setParent(currentNode);
			rightSubTreeNode.setParent(currentNode);
			System.out.println(currentNode);
			return currentNode;
		}
		}
		return null;
		
	}
	
	private static List<Integer> findPreorderSubList(List<Integer> preorder,List<Integer> inorder){
		List<Integer> preorderSubList = preorder.stream().filter(num -> inorder.contains(num)).collect(Collectors.toList());
		return preorderSubList;
	}
	
	private static List<Integer> findLeftNodes( List<Integer> inorder ,int root){
		int indexOfRoot = inorder.indexOf(root);
		return inorder.subList(0, indexOfRoot);
	}

	
	private static List<Integer> findRightNodes( List<Integer> inorder ,int root){
		int indexOfRoot = inorder.indexOf(root);
		return inorder.subList(indexOfRoot+1, inorder.size());
	}
	
	private static void bfs(Queue<TreeNode<Integer>> queue) {
		if(!queue.isEmpty()) {
			TreeNode<Integer> treeNode = queue.poll();
			System.out.println(treeNode.value);
			if(treeNode.left != null) {
				queue.add(treeNode.left);
			}
			if(treeNode.right != null) {
				queue.add(treeNode.right);
			}
			bfs(queue);
		}
		
	}
}
