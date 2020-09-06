package ll;

import java.util.Arrays;
import java.util.Stack;

public class ReverseLL {

	public static void main(String[] args) {
		LinkedListNode<Integer> list = LinkedListUtil.createLL(Arrays.asList(1,2,3,4,5,6));
		LinkedListUtil.iterate(reverse(list));
	}
	
	private static LinkedListNode<Integer> reverse(LinkedListNode<Integer> list){
		LinkedListNode<Integer> reversedLL = null;
		LinkedListNode<Integer> reversedLLCurrent = null;
		Stack<LinkedListNode<Integer> > stack = new Stack<>();
		LinkedListNode<Integer> current=list;
		while(current != null) {
			LinkedListNode<Integer> node = new LinkedListNode<Integer>();
			node.setValue(current.getValue());
			stack.add(node);
			current = current.getNext();
		}
		
		while(!stack.isEmpty()) {
			LinkedListNode<Integer> node = stack.pop();
			if(reversedLL == null) {
				reversedLL = new LinkedListNode<Integer>();
				reversedLL.setValue(node.getValue());
				reversedLLCurrent = reversedLL;
			}
			else {
				node.setPrevious(reversedLL);
				reversedLLCurrent.setNext(node);
				reversedLLCurrent = node;
			}
		}
		return reversedLL;
	}

}
