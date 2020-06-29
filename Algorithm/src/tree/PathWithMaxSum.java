package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PathWithMaxSum {
	private static void traverse(TreeNode<Integer> node,Stack<Integer> path,List<List<Integer>> paths){
		path.push(node.getValue());
		if(node.getLeft() != null) {
			traverse(node.getLeft(),path,paths);
		}
		if(node.getRight() != null) {
			traverse(node.getRight(),path,paths);
		}
		
		if(node.getLeft() == null && node.getRight() == null) {
			System.out.println("sum to leaf node is: " +path.stream().reduce((x,y)->x+y).orElseGet(()->0));
			//path.stream().collect(Collectors.toList());
			paths.add(path.stream().collect(Collectors.toList()));
		}
		path.pop();
	}
	
	public static void main(String[] args) {
		TreeNode node = TreeCreator.createBinarySearchTree();
		List<List<Integer>> paths = new ArrayList<List<Integer>>();
		traverse(node,new Stack<Integer>(),paths);
		List<Integer> pathWithMaxSum = null;;
		int maxSum = 0;
		for(List<Integer> path : paths) {
			int sum = path.stream().reduce((x,y)->x+y).orElseGet(()->0);
			if(sum >  maxSum) {
				maxSum = sum;
				pathWithMaxSum = path;
			}
		}
		System.out.println(pathWithMaxSum);
	}
}
