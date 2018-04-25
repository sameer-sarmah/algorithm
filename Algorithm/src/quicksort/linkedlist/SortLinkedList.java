package quicksort.linkedlist;
import java.util.Arrays;
import java.util.List;
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
		System.out.println(Arrays.toString(values));
		LinkedListNode<Integer> sortedLL=LinkedListUtil.quickSort(head);
		LinkedListUtil.print(sortedLL);
		
//		Integer pivot=LinkedListUtil.getElementAtIndex(head, 2);
//		System.out.println("pivot is "+pivot);
//		head=LinkedListUtil.removeElementAtIndex(head, 2);
//		System.out.println("After deletion of pivot ");
//		LinkedListUtil.print(head);
//		List<LinkedListNode<Integer>> partitionedLL=LinkedListUtil.partition(head, pivot);
//		System.out.println("LL of elements lesser than pivot ");
//		LinkedListUtil.print(partitionedLL.get(0));
//		System.out.println("LL of elements greater than pivot ");
//		LinkedListUtil.print(partitionedLL.get(1));
//		System.out.println("sorted LL");
//		LinkedListUtil.print(LinkedListUtil.merge(partitionedLL.get(0), partitionedLL.get(1), pivot));
		
	

	}

};