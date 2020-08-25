package tree;

import java.util.Stack;

public class DFS {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		Stack<TreeNode<Integer>> stack =new Stack<>();
		stack.add(tree);
		postorder(tree);
		//inorderWithStack(stack);
	}
	
	private static void inorderWithStack(Stack<TreeNode<Integer>> stack) {
		if(!stack.isEmpty()) {
			TreeNode<Integer> node = stack.pop();
			if(node.left != null) {
				stack.add(node.left);
				inorderWithStack(stack);
			}
			
			System.out.println(node.value);
			
			if(node.right != null) {
				stack.add(node.right);
				inorderWithStack(stack);
			}
			
		}
	}
	
	private static void postorderWithStack(Stack<TreeNode<Integer>> stack) {
		if(!stack.isEmpty()) {
			TreeNode<Integer> node = stack.pop();
			if(node.left != null) {
				stack.add(node.left);
				postorderWithStack(stack);
			}
			if(node.right != null) {
				stack.add(node.right);
				postorderWithStack(stack);
			}
			System.out.println(node.value);
		}
	}
	
	private static void preorderWithStack(Stack<TreeNode<Integer>> stack) {
		if(!stack.isEmpty()) {
			TreeNode<Integer> node = stack.pop();
			System.out.println(node.value);
			if(node.left != null) {
				stack.add(node.left);
				preorderWithStack(stack);
			}
			if(node.right != null) {
				stack.add(node.right);
				preorderWithStack(stack);
			}
		}
	}
	
	private static void preorder(TreeNode<Integer> node) {
		System.out.println(node.value);
		if(node.left != null) {
			preorder(node.left);
		}
		if(node.right != null) {
			preorder(node.right);
		}
		
	}
	
	private static void postorder(TreeNode<Integer> node) {
		
		if(node.left != null) {
			postorder(node.left);
		}
		if(node.right != null) {
			postorder(node.right);
		}
		System.out.println(node.value);
		
	}
	
	private static void inorder(TreeNode<Integer> node) {
		
		if(node.left != null) {
			inorder(node.left);
		}
		
		System.out.println(node.value);
		
		if(node.right != null) {
			inorder(node.right);
		}
		
		
	}


}
