package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MinimumJumpsDP {

	public static void main(String[] args) {
		int[] numbers = {2, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		Map<Integer,List<Integer>> toIndexIndicesPath = new HashMap<>();
		List<Integer> indicesPath=findMinimumJumpPath(toIndexIndicesPath,numbers,0);
		System.out.println(indicesPath);
	}
	
	private static List<Integer> findMinimumJumpPath(Map<Integer,List<Integer>> toIndexIndicesPath,int[] numbers,int currentIndex) {
		List<Integer> indicesPath = new ArrayList<>();
		indicesPath.add(0);
		toIndexIndicesPath.put(currentIndex, indicesPath);
		
	        for (int index = 1; index < numbers.length; index++) {  
	            for (int subArrayIndex = 0; subArrayIndex < index; subArrayIndex++) { 
	                if (index <= subArrayIndex + numbers[subArrayIndex] && toIndexIndicesPath.get(subArrayIndex) != null) { 
	                	indicesPath = toIndexIndicesPath.get(subArrayIndex).stream().collect(Collectors.toList());
	                	indicesPath.add(index);
	                	toIndexIndicesPath.put(index, indicesPath);
	                	System.out.println(String.format("path for index %d is %s",index,indicesPath));
	                    break; 
	                } 
	            } 
	        }
	      return  toIndexIndicesPath.get(numbers.length -1);	
		
	}
	


}
