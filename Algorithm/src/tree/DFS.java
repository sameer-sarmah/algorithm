package tree;

import java.util.Stack;

public class DFS<T extends Comparable<T>> {
	private Stack<TreeNode<T>> stack = new Stack<>(); 

	public void dfs(TreeNode<T> node){
		System.out.println(node.getValue());
		
		if(node.getLeft()!=null) {
			stack.add(node.getLeft());
		}

		if(!stack.isEmpty())
			dfs(stack.pop());
		
		if(node.getRight()!=null) {
			stack.add(node.getRight());
		}

		if(!stack.isEmpty())
			dfs(stack.pop());
	}
}
