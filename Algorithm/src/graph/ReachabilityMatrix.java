package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReachabilityMatrix {
	private static int MAX_VALUE = 1000;
	static int[][] am = {  { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, 
					{ 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
					{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, 
					{ MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
					{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, 
					{ 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	
	public static void main(String[] args) {
		List<Integer> visited=new ArrayList<>();
		int[][] reachabilityMatrix = new int[am.length][am.length];
		for(int currentNode = 0 ;currentNode < am.length ; currentNode++) {
			
			for(int target = 0 ;target < am.length ; target++) {
				visited.clear();
				if(currentNode == target) {
					reachabilityMatrix[currentNode][currentNode] = 1;
				}
				else {
					if(isReachable(currentNode, target,visited)) {
						reachabilityMatrix[currentNode][target] = 1;
					}
				}
			}
		}
		for(int currentNode = 0 ;currentNode < am.length ; currentNode++) {
			System.out.println(Arrays.toString(reachabilityMatrix[currentNode]));
		}
	}

	private static boolean isReachable(int currentNode,int target,List<Integer> visited) {
		visited.add(currentNode);
		int[] connections = am[currentNode];
		if(connections[target] < MAX_VALUE && connections[target] > 0) {
			return true;
		}
		for(int node = 0;node < connections.length;node++) {
			if(connections[node] < MAX_VALUE && connections[node] > 0 && !visited.contains(node)) {
				if(isReachable(node,target,visited)) {
					System.out.println(String.format("%d node is reachable from %d", target,node));
					return true;
				}
			}
		}
		return false;
	}
}
