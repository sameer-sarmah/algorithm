package graph;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TopologicalSorting {
	private static final int MAX=Integer.MAX_VALUE;
	public static void main(String[] args) {
		int[][] am={{0,MAX,1,MAX,MAX,MAX,MAX,MAX},
				{MAX,0,1,1,MAX,MAX,MAX,MAX},
				{MAX,MAX,0,MAX,1,MAX,MAX,MAX},
				{MAX,MAX,MAX,0,MAX,1,MAX,MAX},
				{MAX,MAX,MAX,MAX,0,1,MAX,1},
				{MAX,MAX,MAX,MAX,MAX,0,1,MAX},
				{MAX,MAX,MAX,MAX,MAX,MAX,0,MAX},
				{MAX,MAX,MAX,MAX,MAX,MAX,MAX,0}
			   };
		Stack<Integer> visited = new Stack<>();
		Stack<Integer> topologicallySorted = new Stack<>();
		while(visited.size() < am.length) {
			int node=getRandomNonVisitedNode(visited,am.length);
			topologicalSorting(node, am, visited, topologicallySorted);
		}
		System.out.println("topological sorting completed");
		print(topologicallySorted);
	}

	public static void topologicalSorting(int node,int[][] am,Stack<Integer> visited,Stack<Integer> topologicallySorted) {
		if(visited.size() == am.length || visited.contains(node)) {
			return;
		}

		
		visited.push(node);
		int[] allNodes= am[node];
		for(int currentNode=0; currentNode < allNodes.length;currentNode++) {
			if(allNodes[currentNode] == 1 && !visited.contains(allNodes[currentNode])) {
				topologicalSorting(currentNode,am,visited,topologicallySorted);
			}
		}
		System.out.println(node+" added to topologically sorted stack");
		topologicallySorted.add(node);
		
	}
	
	private static int getRandomNonVisitedNode(Stack<Integer> visited,int nodeCount) {
		Integer[] visitedNodes=new Integer[visited.size()];
		visited.toArray(visitedNodes);
		List<Integer> unVisitedNode=IntStream.range(0, nodeCount).boxed().collect(Collectors.toList());
		for(Integer visitedNode:visitedNodes) {
			if(visitedNode != null) {
			int index =unVisitedNode.indexOf(visitedNode);
			unVisitedNode.remove(index);
			}
		}
        Random random=new Random();
        if(unVisitedNode.size()-1>0) {
		int node=random.nextInt(unVisitedNode.size()-1);
		return node;
        }
        else if(unVisitedNode.size()==1){
        	return unVisitedNode.get(0);
        }
        else {
        	return 0;
        }
		
	}
	
	private static void print(Stack<Integer> topologicallySorted) {
		StringBuilder string=new StringBuilder();
		while(!topologicallySorted.isEmpty()) {
			int node = topologicallySorted.pop();
			char convertedCharacter=convertToCharacter(node);
			string.append(convertedCharacter+" ");
		}
		System.out.println(string.toString());
		
		
	}
	
	private static char convertToCharacter(int node) {
		node+=65;//to capitalize,we assume that the nodes are represented a char between by A-Z
		return (char)node;
	}
}
