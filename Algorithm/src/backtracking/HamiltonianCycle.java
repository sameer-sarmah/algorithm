package backtracking;

import java.util.Stack;
/*A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that there is an edge 
 * (in the graph) from the last vertex to the first vertex of the Hamiltonian Path.
 * */
public class HamiltonianCycle {
	private static int MAX_VALUE = Integer.MAX_VALUE;
	private static int[][] am = { { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, 
			   { 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
			   { 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, 
			   { MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
		       { MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, 
			   { 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	public static void main(String[] args) {
		Stack<Integer> path = new Stack<Integer>();
		int source = 0;
		int target = 5;
		isHamiltonianCyclePresent(path, source, target, source);

	}
	
	private static boolean areAllNodesVisited(Stack<Integer> path,int target){
		for(int node = 0;node < am.length ; node++){
			if(node != target && !path.contains(node)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isHamiltonianCyclePresent(Stack<Integer> path,int source,int target,int currentNode) {
		path.add(currentNode);
		int[] connections = am[currentNode];
		for(int node = 0;node < am.length ; node++){
			if(connections[node] > 0  && connections[node] < MAX_VALUE && ((currentNode == target && node == source) || !path.contains(node)) ) {
				if( areAllNodesVisited(path,target)) {
					path.add(source);
					System.out.println(path);
					return true;
				}
				if(isHamiltonianCyclePresent(path, source, target, node)) {
					return true;
				}
			}
		}
		path.pop();
		return false;
	}

}
