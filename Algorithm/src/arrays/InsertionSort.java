package arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertionSort {

	public static void main(String[] args) {
		 int numbers[] = { 10, 22, 9, 13, 18, 30, 41, 60 };
		 for(int sortedSubListLastIndex = 0;sortedSubListLastIndex< numbers.length;sortedSubListLastIndex++) {

			 for(int innerIndex = 0 ;innerIndex < sortedSubListLastIndex;innerIndex++) {
				 if(numbers[sortedSubListLastIndex] < numbers[innerIndex]) {
					 System.out.println(String.format("swapped %d and %d", numbers[sortedSubListLastIndex],numbers[innerIndex]));
					 int temp = numbers[sortedSubListLastIndex];
					 numbers[sortedSubListLastIndex] = numbers[innerIndex];
					 numbers[innerIndex] = temp;
					 
				 }
			 }
			 System.out.println(Arrays.stream(numbers).boxed().collect(Collectors.toList()).subList(0, sortedSubListLastIndex+1));
		 }
		 System.out.println(Arrays.toString(numbers));

	}

}
