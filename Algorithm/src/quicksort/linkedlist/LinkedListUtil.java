package quicksort.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class LinkedListUtil<T extends Comparable<T>> {

	public static <T extends Comparable<T>> LinkedListNode<T> merge(LinkedListNode<T> left, LinkedListNode<T> right ,T pivot) {
		LinkedListNode<T> leftRef = left;
		LinkedListNode<T> rightRef = right;
		LinkedListNode<T> head = null;
		LinkedListNode<T> cumulativeRef = null;

		while (leftRef != null ) {
			if (head == null) {
				head = leftRef;
			} 
			cumulativeRef=leftRef;
			leftRef = leftRef.getNext();
		}

		LinkedListNode<T> nodeForPivot=new LinkedListNode<>(pivot);
		if (head == null) {
			head = nodeForPivot;
		} 
		if(cumulativeRef==null) {
			cumulativeRef=nodeForPivot;
		}
		else {
		cumulativeRef.setNext(nodeForPivot);
		cumulativeRef=nodeForPivot;
		}
		
		while (rightRef != null ) {
			cumulativeRef.setNext(rightRef);
			cumulativeRef=rightRef;
			rightRef = rightRef.getNext();
		}
		
		return head;

	}

	public static <T extends Comparable<T>> int getSize(LinkedListNode<T> node) {
		int size = 0;
		while (node != null) {
			node = node.getNext();
			size++;
		}
		return size;
	}

	public static <T extends Comparable<T>> T getElementAtIndex(LinkedListNode<T> node, int index) {
		int counter = 0;
		while (node != null && counter < index) {
			node = node.getNext();
			counter++;
		}
		return node.getValue();
	}

	public static <T extends Comparable<T>> LinkedListNode<T> removeElementAtIndex(LinkedListNode<T> node, int index) {
		int counter = 0;
		LinkedListNode<T> head = node;
		while (node != null && counter < index - 1) {
			node = node.getNext();
			counter++;
		}
		LinkedListNode<T> nodeToDel = node.getNext();
		node.setNext(nodeToDel.getNext());
		nodeToDel.setNext(null);
		return head;
	}

	public static <T extends Comparable<T>> List<LinkedListNode<T>> partition(LinkedListNode<T> node, T pivot) {
		int size = LinkedListUtil.getSize(node);
		List<LinkedListNode<T>> listOfLL = new ArrayList<>();
		LinkedListNode<T> lessThanPivot = null;
		LinkedListNode<T> lessThanPivotHead = null;
		LinkedListNode<T> greaterThanPivot = null;
		LinkedListNode<T> greaterThanPivotHead = null;
		if (size == 1 || node == null) {
			listOfLL.add(node);
			return listOfLL;
		} else {

			while (node != null) {

				LinkedListNode<T> individualNode = node;
				node = node.getNext();
				individualNode.setNext(null);
				if (individualNode.getValue().compareTo(pivot) <= 0) {
					if (lessThanPivot == null) {
						lessThanPivot = individualNode;
						lessThanPivotHead = lessThanPivot;
					} else {
						lessThanPivot.setNext(individualNode);
						lessThanPivot = individualNode;
					}
				} else if (individualNode.getValue().compareTo(pivot) > 0) {
					if (greaterThanPivot == null) {
						greaterThanPivot = individualNode;
						greaterThanPivotHead = greaterThanPivot;
					} else {
						greaterThanPivot.setNext(individualNode);
						greaterThanPivot = individualNode;
					}
				}
			}
		}
		listOfLL.add(lessThanPivotHead);
		listOfLL.add(greaterThanPivotHead);
		return listOfLL;

	}

	public static <T extends Comparable<T>> void print(LinkedListNode<T> node) {

		while (node != null) {
			System.out.println(node.getValue());
			node = node.getNext();

		}

	}

	public static <T extends Comparable<T>> LinkedListNode<T> quickSort(LinkedListNode<T> node) {
		int size = LinkedListUtil.getSize(node);
		if (size == 1 || node == null) {
			return node;
		}
		int mid = size / 2;
		T pivot = getElementAtIndex(node, mid);
		node=LinkedListUtil.removeElementAtIndex(node, mid);
		List<LinkedListNode<T>> partitionedLL = partition(node, pivot);
		LinkedListNode<T> left = partitionedLL.get(0);
		LinkedListNode<T> right = null;
		if (partitionedLL.size() == 2) {
			right = partitionedLL.get(1);
		}
		LinkedListNode<T> sortedLeftLL = quickSort(left);
		LinkedListNode<T> sortedRightLL = quickSort(right);
		LinkedListNode<T> sortedLL = merge(sortedLeftLL, sortedRightLL,pivot);
		return sortedLL;

	}

}
