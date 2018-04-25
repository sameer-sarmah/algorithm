package algorithm.tree.two_three_tree;

public class TwoThreeTreeDriver {
	public static void main(String[] args) {
		TwoThreeTree<Integer> _23Tree = new TwoThreeTree<>();
		for (int i = 1; i <= 11; i++) {
			_23Tree.insert(i);
		}
		
		_23Tree.printTree();
	}
}
