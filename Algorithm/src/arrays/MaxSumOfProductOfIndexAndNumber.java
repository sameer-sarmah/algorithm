package arrays;

public class MaxSumOfProductOfIndexAndNumber {
	public static void main(String[] args) {
		int[] numbers = {1, 2 ,3 ,4};
		
		
		int maxSum= 0;
		int requiredRightShift = 0;
		
		for(int rightShift = 0;rightShift<numbers.length;rightShift++) {
			int localSum = 0;
			for(int index = 0;index<numbers.length;index++) {
				int effectiveIndex = ((index + rightShift) % numbers.length);
				localSum += effectiveIndex * numbers[index];
			}
			
			if(maxSum < localSum) {
				maxSum = localSum;
				requiredRightShift = rightShift;
			}
		}
		
		System.out.println(String.format("the max sum is %d at right shift %d", maxSum,requiredRightShift));
	}
}
