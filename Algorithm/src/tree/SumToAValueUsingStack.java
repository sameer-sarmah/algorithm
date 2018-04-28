package tree;

import java.util.Stack;

public class SumToAValueUsingStack {
	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createBinarySearchTree2();
		Stack<Integer> stack = new Stack<>();
		sumToGivenValue(root, 12, 0, stack);
	}

	public static void sumToGivenValue(TreeNode<Integer> node, int sum, int accumulatedSum, Stack<Integer> stack) {
		stack.push(node.getValue());
		if (node.getValue() + accumulatedSum == sum) {
			System.out.println("Found,path to get the sum " + sum + " is " + stack);
		}
		
		if (node.getLeft() != null) {
			sumToGivenValue(node.getLeft(), sum, accumulatedSum + node.getValue(), stack);
		}
		
	
			if (accumulatedSum>0 && node.getLeft() != null) {
				Stack<Integer> emptyStack = new Stack<>();
				emptyStack.push(node.getValue());
				sumToGivenValue(node.getLeft(), sum,node.getValue(), emptyStack);
			}
		

		if (node.getRight() != null) {
			sumToGivenValue(node.getRight(), sum, accumulatedSum + node.getValue(), stack);
		}
		
		if (accumulatedSum>0 && node.getRight() != null) {
			Stack<Integer> emptyStack = new Stack<>();
			emptyStack.push(node.getValue());
			sumToGivenValue(node.getRight(), sum, node.getValue(), emptyStack);
		}
		
		stack.pop();

	}
}
