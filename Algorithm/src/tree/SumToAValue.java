package tree;

//
public class SumToAValue {
	public static void main(String[] args) {
		TreeNode<Integer> root = TreeCreator.createBinarySearchTree2();
		sumToGivenValue(root,12,12,"");
	}

	public static void sumToGivenValue(TreeNode<Integer> node, int sum,int requiredSum,String path) {
		
		if (node.getValue()==sum) {
			System.out.println("Found,path to get the sum "+sum+" is "+path+" "+node.getValue());
		}
		
		if (node.getValue()-requiredSum==0) {
			System.out.println("Found,path to get the sum "+sum+" is "+path+" "+node.getValue());
		}
		
		
		if (node.getLeft() != null) {
			sumToGivenValue(node.getLeft(),sum, sum - node.getValue()," "+node.getValue());
		}
		if(sum - node.getValue()!=requiredSum - node.getValue()) {
		if (node.getLeft() != null) {
			sumToGivenValue(node.getLeft(),sum, requiredSum - node.getValue(),path+" "+node.getValue());
		}
		}
		
		if (node.getRight() != null) {
			sumToGivenValue(node.getRight(),sum, sum - node.getValue()," "+node.getValue());
		}
		if (node.getRight() != null) {
			sumToGivenValue(node.getRight(),sum, requiredSum - node.getValue(),path+" "+node.getValue());
		}



	}
}
