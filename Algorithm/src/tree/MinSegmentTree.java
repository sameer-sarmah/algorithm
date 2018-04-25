package tree;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.javatuples.Pair;

/*
 * segment tree answers range queries e.g max element in range(3,7)
 * min element in range(3,8) 
 * sum of all the elements between (2,7)
 * */
public class MinSegmentTree<T extends Comparable<T>> {

	public static void main(String[] args) {
		List<Integer> numbers = IntStream.rangeClosed(1, 4).boxed().collect(Collectors.toList());
		SegmentedTreeNode<Integer> tree=convertToSegmentedTree(numbers,0,numbers.size()-1);
		BFSSegmentTree<Integer> traverser=new  BFSSegmentTree<Integer>();
		traverser.bfs(tree);
		Pair<Integer,Integer> rangeQueried = Pair.with(0, 3);
		int min=findMinimumInRange(tree,rangeQueried);
		System.out.println(min);
		
		rangeQueried = Pair.with(1, 3);
	    min=findMinimumInRange(tree,rangeQueried);
		System.out.println(min);
	}

	private static <T extends Comparable<T>> T findMinimumInRange(SegmentedTreeNode<T> node,Pair<Integer,Integer> rangeQueried) {
		int fromIndex=node.getFrom();
		int toIndex=node.getTo();
		Pair<Integer,Integer> rangeOfNode =Pair.with(fromIndex, toIndex);
		RangeStatus status=findRangeStatus(rangeOfNode,rangeQueried);
		if(status.equals(RangeStatus.OVERLAP)) {
			return node.getValue();
		}
		else if(status.equals(RangeStatus.PARTIAL_OVERLAP)) {
			SegmentedTreeNode<T> leftChild=(SegmentedTreeNode<T>) node.getLeft();
			T leftChildMin=findMinimumInRange(leftChild,rangeQueried);
			
			SegmentedTreeNode<T> rightChild=(SegmentedTreeNode<T>) node.getRight();
			T rightChildMin=findMinimumInRange(rightChild,rangeQueried);
			
			if(leftChildMin != null && rightChildMin != null) {
			if(leftChildMin.compareTo(rightChildMin) < 0) {
				return leftChildMin;
			}
			else {
				return rightChildMin;
			}
			}
			else if(leftChildMin != null && rightChildMin == null) {
				return leftChildMin;
			}
			else if(leftChildMin == null && rightChildMin != null){
				return rightChildMin;
			}
			else {
				return null;
			}
					
		}
		else {
			return null;
		}
		

	}
	
	private static <T extends Comparable<T>> RangeStatus findRangeStatus(Pair<Integer,Integer> rangeOfNode,Pair<Integer,Integer> rangeQueried) {
		if((rangeOfNode.getValue0() >= rangeQueried.getValue0()) && (rangeOfNode.getValue1() <= rangeQueried.getValue1())) {
			return RangeStatus.OVERLAP;
		}
		else if((rangeOfNode.getValue1() < rangeQueried.getValue0()) || (rangeOfNode.getValue0() > rangeQueried.getValue1())) {
			return RangeStatus.NO_OVERLAP;
		}
		else {
			return RangeStatus.PARTIAL_OVERLAP;
		}
	}
	
	private static <T extends Comparable<T>> SegmentedTreeNode<T> createNode(T value, SegmentedTreeNode<T> parent,
			int fromIndex, int toIndex) {
		SegmentedTreeNode<T> node = new SegmentedTreeNode<>(value, fromIndex, toIndex);
		node.setParent(parent);
		return node;
	}

	private static <T extends Comparable<T>> SegmentedTreeNode<T> assignLeftRight(SegmentedTreeNode<T> parent,
			SegmentedTreeNode<T> left, SegmentedTreeNode<T> right) {
		parent.setLeft(left);
		parent.setRight(right);
		return parent;
	}

	private static <T extends Comparable<T>> T findMinimumValue(SegmentedTreeNode<T> left, SegmentedTreeNode<T> right) {
		if (left.getValue().compareTo(right.getValue()) < 0) {
			return left.getValue();
		} else {
			return right.getValue();
		}
	}

	public static <T extends Comparable<T>> SegmentedTreeNode<T> convertToSegmentedTree(List<T> numbers, int fromIndex,
			int toIndex) {

		if (numbers.size() == 1) {
			SegmentedTreeNode<T> node = new SegmentedTreeNode<>(numbers.get(0), fromIndex, toIndex);
			return node;
		} else {
			int midIndex = numbers.size() / 2 - 1;
			List<T> left = numbers.subList(0, midIndex+1);
			List<T> right = numbers.subList(midIndex+1, numbers.size());
			SegmentedTreeNode<T> leftSubTree = convertToSegmentedTree(left, fromIndex, midIndex+fromIndex);
			SegmentedTreeNode<T> rightSubTree = convertToSegmentedTree(right, midIndex+fromIndex+1, toIndex);
			T minValue = findMinimumValue(leftSubTree, rightSubTree);
			SegmentedTreeNode<T> parent = createNode(minValue, null, fromIndex, toIndex);
			assignLeftRight(parent, leftSubTree, rightSubTree);
			return parent;
		}

	}
}

enum RangeStatus{
	PARTIAL_OVERLAP,OVERLAP,NO_OVERLAP
}

class BFSSegmentTree<T extends Comparable<T>> {

	private Queue<SegmentedTreeNode<T>> queue = new LinkedBlockingQueue<>(); 
	public void bfs(SegmentedTreeNode<T> node){
		System.out.println(node);
		
		if(node.getLeft()!=null) {
		queue.add((SegmentedTreeNode<T>) node.getLeft());
		}
		
		if(node.getRight()!=null) {
		queue.add((SegmentedTreeNode<T>) node.getRight());
		}
		
		if(!queue.isEmpty())
			bfs(queue.poll());
	}
	
}
