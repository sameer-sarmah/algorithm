package arrays;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		 int numbers[] = { 10, 22, 9, 13, 18, 30, 41, 60 };
		 for(int outerIndex = 0;outerIndex< numbers.length;outerIndex++) {
			 for(int innerIndex = 0;innerIndex< numbers.length - 1;innerIndex++) {
				 if(numbers[innerIndex] > numbers[innerIndex+1]) {
					 System.out.println(String.format("swapped %d and %d", numbers[outerIndex],numbers[innerIndex]));
					 int temp = numbers[innerIndex+1];
					 numbers[innerIndex+1] = numbers[innerIndex];
					 numbers[innerIndex] = temp;
					 
				 }
			 }
		 }
		 System.out.println(Arrays.toString(numbers));
	}

}
