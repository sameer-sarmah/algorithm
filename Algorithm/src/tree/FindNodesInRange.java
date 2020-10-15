package tree;

import java.util.ArrayList;
import java.util.List;

public class FindNodesInRange {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
	    List<Integer> values = new ArrayList<>();
	    traverse(tree,15,30,values);
	    System.out.println(values);
	}

	
	  private static void traverse(TreeNode<Integer>  node,int low ,int high,List<Integer> values){
	      System.out.println("current node is : "+node.getValue());
	     
	      if(node.left != null && low < node.getValue() ){
	       traverse(node.left,low,high,values);
	      }
	       if(low<= node.getValue() && node.getValue() <= high ){
	        values.add(node.getValue());
	      }
	    
	      if(node.right != null && high > node.getValue()){
	       traverse(node.right,low,high,values);
	      }
	  }
}
