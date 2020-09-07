package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
/*
 * ( 12 - 1 ) * 52 - 473 / ( 4 + 2 * 3 )
operator:(            	operand:12
operator:( -          	operand:12 1
operator:         		operand:11
operator: *       		operand:11 52
operator:         		operand:572
operator: -       		operand:572
operator: -       		operand:572 473
operator: - /     		operand:572 473
operator: - / (   		operand:572 473
operator: - / (   		operand:572 473 4
operator: - / ( + 		operand:572 473 4
operator: - / ( + 		operand:572 473 4 2
operator: - / ( + *  	operand-572 473 4 2
operator: - / ( + *  	operand-572 473 4 2 3
operator: - / ( + 		operand-572 473 4 6
operator: - / (   		operand:572 473 10
operator: - /     		operand:572 473 10
operator: -       		operand:572 47.3
operator:         		operand:524.7 
*/

public class InfixEvaluation {

	private static Stack<Double> operands = new Stack<>(); 
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
	
	private static double evaluate(String operator,double operandOne,double operandTwo ) {
		if(operator.equals(ADD)) {
			return operandOne + operandTwo;
		}
		else if(operator.equals(SUB)) {
			return operandOne - operandTwo;
		}
		else if(operator.equals(MUL)) {
			return operandOne * operandTwo;
		}
		else if(operator.equals(DIV)) {
			return operandOne / operandTwo;
		}
		return 0;
	}
	
	private static void evaluate() {
		double operandRight = operands.pop();
		double operandLeft = operands.pop();
		String operator = operators.pop();
		double result = evaluate(operator,operandLeft,operandRight);
		System.out.println(String.format("Added %.2f to operand stack ",result));
		operands.push(result);
	}
	
	public static void main(String[] args) {
		String infixExpr = "( 12 - 1 ) * 52 - 473 / ( 4 + 2 * 3 )";
		List<String> tokens =Arrays.stream(infixExpr.split(" ")).filter(str -> !str.trim().isEmpty()).collect(Collectors.toList());
		for(String token : tokens) {
			double number = Integer.MIN_VALUE;
			try {
				number = Integer.parseInt(token);
			}
			catch(NumberFormatException e) {
				//System.out.println();
			}
			if(number > Integer.MIN_VALUE) {
				operands.push(number);
				System.out.println(String.format("Added %.2f to operand stack ",number));
			}
			else {
				if(isOperator(token)) {
					//the top of stack is a operator
					if(!operators.isEmpty() && isOperator(operators.peek())) {
						//operator in stack has higher precedence than current token
						while(!operators.isEmpty() && isOperator(operators.peek()) && (operatorPrecedence.get(operators.peek()) < operatorPrecedence.get(token))) {
							evaluate();
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
						evaluate();
					}
					if(!operators.isEmpty() && operators.peek().equals(LEFT_PARENTHESIS)) {
						operators.pop();
					}
				}
			}
			
		}
		while(!operators.isEmpty() && isOperator(operators.peek()) ) {
			evaluate();
		}
		
		System.out.println(operands.pop());
	}

}
