package arrays;

/*
 * Find missing number in O(n)
 * */
public class FindMissingNumber {

	public static void main(String[] args) {
		//int[] numbers =  {2,4,6,5,1,-1,-2,7};
		int[] numbers =  {2,4,6,5,1,7};
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int total = 0;
		for(int number : numbers ) {
			total += number;
			if(number < min) {
				min = number;
			}
			if(number > max) {
				max = number;
			}
		}
		
		System.out.println(String.format("the min and max are %d , %d ", min,max));

		if(min < 0) {
			int sumOfSequenceTillMax = (max * (max + 1))/2;
			int sumOfSequenceTillMin = (Math.abs(min)  * (Math.abs(min) + 1))/2;
			int expectedSum = sumOfSequenceTillMax - sumOfSequenceTillMin ;
			int missingNumber = expectedSum - total;
			System.out.println(missingNumber);
		}
		else {
			int sumOfSequenceTillMax = (max * (max + 1))/2;
			int sumOfSequenceTillPreviousNumberOfMin = ((min - 1) * ((min -1) + 1))/2;
			int expectedSum = sumOfSequenceTillMax - sumOfSequenceTillPreviousNumberOfMin ;
			int missingNumber = expectedSum - total;
			System.out.println(missingNumber);
		}

	}

}
