package arrays;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompareTwoCollections {
	public static void main(String[] args) {
		Set<String> listOne = new HashSet<String>();
		listOne.add("R & D");
		listOne.add("IT");
		listOne.add("Business");
		
		Set<String> listTwo = new HashSet<String>();
		listTwo.add("IT");
		listTwo.add("R & D");
		
		System.out.println(compare(listOne, listTwo));
		
	}
	
	private static boolean compare(Collection<String> listOne ,Collection<String> listTwo ) {
		 Collection<String> commonItems = new HashSet<String>( listOne );
		 //at first differentItems is the union of both collections
         Collection<String> differentItems = new HashSet<String>();
         differentItems.addAll( listOne );
         differentItems.addAll( listTwo );
         //this give the intersection items of both collections
         commonItems.retainAll( listTwo );
         differentItems.removeAll( commonItems );
         System.out.println(commonItems);
         System.out.println(differentItems);
         return differentItems.size() == 0;
	}
}
