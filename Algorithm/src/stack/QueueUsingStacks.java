package stack;

import java.util.Stack;
import java.util.stream.IntStream;

public class QueueUsingStacks {

	public static void main(String[] args) {
		QueueUsingStacks queue =new QueueUsingStacks();
		IntStream.rangeClosed(1, 6).forEach(number -> queue.enqueue(number));
		while(!queue.isEmpty()) {
			System.out.println(queue.dequeue());
		}

	}
	
	private Stack<Integer> stackOne = new Stack<>();
	private Stack<Integer> stackTwo = new Stack<>();
	
	public void enqueue(int number) {
		stackOne.push(number);
	}
	
	public int dequeue() {
		while(!stackOne.isEmpty()) {
			stackTwo.push(stackOne.pop());
		}
		int item = stackTwo.pop();
		Stack<Integer> temp = stackOne;
		while(!stackTwo.isEmpty()) {
			stackOne.push(stackTwo.pop());
		}
		return item;
	}
	
	public boolean isEmpty() {
		return stackOne.isEmpty();
	}

	public int size() {
		return stackOne.size();
	}
}
