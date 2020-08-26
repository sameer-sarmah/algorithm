package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTreeToBST {

	public static void main(String[] args) {
		TreeNode<Integer> tree =TreeCreator.createBinaryTree();
		List<TreeNode<Integer>> nodesToSwap = new ArrayList<>();
		do {
		nodesToSwap.clear();	
		findNodesToSwap(tree,nodesToSwap);
		nodesToSwap.stream().forEach(BinaryTreeToBST::convertToBST);
		}while(!nodesToSwap.isEmpty());
		//System.out.println(nodesToSwap);
		Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<TreeNode<Integer>>();
		queue.add(tree);
		bfs(queue);
	}
	
	private static void convertToBST(TreeNode<Integer> node) {
		if(node!= null) {
			if(node.left != null && node.value < node.left.value) {
				int temp = node.value;
				node.value = node.left.value;
				node.left.value= temp;
			}
			
			if(node.right != null && node.value > node.right.value) {
				int temp = node.value;
				node.value = node.right.value;
				node.right.value= temp;
			}
		}
	}
	
	
	
	private static void findNodesToSwap(TreeNode<Integer> node,List<TreeNode<Integer>> nodesToSwap) {
		if(node!= null) {
			if(node.left != null && node.value < node.left.value) {
				nodesToSwap.add(node);
			}
			
			if(node.right != null && node.value > node.right.value) {
				nodesToSwap.add(node);
			}
			if(node.left != null) {
				findNodesToSwap(node.left,nodesToSwap);
			}
			
			if(node.right != null) {
				findNodesToSwap(node.right,nodesToSwap);
			}
		}
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
