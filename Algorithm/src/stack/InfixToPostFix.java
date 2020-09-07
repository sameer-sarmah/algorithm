package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/*
( 12 - 1 ) * 52 - 473 / ( 4 + 2 * 3 )
operator:(            	postfix:12
operator:( -          	postfix:12 1
operator:         		postfix:12 1 -
operator: *       		postfix:12 1 - 52
operator:         		postfix:12 1 - 52 *
operator: -       		postfix:12 1 - 52 *
operator: -       		postfix:12 1 - 52 * 473
operator: - /     		postfix:12 1 - 52 * 473
operator: - / (   		postfix:12 1 - 52 * 473
operator: - / (   		postfix:12 1 - 52 * 473 4
operator: - / ( + 		postfix:12 1 - 52 * 473 4 
operator: - / ( + 		postfix:12 1 - 52 * 473 4 2
operator: - / ( + *  	postfix:12 1 - 52 * 473 4 2
operator: - / ( + *  	postfix:12 1 - 52 * 473 4 2 3
operator: - / ( + 		postfix:12 1 - 52 * 473 4 2 3 * 
operator: - / (   		postfix:12 1 - 52 * 473 4 2 3 * +
operator: - /     		postfix:12 1 - 52 * 473 4 2 3 * +
operator: -       		postfix:12 1 - 52 * 473 4 2 3 * + /
operator:         		postfix:12 1 - 52 * 473 4 2 3 * + / -
*/
public class InfixToPostFix {

	public static void main(String[] args) {
		String infixExpr= "( 12 - 1 ) * 52 - 473 / ( 4 + 2 * 3 )";
		StringBuilder builder = new StringBuilder();
		List<String> tokens =Arrays.stream(infixExpr.split(" ")).filter(str -> !str.trim().isEmpty()).collect(Collectors.toList());
		String postfix = infixToPostfix(tokens, builder);
		System.out.println(postfix);
	}

	private static Stack<String> operators = new Stack<>();
	private static final String ADD = "+"; 
	private static final String SUB = "-"; 
	private static final String MUL = "*"; 
	private static final String DIV = "/";
	private static final String LEFT_PARENTHESIS = "(";
	private static final String RIGHT_PARENTHESIS = ")";
	private static Map<String,Integer> operatorPrecedence = new HashMap<>();
	
	static {
		operatorPrecedence.put(MUL, 1);
		operatorPrecedence.put(DIV, 2);
		operatorPrecedence.put(ADD, 3);
		operatorPrecedence.put(SUB, 3);
	}
	
	private static boolean isOperator(String token) {
		return token.equals(ADD) || token.equals(SUB) || token.equals(MUL) || token.equals(DIV);
	}
	

	
	public static String infixToPostfix(List<String> tokens,StringBuilder builder) {
		for(String token : tokens ) {
			int number = Integer.MIN_VALUE;
			try {
				number = Integer.parseInt(token);
			}
			catch(NumberFormatException e) {
				//System.out.println();
			}
			if(number > Integer.MIN_VALUE) {
				builder.append(number);
				builder.append(" ");
				System.out.println(String.format("Added %d to string builder ",number));
			}
			else {
				if(isOperator(token)) {
					//the top of stack is a operator
					if(!operators.isEmpty() && isOperator(operators.peek())) {
						//operator in stack has higher precedence than current token
						while(!operators.isEmpty() && isOperator(operators.peek()) && (operatorPrecedence.get(operators.peek()) < operatorPrecedence.get(token))) {
							builder.append(operators.pop());
							builder.append(" ");
						}
					}
					System.out.println(String.format("Added %s to operator stack ",token));
					operators.push(token);
				}
				else if(token.equals(LEFT_PARENTHESIS)){
					System.out.println(String.format("Added %s to operator stack ",token));
					operators.push(token);
				}
				else if(token.equals(RIGHT_PARENTHESIS)){
					while(!operators.isEmpty() && isOperator(operators.peek()) ) {
						builder.append(operators.pop());
						builder.append(" ");
					}
					if(!operators.isEmpty() && operators.peek().equals(LEFT_PARENTHESIS)) {
						operators.pop();
					}
				}
			}
		}
		while(!operators.isEmpty() && isOperator(operators.peek()) ) {
			builder.append(operators.pop());
			builder.append(" ");
		}
		return builder.toString();
	}
}
