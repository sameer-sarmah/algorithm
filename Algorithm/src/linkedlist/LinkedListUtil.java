package linkedlist;

import hashtable.LinkedList;

public class LinkedListUtil {
	public static LinkedList<Integer> createLinkedList(int number){
		LinkedList<Integer> list = null;
		for(int index=1;index<=number;index++) {
			if(list == null) {
				list=new LinkedList<>();
				list.setValue(index);
			}
			else {
				LinkedList<Integer> newNode=new LinkedList<>();
				newNode.setValue(index);
				list=list.getTailNode();
				list.setNext(newNode);
				newNode.setPrev(list);
			}
		}
		return list.getHeadNode();
	}

	public static void traverse(LinkedList<Integer> list) {
		while(list != null) {
			System.out.printf(list.getValue()+" ");
			list=list.getNext();
		}
	}
	
	public static void traverseReverse(LinkedList<Integer> list) {
		list=list.getTailNode();
		while(list != null) {
			System.out.printf(list.getValue()+" ");
			list=list.getPrev();
		}
	}
}
