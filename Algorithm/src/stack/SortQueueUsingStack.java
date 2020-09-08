package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class SortQueueUsingStack {

	public static void main(String[] args) {
		List<Integer> list =Arrays.asList(34, 3, 31, 98, 92, 23).stream().collect(Collectors.toList());
		Queue<Integer> queue = new LinkedBlockingQueue<>();
		list.stream().forEach(number -> queue.add(number));
		Stack<Integer> auxillaryStack = new Stack<>();
		sort(queue, auxillaryStack);
		System.out.println(auxillaryStack);
	}
	
	private static void sort(Queue<Integer> queue,Stack<Integer> auxillaryStack) {
		if(!queue.isEmpty()) {
			int numberAtTop = queue.poll();
			if(auxillaryStack.isEmpty()  || auxillaryStack.peek() < numberAtTop) {
				auxillaryStack.add(numberAtTop);
			}
			else if(auxillaryStack.peek() > numberAtTop) {
				int greaterNumber = auxillaryStack.pop();
				queue.add(greaterNumber);
				queue.add(numberAtTop);
				reverse(queue);
			}
			sort(queue, auxillaryStack);	
		}

	}
	
	private static void reverse(Queue<Integer> queue) {
		if(!queue.isEmpty()) {
			int number = queue.poll();
			reverse(queue);
			queue.add(number);
		}
	}
}
