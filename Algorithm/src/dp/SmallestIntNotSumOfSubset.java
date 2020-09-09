package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestIntNotSumOfSubset {

	public static void main(String[] args) {
		int[] numbers = {1, 3, 6, 10, 11, 15};
		//int[] numbers = {1, 1,1,1,1};
		//int[] numbers = {1,2,3,4,5};
		Map<Integer,Integer> numberToCount = new HashMap<>();
		for(int number : numbers) {
			if(numberToCount.get(number)== null) {
				numberToCount.put(number, 1);
			}
			else {
				numberToCount.put(number, numberToCount.get(number) + 1);
			}
		}
		System.out.println(findSmallestIntWhichIsNotSumOfSubset(numbers,numberToCount));
	}
	
	private static int findSmallestIntWhichIsNotSumOfSubset(int[] numbers,Map<Integer,Integer> numberToCount) {
		if(numbers[0] > 1){
			return 1;
		}
		else{
			for(int startIndex = 1;startIndex < numbers.length;startIndex++){
				for(int number = numbers[startIndex-1] + 1;number < numbers[startIndex];number++){
					if(!numberToCount.containsKey(number) && !isSumPresentInSubset(numbers,startIndex,number)){
						return number;
					}
				}
			}
		}
		return Arrays.stream(numbers).boxed().max(Integer::compareTo).get() + 1;
	}

	private static boolean isSumPresentInSubset(int[] numbers,int index,int number) {

		for(int startIndex = 0;startIndex< index ; startIndex++){
			int localSum = 0;
			for(int subArrayIndex = startIndex;subArrayIndex<index;subArrayIndex++){
				localSum += numbers[subArrayIndex];
				if(localSum == number){
				 return true;
				}
			}
		}
		
		return false;
	}
}
