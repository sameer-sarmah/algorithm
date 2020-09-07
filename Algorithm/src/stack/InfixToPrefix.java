package stack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InfixToPrefix {

	public static void main(String[] args) {
		String infixExpr= "( 12 - 1 ) * 52 - 473 / ( 4 + 2 * 3 )";
		StringBuilder builder = new StringBuilder();
		List<String> tokens =Arrays.stream(infixExpr.split(" ")).filter(str -> !str.trim().isEmpty()).collect(Collectors.toList());
		System.out.println(builder.reverse().toString());
	}

}
