package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StrPermutation {

	public static void main(String[] arg) {
		List<Character> word = Arrays.asList('a','b','c');
		List<String> permutations = new ArrayList<>();
		new StrPermutation().permute(word, 0, permutations);
		System.out.println(permutations);
	}

	private void permute(List<Character> word, int leftIndex, List<String> permutations) {
		if (leftIndex == word.size()) {
			permutations.add(word.stream().map(ch -> ch.toString()).reduce((x, y) -> x + y).orElseGet(()->"").toString());
		} else {
			List<Character> wordCloned = word.stream().collect(Collectors.toList());
			for (int swapRightIndex = leftIndex; swapRightIndex < word.size(); swapRightIndex++) {
				Collections.swap(wordCloned, leftIndex, swapRightIndex);// wordCloned="abc" left=0 i=1 after swap bac
				permute(wordCloned, leftIndex + 1, permutations);
			}
		}
	}

}
