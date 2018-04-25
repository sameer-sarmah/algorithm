package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class AlternateBST {
	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
		Cell<Integer> cell = new Cell<Integer>(0,0,root);
		List<Cell<Integer>> list=new ArrayList<>();
		list.add(cell);
        Map<Integer,List<Cell<Integer>>> depthToNodes=new HashMap<>();//for a depth contains all the nodes in that depth
        depthToNodes.put(0, list);
        Queue<Cell<Integer>> queue = new ConcurrentLinkedQueue<>();
        reverseBST(cell,queue,depthToNodes);
        List<Integer> depths=new ArrayList<>(depthToNodes.keySet());
        depths=depths.stream().sorted().collect(Collectors.toList());
        boolean isLeftToRight=false;
        for(int depth:depths) {
        	List<Cell<Integer>> cellsAtDepth=depthToNodes.get(depth);
        	cellsAtDepth.sort(new CellComparator<Integer>());
        	isLeftToRight=!isLeftToRight;
        	if(!isLeftToRight) {
        		Collections.reverse(cellsAtDepth);
        	}
        	System.out.println(cellsAtDepth);
        }
        
	}
	
	public static <T extends Comparable<T>> void reverseBST(Cell<Integer> cell,Queue<Cell<Integer>> queue, Map<Integer,List<Cell<Integer>>> depthToNodes) {
		
		TreeNode<Integer> node =cell.getNode();
		int depth=cell.getDepth();
		int column=cell.getColumn();
		if(node.getLeft() != null) {
			Cell<Integer> leftCell=new Cell<Integer>(depth+1,column-1,node.getLeft());
			queue.add(leftCell);
			if(depthToNodes.get(depth+1) == null) {
				List<Cell<Integer>> list=new ArrayList<>();
				list.add(leftCell);
				depthToNodes.put(depth+1, list);
			}
			else {
				depthToNodes.get(depth+1).add(leftCell);
			}
		}
		
		if(node.getRight() != null) {
			Cell<Integer> rightCell=new Cell<Integer>(depth+1,column+1,node.getRight());
			queue.add(rightCell);
			if(depthToNodes.get(depth+1) == null) {
				List<Cell<Integer>> list=new ArrayList<>();
				list.add(rightCell);
				depthToNodes.put(depth+1, list);
				
			}
			else {
				depthToNodes.get(depth+1).add(rightCell);
			}
		}
		
		if(!queue.isEmpty()) {
			Cell<Integer> nextInProcess=queue.poll();
			reverseBST(nextInProcess,queue,depthToNodes);
		}
		
		
	}
}

class Cell<T extends Comparable<T>>{
	private int depth,column;
	private TreeNode<T> node;
	public Cell(int depth, int column, TreeNode<T> node) {
		super();
		this.depth = depth;
		this.column = column;
		this.node = node;
	}
	public int getDepth() {
		return depth;
	}
	public int getColumn() {
		return column;
	}
	public TreeNode<T> getNode() {
		return node;
	}
	
	@Override
	public String toString() {
		return node.value.toString();
	}
}

class CellComparator<T extends Comparable<T>> implements Comparator<Cell<T>>{

	@Override
	public int compare(Cell<T> cell1, Cell<T> cell2) {
		if(cell1.getDepth() < cell2.getDepth()) {
			return -1;
		}
		else if(cell1.getDepth() > cell2.getDepth()) {
			return 1;
		}
		else {
			if(cell1.getColumn() < cell2.getColumn()) {
				return -1;
			}
			else if(cell1.getColumn() > cell2.getColumn()) {
				return 1;
			}
			else
				return 0;
		}
	}
	
}