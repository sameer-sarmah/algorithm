package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class AlternateBFS {
	private static Queue<TreeNode<Integer>> queue =new  LinkedList<TreeNode<Integer>>();
	
	public static void main(String[] args) {
		TreeNode<Integer> node = TreeCreator.createBinarySearchTree();
		queue.add(node);
		Map<Integer,List<Integer>> levelToNodes = new HashMap<>();
		bfsWithLevel(queue,0,levelToNodes);
		System.out.println(levelToNodes);
		for(int level:levelToNodes.keySet()) {
			if(level % 2 == 1) {
				levelToNodes.get(level).stream().forEach(number ->System.out.println(number));
			}
			else {
				List<Integer> reversedNodes = levelToNodes.get(level).stream().collect(Collectors.toList());
				Collections.reverse(reversedNodes);
				reversedNodes.stream().forEach(number ->System.out.println(number));
			}
		}
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
