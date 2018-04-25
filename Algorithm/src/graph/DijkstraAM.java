package algorithm.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraAM {
    private static int MAX_VALUE=1000;
	int[][] am = { { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 },
			{ 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
			{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE },
			{ MAX_VALUE,16,11,0,6,MAX_VALUE },
			{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 },
			{ 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };

	Map<Integer, Integer> indexToDstanceFromSource = new HashMap<>();
	Queue<Vertex> priorityQueue = new PriorityQueue<>(new VertexCmp());
	List<Integer> visited = new ArrayList<>();

	public static void main(String[] args) {
		DijkstraAM am=new DijkstraAM();
		am.findShortestDistance(0);
		am.printDistances();
	}

	public void printDistances()
	{
		for(int i=0;i<am[0].length;i++)
		{
			System.out.println("Distance of node "+i +" is "+am[0][i]);
		}
	}
	
	public void findShortestDistance(int source) {

		for (int node = source; node < am.length; node++) {
			int[] connectedVertices = am[node];
			for (int i = 0; i < connectedVertices.length; i++) {
				if (connectedVertices[i] != 0 && connectedVertices[i] != MAX_VALUE) {
					if (!priorityQueue.contains(new Vertex(i, MAX_VALUE))
							&& !visited.contains(i))
						priorityQueue.add(new Vertex(i, (connectedVertices[node] + connectedVertices[i])));
				}

				// node=1,i=2
				// connectedVertices[i]=10,am[source][node]=7,am[source][i]=9
				if (connectedVertices[i] + am[source][node] < am[source][i]) {
					am[source][i] = connectedVertices[i] + am[source][node];
					System.out.println("Distance of " + i + " changed to " + am[source][i]);
				}
			}

			visited.add(node);

		}

	}
}

class Vertex {
	int nodeID;
	int distanceFromSource;

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

	public Vertex(int nodeID, int distanceFromSource) {
		super();
		this.nodeID = nodeID;
		this.distanceFromSource = distanceFromSource;
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

}

class VertexCmp implements Comparator<Vertex> {

	@Override
	public int compare(Vertex node1, Vertex node2) {
		if (node1.getDistanceFromSource() > node2.getDistanceFromSource())
			return -1;
		else if (node1.getDistanceFromSource() < node2.getDistanceFromSource())
			return 1;
		else
			return 0;
	}

}