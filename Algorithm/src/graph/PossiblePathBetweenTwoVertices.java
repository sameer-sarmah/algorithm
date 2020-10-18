package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PossiblePathBetweenTwoVertices {

	private static int MAX_VALUE = 1000;
	private static int[][] am = { { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, 
								{ 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
								{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, 
								{ MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
								{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, 
								{ 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };

	private static List<Integer> visited = new ArrayList<>();


	public static void main(String[] args) {
		List<List<Integer>> paths = new ArrayList<>();
		traverse(0, paths,0,5);
	}

	private static void traverse(int currentNode, List<List<Integer>> paths,int source,int target) {
		visited.add(currentNode);
		int[] connections = am[currentNode];
		for (int node = 0; node < connections.length; node++) {
			if (connections[node] > 0 && connections[node] < MAX_VALUE && !visited.contains(node)) {
				if (node == target) {
					List<Integer> path = visited.stream().collect(Collectors.toList());
					path.add(target);
					paths.add(path);
					System.out.println(path);
				}
				traverse(node,paths,source,target);
			}
		}
		int index = visited.indexOf(currentNode);
		visited.remove(index);
	}

}
