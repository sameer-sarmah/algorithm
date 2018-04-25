package mergesort.linkedlist;
import java.util.ArrayList;
import java.util.List;

public class LinkedListUtil<T extends Comparable<T>> {

	public static <T extends Comparable<T>> LinkedListNode<T> merge(LinkedListNode<T> left, LinkedListNode<T> right) {
		LinkedListNode<T> leftRef = left;
		LinkedListNode<T> rightRef = right;
		LinkedListNode<T> head = null;
		LinkedListNode<T> cumulativeRef = null;

		while (leftRef != null && rightRef != null && leftRef.getValue().compareTo(rightRef.getValue()) < 0) {
			LinkedListNode<T> node = new LinkedListNode<T>();
			node.setValue(leftRef.getValue());
			if (cumulativeRef == null) {
				cumulativeRef = node;
				head=cumulativeRef;
			} else {
				cumulativeRef.setNext(node);
				cumulativeRef = node;
			}
			leftRef = leftRef.getNext();
		}

		while (leftRef != null && rightRef != null && leftRef.getValue().compareTo(rightRef.getValue()) > 0) {
			LinkedListNode<T> node = new LinkedListNode<T>();
			node.setValue(rightRef.getValue());
			if (cumulativeRef == null) {
				cumulativeRef = node;
				head=cumulativeRef;
			} else {
				cumulativeRef.setNext(node);
				cumulativeRef = node;
			}
			rightRef = rightRef.getNext();
		}

		while (leftRef != null) {
			LinkedListNode<T> node = new LinkedListNode<T>();
			node.setValue(leftRef.getValue());
			if (cumulativeRef == null) {
				cumulativeRef = node;
				head=cumulativeRef;
			} else {
				cumulativeRef.setNext(node);
				cumulativeRef = node;
			}
			leftRef = leftRef.getNext();
		}

		while (rightRef != null) {
			LinkedListNode<T> node = new LinkedListNode<T>();
			node.setValue(rightRef.getValue());
			if (cumulativeRef == null) {
				cumulativeRef = node;
				head=cumulativeRef;
			} else {
				cumulativeRef.setNext(node);
				cumulativeRef = node;
			}
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

	public static <T extends Comparable<T>> List<LinkedListNode<T>> partition(LinkedListNode<T> node) {
		int size = LinkedListUtil.getSize(node);
		List<LinkedListNode<T>> listOfLL = new ArrayList<>();
		if (size == 1 || node == null) {
			listOfLL.add(node);
			return listOfLL;
		} else {
			int mid = size / 2;
			int index = 1;
			LinkedListNode<T> subLL1 = node;

			while (index <  mid) {
				node = node.getNext();
				index++;
			}
			LinkedListNode<T> subLL2 = node.getNext();
			node.setNext(null);
			listOfLL.add(subLL1);
			listOfLL.add(subLL2);
			return listOfLL;
		}

	}
	
	public static <T extends Comparable<T>> void print(LinkedListNode<T> node) {

		while (node != null) {
			System.out.println(node.getValue());
			node = node.getNext();
		
		}
		
	}
	
	public static <T extends Comparable<T>> LinkedListNode<T> mergeSort(LinkedListNode<T> node) {
		int size = LinkedListUtil.getSize(node);
		if (size == 1 || node == null) {
			return node;
		}
		
		List<LinkedListNode<T>> partitionedLL=partition(node);
		LinkedListNode<T> left=partitionedLL.get(0);
		LinkedListNode<T> right = null;
		if(partitionedLL.size()==2) {
		right=partitionedLL.get(1);
		}
		LinkedListNode<T> sortedLeftLL=mergeSort(left);
		LinkedListNode<T> sortedRightLL=mergeSort(right);
		LinkedListNode<T> sortedLL=merge(sortedLeftLL, sortedRightLL);
		return sortedLL;
		
	}
	
}
