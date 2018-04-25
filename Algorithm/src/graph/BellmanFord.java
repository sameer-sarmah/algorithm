package graph;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord {
	private static int MAX_VALUE = 1000;
	int[][] am = { { 0, 7, 6, MAX_VALUE, MAX_VALUE }, { MAX_VALUE, 0, MAX_VALUE, -3, 9 }, { MAX_VALUE, 8, 0, 5, -4 },
			{ MAX_VALUE, MAX_VALUE, -2, 0, MAX_VALUE }, { 2, MAX_VALUE, MAX_VALUE, 7, 0 } };
	Map<Integer, Integer> indexToDstanceFromSource = new HashMap<>();

	public static void main(String[] args) {
		BellmanFord bf=new BellmanFord();
		bf.findShortestDistance(0);
		bf.printDistances(0);
	}
	
	
	public void initializeMap(int source) {
		for (int node = 0; node < am.length; node++) {
			if (node == source)
				indexToDstanceFromSource.put(node, 0);
			else
				indexToDstanceFromSource.put(node, MAX_VALUE);
		}
	}

	public void printDistances(int source) {
		for (int i = 0; i < am[source].length; i++) {
			System.out.println("Distance of node " + i + " is " + am[source][i]);
		}
	}

	public void findShortestDistance(int source) {
		for (int j=0; j < am.length; j++) {
		for (int node = source; node < am.length; node++) {
			int[] connectedVertices = am[node];
			for (int i = 0; i < connectedVertices.length; i++) {
				if (connectedVertices[i] != 0 && connectedVertices[i] != MAX_VALUE) {
					if (connectedVertices[i] + am[source][node] < am[source][i]) {
						am[source][i] = connectedVertices[i] + am[source][node];
						System.out.println("Distance of " + i + " changed to " + am[source][i]);
					}
				}

			}

		}
		}

	}

}
