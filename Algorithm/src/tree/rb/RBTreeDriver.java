package tree.rb;

public class RBTreeDriver {

	public static void main(String[] args) {
		RBTree<Integer> rbTree = new RBTree<>();
		for (int i = 1; i <= 5; i++) {
			rbTree.insert(i);
		}
		rbTree.printTree();
		


		
	}

}
