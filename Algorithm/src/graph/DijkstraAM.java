package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraAM {
    private static int MAX_VALUE=1000;
    private static int[][] am = { 
			{ 0, 6, MAX_VALUE, 1, MAX_VALUE },
			{ 6, 0, 5, 2, 2 },
			{ MAX_VALUE, 5, 0,  MAX_VALUE, 5 },
			{ 1,2,MAX_VALUE,0,1 },
			{ MAX_VALUE,2,5,1,0 }};

    private static  Map<Integer,Vertex> distanceFromSource =  new HashMap<>();
    private static  PriorityQueue<Vertex> nodesToVisit = new PriorityQueue<>(new VertexCmp());
    private static  List<Integer> visited = new ArrayList<>();

	public static void main(String[] args) {

		int source = 0;
		Vertex sourceVertex = new Vertex(source, 0, -1);
		distanceFromSource.put(source, sourceVertex);
		for(int node = 0;node < am.length ; node++) {
			if( node != source) {
				Vertex nodeVertex = new Vertex(node, MAX_VALUE, -1);
				distanceFromSource.put(node, nodeVertex);
			}
		}
		nodesToVisit.add(sourceVertex);
		findShortestDistance(am,nodesToVisit,visited,distanceFromSource);
		System.out.println(distanceFromSource);

	}


	
	public static void findShortestDistance(int[][] am ,PriorityQueue<Vertex> nodesToVisit,
			List<Integer> visited,Map<Integer,Vertex> distanceFromSource) {
		  if(!nodesToVisit.isEmpty()) {
			Vertex visitingNode = nodesToVisit.poll();
			int visitingNodeId = visitingNode.getNodeID(); 
			int[ ] vertices = am[visitingNodeId];
			//find connected nodes 
			for(int node=0 ; node < vertices.length ; node++) {
				if(vertices[node] > 0 && vertices[node] < MAX_VALUE) {
					if(!visited.contains(node)) {
						int distanceFromSourceToVisitingNode = distanceFromSource.get(visitingNodeId).getDistanceFromSource();
						int distanceFromVisitingNodeToCurrentNode = am[visitingNodeId][node];
						int total = distanceFromSourceToVisitingNode + distanceFromVisitingNodeToCurrentNode;
						Vertex updatedNode= new Vertex(node, total,visitingNodeId);
						Vertex vertex = distanceFromSource.get(node);
						
						if(!nodesToVisit.contains(new Vertex(node))){
							nodesToVisit.add(updatedNode);
						} 
						
						
						if(vertex.getDistanceFromSource() > total) {
							distanceFromSource.put(node, updatedNode);	
							nodesToVisit.remove(new Vertex(node));
							nodesToVisit.add(updatedNode);
						}
					}
				}
			}
			visited.add(visitingNodeId);
			if(!nodesToVisit.isEmpty()) {
				findShortestDistance(am,nodesToVisit,visited,distanceFromSource);
			}

		  }

	}
}

class Vertex {
	private int nodeID;
	private int distanceFromSource;
	//previous node from source to this node
	private int previousNode;

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}
	
	public int getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(int previousNode) {
		this.previousNode = previousNode;
	}

	public Vertex(int nodeID) {
		super();
		this.nodeID = nodeID;
		this.distanceFromSource = Integer.MAX_VALUE;
		this.previousNode = -1;
	}
	
	public Vertex(int nodeID, int distanceFromSource,int previousNode) {
		super();
		this.nodeID = nodeID;
		this.distanceFromSource = distanceFromSource;
		this.previousNode = previousNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nodeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (nodeID != other.nodeID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return "[ node "+nodeID+", distanceFromSource "+distanceFromSource +" ]";
	}

	
}

/*
 * Used by the priority queue to store the nodes to visit next. 
 * The need for a priority queue to find the node which is closest to the source node.
 * Therefore reverse sort,nodes with least distanceFromSource will have higher priority
 * */
class VertexCmp implements Comparator<Vertex> {

	@Override
	public int compare(Vertex node1, Vertex node2) {
		if (node1.getDistanceFromSource() < node2.getDistanceFromSource())
			return -1;
		else if (node1.getDistanceFromSource() > node2.getDistanceFromSource())
			return 1;
		else
			return 0;
	}

}