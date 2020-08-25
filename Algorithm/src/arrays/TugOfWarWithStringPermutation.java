package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * 
we have to divide the elements of a list of size 4 into two sub lists ,if we use the formula for combination it is 4C2 = 4!/(2! * 2!) = 6
[1,2,3,4]

[1,2] [3,4]
[1,3] [2,4]
[1,4] [2,3]

[2,3] [1,3]
[2,4] [1,3]

[3,4] [1,2]
 * */
public class TagOfWarWithStringPermutation {

	public static void main(String[] args) {
		int[] numbers ={3, 4, 5, -3, 100, 1, 89, 54, 23, 20};
		//int[] numbers ={ 100, 1, 89, 54, 23, 20};
		int totalSum = Arrays.stream(numbers).sum();
		int firstSublistSize = numbers.length / 2;
		StringBuilder builder =new StringBuilder();
		for(int count=1;count<= firstSublistSize ;count++) {
			builder.append("1");
		}
		for(int count=1;count<= (numbers.length - firstSublistSize) ;count++) {
			builder.append("0");
		}
		
		List<Character> word = new ArrayList<>();
		for(Character ch : builder.toString().toCharArray()) {
			word.add(ch);
		}
		Set<String> allPermutations = new HashSet<>();
		permutate(word, 0, allPermutations);
		System.out.println(allPermutations);
		int difference = Integer.MAX_VALUE;
		Set<Integer> firstSubSetIndices = new HashSet<>();
		Set<Set<Integer>> firstSubsetPermutation = new HashSet<>();
		for(String binary: allPermutations) {
			Set<Integer> set = new HashSet<>();
			for(int index = 0;index < binary.length() ;index++) {
				if(binary.charAt(index) == '1') {
					set.add(index);
				}
			}
			firstSubsetPermutation.add(set);
		}
		for( Set<Integer> indicesInFirstSubList : firstSubsetPermutation ) {
			int sumInFirstSubSet = indicesInFirstSubList.stream().map(index -> numbers[index]).reduce((x,y)-> x+y).get();
			int sumInSecondSubSet = totalSum - sumInFirstSubSet;
			if(Math.abs(sumInSecondSubSet - sumInFirstSubSet )< difference) {
				difference = Math.abs(sumInSecondSubSet - sumInFirstSubSet );
				firstSubSetIndices = indicesInFirstSubList;
			}
			 
		}
		//System.out.println(firstSubSetIndices);
		List<Integer> firstList = new ArrayList<>();
		List<Integer> secondList = new ArrayList<>();
		for(int index =0; index < numbers.length ; index++) {
			if(firstSubSetIndices.contains(index)) {
				firstList.add(numbers[index]);
			}
			else {
				secondList.add(numbers[index]);
			}
		}
		System.out.println(firstList);
		System.out.println(secondList);
		System.out.println(difference);
	}

	private static void permutate(List<Character> word,int leftIndex,Set<String> words) {
		if(leftIndex < word.size()) {
			List<Character> clonedWord = word.stream().collect(Collectors.toList());
			for(int index = leftIndex;index< clonedWord.size();index++) {
				Collections.swap(clonedWord, leftIndex, index);
				words.add(clonedWord.stream().map(ch -> ch.toString()).reduce("",(x,y) -> x+y));
				permutate(clonedWord, leftIndex+1, words);
			}
		}
		
	}
	
	
}
