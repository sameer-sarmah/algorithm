package hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HashTable<K,V> {
	private final int capacity=10;
    private final int denominator=7;//a big prime number,we are assuming that the size of hashtable will be less than this number.
    private List<LinkedList<Entry<K,V>>> keys=new ArrayList<>(capacity);
    
    
	
	public HashTable() {
		super();
		IntStream.rangeClosed(0, capacity-1).forEach((int i)->keys.add(null));
	}

	public void put(K key,V value) {
		int hashCode =key.hashCode();
		int index=hashCode%denominator;
		Entry<K,V> entry=new Entry<>(key,value);
		if(keys.get(index) == null) {
			LinkedList<Entry<K,V>> entries= new LinkedList<>();
			entries.setValue(entry);
			keys.add(index, entries);
		}
		else {
			LinkedList<Entry<K,V>> tailNode=keys.get(index).getTailNode();
			LinkedList<Entry<K,V>> entryNode= new LinkedList<>();
			entryNode.setValue(entry);
			tailNode.setNext(entryNode);
			entryNode.setPrev(tailNode);
		}
	}
	
	public V get(K key) {
		int hashCode =key.hashCode();
		int index=hashCode%denominator;
		if(keys.get(index) != null) {
			LinkedList<Entry<K,V>> headNode=keys.get(index).getHeadNode();
			LinkedList<Entry<K,V>> currentNode=headNode;
			while(currentNode != null) {
				Entry<K,V> entry=currentNode.getValue();
				if(entry.getKey().equals(key)) {
					return entry.getValue();
				}
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		if(this.get(key)!=null)
			return true;
		else
			return false;
	}
	
	public List<K> getKeys(){
		List<K> allKeys=new ArrayList<>();
		for(int index=0;index<capacity;index++) {
			if(keys.get(index) != null) {
				LinkedList<Entry<K,V>> headNode=keys.get(index).getHeadNode();
				LinkedList<Entry<K,V>> currentNode=headNode;
				while(currentNode != null) {
					Entry<K,V> entry=currentNode.getValue();
					allKeys.add(entry.getKey());
					currentNode = currentNode.getNext();
				}
			}
		}
		return allKeys;
	}
}
