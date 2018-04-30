package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS_AM {
	private static int MAX_VALUE = Integer.MAX_VALUE;


	public static void dfsUsingStack(int[][] am,int node,List<Integer> visited,Stack<Integer> stack) {
		visited.add(node);
		System.out.println(node);
		int[] vertices = am[node];
		for (int i = 0; i < vertices.length; i++) {
			int weight = vertices[i];
			if (weight > 0 && weight < MAX_VALUE && !visited.contains(i)) {
				stack.add(i);
				break;
			}
		}

		if (stack.size() > 0)
			dfsUsingStack(am,stack.pop(),visited,stack);
		else
			System.out.println(visited);

	}
	
	public static void dfsUsingRecursion(int[][] am,int node,List<Integer> visited) {
		if(visited.contains(node)) {
			return;
		}
		else {
		visited.add(node);
		int[] vertices = am[node];
		for (int currentNode = 0; currentNode < vertices.length; currentNode++) {
			int weight = vertices[currentNode];
			if (weight > 0 && weight < MAX_VALUE && !visited.contains(currentNode)) {
				dfsUsingRecursion(am,currentNode,visited);
			}
		}
		System.out.println(node);
		}	

	}

	public static void main(String... arg) {
		int[][] am = { { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, 
				   { 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
				   { 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, 
				   { MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
			       { MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, 
				   { 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	 Stack<Integer> stack = new Stack<>();
	 List<Integer> visited = new ArrayList<>();
		dfsUsingStack(am,0,visited,stack);
		dfsUsingRecursion(am,0,visited);

	}
	
}
