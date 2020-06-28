package dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BitonicSeq {

	public static void main(String[] args) {
		int arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
		bitonicSequence(arr);
	}
	
	
	private static void bitonicSequence(int [] numbers) {
		List<Integer> bitonicSequenceByIndex = Arrays.stream(numbers).boxed().map(number -> 0).collect(Collectors.toList());
		for(int index = 0;index < numbers.length; index++) {
			int decreasingLeftSequenceLength = continousDecreasingSequenceLengthInLeft(numbers,index);
			int decreasingRightSequenceLength=  continousDecreasingSequenceLengthInRight(numbers,index);
			if(decreasingLeftSequenceLength > 0 && decreasingRightSequenceLength>0)
			bitonicSequenceByIndex.set(index, decreasingLeftSequenceLength + decreasingRightSequenceLength + 1);
		}
		int bitonicPoint = Collections.max(bitonicSequenceByIndex);
		System.out.println(bitonicPoint);
	}
	
	    
	    private static int continousDecreasingSequenceLengthInLeft(int numbers[],int currentIndex) {
	    	if(currentIndex-1 >=0 && numbers[currentIndex] > numbers[currentIndex-1]) {
	    		return  1 +continousDecreasingSequenceLengthInLeft(numbers,currentIndex-1);
	    	}
	    	else {
	    		return 0;
	    	}
	    }
	    private static int continousDecreasingSequenceLengthInRight(int numbers[],int currentIndex) {
	    	if(currentIndex+1 < numbers.length && numbers[currentIndex] > numbers[currentIndex+1]) {
	    		return  1 + continousDecreasingSequenceLengthInRight(numbers,currentIndex+1);
	    	}
	    	else {
	    		return 0;
	    	}
	    } 
}
