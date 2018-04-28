package tree;

public class RootToLeafSum {

	public static void main(String[] args) {
		TreeNode<Integer> root=TreeCreator.createBinarySearchTree();
		rootToLeafSum(root,0);
	}
	
	public static void rootToLeafSum(TreeNode<Integer> node,int sum){
		if(node.getLeft() != null ) {
			rootToLeafSum(node.getLeft(),sum+node.getValue());
		}
		if(node.getRight() != null ) {
			rootToLeafSum(node.getRight(),sum+node.getValue());
		}
		
		if(node.getLeft() == null && node.getRight() == null) {
			System.out.println(node.getValue()+" is a leaf node,sum till here is "+(sum+node.getValue()));
		}
		
	}

}
