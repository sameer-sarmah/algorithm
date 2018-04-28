package arrays;

import java.util.Stack;

public class SortNumbersInAStack {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(34);
		stack.push(3);
		stack.push(31);
		stack.push(98);
		stack.push(92);
		stack.push(23);
		Stack<Integer> tempStack = new Stack<>();

		int temp;
		if (tempStack.isEmpty()) {
			tempStack.push(stack.pop());
		}
		while (!stack.isEmpty()) {
			temp = stack.pop();
			if (tempStack.peek() < temp) {
				tempStack.push(temp);
			} else {
				while (!tempStack.isEmpty() && tempStack.peek() > temp) {
					stack.push(tempStack.pop());
				}
				tempStack.push(temp);
			}

		}
		System.out.println(tempStack);
	}

}
