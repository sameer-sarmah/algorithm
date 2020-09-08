package arrays;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		 int numbers[] = { 10, 22, 9, 13, 18, 30, 41, 60 };
		 for(int sortedSubListLastIndex = 0;sortedSubListLastIndex< numbers.length;sortedSubListLastIndex++) {
			 for(int innerIndex = sortedSubListLastIndex + 1;innerIndex < numbers.length;innerIndex++) {
				 if(numbers[sortedSubListLastIndex] > numbers[innerIndex]) {
					 System.out.println(String.format("swapped %d and %d", numbers[sortedSubListLastIndex],numbers[innerIndex]));
					 int temp = numbers[sortedSubListLastIndex];
					 numbers[sortedSubListLastIndex] = numbers[innerIndex];
					 numbers[innerIndex] = temp;
					 
				 }
			 }
		 }
		 System.out.println(Arrays.toString(numbers));

	}

}
