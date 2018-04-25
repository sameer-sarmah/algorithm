package tree;

public class BST {

	public static <T extends Comparable<T>> TreeNode<T> search(TreeNode<T> root, T key) {
		if (root.getValue().compareTo(key) == 0) {
			return root;
		} else if (root.getValue().compareTo(key) > 0 && root.getLeft() != null) {
			return search(root.getLeft(), key);
		} else if (root.getValue().compareTo(key) < 0 && root.getRight() != null) {
			return search(root.getRight(), key);
		} else {
			return null;
		}
	}

	public static <T extends Comparable<T>> TreeNode<T> insert(TreeNode<T> root, T key) {
		if (root == null) {
			TreeNode<T> node = new TreeNode<T>(key);
			return node;
		} else if (root.getValue().compareTo(key) > 0 && root.getLeft() == null) {
			TreeNode<T> node = new TreeNode<T>(key);
			node.setParent(root);
			root.setLeft(node);
			return node;
		} else if (root.getValue().compareTo(key) > 0 && root.getLeft() != null) {
			return insert(root.getLeft(), key);
		} else if (root.getValue().compareTo(key) < 0 && root.getRight() == null) {
			TreeNode<T> node = new TreeNode<T>(key);
			node.setParent(root);
			root.setRight(node);
			return node;
		} else if (root.getValue().compareTo(key) < 0 && root.getRight() != null) {
			return insert(root.getRight(), key);
		} else {
			return null;
		}
	}

	public static <T extends Comparable<T>> TreeNode<T> arrayToBST(T[] numbers) {
		TreeNode<T> root = null;
		for (T element : numbers) {
			TreeNode<T> node = insert(root, element);
			if (root == null) {
				root = node;
			}
		}
		return root;
	}

}
