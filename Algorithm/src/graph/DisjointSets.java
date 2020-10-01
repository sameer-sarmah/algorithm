package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DisjointSets {


	  public static void main(String[] args) {
		  Set<Set<Integer>> clusters = new HashSet<>();
		  Set<Set<Integer>> distinctClusters = new HashSet<>();
		  int N = 7;
		  int[][] am = new int[N][N];
		  List<Pair> connections = connections();
		  for(Pair connection : connections) {
			  int from = connection.getFrom();
			  int to = connection.getTo();
			  am[from][to] = 1;
			  am[to][from] = 1;
		  }
		  for(int row =0 ;row < am.length ; row++) {
			  Set<Integer> cluster = new HashSet<Integer>();
			  cluster.add(row);
			  for(int column = 0;column < am.length ;column++) { 
				  if(am[row][column] == 1) {
					  cluster.add(column);
				  }
			  }
			  clusters.add(cluster);
		  }
		  
		  for(Set<Integer> cluster : clusters) {
			  merge(distinctClusters,cluster);
		  }
		  System.out.println(distinctClusters);
	}
	  

	 
	  
	  private static void merge( Set<Set<Integer>> distinctClusters,Set<Integer> cluster) {
		
		  	for(Set<Integer> distinctCluster : distinctClusters) {
		  		for(int node : cluster) {
		  		  if(distinctCluster.contains(node)) {
		  			distinctCluster.addAll(cluster);
		  			  return ;
		  		  }
		  	  }
		  }
		  
		  	distinctClusters.add(cluster);
	  }
	  
	  private static List<Pair> connections() {
		  List<Pair> connections = new ArrayList<>();
		  connections.add(new Pair(0,1));
		  connections.add(new Pair(3,4));
		  connections.add(new Pair(2,3));
		  connections.add(new Pair(1,2));
		  connections.add(new Pair(5,6));
		  return connections;
	  }
	  
	  static class Pair{
		  int from,to;

		public Pair(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}

		public int getFrom() {
			return from;
		}

		public int getTo() {
			return to;
		}
		 
		
	  }

}
