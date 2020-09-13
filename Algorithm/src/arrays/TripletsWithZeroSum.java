package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripletsWithZeroSum {


	public static void main(String[] args) {
		int[] numbers= {0, -1, 2, -3, 1};
//		List<List<Integer>> triplets = usingThreeLoops(numbers);
//		System.out.println(triplets);
		List<List<Integer>> triplets = usingTwoLoops(numbers);
		System.out.println(triplets);
	}

	private static List<List<Integer>> usingThreeLoops(int[] numbers){
		List<List<Integer>> triplets = new ArrayList<>();
		for(int first = 0;first<numbers.length;first++){
			for(int second = first+1;second<numbers.length;second++){
				for(int third = second+1;third<numbers.length;third++){
					if(numbers[first] + numbers[second] + numbers[third] ==0) {
						List<Integer> triplet = new ArrayList<>();	
						triplet.add(numbers[first]);
						triplet.add(numbers[second]);
						triplet.add(numbers[third]);
						triplets.add(triplet);
					}
				}
			}
		}
		return triplets;
		
	} 
	
	
	private static List<List<Integer>> usingTwoLoops(int[] numbers){
		Map<Integer,Integer> numberToCount = new HashMap<>();
		for(int number :numbers) {
			if(numberToCount.get(number) == null) {
				numberToCount.put(number, 1);
			}
			else {
				numberToCount.put(number, numberToCount.get(number)+1);
			}
		}
		List<List<Integer>> triplets = new ArrayList<>();
		for(int first = 0;first<numbers.length;first++){
			for(int second = first+1;second<numbers.length;second++){
				int required = -(numbers[first] + numbers[second]);
				if(numberToCount.get(required) != null) {
					List<Integer> triplet = new ArrayList<>();	
					triplet.add(numbers[first]);
					triplet.add(numbers[second]);
					triplet.add(required);
					triplets.add(triplet);
				}
				
			}
		}
		return triplets;
		
	} 
}
