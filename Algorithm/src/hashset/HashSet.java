package hashset;

import java.util.List;

import hashtable.HashTable;

public class HashSet<E> {
  private final HashTable<E,E> map=new HashTable<>();
  
  public void add(E value) {
	  map.put(value, null);
  }
  
  public boolean isContained(E value) {
	  return map.containsKey(value);
  }
  
  public List<E> getElements(){
	  return map.getKeys();
  }
}
