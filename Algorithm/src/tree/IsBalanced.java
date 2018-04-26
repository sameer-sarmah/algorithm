package tree;

public class IsBalanced {

	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createTree();
        int balanceFactor= findBalanced(root);
        if(Math.abs(balanceFactor)<2)
        System.out.println("Balanced");
        else
        System.out.println("Unbalanced");	
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

		return maxLeftSubtreeDepth - maxRightSubtreeDepth;

	}
}
