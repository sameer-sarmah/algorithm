package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class DistanceBetweenTwoNodes {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		int node1=15;
		int node2 = 35;
		List<Integer>  nodePath1 = findPathToNode(tree, new Stack<Integer>(), node1);
		List<Integer>  nodePath2 = findPathToNode(tree, new Stack<Integer>(), node2);
		int commonAncestor = findCommonAncestors(nodePath1,nodePath2);
		long distance = distanceBetweenTwoNodes(nodePath1,nodePath2,commonAncestor);
		System.out.println(distance);

	}
	private static List<Integer>  findPathToNode(TreeNode<Integer> node,Stack<Integer> path,int nodeToFind) {
		path.add(node.value);
		if(node.value == nodeToFind) {
			return path.stream().collect(Collectors.toList());
		}
		if(node.left != null) {
			List<Integer> leftSubtreePath = findPathToNode(node.left,path,nodeToFind); 
			if(leftSubtreePath != null) {
				return leftSubtreePath;
			}
		}
		if(node.right != null) {
			List<Integer> rightSubtreePath = findPathToNode(node.right,path,nodeToFind);
			if(rightSubtreePath != null) {
				return rightSubtreePath;
			}
		}
		path.pop();
		return null;
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
	
	private static long distanceBetweenTwoNodes(List<Integer> path1,List<Integer> path2,int commonAncestor) {
		int indexOfCommonAncestor = path1.indexOf(commonAncestor);
		long distanceTillLeaf1 = path1.subList(indexOfCommonAncestor+1,path1.size() ).stream().count();
		long distanceTillLeaf2 = path2.subList(indexOfCommonAncestor+1,path2.size() ).stream().count();
		return distanceTillLeaf1 + distanceTillLeaf2 + 1;
	}
}
