package linkedlist;

import hashtable.LinkedList;

public class MiddleElement {
	public static void main(String[] args) {
		LinkedList<Integer> list = LinkedListUtil.createLinkedList(5);
		LinkedListUtil.traverse(list.getHeadNode());
		int element = findMiddleElement(list);
		System.out.println("");
		System.out.println("middle element of the linked list is " + element);
	}

	private static int findMiddleElement(LinkedList<Integer> list) {
		LinkedList<Integer> head = list.getHeadNode();
		list = head;
		int counter = 0;
		LinkedList<Integer> middleElement = head;
		while (list.getNext() != null) {
			list = list.getNext();
			counter++;
			if (counter % 2 == 0) {
				middleElement = middleElement.getNext();
			}
		}
		return middleElement.getValue();
	}

}
