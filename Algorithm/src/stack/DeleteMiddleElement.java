package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DeleteMiddleElement {

	public static void main(String[] args) {
		List<Integer> list =Arrays.asList(34, 3, 31, 98, 92, 23);
		Stack<Integer> stack = new Stack<>();
		list.stream().forEach(number -> stack.push(number));
		int indexToDelete = stack.size()/2;
		delete(stack, indexToDelete);
		System.out.println(stack);
	}

	private static void delete(Stack<Integer> stack,int indexToDelete) {
		if(stack.size() == indexToDelete) {
			stack.pop();
		}
		else {
			int poppedNumber = stack.pop();
			delete(stack, indexToDelete);
			stack.push(poppedNumber);
		}
	}
}
