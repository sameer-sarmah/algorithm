package arrays;

import java.util.ArrayList;
import java.util.List;

public class DistanceBetweenTwoWords {
	public static void main(String[] args) {
		String text = "Now that we have defined the have parameters for our search we";
		System.out.println(shortestDistance(text, "we","have"));
	}
	
	private static int shortestDistance(String textString, String wordOne,String wordTwo) {
		List<Integer> indicesOfFirstWord = findWord(textString, wordOne);
		List<Integer> indicesOfSecondWord = findWord(textString, wordTwo);
		int distance = Integer.MAX_VALUE;
		for(int firstWordIndex : indicesOfFirstWord) {
			for(int secondWordIndex : indicesOfSecondWord) {
				int localDistance = 0;
				if(firstWordIndex < secondWordIndex) {
					localDistance = secondWordIndex -firstWordIndex - wordOne.length();
				}
				else {
					localDistance = firstWordIndex - secondWordIndex - wordTwo.length();
				}
				System.out.println(String.format("local distance between '%s' and '%s' located at %d and %d is :%d",wordOne,wordTwo,firstWordIndex,secondWordIndex,localDistance));
				if(distance > localDistance) {
					distance =localDistance;
				}
				
			}
			
		}
		return distance;
		
	}
	
	private static  List<Integer> findWord(String textString, String word) {
        List<Integer> indexes = new ArrayList<Integer>();
        int index = 0;
        while(index != -1){
            index = textString.indexOf(word, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }
}
