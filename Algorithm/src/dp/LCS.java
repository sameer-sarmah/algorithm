package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javatuples.Pair;

//longest consecutive subsequence
public class LCS {
	private static List<Integer> numbers = Arrays.asList(36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42);
	public static void main(String[] args) {
		//LicUsingSort(numbers);
		LicUsingMap( numbers);
	}
	
	private static void LicUsingMap(List<Integer> numbers) {
		int maxSequenceLength =0;
		int maxNumberInSequence = 0;
		Map<Integer,Integer> numberToSequenceLength = new HashMap<>();
		for(int number:numbers) {
			numberToSequenceLength.put(number, 1);
		}
		for(int index=0;index < numbers.size() ;index++) {
			if(numberToSequenceLength.get(numbers.get(index) - 1) == null && numberToSequenceLength.get(numbers.get(index) + 1) != null ) {
				//sequence starts with this number
				Pair<Integer, Integer> sequenceTuple = Pair.with(maxSequenceLength, maxNumberInSequence);
				sequenceTuple = traverse(numbers.get(index) + 1,numberToSequenceLength,sequenceTuple);
				System.out.println("for number "+numbers.get(index)+" ,maxSequenceLength: "+sequenceTuple.getValue0()+" maxNumberInSequence: "+sequenceTuple.getValue1());
				if(maxSequenceLength < sequenceTuple.getValue0()) {
					maxSequenceLength = sequenceTuple.getValue0();
					maxNumberInSequence = sequenceTuple.getValue1();
				}
				
			}
		}
		System.out.println("numbers forming the sequence");
		while(maxSequenceLength>0) {
			System.out.println(maxNumberInSequence);
			--maxNumberInSequence;
			--maxSequenceLength;
		}
		
	}
	
	private static Pair<Integer, Integer> traverse(Integer number ,Map<Integer,Integer> numberToSequenceLength,Pair<Integer, Integer> sequenceTuple) {
		if(numberToSequenceLength.get(number) != null && numberToSequenceLength.get(number - 1) != null) {
			int maxSequenceLength = sequenceTuple.getValue0();
		    int maxNumberInSequence = sequenceTuple.getValue1();
			int sequenceLength = numberToSequenceLength.get(number) + numberToSequenceLength.get(number - 1);
			numberToSequenceLength.put(number,sequenceLength);

			if(sequenceLength > maxSequenceLength) {
				maxSequenceLength = sequenceLength;
				maxNumberInSequence = number;
			}
			Pair<Integer, Integer> updatedSequenceTuple = Pair.with(maxSequenceLength, maxNumberInSequence);
			if(numberToSequenceLength.get(number + 1) != null) {
				return traverse(number + 1,numberToSequenceLength,updatedSequenceTuple);
			}
			else
				return updatedSequenceTuple;
				
		}
		return Pair.with(1,number);
	}
	
	private static void LicUsingSort(List<Integer> numbers) {
		List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
		List<Integer> sequence = sortedNumbers.stream().map(n -> 0).collect(Collectors.toList());
		for(int index=0;index < sequence.size();index++) {
			if(index == 0) {
				sequence.set(index,1);
			}
			else {
				if(sortedNumbers.get(index) -1 == sortedNumbers.get(index - 1)) {
					sequence.set(index,sequence.get(index-1) + 1);//memoized value
				}
				else {
					sequence.set(index,1);
				}
			}
			
		}
		int sequenceLength = sequence.stream().max(Integer::compareTo).orElseGet(() -> 0);
		System.out.println(sequenceLength);
		int indexOfMaxNumberInSequence = sequence.indexOf(sequenceLength);
		System.out.println("numbers forming the sequence");
		for(int index=indexOfMaxNumberInSequence; index > indexOfMaxNumberInSequence - sequenceLength ; index--) {
			System.out.println(sortedNumbers.get(index));
		}
	}
}
