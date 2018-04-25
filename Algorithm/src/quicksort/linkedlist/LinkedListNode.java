package quicksort.linkedlist;

public class LinkedListNode<T extends Comparable<T>>  {
private T value;
private LinkedListNode<T> next;

public LinkedListNode() {
	super();
}
public LinkedListNode(T value) {
	super();
	this.value = value;
}
public T getValue() {
	return value;
}
public void setValue(T value) {
	this.value = value;
}
public LinkedListNode<T> getNext() {
	return next;
}
public void setNext(LinkedListNode<T> next) {
	this.next = next;
}




}
