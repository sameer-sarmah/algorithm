package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FloydWarshall {
	private static int MAX_VALUE = 1000;
	// S A B C T
	int[][] am = { 
			{ 0, 7, 6, MAX_VALUE, MAX_VALUE }, 
			{ MAX_VALUE, 0, MAX_VALUE, -3, 9 }, 
			{ MAX_VALUE, 8, 0, 5, -4 },
			{ MAX_VALUE, MAX_VALUE, -2, 0, MAX_VALUE }, 
			{ 2, MAX_VALUE, MAX_VALUE, 7, 0 } };
	final int N = am.length;

	public FloydWarshall() {
		super();
	}

	private void printAM(){
		for(int [] arr :am)
		{
			System.out.println(Arrays.toString(arr));
		}
	}

	/*
	 * S-T S-A-T S-A-B-T S-A-C-T S-B-T S-B-A-T S-B-C-T
	 */
	public void findShortestDistance() {
		// The outermost loop holds the index of S
		// The middle loop holds the index of A
		// The innermost loop holds the index of T
		// if SA+AT<ST the make ST=SA+AT
		for (int fromNode = 0; fromNode < N; fromNode++) {
			for (int firstLayer = 0; firstLayer < N; firstLayer++) {
				for (int secondLayer = 0; secondLayer < N; secondLayer++)
				{
					int existingDistanceToSecondLayerNode = am[fromNode][secondLayer];
					int distanceFromCurrentNodeToFirstLayerNode = am[fromNode][firstLayer];
					int distanceFromFirstLayerNodeToSecondLayerNode = am[firstLayer][secondLayer];
					if (distanceFromCurrentNodeToFirstLayerNode + distanceFromFirstLayerNodeToSecondLayerNode < existingDistanceToSecondLayerNode) {
						am[fromNode][secondLayer] =distanceFromCurrentNodeToFirstLayerNode + distanceFromFirstLayerNodeToSecondLayerNode;
					}
				}
			}
		}
		printAM();
	}
	
	public static void main(String ...arg)
	{
		new FloydWarshall().findShortestDistance();
		
	}

}
