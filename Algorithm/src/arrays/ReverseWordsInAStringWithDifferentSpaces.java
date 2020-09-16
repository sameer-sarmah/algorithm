package arrays;

import java.util.Stack;

public class ReverseWordsInAStringWithDifferentSpaces {
	public static void main(String[] args) {
		  String input = " I  have too many files to compile ";
		  withStack(input);
	}
	
	private static void withStack(String input) {
		  StringBuilder builder = new StringBuilder();
		  Stack<String> stack =new Stack<String>();
		  for(int index = input.length() -1;index >= 0;index--) {
			char ch =  input.charAt(index);
			String segment =Character.valueOf(ch).toString();
			if(segment.equals(" ")) {
				if(!stack.isEmpty()) {
					  while(!stack.isEmpty()){
						  builder.append(stack.pop());
					  }
				}
				builder.append(" ");
			}
			else {
				stack.push(segment);
			}
		  }
		  
		  System.out.println(builder.toString());
	}
	
}
