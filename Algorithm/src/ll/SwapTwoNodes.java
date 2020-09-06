package ll;

import java.util.Arrays;

//swap 2 nodes not just data
public class SwapTwoNodes {

	public static void main(String[] args) {
		LinkedListNode<Integer> list = LinkedListUtil.createLL(Arrays.asList(1,2,3,4,5,6));
		LinkedListNode<Integer> first = LinkedListUtil.findNode(list, 3);
		LinkedListNode<Integer> second = LinkedListUtil.findNode(list, 5);
		swap(first,second);
		LinkedListUtil.iterate(list);
	}

	private static void swapAdjucentNodes(LinkedListNode<Integer> first, LinkedListNode<Integer> second) {
		// swap 3,4
		// one -> 3,two -> 4
		if (first.getNext() == second) {
			LinkedListNode<Integer> previousOfFirst = first.getPrevious();// 2
			LinkedListNode<Integer> nextOfSecond = second.getNext();// 5

			first.setNext(nextOfSecond);// 3->5
			nextOfSecond.setPrevious(first);// 3 <- 5

			previousOfFirst.setNext(second);// 2 -> 4
			second.setPrevious(previousOfFirst);// 2 <- 4

			first.setPrevious(second);// 4 <- 3
			second.setNext(first);// 4 -> 3
		}
	}

	private static void swap(LinkedListNode<Integer> first, LinkedListNode<Integer> second) {
		if (first.getNext() == second) {
			swapAdjucentNodes(first, second);
		} else if (second.getNext() == first) {
			LinkedListNode<Integer> temp = first;
			first = second;
			second = first;
			swapAdjucentNodes(first, second);
		} else {
			// 1 2 3 4 5 6
			// swap 3,5
			LinkedListNode<Integer> previousOfFirst = first.getPrevious();// 2
			LinkedListNode<Integer> nextOfFirst = first.getNext();// 4

			LinkedListNode<Integer> previousOfSecond = second.getPrevious();// 4
			LinkedListNode<Integer> nextOfSecond = second.getNext();// 6

			first.setNext(nextOfSecond); // 3 -> 6
			nextOfSecond.setPrevious(first);// 3 <- 6

			first.setPrevious(previousOfSecond);// 4 <- 3
			previousOfSecond.setNext(first);// 4 -> 3

			second.setNext(nextOfFirst); // 5 -> 4
			nextOfFirst.setPrevious(second);// 5 <- 4

			second.setPrevious(previousOfFirst);// 2 <- 5
			previousOfFirst.setNext(second);// 2 -> 5

		}
	}

}
