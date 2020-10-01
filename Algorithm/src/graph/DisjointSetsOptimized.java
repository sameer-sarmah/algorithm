package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class DisjointSetsOptimized {

      private static int[][] am = {{0, 1, 0, 0, 0, 0, 0},
    		  {1, 0, 1, 0, 0, 0, 0},
    		  {0, 1, 0, 1, 0, 0, 0},
    		  {0, 0, 1, 0, 1, 0, 0},
    		  {0, 0, 0, 1, 0, 0, 0},
    		  {0, 0, 0, 0, 0, 0, 1},
    		  {0, 0, 0, 0, 0, 1, 0}};
	  public static void main(String[] args) {
		  Stack<Integer> stack = new Stack<Integer>();
		  Set<Integer> visited = new HashSet<Integer>();
		  Set<Set<Integer>> clusters = new HashSet<>();
		  stack.add(0);
		  int nodesVisited = 0;
		  while(nodesVisited < am.length) {
			  dfs(am, stack, visited);
			  clusters.add(visited);
			  nodesVisited += visited.size();
			  System.out.println(visited);
			  int nodeNotVisited = -1;
			  for(int node = 0;node < am.length;node++) {
				  if(!visited.contains(node)) {
					  nodeNotVisited = node;
				  }
			  }
			  visited = new HashSet<Integer>();
			  stack = new Stack<Integer>();
			  stack.push(nodeNotVisited);
		  }
		 
		  System.out.println(clusters);
	}
	  

	 private static void dfs(int [][]am,Stack<Integer> stack,Set<Integer> visited) {
		 if(stack != null && !stack.isEmpty()) {
			 int node = stack.pop();
			 visited.add(node);
			 int [] connections = am[node];
			 for(int currentNode = 0;currentNode < connections.length ; currentNode++) {
				 if(connections[currentNode] == 1 && !visited.contains(currentNode) &&  !stack.contains(currentNode)) {
					 stack.push(currentNode);
					 break;
				 }
			 }
			 dfs(am,stack,visited);
		 }
	 }
	  

	  
	  

}
