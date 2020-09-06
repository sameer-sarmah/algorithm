package ll;

import java.util.List;

public class LinkedListUtil {
	
	public static LinkedListNode<Integer> createLL(List<Integer> numbers){
		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> current = null;
		for(int number :numbers) {
			if(head == null) {
				head = new LinkedListNode<>();
				head.setValue(number);
				current = head;
			}
			else {
				LinkedListNode<Integer> node = new 	LinkedListNode<>();
				node.setValue(number);
				current.setNext(node);
				node.setPrevious(current);
				current= node;
			}
		}
		return head;
	}
	
	public static LinkedListNode<Integer> createCircularLL(List<Integer> numbers){
		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> current = null;
		for(int number :numbers) {
			if(head == null) {
				head = new LinkedListNode<>();
				head.setValue(number);
				current = head;
			}
			else {
				LinkedListNode<Integer> node = new 	LinkedListNode<>();
				node.setValue(number);
				current.setNext(node);
				node.setPrevious(current);
				current= node;
			}
		}
		current.setNext(head);
		head.setPrevious(current);
		return head;
	}
	
	public static LinkedListNode<Integer> findNode(LinkedListNode<Integer> head,int number){
		LinkedListNode<Integer> current = head;
		while(current!= null) {
			if(current.getValue() == number) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}
	
	public static LinkedListNode<Integer> iterate(LinkedListNode<Integer> head){
		LinkedListNode<Integer> current = head;
		while(current!= null) {
			System.out.println(current.getValue());
			current = current.getNext();
		}
		return null;
	}
}
