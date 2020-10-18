package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//An edge in an undirected connected graph is a bridge if removing it disconnects the graph.
public class FindBridges {

	private static final int MAX_VALUE = 1000;
	private static int[][] am = { {0,1,1,1,MAX_VALUE},
								  {1,0,1,MAX_VALUE,MAX_VALUE},
								  {1,1,0,MAX_VALUE,MAX_VALUE},
								  {1,MAX_VALUE,MAX_VALUE,0,1},
								  {MAX_VALUE,MAX_VALUE,MAX_VALUE,1,0}
			  };
	public static void main(String[] args) {
		Set<Edge> bridges =findBridges(am);
		System.out.println(bridges);

	}

	private static Set<Edge> findBridges(int[][] am) {
		Set<Edge> bridges = new HashSet<Edge>();
		for(int source = 0 ; source < am.length ;source++) {
			for(int target = 0 ; target < am.length ;target++) {
				if(am[source][target] < MAX_VALUE && am[source][target] > 0) {
					int [][] clonedAM = clone(am);
					clonedAM[source][target] = MAX_VALUE;
					int[][] connectivityMatrix = findConnectivityMatrix(clonedAM);
					if(!isStronglyConnected(connectivityMatrix)) {
						bridges.add(new Edge(source, target));
					}
				}
			}
		}
		return bridges;
		
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
	
	private static boolean isConnected(int[][] am,int currentNode,int target,List<Integer> visited) {
		visited.add(currentNode);
		int[] connections = am[currentNode];
		for(int node = 0 ; node < am.length ;node++) {
			if(node == target && connections[node] < MAX_VALUE && connections[node] > 0) {
				return true;
			}
			if(connections[node] < MAX_VALUE && connections[node] > 0 && !visited.contains(node)) {
				if(isConnected(am,node,target,visited)) {
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
	
	static class Edge{
		int source,target;

		public Edge(int source, int target) {
			super();
			this.source = source;
			this.target = target;
		}

		@Override
		public int hashCode() {
			int prime =31;
			return prime * source * target;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if ((source == other.source || source == other.target)
					&& (target == other.target || target == other.source))
				return true;

			return false;
		}

		@Override
		public String toString() {
			return "Edge [source=" + source + ", target=" + target + "]";
		}
		
		
		
	}
}


