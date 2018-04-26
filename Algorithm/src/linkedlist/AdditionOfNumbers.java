package linkedlist;

import hashtable.LinkedList;

public class AdditionOfNumbers {
	public static void main(String[] args) {
		LinkedList<Integer> num1 = LinkedListUtil.createLinkedList(2);
		LinkedList<Integer> num2 = LinkedListUtil.createLinkedList(3);
		LinkedList<Integer> result = null;
		LinkedListUtil.traverseReverse(num1);
		System.out.println(" ");
		LinkedListUtil.traverseReverse(num2);

		int sum = 0, quotient = 0;

		while (num1 != null || num2 != null) {
            if(num1 != null && num2 != null) {
    			sum = num1.getValue() + num2.getValue() + quotient;
    			quotient = sum / 10;
    			sum = sum % 10;
            }
            else if(num1 != null) {
            	sum= num1.getValue();
            }
            else {
            	sum= num2.getValue();
            }

			if (result == null) {
				result = new LinkedList<Integer>();
				result.setValue(sum);
			} else {
				LinkedList<Integer> node = new LinkedList<Integer>();
				node.setValue(sum);
				node.setPrev(result);
				result.setNext(node);
				result=result.getNext();
			}
			
			if(num1 !=null) {
				num1 = num1.getNext();
			}
			
			if(num2 !=null) {
				num2 = num2.getNext();
			}
			
		}
		System.out.println(" ");
		LinkedListUtil.traverseReverse(result);
	}
}
