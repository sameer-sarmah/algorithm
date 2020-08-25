package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VerticalTraversal {
	private static Map<Integer,List<Integer>> columnToNumbers = new HashMap<>();
	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		traverse(tree,0,0);
		//System.out.println(columnToNumbers);
		List<Integer> columns =columnToNumbers.keySet().stream().sorted().collect(Collectors.toList());
		for(int column:columns) {
			System.out.println(columnToNumbers.get(column));
		}
	}
	

	
	private static void traverse(TreeNode<Integer> currentNode ,int row,int column) {
		if(currentNode !=null) {
			System.out.println(String.format("%d at row : %d ,column :%d ",currentNode.value,row,column));
			if(columnToNumbers.get(column) == null) {
				List<Integer> numbers = new ArrayList<Integer>();
				numbers.add(currentNode.value);
				columnToNumbers.put(column, numbers);
			}
			else {
				columnToNumbers.get(column).add(currentNode.value);
			}
			
			
			if(currentNode.left != null) {
				traverse(currentNode.left, row+1, column-1);
			}
			if(currentNode.right != null) {
				traverse(currentNode.right, row+1, column+1);
			}
		}
	}
	

}
