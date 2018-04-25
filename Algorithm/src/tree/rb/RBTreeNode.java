package tree.rb;

import tree.TreeNode;

public class RBTreeNode<T extends Comparable<T>>  extends TreeNode<T> {

    private NodeColor color;
    private int maxBlockNodeCountInLST,maxBlockNodeCountInRST;
	public RBTreeNode(T value, RBTreeNode<T> left, RBTreeNode<T> right, RBTreeNode<T> parent) {
		super(value, left, right, parent);
		color=NodeColor.RED;
	}

	public void setRed()
	{
		color=NodeColor.RED;
	}
	
	public void setBlack()
	{
		color=NodeColor.BLACK;
	}
	
	
	public boolean isRed() {
		return color==NodeColor.RED;
	}

	@Override
	public String toString() {
		return "RBTreeNode [color=" + color + ", value=" + value + "]";
	}


	
	


	
}
