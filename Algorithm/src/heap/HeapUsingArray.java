package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 for any index its children are at 2*index+1 and 2*index+2
 parent= (index-1)/2
 This example is of a max heap
 * */
public class HeapUsingArray {

	private static void heapifyTopDown(int currentIndex, List<Integer> list) {
		int leftChildIndex = 2 * currentIndex + 1;
		int rightChildIndex = 2 * currentIndex + 2;
		int leftChild = -1;
		if(leftChildIndex <= list.size() - 1) {
			leftChild =  list.get(leftChildIndex); 
		}
		int rightChild = -1;
		if(rightChildIndex <= list.size() - 1) {
			rightChild =  list.get(rightChildIndex); 
		}
		
		System.out.println(String.format("current element :%d ,left child :%d ,right child: %d",list.get(currentIndex), leftChild,rightChild));
		if (list.size() - 1 >= leftChildIndex && list.get(currentIndex) < list.get(leftChildIndex)) {
			System.out.println(String.format("left child %d is greater than parent %d ,thus swapping", list.get(leftChildIndex),list.get(currentIndex)));
			Collections.swap(list, currentIndex, leftChildIndex);
			heapifyTopDown(leftChildIndex, list);
		}
		if (list.size() - 1 >= rightChildIndex && list.get(currentIndex) < list.get(rightChildIndex)) {
			System.out.println(String.format("right child %d is greater than parent %d ,thus swapping", list.get(rightChildIndex),list.get(currentIndex)));
			Collections.swap(list, currentIndex, rightChildIndex);
			heapifyTopDown(rightChildIndex, list);
		}

	}

	private static void heapifyBottomUp(int currentIndex, List<Integer> list) {
		int parentIndex = (currentIndex - 1)/2;
		int parent = -1;
		if(parentIndex >= 0) {
			parent =  list.get(parentIndex); 
		}
		
		System.out.println(String.format("current element :%d ,parent :%d",list.get(currentIndex), parent));
		if(parentIndex >= 0 && list.get(parentIndex) < list.get(currentIndex)) {
			System.out.println(String.format("child %d is greater than parent %d ,thus swapping", list.get(currentIndex),list.get(parentIndex)));
			Collections.swap(list, currentIndex, parentIndex);
			heapifyBottomUp(currentIndex, list);
		}

	}
	
	private static void heapify(List<Integer> list) {
//		for (int index = list.size() / 2; index >= 0; index--) {
//			heapifyTopDown(index, list);
//		}
		for (int index = list.size()-1; index >= 0; index--) {
			heapifyBottomUp(index, list);
		}

	}

	/*
	 * bfs of the heap array
	 */
	private static void printHeapBFS(List<Integer> heap, int currentIndex, Queue<Integer> queue) {
		if (heap.size() - 1 >= currentIndex) {
			System.out.println(heap.get(currentIndex));
		}

		int leftChildIndex = 2 * currentIndex + 1, rightChildIndex = 2 * currentIndex + 2;
		if (heap.size() - 1 >= leftChildIndex) {
			queue.add(leftChildIndex);
		}
		if (heap.size() - 1 >= rightChildIndex) {
			queue.add(rightChildIndex);
		}
		if (!queue.isEmpty()) {
			printHeapBFS(heap, queue.poll(), queue);
		}

	}

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(13, 14, 15, 18, 11, 12, 17, 16);
		heapify(numbers);
		Queue<Integer> queue = new ConcurrentLinkedQueue<>();
		printHeapBFS(numbers, 0, queue);
	}

}
