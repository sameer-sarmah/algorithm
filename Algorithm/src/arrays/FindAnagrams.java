package arrays;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindAnagrams {

	public static void main(String[] args) throws IOException {
		//List<String> text = Arrays.asList("code","doce","ecod","framer","frame"); 
		List<String> text = Arrays.asList("aaagmnrs","anagrams","code","dcoe"); 
		//List<String> text = Arrays.asList("poke","pkoe","okpe","ekop");
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
		if(wordOne.length() != wordTwo.length()) {
			return false;
		}
		
		for(int charIndex = 0 ; charIndex < wordOne.length();charIndex++) {
			Character currentChar = wordOne.charAt(charIndex);
			if(!wordTwo.contains(currentChar.toString())) {
				return false;
			}
		}
		return true;
	}

}
