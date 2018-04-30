package graph;

import java.util.ArrayList;
import java.util.List;

public class AreNodesConnected {
   private static int MAX= Integer.MAX_VALUE;

	
	public static void main(String[] args) {
		int [][] am= {
				{0, MAX, -3 ,9 ,MAX},
				{8 ,0 ,5 ,-4 ,MAX},
				{MAX ,-2 ,0 ,MAX,MAX},
				{MAX ,MAX , 7 ,0 ,2},
				{7 ,6, MAX ,MAX ,0}
		};
		System.out.println(isConnected(am,0,4,new ArrayList<>()));
	}
	//check if two nodes are connected in a DAG
	public static boolean isConnected(int[][] am , int from ,int to,List<Integer> visited) {
		int[] nodeConnections=am[from];
		visited.add(from);
		for(int node=0;node<nodeConnections.length;node++) {
			if(nodeConnections[node]!=0 && nodeConnections[node]<MAX && !visited.contains(node)) {
				int connectedNode=node;
				if(connectedNode == to) {
					return true;
				}
				else {
					return isConnected(am,node,to,visited);
				}
				
			}
		}
		return false;
	}
	
}
