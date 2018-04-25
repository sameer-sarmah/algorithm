package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DFS_AM {
	private static int MAX_VALUE = 1000;
	Map<Integer, String> map = new HashMap<>();
	int[][] am = { { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, { 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
			{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, { MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
			{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, { 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	final int N = am.length;
	private Stack<Integer> stack = new Stack<>();
	private List<Integer> visited = new ArrayList<>();

	public void dfs(int node) {
		visited.add(node);
		System.out.println(node);
		int[] vertex = am[node];
		for (int i = 0; i < vertex.length; i++) {
			int weight = vertex[i];
			if (weight > 0 && weight != MAX_VALUE && !visited.contains(i) && !stack.contains(i)) {
				stack.add(i);
				break;
			}
		}

		if (stack.size() > 0)
			dfs(stack.pop());
		else
			System.out.println(visited);

	}

	public static void main(String... arg) {
		new DFS_AM().dfs(0);

	}
}
