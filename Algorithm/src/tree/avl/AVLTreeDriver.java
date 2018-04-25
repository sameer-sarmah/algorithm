package tree.avl;

public class AVLTreeDriver {

	public static void main(String[] args) {
		AVLTree<Integer> avltree = new AVLTree<>();
		for (int i = 1; i <= 5; i++) {
			System.out.println("------------inserting "+i);
			avltree.insert(i);
		}
		System.out.println("-------------printing--------------- ");
        avltree.printTree();
		
	}

}
