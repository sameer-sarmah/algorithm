package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FloydWarshall {
	private static int MAX_VALUE = 1000;
	// S A B C T
	int[][] am = { { 0, 7, 6, MAX_VALUE, 2 }, 
			{ 7, 0, 8, 3, 9 }, 
			{ 6, 8, 0, 2, 4 },
			{ MAX_VALUE, 3, 2, 0, 7 }, 
			{ 2, 9, 4, 7, 0 } };
	final int N = am.length;
	Map<Integer, String> map = new HashMap<>();

	public FloydWarshall() {
		super();
		this.populateMap();
	}

	private void populateMap() {
		map.put(0, "S");
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(4, "T");

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
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
				{
					if (am[k][i] + am[i][j] < am[k][j]) {
						am[k][j] =am[k][i] + am[i][j];
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
