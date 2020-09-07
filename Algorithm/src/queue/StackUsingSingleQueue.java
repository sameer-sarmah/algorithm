package queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class StackUsingSingleQueue {

	public static void main(String[] args) {
		StackUsingSingleQueue stack = new StackUsingSingleQueue();
		IntStream.rangeClosed(1, 6).forEach(number -> stack.push(number));
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	private Queue<Integer> queue = new LinkedBlockingQueue<>();
	
	public void push(int number) {
		queue.add(number);
	}
	
	public int pop() {
		int originalSize = queue.size();
		int popped = 1;
		while( popped < originalSize ) {
			queue.add(queue.poll());
			++popped;
		}
		int poppedItem = queue.poll();
		Queue<Integer> temp = queue;
		return poppedItem;
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
