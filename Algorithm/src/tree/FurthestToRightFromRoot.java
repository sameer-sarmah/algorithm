package tree;

public class FurthestToRightFromRoot {

	public static void main(String[] args) {
		TreeNode<Integer> tree = TreeCreator.createBinarySearchTree();
		int rightMostColumn = rightMost(tree,0);
		System.out.println(rightMostColumn);
	}

	private static int rightMost(TreeNode<Integer> currentNode,int column) {
		if(currentNode !=null) {
			System.out.println(String.format("%d at ,column :%d ",currentNode.value,column));
			int rightMostInLeftSubTree = 0;
			int rightMostInRightSubTree = 0;
			if(currentNode.left != null) {
				rightMostInLeftSubTree =rightMost(currentNode.left,column-1);
			}
			if(currentNode.right != null) {
				rightMostInRightSubTree = rightMost(currentNode.right, column+1);
			}
			return Math.max(Math.max(rightMostInLeftSubTree, rightMostInRightSubTree),column);
		}
		return 0;
	}
}
