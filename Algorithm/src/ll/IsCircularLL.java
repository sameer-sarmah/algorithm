package ll;

import java.util.Arrays;

public class IsCircularLL {

	public static void main(String[] args) {
		LinkedListNode<Integer> list = LinkedListUtil.createCircularLL(Arrays.asList(1,2,3,4,5,6));
		System.out.println(isCircular(list));
	}
	
	private static boolean isCircular(LinkedListNode<Integer> list) {
		LinkedListNode<Integer> current = list;
		LinkedListNode<Integer> first = list;
		while(current != null) {
			if(current == first) {
				return true;
			}
			current = current.getNext();
		}
		return false;
		
	}

}
