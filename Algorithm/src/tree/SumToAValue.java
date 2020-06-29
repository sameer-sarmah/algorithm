package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class SumToAValue {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createBinarySearchTree2();
		Stack<Integer> path = new Stack<Integer>();
		//List<Integer> pathFound = findSinglePathOfRequiredSum(root,12,path);
		//System.out.println("found "+ pathFound);
		List<List<Integer>> pathsFound = findMultiplePathOfRequiredSum(root,12,path);
		System.out.println(pathsFound);
	}
	
	private static List<Integer> findSinglePathOfRequiredSum(TreeNode<Integer> node, int requiredSum,Stack<Integer> path) {
		List<Integer> pathTillNow = path.stream().collect(Collectors.toList());
		pathTillNow.add(node.getValue());
		//System.out.println(pathTillNow);
		List<Integer> pathFound = doesMatchRequiredSum(requiredSum, pathTillNow);
		if (!pathFound.isEmpty()) {
			return pathFound;
		}
		path.add(node.getValue());
		if (node.getLeft() != null) {
			pathFound = findSinglePathOfRequiredSum(node.getLeft(), requiredSum, path);
			if (!pathFound.isEmpty()) {
				return pathFound;
			}
		}
		if (node.getRight() != null) {
			pathFound = findSinglePathOfRequiredSum(node.getRight(), requiredSum, path);
			if (!pathFound.isEmpty()) {
				return pathFound;
			}
		}
		path.pop();
		return new ArrayList<>();
	}
	
	private static List<Integer> doesMatchRequiredSum(int requiredSum,List<Integer> path) {
		List<Integer> pathFound = new ArrayList<>();
		for(int outerIndex = 0;outerIndex < path.size() ; outerIndex++) {
			int sum = 0;
			pathFound.clear();
			for(int innerIndex = outerIndex;innerIndex < path.size() ; innerIndex++) {
				sum+=path.get(innerIndex);
				pathFound.add(path.get(innerIndex));
			}
			if(sum == requiredSum) {
				return pathFound;
			}
		}
		pathFound.clear();
		return pathFound;
	}
	
	private static List<List<Integer>> findMultiplePathOfRequiredSum(TreeNode<Integer> node,int requiredSum,Stack<Integer> path) {
		List<Integer> pathTillNow = path.stream().collect(Collectors.toList());
		pathTillNow.add(node.getValue());
		//System.out.println(pathTillNow);
		List<List<Integer>> pathsFound = new ArrayList<>();
		List<Integer> pathFound = doesMatchRequiredSum(requiredSum, pathTillNow);
		if (!pathFound.isEmpty()) {
			pathsFound.add( pathFound);
		}
		path.add(node.getValue());
		if (node.getLeft() != null) {
			List<List<Integer>> pathsFoundFromLeftSubtree = findMultiplePathOfRequiredSum(node.getLeft(), requiredSum, path);
			if (!pathsFoundFromLeftSubtree.isEmpty()) {
				pathsFound.addAll(pathsFoundFromLeftSubtree);
			}
		}
		if (node.getRight() != null) {
			List<List<Integer>> pathsFoundFromRightSubtree = findMultiplePathOfRequiredSum(node.getRight(), requiredSum, path);
			if (!pathsFoundFromRightSubtree.isEmpty()) {
				pathsFound.addAll(pathsFoundFromRightSubtree);
			}
		}
		path.pop();
		return pathsFound;
	}
}
