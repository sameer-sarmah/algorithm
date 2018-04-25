import java.util.Deque;
import java.util.ArrayDeque;
public class DequeDemo {

	public static void main(String[] args) {
		Deque<Integer> deque=new ArrayDeque<>();
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		deque.offerFirst(4);
		deque.offerFirst(5);
		
		deque.addLast(11);
		deque.addLast(12);
		deque.addLast(13);
		deque.offerLast(14);
		deque.offerLast(15);
		
		deque.forEach((Integer element)->{
			System.out.println(element.intValue());
		});

		deque.removeFirst();//last inserted,as the head is a stack
		deque.removeLast();//first inserted,as the tail is a queue
		
		System.out.println("After removal from head and tail");
		
		deque.forEach((Integer element)->{
			System.out.println(element.intValue());
		});
	}

}
