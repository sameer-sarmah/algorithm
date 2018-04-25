package algorithm.recursion;

import java.util.Stack;

public class TowerOfHanoi {
	private int N;
	private Stack<Integer> s1 = new Stack<>();// starting stack
	private Stack<Integer> s2 = new Stack<>();// destination stack
	private Stack<Integer> s3 = new Stack<>();// intermediate stack

	public TowerOfHanoi(int n) {
		super();
		N = n;
		if (N < 1)
			throw new IllegalArgumentException();
		for (int i = 1; i <= N; i++) {
			s1.push(i);
		}
	}

	private void transferDiscs(Stack<Integer> source,Stack<Integer> target,Stack<Integer> intermediate,int number) {
		if (number == 3) {
			target.push(source.pop());
			intermediate.push(source.pop());
			intermediate.push(target.pop());
			target.push(source.pop());
			source.push(intermediate.pop());
			target.push(intermediate.pop());
			target.push(source.pop());
		} else if (number == 2) {
			intermediate.push(source.pop());
			target.push(source.pop());
			target.push(intermediate.pop());
		} else if (number == 1) {
			target.push(source.pop());
		}
	}
	
	public void transferDiscs(){
		if(N<=3){
			transferDiscs(s1,s2,s3,N);
		}else if(N==4){
//			transferDiscs(s1,s3,s2,3);
//			s2.push(s1.pop());
//			transferDiscs(s3,s2,s1,3);	
			transfer4Discs(s1,s3,s2);
		}else if(N==5){
			transfer5Discs(s1,s3,s2);
		}
		printStacks();
	}
	
	private void transfer4Discs(Stack<Integer> source,Stack<Integer> intermediate,Stack<Integer> target){
		transferDiscs(source,intermediate,target,3);
		target.push(source.pop());
		transferDiscs(intermediate,target,source,3);
		}
		
		private void transfer5Discs(Stack<Integer> source,Stack<Integer> intermediate,Stack<Integer> target){
		transfer4Discs(source,intermediate,target);		
		transfer4Discs(target,source,intermediate);
		source.push(intermediate.pop());
		transfer4Discs(intermediate,source,target);
		}
	
	private void printStacks(){
	   System.out.println("Starting stack "+s1);
	   System.out.println("destination stack "+s2);
   }
   
   public static void main(String [] args){
	   TowerOfHanoi hanoi=new TowerOfHanoi(5);
	   hanoi.transferDiscs();
   }
}
