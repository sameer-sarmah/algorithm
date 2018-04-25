package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrPermutation {
    
    public static void main(String[] arg) {
        List<Character> list =new ArrayList<>();

        {
            list.add('A');    
            list.add('B');  
            list.add('C');  
        }
  new StrPermutation().permute(list, 0, list.size());
    }

    private void permute(List<Character> word, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            System.out.println(word);
            return;
        } else {
            List<Character> wordCloned=deepCopy(word);
            for (int swapRightIndex = leftIndex; swapRightIndex < rightIndex; swapRightIndex++) {
                Collections.swap(wordCloned, leftIndex, swapRightIndex);//wordCloned="abc" left=0 i=1 after swap bac
                permute(wordCloned, leftIndex + 1, rightIndex);
            }
        }
    }
    
    private List<Character> deepCopy(List<Character> word)
    {  List<Character> wordCloned =new ArrayList<>();
        for(Character c:word)
        {
        	wordCloned.add(c); 
        }
        return wordCloned;
    }
}
