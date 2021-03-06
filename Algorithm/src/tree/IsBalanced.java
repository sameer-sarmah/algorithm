package tree;

public class IsBalanced {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createBinarySearchTree();
        findBalanced(root);
	
	}

	public static int findBalanced(TreeNode<Integer> node) {
		int maxLeftSubtreeDepth, maxRightSubtreeDepth;
		if (node.getLeft() == null) {
			maxLeftSubtreeDepth = 0;
		} else {
			maxLeftSubtreeDepth = findBalanced(node.getLeft());
		}

		if (node.getRight() == null) {
			maxRightSubtreeDepth = 0;
		} else {
			maxRightSubtreeDepth = findBalanced(node.getRight());
		}
		int balanceFactor=maxLeftSubtreeDepth - maxRightSubtreeDepth;
		if(Math.abs(balanceFactor)<2) {
	        System.out.println(node+"is Balanced");
		}
        else {
        	System.out.println(node+"is Unbalanced");
        }
		return Math.max(maxLeftSubtreeDepth, maxRightSubtreeDepth);

	}
}
