package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallestTripletDifference {

	public static void main(String[] args) {
		int[] numbers= {1, 2, 3, 0, -1, 8, 10};
//		List<List<Integer>> triplets = usingThreeLoops(numbers);
//		System.out.println(triplets);
		List<List<Integer>> triplet = usingTwoLoops(numbers);
		System.out.println(triplet);
	}

	private static List<List<Integer>> usingThreeLoops(int[] numbers){
		int min = Integer.MAX_VALUE;
		List<List<Integer>> triplets = new ArrayList<>();
			
		for(int first = 0;first<numbers.length;first++){
			for(int second = first+1;second<numbers.length;second++){
				for(int third = second+1;third<numbers.length;third++){
					List<Integer> subList = Arrays.asList(numbers[first] ,numbers[second], numbers[third]);
					Collections.sort(subList);
					int localMin = (subList.get(2) - subList.get(1)) + (subList.get(1) - subList.get(0));
					if(localMin < min) {
						min = localMin;
						List<Integer> triplet = new ArrayList<>();
						triplet.add(numbers[first]);
						triplet.add(numbers[second]);
						triplet.add(numbers[third]);
						triplets.add(triplet);
					}
					else if(localMin == min) {
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
		List<List<Integer>> triplets = new ArrayList<>();
		Arrays.sort(numbers);
		int startIndex = 0;
		int min = Integer.MAX_VALUE;
		for(int index = startIndex; index <=  numbers.length - 3; index++){
			List<Integer> subList = Arrays.asList(numbers[index] ,numbers[index + 1], numbers[index + 2]);
			int localMin = (subList.get(2) - subList.get(1)) + (subList.get(1) - subList.get(0));
			if(localMin < min) {
				min = localMin;
				List<Integer> triplet = new ArrayList<>();
				triplet.add(numbers[index]);
				triplet.add(numbers[index + 1]);
				triplet.add(numbers[index + 2]);
				triplets.add(triplet);
			}
			else if(localMin == min) {
				List<Integer> triplet = new ArrayList<>();
				triplet.add(numbers[index]);
				triplet.add(numbers[index + 1]);
				triplet.add(numbers[index + 2]);
				triplets.add(triplet);
			}
			++startIndex;
		}
		return triplets;
		
	}
}
