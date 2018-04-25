package algorithm.heap;

public class HeapDriver {
	public static void main(String[] args) {
		Heap<Integer> heap = new Heap<>();
		for (int i = 5; i >0; i--) {
			heap.insert(i);
		}
		//heap.search(7);
//		System.out.println(heap.getLastInserted());
//		heap.getMinNode();
	}
}
