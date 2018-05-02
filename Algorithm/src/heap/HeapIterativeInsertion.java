package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 Build a max heap by iteratively adding numbers
 * */
public class HeapIterativeInsertion {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(13, 14, 15, 18, 11, 12, 17, 16);
		List<Integer> heap = new ArrayList<>();
		numbers.stream().forEach((number) -> {
			insertIteratively(heap, number);
		});
		System.out.println(heap);
		Queue<Integer> queue = new ConcurrentLinkedQueue<>();
		printHeapBFS(heap, 0, queue);
		System.out.println("deleting root");
		delete(heap, 0);
		printHeapBFS(heap, 0, queue);

	}

	public static List<Integer> insertIteratively(List<Integer> heap, int number) {
		int insertedIndex = heap.size();
		int parentIndex = (insertedIndex - 1) / 2;
		heap.add(number);
		if (heap.get(parentIndex) < number) {
			return heapifyBottomToUp(heap, insertedIndex);
		} else {
			return heap;
		}

	}

	/*
	 * recursively go up till the root is reached and heap conditions are satisfied
	 */
	private static List<Integer> heapifyBottomToUp(List<Integer> heap, int childIndex) {
		int parentIndex = (childIndex - 1) / 2;
		Collections.swap(heap, parentIndex, childIndex);
		childIndex = parentIndex;
		parentIndex = (parentIndex - 1) / 2;
		if (parentIndex >= 0 && heap.get(parentIndex) < heap.get(childIndex)) {
			return heapifyBottomToUp(heap, childIndex);
		} else {
			return heap;
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

	public static void delete(List<Integer> heap, int index) {
		heap.remove(index);
		int lastItem = heap.remove(heap.size() - 1);
		heap.add(0, lastItem);
		heapifyTopDown(0, heap);
	}

	private static void heapifyTopDown(int currentIndex, List<Integer> list) {
		int leftChildIndex = 2 * currentIndex + 1;
		int rightChildIndex = 2 * currentIndex + 2;
		Integer leftChild = null, rightChild = null;
		if (list.size() - 1 >= leftChildIndex) {
			leftChild = list.get(leftChildIndex);
		}
		if (list.size() - 1 >= rightChildIndex) {
			rightChild = list.get(rightChildIndex);
		}

		// both children are present
		if (leftChild != null && rightChild != null) {
			if (leftChild > rightChild) {
				if (list.get(currentIndex) < list.get(leftChildIndex)) {
					Collections.swap(list, currentIndex, leftChildIndex);
					heapifyTopDown(leftChildIndex, list);
				}
			} else {
				if (list.get(currentIndex) < list.get(rightChildIndex)) {
					Collections.swap(list, currentIndex, rightChildIndex);
					heapifyTopDown(rightChildIndex, list);
				}
			}

		}
		// only left child is present
		else if (leftChild != null && rightChild == null) {
			if (list.get(currentIndex) < list.get(leftChildIndex)) {
				Collections.swap(list, currentIndex, leftChildIndex);
				heapifyTopDown(leftChildIndex, list);
			}

		}
		// only right child is present
		else if (leftChild == null && rightChild != null) {
			if (list.get(currentIndex) < list.get(rightChildIndex)) {
				Collections.swap(list, currentIndex, rightChildIndex);
				heapifyTopDown(rightChildIndex, list);
			}
		}

	}

}
