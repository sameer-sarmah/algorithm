package tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CreateMirrorTree {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		TreeNode<Integer> mirror = createMirror(tree);
		Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<TreeNode<Integer>>();
		queue.add(mirror);
		bfs(queue);

	}
	
	private static TreeNode<Integer> createMirror(TreeNode<Integer> original) {
		TreeNode<Integer> subtreeRootMirror = null,leftSubtreeMirror = null,rightSubTreeMirror =null;
		
		if(original.left != null) {
			leftSubtreeMirror =createMirror(original.left);
		}
		
		if(original.right != null) {
			rightSubTreeMirror =createMirror(original.right);
		}
		
		if(original.left == null &&  original.right == null) {
			return new TreeNode<Integer>(original.value);
		}
		else {
			subtreeRootMirror = new TreeNode<Integer>(original.value);
			subtreeRootMirror.setLeft(rightSubTreeMirror);
			subtreeRootMirror.setRight(leftSubtreeMirror);
			
			leftSubtreeMirror.setParent(subtreeRootMirror);
			rightSubTreeMirror.setParent(subtreeRootMirror);
		}
		return subtreeRootMirror;
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
