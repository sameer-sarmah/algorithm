package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BoundaryTraveral {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		List<Integer> leftNodesPath =new ArrayList<Integer>();
		List<Integer> leavesPath =new ArrayList<Integer>();
		List<Integer> rightNodesPath =new ArrayList<Integer>();
		Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<TreeNode<Integer>>();
		queue.add(tree);
		findLeftNonLeafNodes(tree,leftNodesPath);
		findLeafNodes(queue,leavesPath);
		findRightNonLeafNodes(tree,rightNodesPath);
		Collections.reverse(rightNodesPath);
		rightNodesPath.remove(rightNodesPath.size()-1);
		System.out.println(leftNodesPath);
		System.out.println(leavesPath);
		System.out.println(rightNodesPath);
		
		leftNodesPath.addAll(leavesPath);
		leftNodesPath.addAll(rightNodesPath);
		System.out.println(leftNodesPath);
	}
	
	private static void findLeftNonLeafNodes(TreeNode<Integer> node,List<Integer> path) {
		if(node != null) {
			if(node.left != null || node.right != null) {
			path.add(node.value);
			System.out.println(String.format("added %d in findLeftNonLeafNodes", node.value));
			findLeftNonLeafNodes(node.left,path);
			}
		}
	}
	
	private static void findRightNonLeafNodes(TreeNode<Integer> node,List<Integer> path) {
		if(node != null) {
			if(node.left != null || node.right != null) {
			path.add(node.value);
			System.out.println(String.format("added %d in findRightNonLeafNodes", node.value));
			findRightNonLeafNodes(node.right,path);
			}
		}
	}
	
	private static void findLeafNodes(Queue<TreeNode<Integer>> queue,List<Integer> path) {
		if(!queue.isEmpty()) {
			TreeNode<Integer> currentNode = queue.poll();
			if(currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if(currentNode.right != null) {
				queue.add(currentNode.right);
			}
			if(currentNode.left == null && currentNode.right == null) {
				System.out.println(String.format("added %d in findLeafNodes", currentNode.value));
				path.add(currentNode.value);
			}
			findLeafNodes(queue,path);
		}
	}

	
}
