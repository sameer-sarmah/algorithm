package heap;

public class HeapDriver {
	public static void main(String[] args) {
		HeapTree<Integer> heap = new HeapTree<>();
		for (int i = 5; i >0; i--) {
			heap.insert(i);
		}
		//heap.search(7);
//		System.out.println(heap.getLastInserted());
//		heap.getMinNode();
	}
}
