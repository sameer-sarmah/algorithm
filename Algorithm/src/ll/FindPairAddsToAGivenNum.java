package ll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindPairAddsToAGivenNum {

	public static void main(String[] args) {
		LinkedListNode<Integer> list = LinkedListUtil.createLL(Arrays.asList(1,2,3,4,5,6));
		int requiredSum =10;
		System.out.println(findPair(list, requiredSum));
	}

	private static List<Integer> findPair(LinkedListNode<Integer> list,int requiredSum){
		Set<Integer> numbers = new HashSet<>();
		LinkedListNode<Integer> current = list;
		List<Integer> pair = new ArrayList<>();
		while(current != null) {
			if(numbers.contains(requiredSum - current.getValue())) {
				pair.add(current.getValue());
				pair.add(requiredSum - current.getValue());
				return pair;
			}
			numbers.add(current.getValue());
			current =current.getNext();
		}
		return pair;
	} 
	
}
