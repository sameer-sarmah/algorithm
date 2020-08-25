package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
 * Given a graph, a source vertex in the graph and a number k, 
 * find if there is a simple path (without any cycle) starting from given source and ending at any other vertex.
 * The addition of the weight of the edges in the traversal path should be equal to or greater than k
 * */
public class KWeightPath {
	
	private static int MAX_VALUE = 1000;
	private static int[][] am = {   { 0, 7, 9, MAX_VALUE, MAX_VALUE, 14 }, 
									{ 7, 0, 10, 16, MAX_VALUE, MAX_VALUE },
									{ 9, 10, 0, 11, MAX_VALUE, MAX_VALUE }, 
									{ MAX_VALUE, 16, 11, 0, 6, MAX_VALUE },
									{ MAX_VALUE, MAX_VALUE, MAX_VALUE, 6, 0, 9 }, 
									{ 14, MAX_VALUE, MAX_VALUE, MAX_VALUE, 9, 0 } };
	private static int minPathWeight = 50;
	private static List<Integer> visited = new ArrayList<>(); 
	private static Stack<Integer> stack = new Stack<>(); 
	
	public static void main(String[] args) {
		stack.add(0);
		findPath(stack,visited,0);
	}
	
	private static boolean findPath(Stack<Integer> stack,List<Integer> visited,int accumulated) {
		if(!stack.isEmpty()) {
			int currentNode = stack.pop();
			visited.add(currentNode);
			System.out.println(String.format("current node : %d , accumulated : %d", currentNode,accumulated));
			for(int node = 0; node < am.length ; node++) {
				if(am[currentNode][node] > 1 && am[currentNode][node] < MAX_VALUE  && !visited.contains(node)) {
					if(am[currentNode][node] + accumulated >=  minPathWeight) {
						System.out.println("found path: "+visited +" , accumulated value: "+(am[currentNode][node] +accumulated));
						return true;
					}
					else {
						stack.add(node);
						if(findPath(stack,visited,accumulated + am[currentNode][node])) {
							return true;
						}
						else if(!stack.isEmpty()){
						stack.pop();
						}
						
					}
				}
			}
			if(visited.contains(currentNode)) {
				int index = visited.indexOf(currentNode);
				visited.remove(index);
				System.out.println("removed node :"+currentNode+" from visited list");
			}

		}
		
		return false;
	}

}
