package arrays;

import java.util.Arrays;

public class SwapPositiveAndNegativeNumbers {

	public static void main(String[] args) {
		int[] numbers = { 1, -2, 3, -4, 5, -6 };
		int postiveNumberLastIndex = 0;
		int negativeNumberFirstIndex = numbers.length - 1;

		while (postiveNumberLastIndex < negativeNumberFirstIndex) {

			while (numbers[postiveNumberLastIndex] >= 0) {
				++postiveNumberLastIndex;
			}

			while (numbers[negativeNumberFirstIndex] < 0) {
				--negativeNumberFirstIndex;
			}
			
			if(postiveNumberLastIndex < negativeNumberFirstIndex) {
				System.out.println(String.format("swapping number :%d and %d located at %d and %d",numbers[postiveNumberLastIndex] ,
						numbers[negativeNumberFirstIndex],postiveNumberLastIndex,negativeNumberFirstIndex));
				int temp = numbers[postiveNumberLastIndex];
				numbers[postiveNumberLastIndex] = numbers[negativeNumberFirstIndex];
				numbers[negativeNumberFirstIndex] = temp;
			}


		}
		System.out.println(Arrays.toString(numbers));
	}

}
