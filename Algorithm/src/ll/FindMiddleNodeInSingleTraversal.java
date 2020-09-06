package ll;

import java.util.Arrays;

public class FindMiddleNodeInSingleTraversal {

	public static void main(String[] args) {
		LinkedListNode<Integer> list = LinkedListUtil.createLL(Arrays.asList(1,2,3,4,5,6));
		System.out.println(findMiddle(list));
	}
	
	public static LinkedListNode<Integer> findMiddle(LinkedListNode<Integer> list){
		LinkedListNode<Integer> middle= list;
		LinkedListNode<Integer> current= list;
		while(current != null && current.getNext() != null && current.getNext().getNext() != null) {	
		middle = current;	
		current = current.getNext().getNext();	
		}
		return middle;
		
	}

}
