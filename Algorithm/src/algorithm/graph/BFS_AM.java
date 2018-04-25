package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS_AM {
	private static int MAX_VALUE = 1000;
	Map<Integer, String> map = new HashMap<>();
	int[][] am = { { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, { 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
			{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, { MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
			{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, { 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	final int N = am.length;
	private Queue<Integer> queue = new LinkedBlockingQueue<>();
    private List<Integer> visited=new ArrayList<>();
	public void bfs(int node) {
		visited.add(node);
		System.out.println(node);
		int[] vertex = am[node];
		for (int i = 0; i < vertex.length; i++) {
			int weight = vertex[i];
			if (weight > 0 && weight != MAX_VALUE && !visited.contains(i) && !queue.contains(i)) {
				queue.add(i);
			}
		}

		if (queue.size() > 0)
			bfs(queue.poll());
		else
			System.out.println(visited);

	}
	public static void main(String ...arg)
	{
		new BFS_AM().bfs(0);
		
	}
}
