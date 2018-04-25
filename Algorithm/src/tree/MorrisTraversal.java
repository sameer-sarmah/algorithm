package tree;

/*
 * if are is no left child then print the node and move to right and repeat
 * else
 *   find the predecessor ,and set the right of the predecessor to the node
 *   move to left and repeat
 * */
public class MorrisTraversal {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
		traverse(root);
	}

	private static <T extends Comparable<T>> TreeNode<T> findPredecessor(TreeNode<T> node) {
		TreeNode<T> currentNode = node.getLeft();
		if (currentNode != null) {
			while (currentNode.getRight() != null && currentNode.getRight() != node) {
				currentNode = currentNode.getRight();
			}
			return currentNode;
		}
		return null;
	}

	public static <T extends Comparable<T>> void traverse(TreeNode<T> node) {
		if (node.getLeft() == null) {
			System.out.print(node.getValue() + " ");
			if(node.getRight() != null) {
			traverse(node.getRight());
			}
		} else {
			TreeNode<T> predecessor = findPredecessor(node);
			if (predecessor != null && predecessor.getRight() != node) {
				predecessor.setRight(node);//establish a link so that we can come back to this node later,like call stack of programs
				traverse(node.getLeft());
			} else if (predecessor != null && predecessor.getRight() == node) {
				System.out.print(node.getValue() + " ");
				predecessor.setRight(null);//removing the link as it is no longer needed
				traverse(node.getRight());

			}

		}
	}

}
