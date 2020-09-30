package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * numbers to the left of a number are all smaller and numbers to the right of a number are all greater
 * */
public class AllLeftSmallerAllRightGreater {

	public static void main(String[] args) {
		int arr[] = {5, 11, 2, 10, 14, 25, 20, 18};
		bitonicSequence(arr);
	}

	private static void bitonicSequence(int[] numbers) {
	
		int[] maximunNumberTillIndexFromFirst = new int[numbers.length];
		for(int index = 0;index < numbers.length;index++) {
			if(index ==0) {
				maximunNumberTillIndexFromFirst[index] = numbers[index];
			}
			else {
				maximunNumberTillIndexFromFirst[index] = Math.max(numbers[index],maximunNumberTillIndexFromFirst[index-1]);
			}
		}
		
		System.out.println(Arrays.toString(maximunNumberTillIndexFromFirst));
		int[] minimumNumberTillIndexFromLast = new int[numbers.length];
		for(int index = numbers.length - 1;index >= 0;index--) {
			if(index == numbers.length - 1) {
				minimumNumberTillIndexFromLast[index] = numbers[index];
			}
			else {
				minimumNumberTillIndexFromLast[index] = Math.min(numbers[index],minimumNumberTillIndexFromLast[index+1]);
			}
		}
		System.out.println(Arrays.toString(minimumNumberTillIndexFromLast));
		
		List<Integer> bitonicNumbers = new ArrayList<>();
		for(int index = 0;index < numbers.length;index++) {
			if(index ==0) {
				if(minimumNumberTillIndexFromLast[index+1] > numbers[index]) {
					bitonicNumbers.add(numbers[index]);
				}
			}
			else if(index == numbers.length - 1){
				if(maximunNumberTillIndexFromFirst[index - 1] < numbers[index]) {
					bitonicNumbers.add(numbers[index]);
				}
			}
			else {
				if(minimumNumberTillIndexFromLast[index+1] > numbers[index] && maximunNumberTillIndexFromFirst[index - 1] < numbers[index]) {
					bitonicNumbers.add(numbers[index]);
				}
			}
		}	
		System.out.println(bitonicNumbers);
	}
	
	
}
