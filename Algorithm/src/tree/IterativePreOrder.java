package algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativePreOrder {
	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
		Stack<TreeNode<Integer>> callStack = new Stack<TreeNode<Integer>>();
		callStack.push(root);
		iterativePreOrder(callStack);
	}

	// in order tree traversal without recursion
	private static <T extends Comparable<T>> void iterativePreOrder(Stack<TreeNode<T>> callStack) {

		List<TreeNode<T>> result = new ArrayList<>();
		while (!callStack.isEmpty()) {

			TreeNode<T> node = callStack.pop();
			result.add(node);

			if (node.getRight() != null) {
				callStack.add(node.getRight());
			}

			if (node.getLeft() != null) {
				callStack.add(node.getLeft());
			}

		}

		result.forEach((node) -> System.out.printf(node.getValue() + " "));

	}
}
