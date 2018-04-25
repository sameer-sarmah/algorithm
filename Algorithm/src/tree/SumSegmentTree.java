package algorithm.tree;

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
public class SumSegmentTree {

	public static void main(String[] args) {
		List<Integer> numbers = IntStream.rangeClosed(1, 4).boxed().collect(Collectors.toList());
		SummableSegmentedTreeNode tree=convertToSegmentedTree(numbers,0,numbers.size()-1);
		BFSSummableSegmentTree traverser=new  BFSSummableSegmentTree();
		traverser.bfs(tree);
		Pair<Integer,Integer> rangeQueried = Pair.with(0, 3);
		Number sum=findSumInRange(tree,rangeQueried);
		System.out.println(sum);
		
		rangeQueried = Pair.with(1, 3);
	    sum=findSumInRange(tree,rangeQueried);
		System.out.println(sum);
	}

	private static  Number findSumInRange(SummableSegmentedTreeNode node,Pair<Integer,Integer> rangeQueried) {
		int fromIndex=node.getFrom();
		int toIndex=node.getTo();
		Pair<Integer,Integer> rangeOfNode =Pair.with(fromIndex, toIndex);
		RangeStatus status=findRangeStatus(rangeOfNode,rangeQueried);
		if(status.equals(RangeStatus.OVERLAP)) {
			return node.getValue();
		}
		else if(status.equals(RangeStatus.PARTIAL_OVERLAP)) {
			SummableSegmentedTreeNode leftChild=(SummableSegmentedTreeNode) node.getLeft();
			Number leftChildSum=findSumInRange(leftChild,rangeQueried);
			
			SummableSegmentedTreeNode rightChild=(SummableSegmentedTreeNode) node.getRight();
			Number rightChildSum=findSumInRange(rightChild,rangeQueried);
			
			if(leftChildSum != null && rightChildSum != null) {
				return leftChildSum.intValue() +rightChildSum.intValue();
			}
			else if(leftChildSum != null && rightChildSum == null) {
				return leftChildSum;
			}
			else if(leftChildSum == null && rightChildSum != null){
				return rightChildSum;
			}
			else {
				return null;
			}
					
		}
		else {
			return null;
		}
		

	}
	
	private static  RangeStatus findRangeStatus(Pair<Integer,Integer> rangeOfNode,Pair<Integer,Integer> rangeQueried) {
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
	
	private static  SummableSegmentedTreeNode createNode(Number value, SummableSegmentedTreeNode parent,
			int fromIndex, int toIndex) {
		SummableSegmentedTreeNode node = new SummableSegmentedTreeNode(value, fromIndex, toIndex);
		node.setParent(parent);
		return node;
	}

	private static  SummableSegmentedTreeNode assignLeftRight(SummableSegmentedTreeNode parent,
			SummableSegmentedTreeNode left, SummableSegmentedTreeNode right) {
		parent.setLeft(left);
		parent.setRight(right);
		return parent;
	}

	private static  int sum(SummableSegmentedTreeNode left, SummableSegmentedTreeNode right) {
		return left.getValue().intValue() + right.getValue().intValue();
	}

	public static  SummableSegmentedTreeNode convertToSegmentedTree(List<Integer> numbers, int fromIndex,
			int toIndex) {

		if (numbers.size() == 1) {
			SummableSegmentedTreeNode node = new SummableSegmentedTreeNode(numbers.get(0), fromIndex, toIndex);
			return node;
		} else {
			int midIndex = numbers.size() / 2 - 1;
			List<Integer> left = numbers.subList(0, midIndex+1);
			List<Integer> right = numbers.subList(midIndex+1, numbers.size());
			SummableSegmentedTreeNode leftSubTree = convertToSegmentedTree(left, fromIndex, midIndex+fromIndex);
			SummableSegmentedTreeNode rightSubTree = convertToSegmentedTree(right, midIndex+fromIndex+1, toIndex);
			int sum = sum(leftSubTree, rightSubTree);
			SummableSegmentedTreeNode parent = createNode((Number)sum, null, fromIndex, toIndex);
			assignLeftRight(parent, leftSubTree, rightSubTree);
			return parent;
		}

	}
}


class BFSSummableSegmentTree {

	private Queue<SummableSegmentedTreeNode> queue = new LinkedBlockingQueue<>(); 
	public void bfs(SummableSegmentedTreeNode node){
		System.out.println(node);
		
		if(node.getLeft()!=null) {
		queue.add((SummableSegmentedTreeNode) node.getLeft());
		}
		
		if(node.getRight()!=null) {
		queue.add((SummableSegmentedTreeNode) node.getRight());
		}
		
		if(!queue.isEmpty())
			bfs(queue.poll());
	}
	
}
