package queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class StackUsingQueues {

	public static void main(String[] args) {
		StackUsingQueues stack = new StackUsingQueues();
		IntStream.rangeClosed(1, 6).forEach(number -> stack.push(number));
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	private Queue<Integer> queueOne = new LinkedBlockingQueue<>();
	private Queue<Integer> queueTwo = new LinkedBlockingQueue<>();
	
	public void push(int number) {
		queueOne.add(number);
	}
	
	public int pop() {
		int originalSize = queueOne.size();
		int popped = 1;
		while( popped < originalSize ) {
			queueTwo.add(queueOne.poll());
			++popped;
		}
		int poppedItem = queueOne.poll();
		Queue<Integer> temp = queueOne;
		queueOne = queueTwo;
		queueTwo = temp;
		return poppedItem;
	}
	
	public int size() {
		return queueOne.size();
	}
	
	public boolean isEmpty() {
		return queueOne.isEmpty();
	}
}
