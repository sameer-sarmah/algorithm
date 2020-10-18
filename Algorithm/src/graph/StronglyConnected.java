package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A directed graph is strongly connected if there is a path between any two pair of vertices
public class StronglyConnected {

	private static int MAX_VALUE = 1000;
	// S A B C T
	private static int[][] am = { 
			{ 0, 7, 6, MAX_VALUE, MAX_VALUE }, 
			{ MAX_VALUE, 0, MAX_VALUE, -3, 9 }, 
			{ MAX_VALUE, 8, 0, 5, -4 },
			{ MAX_VALUE, MAX_VALUE, -2, 0, MAX_VALUE }, 
			{ 2, MAX_VALUE, MAX_VALUE, 7, 0 } };
	private static int[][] connectivityMatrix = new int[am.length][am.length];
	
	
	public static void main(String[] args) {
		List<Integer> visited=new ArrayList<>();
		for(int source = 0 ; source < am.length ;source++) {
			visited.clear();
			for(int target = 0 ; target < am.length ;target++) {
				if(isConnected(source,target,visited)) {
					connectivityMatrix[source][target] = 1;
				}
			}
		}
		
		for(int currentNode = 0 ;currentNode < am.length ; currentNode++) {
			System.out.println(Arrays.toString(connectivityMatrix[currentNode]));
		}
		
		System.out.println(isStronglyConnected(connectivityMatrix));

	}
	
	private static boolean isStronglyConnected(int[][] connectivityMatrix) {
		for(int source = 0 ; source < am.length ;source++) {
			for(int target = 0 ; target < am.length ;target++) {
				if(connectivityMatrix[source][target] != 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean isConnected(int currentNode,int target,List<Integer> visited) {
		int[] connections = am[currentNode];
		for(int node = 0 ; node < am.length ;node++) {
			if(node == target) {
				return true;
			}
			if(connections[node] < MAX_VALUE && connections[node] > 0 && !visited.contains(node)) {
				if(isConnected(node,target,visited)) {
					System.out.println(String.format("%d node is reachable from %d", target,node));
					return true;
				}
			}
		}
		return false;
	}
	
	
	

}
