package queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class ReversingWithRecursion {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
		IntStream.rangeClosed(1, 4).forEach(number -> queue.add(number));
		System.out.println(queue);
		reverse(queue);
		System.out.println(queue);
	}
	
	private static void reverse(Queue<Integer> queue) {
		if(queue != null && !queue.isEmpty()) {
			int number = queue.poll();
			reverse(queue);
			queue.add(number);
		}
	}

}
