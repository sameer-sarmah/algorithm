package mergesort.linkedlist;
import java.util.Random;
import java.util.stream.IntStream;

public class SortLinkedList {

	public static void main(String[] args) {
		LinkedListNode<Integer> ll = null;
		LinkedListNode<Integer> head = null;
		Random r = new Random();
		int[] values = IntStream.range(1, 6).map((val) -> {
			return val * r.nextInt(10);
		}).toArray();
		for (int val : values) {
			LinkedListNode<Integer> node = new LinkedListNode<>(val);
			if (ll == null) {
				ll = node;
				head = node;
			} else {
				ll.setNext(node);
				ll = node;
			}
		}
		LinkedListNode<Integer> sortedLL=LinkedListUtil.mergeSort(head);
		LinkedListUtil.print(sortedLL);

	}

};