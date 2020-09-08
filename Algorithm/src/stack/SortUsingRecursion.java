package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SortUsingRecursion {

	public static void main(String[] args) {
		List<Integer> list =Arrays.asList(34, 3, 31, 98, 92, 23);
		Stack<Integer> stack = new Stack<>();
		list.stream().forEach(number -> stack.push(number));
		sort(stack);
		System.out.println(stack);
	}
	
	private static void sort(Stack<Integer> stack) {
		if(stack != null && !stack.isEmpty()) {
			int number = stack.pop();
			sort(stack);
			insert(stack, number);
		}
	}
	
	private static void insert(Stack<Integer> stack,int number) {
		if(stack != null) {
			if(stack.isEmpty() || stack.peek() < number) {
				stack.push(number);
				System.out.println(number+" inserted in the stack");
			}
			else {
				int greaterNumber = stack.pop();
				System.out.println(number+" popped from the stack");
				insert(stack, number);
				stack.add(greaterNumber);
			}
		}
	}

}
