package algorithm.tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BottomToTopBFSUsingStackQueue {
	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
		bottomToTopBFS(root);
	}
	
	
	public static void bottomToTopBFS(TreeNode<Integer> root) {
	  Queue<TreeNode<Integer>> queue=new ConcurrentLinkedQueue<>();
	  Stack<TreeNode<Integer>> stack=new Stack<>();
	  
	  queue.add(root);
	  TreeNode<Integer> currentNode=null;
	  while(!queue.isEmpty()) {
		  currentNode = queue.poll();
		  stack.push(currentNode);
		  if(currentNode.getLeft()!=null) {
			  queue.add(currentNode.getLeft());
		  }
		  
		  if(currentNode.getRight()!=null) {
			  queue.add(currentNode.getRight());
		  }
	  }
	  
      while(!stack.isEmpty()) {
    	  System.out.printf( stack.pop().getValue()+" ");
      }
	}
}
