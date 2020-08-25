package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalTraversal {
	private static Map<Integer,List<Integer>> diagonalToNumbers = new HashMap<>();
 public static void main(String[] args) {
	 TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
	 traverse(tree,0);
	 System.out.println(diagonalToNumbers);
}
 
	private static void traverse(TreeNode<Integer> currentNode ,int diagonal) {
		if(currentNode !=null) {
			System.out.println(String.format("%d at diagonal : %d",currentNode.value,diagonal));
			if(diagonalToNumbers.get(diagonal) == null) {
				List<Integer> numbers = new ArrayList<Integer>();
				numbers.add(currentNode.value);
				diagonalToNumbers.put(diagonal, numbers);
			}
			else {
				diagonalToNumbers.get(diagonal).add(currentNode.value);
			}
			if(currentNode.left != null) {
				traverse(currentNode.left, diagonal+1);
			}
			if(currentNode.right != null) {
				traverse(currentNode.right, diagonal);
			}
		}
	}
}
