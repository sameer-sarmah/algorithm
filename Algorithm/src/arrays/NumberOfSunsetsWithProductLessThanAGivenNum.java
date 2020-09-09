package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumberOfSunsetsWithProductLessThanAGivenNum {

	public static void main(String[] args) {
		int numbers[] = {1, 4, 45, 6, 1, 19};
		int max  =  50;
		List<List<Integer>> subsets =  usingTwoLoops(numbers, max);
		System.out.println(subsets);
	}
	
	private static List<List<Integer>> usingTwoLoops(int numbers[],int max) {
		List<List<Integer>> subsets = new ArrayList<>();
		for(int startIndex = 0;startIndex< numbers.length ; startIndex++){
			int localProduct = 1;
			List<Integer> subset = new ArrayList<>();
			for(int subArrayIndex = startIndex;subArrayIndex<numbers.length;subArrayIndex++){
				localProduct *= numbers[subArrayIndex];
				if(localProduct <= max ){
					subset.add(numbers[subArrayIndex]);
					subsets.add(subset.stream().collect(Collectors.toList()));
				}
				else {
					break;
				}
			}
		}
		return subsets; 
	}

}
