package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BfsWithoutQueue {
	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		Map<Integer,List<Integer>> levelToNodes = new HashMap<>();
		Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<>();
		queue.add(tree);
		traverse(tree, 0, levelToNodes);
		int bottomLevel = levelToNodes.keySet().stream().max(Integer::compare).get();
		int currentLevel = 0;
		while(currentLevel <= bottomLevel) {
			System.out.println(levelToNodes.get(currentLevel));
			++currentLevel;
		}
	}
	
	private static void traverse(TreeNode<Integer> node, int level,Map<Integer,List<Integer>> levelToNodes){
		if(node != null) {
			
			System.out.println(String.format("current node : %d ,at level :%d", node.value,level));
			if(levelToNodes.get(level) == null) {
				List<Integer> nodeIds = new ArrayList<Integer>();
				nodeIds.add(node.value);
				levelToNodes.put(level, nodeIds);
			}
			else {
				levelToNodes.get(level).add(node.value);
			}
			
			if(node.left != null) {
				traverse(node.left,level+1, levelToNodes);
			}
			
			if(node.right != null) {
				traverse(node.right,level+1, levelToNodes);
			}
		}
	}
}
