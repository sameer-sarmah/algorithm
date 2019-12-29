package graph;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord {
	private static int MAX_VALUE = 1000;
	int[][] am = { 
			{ 0, 7, 6, MAX_VALUE, MAX_VALUE }, 
			{ MAX_VALUE, 0, MAX_VALUE, -3, 9 }, 
			{ MAX_VALUE, 8, 0, 5, -4 },
			{ MAX_VALUE, MAX_VALUE, -2, 0, MAX_VALUE }, 
			{ 2, MAX_VALUE, MAX_VALUE, 7, 0 } };
	Map<Integer, Integer> indexToDistanceFromSource = new HashMap<>();

	public void initializeMap(int source) {
		for (int node = 0; node < am.length; node++) {
			if (node == source)
				indexToDistanceFromSource.put(node, 0);
			else
				indexToDistanceFromSource.put(node, MAX_VALUE);
		}
	}

	public static void main(String[] args) {
		BellmanFord bf=new BellmanFord();
		int source = 0;
		bf.initializeMap(source);
		bf.findShortestDistance(source);
		bf.printDistances(source);
	}


	public void printDistances(int source) {
		System.out.println(indexToDistanceFromSource);
	}

	public void findShortestDistance(int source) {
		for (int vertex=0; vertex < am.length; vertex++) {
			
		for (int fromVertex = 0; fromVertex < am.length; fromVertex++) {
			for (int toVertex = 0; toVertex < am[fromVertex].length; toVertex++) {
				if (am[fromVertex][toVertex] != 0 && am[fromVertex][toVertex] != MAX_VALUE) {
					if (am[fromVertex][toVertex] + indexToDistanceFromSource.get(fromVertex) < indexToDistanceFromSource.get(toVertex)) {
						indexToDistanceFromSource.put(toVertex,am[fromVertex][toVertex] + indexToDistanceFromSource.get(fromVertex));
						System.out.println("Distance of " + toVertex + " changed to " + indexToDistanceFromSource.get(toVertex));
					}
				}

			}

		}
		}

	}

}
