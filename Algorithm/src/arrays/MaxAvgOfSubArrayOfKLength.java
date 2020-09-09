package arrays;

public class MaxAvgOfSubArrayOfKLength {

	public static void main(String[] args) {
		int[] numbers = {1, 12, -5, -6, 50, 3};
		int subArrayLength = 4;
		int subArrayStartIndex = 0;
		double maxAvg=0.0;
		for(int startIndex = 0;startIndex <= numbers.length-subArrayLength;startIndex++) {
			
			double localSum =0;
			for(int subArrayIndex = startIndex; subArrayIndex < startIndex +subArrayLength;subArrayIndex++) {
				localSum += numbers[subArrayIndex];
			}
			double localAvg =(localSum/subArrayLength);
			System.out.println(String.format("avg is :%.2f when the start index is %d", localAvg,startIndex));
			if((localSum/subArrayLength) > maxAvg) {
				maxAvg = localSum/subArrayLength;
				subArrayStartIndex = startIndex;
			}
		}

		System.out.println(String.format("The max avg is :%.2f and the start index is %d", maxAvg,subArrayStartIndex));
	}

}
