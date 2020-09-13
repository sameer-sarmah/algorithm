package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxTripletSum {

	public static void main(String[] args) {
		int[] numbers= {1, 2, 3, 0, -1, 8, 10};
//		List<Integer> triplet = usingThreeLoops(numbers);
//		System.out.println(triplet);
		List<Integer> triplet = usingTwoLoops(numbers);
		System.out.println(triplet);
	}
	private static List<Integer> usingThreeLoops(int[] numbers){
		int max = 0;
		List<Integer> triplet = new ArrayList<>();	
		for(int first = 0;first<numbers.length;first++){
			for(int second = first+1;second<numbers.length;second++){
				for(int third = second+1;third<numbers.length;third++){
					if(numbers[first] + numbers[second] + numbers[third] > max) {
						triplet.clear();
						max = numbers[first] + numbers[second] + numbers[third];
						triplet.add(numbers[first]);
						triplet.add(numbers[second]);
						triplet.add(numbers[third]);
					}
				}
			}
		}
		return triplet;
		
	} 
	
	
	private static List<Integer> usingTwoLoops(int[] numbers){
		List<Integer> triplet = new ArrayList<>();
		Arrays.sort(numbers);
		triplet.add(numbers[numbers.length -1]);
		triplet.add(numbers[numbers.length -2]);
		triplet.add(numbers[numbers.length -3]);
		return triplet;
		
	}
}
