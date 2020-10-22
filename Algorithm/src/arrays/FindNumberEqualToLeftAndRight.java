package arrays;

public class FindNumberEqualToLeftAndRight {

	public static void main(String[] args) {
		int [] numbers= {1,2,3,20,4,7,3};
		int total = 0;
		int[] totalTillIndexFromLeft = new int[numbers.length];
		for(int index = 0 ;index < numbers.length ; index++) {
			if(index  == 0) {
				totalTillIndexFromLeft[index] = numbers[index];
			}
			else {
				totalTillIndexFromLeft[index] = totalTillIndexFromLeft[index - 1] + numbers[index];
			}
			total += numbers[index];
			
		}
		
		for(int index = 0 ;index < numbers.length ; index++) {
			if(index > 0) {
				int leftSum = totalTillIndexFromLeft[index - 1];
				int rightSum = total - leftSum - numbers[index];
				if(leftSum + rightSum == numbers[index]) {
					System.out.println(numbers[index]);
				}
			}
			
		}
	}

}
