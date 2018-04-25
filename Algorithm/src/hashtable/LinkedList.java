package hashtable;

public class LinkedList<T> {
private T value;
private LinkedList<T> next;
private LinkedList<T> prev;

public T getValue() {
	return value;
}
public void setValue(T value) {
	this.value = value;
}
public LinkedList<T> getNext() {
	return next;
}
public void setNext(LinkedList<T> next) {
	this.next = next;
}

public LinkedList<T> getTailNode() {
	LinkedList<T> currentNode=this;
	while(currentNode.getNext() != null) {
		currentNode=currentNode.getNext();
	}
	return currentNode;
}

public LinkedList<T> getHeadNode() {
	LinkedList<T> currentNode=this;
	while(currentNode.getPrev() != null) {
		currentNode=currentNode.getPrev();
	}
	return currentNode;
}
public LinkedList<T> getPrev() {
	return prev;
}
public void setPrev(LinkedList<T> prev) {
	this.prev = prev;
}



}
