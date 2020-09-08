package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SortUsingStack {

	public static void main(String[] args) {
		List<Integer> list =Arrays.asList(34, 3, 31, 98, 92, 23);
		Stack<Integer> stack = new Stack<>();
		list.stream().forEach(number -> stack.push(number));
		Stack<Integer> auxillaryStack = new Stack<>();
		sort(stack, auxillaryStack);
		System.out.println(auxillaryStack);
	}
	
	private static void sort(Stack<Integer> stack,Stack<Integer> auxillaryStack) {
		if(!stack.isEmpty()) {
			int numberAtTop = stack.pop();
			if(auxillaryStack.isEmpty()  || auxillaryStack.peek() < numberAtTop) {
				auxillaryStack.add(numberAtTop);
			}
			else if(auxillaryStack.peek() > numberAtTop) {
				int greaterNumber = auxillaryStack.pop();
				stack.push(greaterNumber);
				stack.push(numberAtTop);
			}
			sort(stack, auxillaryStack);	
		}

	}

}
