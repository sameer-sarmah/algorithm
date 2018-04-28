package tree;

public class RootToLeafPath {

	public static void main(String[] args) {
		TreeNode<Integer> root=TreeCreator.createBinarySearchTree();
		rootToLeafPath(root,"");
	}
	
	public static void rootToLeafPath(TreeNode<Integer> node,String path){
		if(node.getLeft() != null ) {
			rootToLeafPath(node.getLeft(),path+" "+node.getValue());
		}
		if(node.getRight() != null ) {
			rootToLeafPath(node.getRight(),path+" "+node.getValue());
		}
		
		if(node.getLeft() == null && node.getRight() == null) {
			System.out.println(node.getValue()+" is a leaf node,sum till here is "+(path+" "+node.getValue()));
		}
		
	}
}
