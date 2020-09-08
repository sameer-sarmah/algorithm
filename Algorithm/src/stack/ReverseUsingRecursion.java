package stack;

import java.util.Stack;
import java.util.stream.IntStream;

public class ReverseUsingRecursion {

	public static void main(String[] args) {
		 Stack<Integer> stack = new Stack<>(); 
		 IntStream.rangeClosed(1, 4).forEach(number -> stack.push(number) );
		 System.out.println(stack);
		 reverse(stack);
		 System.out.println(stack);
	}

	private static void reverse(Stack<Integer> stack) {
		if(stack != null && !stack.isEmpty()) {
			int numberAtTop = stack.pop();
			reverse(stack);
			insert(stack,numberAtTop);
		}
	}
	
	private static void insert(Stack<Integer> stack,int number) 
    { 
  
        if(stack.isEmpty()) {
            stack.push(number); 
        }
        else { 
            int numberAtTop = stack.pop(); 
            insert(stack,number); 
            stack.push(numberAtTop); 
        } 
    } 
}
