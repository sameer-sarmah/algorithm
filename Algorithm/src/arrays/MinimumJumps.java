package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MinimumJumps {

	public static void main(String[] args) {
		int[] numbers = {2, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		Stack<Integer> path = new Stack<>();
		path.push(numbers[0]);
		List<List<Integer>> jumps = new ArrayList<>();
		findJumps(numbers, 0, jumps, path);
		List<Integer> subList = null;
		int minSize = numbers.length;
		for(List<Integer> jump : jumps) {
			if(jump.size() < minSize) {
				subList = jump;
				minSize = jump.size();
			}
		}
		System.out.println(subList);
	}
	
	private static void findJumps(int[] numbers,int currentIndex, List<List<Integer>> jumps,Stack<Integer> path) {
		if(currentIndex == numbers.length - 1) {
			List<Integer> subList =path.stream().collect(Collectors.toList());
			jumps.add(subList);
			System.out.println(subList);
			return;
		}
		else {
			int jump = numbers[currentIndex];
			if(jump>0) {
				for(int index = currentIndex+1;index <= currentIndex + jump && index < numbers.length;index++ ) {
					int furtherJump = numbers[index];
					path.push(furtherJump);
					System.out.println(String.format("current index is :%d and the number :%d", index,furtherJump));
					findJumps(numbers,index,jumps,path);
					path.pop();
				}
			}
		}
		
	}
	

}
