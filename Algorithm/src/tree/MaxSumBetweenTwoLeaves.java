package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MaxSumBetweenTwoLeaves {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		List<List<Integer>> paths = new ArrayList<>();
		Stack<Integer> path = new Stack<>();
		findPathToLeaves(tree, paths, path);
		int maxSum = 0;
		int leafOne=0,leafTwo=0;
		for(int firstLeaf =0;firstLeaf< paths.size();firstLeaf++) {
			for(int secondLeaf =0;secondLeaf< paths.size();secondLeaf++) {
				if(firstLeaf != secondLeaf) {
					int commonAncestor = findCommonAncestors(paths.get(firstLeaf),paths.get(secondLeaf));
					int sumBetweenThem = sumBetweenTwoLeaves(paths.get(firstLeaf),paths.get(secondLeaf),commonAncestor);
					if(sumBetweenThem > maxSum) {
						maxSum = sumBetweenThem;
						leafOne = paths.get(firstLeaf).get(paths.get(firstLeaf).size()-1);
						leafTwo = paths.get(secondLeaf).get(paths.get(secondLeaf).size()-1);
					}
				}

			}
		}
		System.out.println(String.format("the max sum is :%d ,between leaves :%d and %d", maxSum,leafOne,leafTwo));
	}
	
	private static void findPathToLeaves(TreeNode<Integer> node,List<List<Integer>> paths,Stack<Integer> path) {
		path.add(node.value);
		if(node.left == null && node.right == null) {
			paths.add(path.stream().collect(Collectors.toList()));
		}
		if(node.left != null) {
			findPathToLeaves(node.left,paths,path);
		}
		if(node.right != null) {
			findPathToLeaves(node.right,paths,path);
		}
		path.pop();
	}
	
	private static int findCommonAncestors(List<Integer> path1,List<Integer> path2) {
		int index = 0;
		int max = Math.min(path1.size(),path2.size());
		while(index < max) {
			if(!path1.get(index).equals(path2.get(index))) {
				return path1.get(index-1);
			}
			++index;
		}
		return -1;
	}
	
	private static int sumBetweenTwoLeaves(List<Integer> path1,List<Integer> path2,int commonAncestor) {
		int indexOfCommonAncestor = path1.indexOf(commonAncestor);
		int sumTillLeaf1 = path1.subList(indexOfCommonAncestor+1,path1.size() ).stream().reduce((x,y)-> x+y).get();
		int sumTillLeaf2 = path2.subList(indexOfCommonAncestor+1,path2.size() ).stream().reduce((x,y)-> x+y).get();
		return sumTillLeaf1 + sumTillLeaf2 + commonAncestor;
	}

}
