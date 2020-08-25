package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets 
 * is as minimum as possible.
 * 
 * */
public class TagOfWar {

	//static int[] numbers = { 3, 4, 5, -3, 100, 1, 89, 54, 23, 20 };
	static int[] numbers = { 100, 1, 89, 54, 23, 20 };
	static int minDifference = Integer.MAX_VALUE;
	static List<Integer> firstSubSetIndices = new ArrayList<>();
	//the indices with value 1 will be part of first sub list
	//the indices with value 0 will be part of second sub list
	static int[] indicesStatus = new int[numbers.length];

	public static void main(String[] args) {
		divideIntoSubLists(0);
		printSolution();
	}


	private static void divideIntoSubLists(int currentIndex) {
		System.out.println(currentIndex);
		System.out.println(Arrays.toString(indicesStatus));

		long selectedSize = Arrays.stream(indicesStatus).filter(num -> num == 1).count();

		if (selectedSize == numbers.length / 2) {
			int difference = getDifference();
			if (difference < minDifference) {
				minDifference = difference;
				
				firstSubSetIndices.clear();
				for (int index = 0; index < numbers.length; index++) {
					if (indicesStatus[index] == 1) {
						firstSubSetIndices.add(index);
					}
				}
			}
		}

		if (currentIndex >= numbers.length)
			return;

		indicesStatus[currentIndex] = 1;
		divideIntoSubLists(currentIndex + 1);

		indicesStatus[currentIndex] = 0;
		divideIntoSubLists(currentIndex + 1);
	}


	private static void printSolution() {
		List<Integer> firstList = new ArrayList<>();
		List<Integer> secondList = new ArrayList<>();
		for (int index = 0; index < numbers.length; index++) {
			if (firstSubSetIndices.contains(index)) {
				firstList.add(numbers[index]);
			} else {
				secondList.add(numbers[index]);
			}
		}
		System.out.println(firstList);
		System.out.println(secondList);
		System.out.println(minDifference);
	}

	private static int getDifference() {

		int leftSubListSum = 0;
		int rightSubListSum = 0;

		for (int index = 0; index < numbers.length; index++) {
			if (indicesStatus[index] == 1)
				leftSubListSum += numbers[index];
			else
				rightSubListSum += numbers[index];
		}

		return Math.abs(rightSubListSum - leftSubListSum);
	}
}
