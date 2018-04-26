package linkedlist;

import java.util.ArrayList;
import java.util.List;

import hashtable.LinkedList;

public class KthLastElement {
	
public static void main(String[] args) {
	LinkedList<Integer> list = LinkedListUtil.createLinkedList(5);
	LinkedListUtil.traverse(list.getHeadNode());
	int element=getKthLastElement(3,list);
	System.out.println("");
	System.out.println("last 3rd element of the linked list is "+element);
}

private static int getKthLastElement(int lastKElement,LinkedList<Integer> list) {
	LinkedList<Integer> head=list.getHeadNode();
    list=head;
	List<LinkedList<Integer>> tempList =new ArrayList<>();
	for(int index=1;index <=lastKElement;index++  ) {
		LinkedList<Integer> node=list;
		tempList.add(node);
		list=list.getNext();
	}
	list=head;
	while(tempList.get(lastKElement-1).getNext() != null) {
		
		for(int index=0;index <lastKElement;index++  ) {
			LinkedList<Integer> node=tempList.get(index);
			node=node.getNext();
			tempList.remove(index);
			tempList.add(index, node);
		}
	}
	
	return tempList.get(0).getValue();
	
}



}
