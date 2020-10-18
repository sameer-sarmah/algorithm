package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
An undirected graph is called Biconnected 
1) It is connected, i.e. it is possible to reach every vertex from every other vertex, by a simple path.
2) Even after removing any vertex the graph remains connected.
 * 
 * */
public class Biconnected {
	
	private static int MAX_VALUE = 1000;
	private static int[][] am = {  { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, 
					{ 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
					{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, 
					{ MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
					{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, 
					{ 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	
	
	public static void main(String[] args) {
		System.out.println(isBiconnected(am));
	}
	
	private static boolean isBiconnected(int[][] am) {
		for(int source = 0 ; source < am.length ;source++) {
			for(int target = 0 ; target < am.length ;target++) {
				if(am[source][target] < MAX_VALUE && am[source][target] > 0) {
					int [][] clonedAM = clone(am);
					clonedAM[source][target] = MAX_VALUE;
					int[][] connectivityMatrix = findConnectivityMatrix(clonedAM);
					if(!isStronglyConnected(connectivityMatrix)) {
						return false;
					}
				}
			}
		}
		return true;
		
	}
	
	private static int[][] findConnectivityMatrix(int[][] am ){
		int[][] connectivityMatrix = new int[am.length][am.length];
		List<Integer> visited=new ArrayList<>();
		for(int source = 0 ; source < am.length ;source++) {
			for(int target = 0 ; target < am.length ;target++) {
				visited.clear();
				if(isConnected(am,source,target,visited)) {
					connectivityMatrix[source][target] = 1;
				}
			}
		}
		return connectivityMatrix;
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
	
	private static boolean isConnected(int[][] am,int currentNode,int target,List<Integer> visited) {
		visited.add(currentNode);
		int[] connections = am[currentNode];
		for(int node = 0 ; node < am.length ;node++) {
			if(node == target) {
				return true;
			}
			if(connections[node] < MAX_VALUE && connections[node] > 0 && !visited.contains(node)) {
				if(isConnected(am,node,target,visited)) {
					System.out.println(String.format("%d node is reachable from %d", target,node));
					return true;
				}
			}
		}
		return false;
	}
	
	private static int[][] clone(int[][] am){
		int [][] clone = new int[am.length][am.length];
		for(int row = 0;row < am.length ;row++) {
			for(int col = 0;col < am.length ;col++) {
				clone[row][col] = am[row][col];
			}
		}
		return clone;
	}

}
