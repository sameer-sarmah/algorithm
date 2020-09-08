package arrays;

import java.util.Arrays;

public class PositiveAtEvenAndNegativeAtOdd {

	public static void main(String[] args) {
		int[] numbers = { 1, 5, 3, -4, -2, -6 };
		int evenIndex = 0;
		int oddIndex = 1;

		while (evenIndex < numbers.length && oddIndex < numbers.length) {

			while (evenIndex < numbers.length && numbers[evenIndex] > 0) {
				evenIndex = evenIndex +2;
			}
			if(evenIndex < numbers.length) {
				System.out.println(String.format("%d at even index %d is negative", numbers[evenIndex],evenIndex));
			}
			
			while (oddIndex < numbers.length && numbers[oddIndex] < 0) {
				oddIndex = oddIndex + 2;
			}
			if(oddIndex < numbers.length) {
				System.out.println(String.format("%d at even index %d is positive", numbers[oddIndex],oddIndex));
			}
			if(evenIndex < numbers.length && numbers[evenIndex] < 0 && oddIndex < numbers.length && numbers[oddIndex] > 0) {
				System.out.println(String.format("swapping number :%d and %d located at %d and %d",numbers[evenIndex] ,
						numbers[oddIndex],evenIndex,oddIndex));
				int temp = numbers[evenIndex];
				numbers[evenIndex] = numbers[oddIndex];
				numbers[oddIndex] = temp;	
			}


		}
		System.out.println(Arrays.toString(numbers));
	}

}
