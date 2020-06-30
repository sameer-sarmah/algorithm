package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TreeLevel {
	
	private static Queue<TreeNode<Integer>> queue =new  LinkedList<TreeNode<Integer>>();
	
	public static void main(String[] args) {
		TreeNode<Integer> node = TreeCreator.createBinarySearchTree();
		queue.add(node);
		Map<Integer,List<Integer>> levelToNodes = new HashMap<>();
		bfsWithLevel(queue,0,levelToNodes);
		System.out.println(levelToNodes);
	}
	
	public static void bfsWithLevel(Queue<TreeNode<Integer>> queue,int level,Map<Integer,List<Integer>> levelToNodes){
		TreeNode<Integer> currentNode = queue.poll();
		if(levelToNodes.get(level) != null) {
			levelToNodes.get(level).add(currentNode.getValue());
		}
		else {
			List<Integer> nodes =new ArrayList<>();
			nodes.add(currentNode.getValue());
			levelToNodes.put(level, nodes);
		}
		
		 if(currentNode.getLeft() != null) {
			 queue.add(currentNode.getLeft());
			 bfsWithLevel(queue,level + 1,levelToNodes); 
		 }
		 if(currentNode.getRight() != null) {
			 queue.add(currentNode.getRight());
			 bfsWithLevel(queue,level + 1,levelToNodes);
		 }
	}
}
