package tree;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/*
 * Given that the Binary Tree is Full,that is each node has either 2 children or none
 * Non duplicate nodes
 * */
public class BinaryTreeFromPreorderPostorder {

	private static List<Integer> preorder = Arrays.asList(20, 10, 5, 15, 30, 25, 35);
	private static List<Integer> postorder = Arrays.asList(5, 15, 10, 25, 35, 30, 20);

	public static void main(String[] args) {
		TreeNode<Integer> root = createTree(preorder,postorder);
		Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<TreeNode<Integer>>();
		queue.add(root);
		bfs(queue);
	}


	private static TreeNode<Integer> createTree(List<Integer> preorder, List<Integer> postorder) {
		if (!preorder.isEmpty() && !postorder.isEmpty()) {
			int root = preorder.get(0);
			TreeNode<Integer> rootNodeOfSubTree = new TreeNode<Integer>(root);
			if (preorder.size() > 1) {
				int leftSubTreeRoot = preorder.get(1);
				List<Integer> leftPostOrderSubTree = findLeftSubTree(leftSubTreeRoot, postorder);
				List<Integer> rightPostOrderSubTree = findRightSubTree(leftSubTreeRoot, postorder);

				List<Integer> leftPreOrderSubTree = findPreorderSubTree(leftPostOrderSubTree);
				List<Integer> rightPreOrderSubTree = findPreorderSubTree(rightPostOrderSubTree);

				if (leftPostOrderSubTree.isEmpty() && rightPostOrderSubTree.isEmpty()) {
					return rootNodeOfSubTree;
				} else {
					TreeNode<Integer> leftSubTree = createTree(leftPreOrderSubTree, leftPostOrderSubTree);
					TreeNode<Integer> rightSubTree = createTree(rightPreOrderSubTree, rightPostOrderSubTree);
					rootNodeOfSubTree.setLeft(leftSubTree);
					rootNodeOfSubTree.setRight(rightSubTree);
					leftSubTree.setParent(rootNodeOfSubTree);
					rightSubTree.setParent(rootNodeOfSubTree);
				}
			}
			return rootNodeOfSubTree;
		}
		return null;

	}

	private static List<Integer> findPreorderSubTree(List<Integer> postOrderSubTree) {
		return preorder.stream().filter(num -> postOrderSubTree.contains(num)).collect(Collectors.toList());
	}

	private static List<Integer> findLeftSubTree(int leftSubTreeRoot, List<Integer> postorder) {
		int indexOfSubTreeRoot = postorder.indexOf(leftSubTreeRoot);
		return postorder.subList(0, indexOfSubTreeRoot + 1).stream().collect(Collectors.toList());
	}

	private static List<Integer> findRightSubTree(int leftSubTreeRoot, List<Integer> postorder) {
		int indexOfSubTreeRoot = postorder.indexOf(leftSubTreeRoot);
		List<Integer> rightSubTree = postorder.subList(indexOfSubTreeRoot + 1, postorder.size()).stream()
				.collect(Collectors.toList());
		rightSubTree.remove(rightSubTree.size() - 1);
		return rightSubTree;
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
