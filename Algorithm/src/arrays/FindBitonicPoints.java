package arrays;

public class FindBitonicPoints {
	public static void main(String[] args) {
		int numbers[] =  {6, 7, 8, 11, 9, 5, 2, 1};
		int bitonicIndex = findBitonicSequence(numbers);
		System.out.println("bitonic index is :"+bitonicIndex+",and the number present at this index is:"+numbers[bitonicIndex]);
	}
	
	
	private static int findBitonicSequence(int [] numbers) {
		int bitonicIndex = -1;
		for(int index =0 ;index<numbers.length;index++ ) {
			if(continuosLesserNumberPresentOnLeft(numbers,index) && continuosLesserNumberPresentOnRight(numbers,index)) {
				bitonicIndex = index;
			}
		}
		return bitonicIndex;
	}
	
	private static boolean continuosLesserNumberPresentOnLeft(int [] numbers,int indexOfNumber) {
		
		for(int index=0;index < indexOfNumber;index++) {
			if(numbers[index] >= numbers[indexOfNumber] || numbers[index] >= numbers[index+1]) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean continuosLesserNumberPresentOnRight(int [] numbers,int indexOfNumber) {
		for(int index=indexOfNumber + 1;index < numbers.length - 1;index++) {
			if(numbers[index] >= numbers[indexOfNumber] || numbers[index] <= numbers[index+1])
				return false;
		}
		return true;
	}
}
