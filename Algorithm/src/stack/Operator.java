package stack;

public enum Operator {
	
	ADD("+"),MUL("*"),DIV("/"),SUB("-");
	private String symbol;
	private Operator(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	
	
	
}
