package hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortingOnValue {

	public static void main(String[] args) {
		Map<String,Person>  map= new HashMap<>();
		map.put("Sameer", new Person("Sameer"));
		map.put("Mayuri", new Person("Mayuri"));
		System.out.println(sortOnValue(map));
	}
	
	private static Map<String,Person> sortOnValue(Map<String,Person> map){
		Set<Map.Entry<String,Person>> setOfEntries = map.entrySet();
		List<Map.Entry<String,Person>> listOfEntries = new ArrayList<>();
		listOfEntries.addAll(setOfEntries);
		Collections.sort(listOfEntries, new EntryComparator());
		Map<String,Person>  sortedMap = new LinkedHashMap<>();
		for(Map.Entry<String, Person> entry : listOfEntries) {
			System.out.println(String.format("key : %s , value : %s", entry.getKey(),entry.getValue().getName()));
			sortedMap.put(entry.getKey(),entry.getValue());
		}
		return sortedMap;
	} 

}

class Person{
	
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}	
}

class EntryComparator implements Comparator<Map.Entry<String,Person>>{

	@Override
	public int compare(Map.Entry<String, Person> entryOne, Map.Entry<String, Person> entryTwo) {
		Person personOne = entryOne.getValue();
		Person personTwo = entryTwo.getValue();
		return personOne.getName().compareTo(personTwo.getName());
	}
	
	
}

