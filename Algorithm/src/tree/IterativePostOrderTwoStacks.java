package algorithm.tree;

import java.util.Stack;

public class IterativePostOrderTwoStacks {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
		Stack<TreeNode<Integer>> callStack = new Stack<TreeNode<Integer>>();
		callStack.push(root);
		iterativePostOrder(callStack);
	}
	
	// post order tree traversal without recursion
	  private static <T extends Comparable<T>> void iterativePostOrder(Stack<TreeNode<T>> callStack) {
			 
		  Stack<TreeNode<T>> resultStack=new Stack<>();
          while(!callStack.isEmpty()) {
 
        	  TreeNode<T> node=callStack.pop(); 
        	  resultStack.add(node);
        	  if(node.getLeft()!=null) {
        		  callStack.add(node.getLeft());
    		  }
        	  
        	  if(node.getRight()!=null) {
        		  callStack.add(node.getRight());
    		  }
          }
          
          while(!resultStack.isEmpty()) {
        	  System.out.printf( resultStack.pop().getValue()+" ");
          }

		 	
	  }
	
}
