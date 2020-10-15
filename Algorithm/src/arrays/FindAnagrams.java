package arrays;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindAnagrams {

	public static void main(String[] args) throws IOException {
		//List<String> text = Arrays.asList("code","doce","ecod","framer","frame"); 
		//List<String> text = Arrays.asList("aaagmnrs","anagrams","code","dcoe"); 
		List<String> text = Arrays.asList("poke","pkoe","okpe","ekop");
		Set<String> distinctWords = new HashSet<>();
		Set<String> anagrams = new HashSet<>();
		for(int wordOneIndex = 0 ; wordOneIndex < text.size(); wordOneIndex++) {
			String wordOne = text.get(wordOneIndex);
			if(!anagrams.contains(wordOne)) {
				distinctWords.add(wordOne);
			}
			for(int wordTwoIndex = wordOneIndex + 1 ; wordTwoIndex < text.size(); wordTwoIndex++) {
				String wordTwo = text.get(wordTwoIndex);
				if(!isAnagram(wordOne, wordTwo) ) {
					distinctWords.add(wordTwo);	
				}
				else {
					anagrams.add(wordTwo);
				}
			}
		}
		System.out.println(anagrams);
		List<String> sortedWords = distinctWords.stream().filter(word -> !anagrams.contains(word)).collect(Collectors.toList());
		System.out.println(sortedWords);
	}

	private static boolean isAnagram(String wordOne,String wordTwo ) {
		Set<String> wordOneSet = wordOne.chars().boxed().map(value -> (char)((int)value)).map(ch -> ch.toString()).collect(Collectors.toSet());
		Set<String> wordTwoSet = wordOne.chars().boxed().map(value -> (char)((int)value)).map(ch -> ch.toString()).collect(Collectors.toSet());
		Map<String,Integer> stringToCountForWordOne = stringToCountMap(wordOneSet);
		Map<String,Integer> stringToCountForWordTwo = stringToCountMap(wordTwoSet);
		if(areCollectionsEqual(stringToCountForWordOne.keySet(),stringToCountForWordTwo.keySet())) {
			for(String ch : stringToCountForWordOne.keySet()) {
				if(stringToCountForWordOne.get(ch) != stringToCountForWordTwo.get(ch))
					return false;
			}
			return true;
		}
		return false;
	}
	
	private static Map<String,Integer> stringToCountMap(Set<String> word){
		Map<String,Integer> stringToCount = new HashMap<>();
		for(String str : word) {
			if(stringToCount.get(str) == null) {
				stringToCount.put(str, 0);
			}
			else {
				stringToCount.put(str, stringToCount.get(str) + 1);
			}
		}
		return stringToCount;
	}
	
	private static boolean areCollectionsEqual(Collection<String> CollectionOne,Collection<String> CollectionTwo) {
		Collection<String> union = new HashSet<>();
		union.addAll(CollectionOne);
		union.addAll(CollectionTwo);
		Collection<String> intersaction = union.stream().collect(Collectors.toSet());
		intersaction.retainAll(CollectionTwo);
		Collection<String> difference = union.stream().collect(Collectors.toSet());
		difference.removeAll(intersaction);
		return difference.isEmpty();
	}
	
	private static boolean isAnagramUsingPermutation(String wordOne,String wordTwo ) {
		Set<String> permutations  = new HashSet<>();
		List<Character> word = wordOne.chars().boxed().map(value -> (char)((int)value)).collect(Collectors.toList());
		return permutations.contains(wordTwo);
	}
	
	private static void permutations(List<Character> word,int leftIndex,Set<String> permutations) {
		
		String permutation = word.stream().map(ch -> ch.toString()).reduce((s1,s2) -> s1+s2).get();
		if(!permutations.contains(permutation)) {
			permutations.add(permutation);
			for(int index =leftIndex ; index < word.size();index++){
				List<Character> wordCloned = word.stream().collect(Collectors.toList());
				Collections.swap(wordCloned, leftIndex, index);
				permutations(wordCloned,leftIndex+1,permutations);
			}
		}
		
	}

}
