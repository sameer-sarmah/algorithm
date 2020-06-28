package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//longest increasing subsequence
class LIS
{
    public static void main(String args[])
    {
        int numbers[] = { 10, 22, 9, 13, 18, 30, 41, 60 };
        //fromBeginning(numbers);
        fromLast(numbers);
    }
    private static void fromBeginning( int numbers[]) {
        List<Integer> sequenceLengthByIndex = Arrays.stream(numbers).boxed().map(number -> 1).collect(Collectors.toList());
        for(int index = 0;index < numbers.length; index++) {
        	List<Integer> sequenceLengthsForCurrentNumber = new ArrayList<>();
        	if(index > 0) {
        		for(int innerIndex = 0;innerIndex < index;innerIndex++) {
        			if(numbers[index] > numbers[innerIndex]) {
        				sequenceLengthsForCurrentNumber.add(sequenceLengthByIndex.get(innerIndex));
        			}
        		}
        		if(!sequenceLengthsForCurrentNumber.isEmpty()) {
        		sequenceLengthByIndex.set(index, Collections.max(sequenceLengthsForCurrentNumber)+1);
				System.out.println("sequence length for "+numbers[index] +" is "+sequenceLengthByIndex.get(index));
        		}
        	}
        }
        System.out.println("numbers are");
        int maxSequenceLength = Collections.max(sequenceLengthByIndex);
        while(maxSequenceLength> 0) {
        	int index = sequenceLengthByIndex.indexOf(maxSequenceLength);
        	System.out.println(numbers[index]);
        	--maxSequenceLength;
        }
    }
    
    private static void fromLast( int numbers[]) {
        List<Integer> sequenceLengthByIndex = Arrays.stream(numbers).boxed().map(number -> 1).collect(Collectors.toList());
        for(int index = numbers.length-1;index >= 0; index--) {
        	List<Integer> sequenceLengthsForCurrentNumber = new ArrayList<>();
        	if(index >= 0) {
        		for(int innerIndex = numbers.length-1 ;innerIndex > index;innerIndex--) {
        			if(numbers[index] < numbers[innerIndex]) {
        				sequenceLengthsForCurrentNumber.add(sequenceLengthByIndex.get(innerIndex));
        			}
        		}
        		if(!sequenceLengthsForCurrentNumber.isEmpty()) {
        		sequenceLengthByIndex.set(index, Collections.max(sequenceLengthsForCurrentNumber)+1);
  				System.out.println("sequence length for "+numbers[index] +" is "+sequenceLengthByIndex.get(index));
        		}
        	}
        }
        System.out.println("numbers are");
        int maxSequenceLength = Collections.max(sequenceLengthByIndex);
        while(maxSequenceLength > 0) {
        	int index = sequenceLengthByIndex.indexOf(maxSequenceLength);
        	System.out.println(numbers[index]);
        	--maxSequenceLength;
        }
    }
 }