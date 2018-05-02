package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 for any index its children are at 2*index+1 and 2*index+2
 parent= (index-1)/2
 * */
public class HeapUsingArray {

	private static void heapifyTopDown(int currentIndex, List<Integer> list) {
		int leftChildIndex = 2 * currentIndex + 1;
		int rightChildIndex = 2 * currentIndex + 2;
		if (list.size() - 1 >= leftChildIndex && list.get(currentIndex) < list.get(leftChildIndex)) {
			Collections.swap(list, currentIndex, leftChildIndex);
			heapifyTopDown(leftChildIndex, list);
		}
		if (list.size() - 1 >= rightChildIndex && list.get(currentIndex) < list.get(rightChildIndex)) {
			Collections.swap(list, currentIndex, rightChildIndex);
			heapifyTopDown(rightChildIndex, list);
		}

	}

	private static void heapify(List<Integer> list) {
		for (int index = list.size() / 2; index >= 0; index--) {
			heapifyTopDown(index, list);
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
