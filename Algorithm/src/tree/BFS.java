package tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS<T extends Comparable<T>> {

	private Queue<TreeNode<T>> queue = new LinkedBlockingQueue<>(); 
	public void bfs(TreeNode<T> node){
		System.out.println(node);
		
		if(node.getLeft()!=null) {
		queue.add(node.getLeft());
		}
		
		if(node.getRight()!=null) {
		queue.add(node.getRight());
		}
		
		if(!queue.isEmpty())
			bfs(queue.poll());
	}
	
}
