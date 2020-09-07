package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
/*
expected ( 12 - 1 ) * 52 - 473 / ( 4 + 2 * 3 )

postfix 12 1 - 52 * 473 4 2 3 * + / -


stack ["12" "1"] 
operator -

stack ["( 12 - 1 )","52"] 
operator *


stack ["( ( 12 -1 ) * 52 )","473", "4", "2", "3"]
operator *

stack ["( ( 12 -1 ) * 52 )","473", "4"," ( 2 * 3 ) "]
operator +

stack ["( ( 12 -1 ) * 52 )","473", " ( 4 + ( 2 * 3 ) ) "]
operator /

stack ["( ( 12 -1 ) * 52 )"," 423 / ( 4 + ( 2 * 3 ) ) "]
operator -


[ " ( ( ( 12 -1 ) * 52 ) / 423 / ( 4 + ( 2 * 3 ) ) "]
*/
public class PostfixToInfix {

	public static void main(String[] args) {
		String postfix= "12 1 - 52 * 473 4 2 3 * + / -";

		System.out.println(convertPostfixToInfix(postfix));
		
	}

	private static String SPACE = " ";
	private static Stack<String> stack = new Stack<>();
	
	private static boolean isOperator(String token) {
		return token.equals(Operator.ADD.getSymbol()) || token.equals(Operator.SUB.getSymbol()) 
				|| token.equals(Operator.MUL.getSymbol()) || token.equals(Operator.DIV.getSymbol());
	}
	
	private static String convertPostfixToInfix(String postfix) {

		List<String> tokens =Arrays.stream(postfix.split(" ")).filter(str -> !str.trim().isEmpty()).collect(Collectors.toList());
		for(String token : tokens) {
			int number = Integer.MIN_VALUE;
			try {
				number = Integer.parseInt(token);
			}
			catch(NumberFormatException e) {
				//System.out.println();
			}
			if(number > Integer.MIN_VALUE) {
				stack.push(String.valueOf(number));
				System.out.println(String.format("Added %d to stack ",number));
			}
			else {
				if(isOperator(token)) {
					if(!stack.isEmpty()) {
						String operandRight = stack.pop();
						String operandLeft = stack.pop();
						String expression = String.format("( %s %s %s )",operandLeft,token,operandRight);
						stack.push(expression);
						System.out.println(String.format("Added %s to stack ",expression));
					}

					
				}

			}
		}

		return stack.pop();
	} 
}
