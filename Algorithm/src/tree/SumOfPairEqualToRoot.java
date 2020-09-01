package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SumOfPairEqualToRoot {

	private static Set<Integer> numbers = new HashSet<>();
	
	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		List<List<Integer>> pairs = new ArrayList<>();
		findPairs(tree, pairs, tree.value);
	}
	
	private static void findPairs(TreeNode<Integer> node,List<List<Integer>> pairs,int rootValue){
		if(numbers.contains(rootValue - node.value)) {
			List<Integer> pair = new ArrayList<>();
			pair.add(node.value);
			pair.add(rootValue - node.value);
			pairs.add(pair);
			System.out.println("Found pair :" +pair);
		}
		else {
			numbers.add(node.value);
		}
		if(node.left != null) {
			findPairs(node.left, pairs, rootValue);
		}
		if(node.right != null) {
			findPairs(node.right, pairs, rootValue);
		}
	}

}
