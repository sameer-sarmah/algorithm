package queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class ReversingFirstNElements {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
		Queue<Integer> temp = new LinkedBlockingQueue<Integer>();
		IntStream.rangeClosed(1, 6).forEach(number -> queue.add(number));
		System.out.println(queue);
		reverseNElements(queue,queue.size());
		
		int element = 0;
		while(element <= queue.size()/2) {
			temp.add(queue.poll());
			element++;
		}
		while(!temp.isEmpty()) {
			queue.add(temp.poll());
		}
		System.out.println(queue);
	}
	
	private static void reverseNElements(Queue<Integer> queue,int originalSize) {
		if(queue != null && !queue.isEmpty()) {
			if(queue.size() != originalSize/2) {
				int number = queue.poll();
				reverseNElements(queue,originalSize);
				queue.add(number);
			}

		}
	}
}
