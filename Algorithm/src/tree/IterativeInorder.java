package tree;

import java.util.Stack;

public class IterativeInorder {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
		iterativeInOrder(root);
	}

	// in order tree traversal without recursion
	private static <T extends Comparable<T>> void iterativeInOrder(TreeNode<Integer> node) {
		
		
		Stack<TreeNode<Integer>> callStack = new Stack<TreeNode<Integer>>();
		callStack.push(node);
		while (!callStack.isEmpty()) {

			TreeNode<Integer> currentNode = callStack.pop();
			while(currentNode !=null ) {
				callStack.push(currentNode);
				currentNode = currentNode.getLeft();
			}
			while(!callStack.isEmpty() && currentNode == null) {
			currentNode = callStack.pop();
			System.out.printf(currentNode.getValue()+" ");
			currentNode = currentNode.getRight();
			}
			callStack.push(currentNode);
						

		}
		

	}
}
