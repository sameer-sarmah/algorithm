package tree.avl;

import tree.TreeNode;

public class AVLTreeNode<T extends Comparable<T>>  extends TreeNode<T>{
   private int maxLeftSubTreeDepth=0,maxRightSubTreeDepth=0;
	public AVLTreeNode(T value, AVLTreeNode<T> left, AVLTreeNode<T> right, AVLTreeNode<T> parent) {
		super(value, left, right, parent);
	}
	public int getBalanceFactor() {
		return (maxLeftSubTreeDepth-maxRightSubTreeDepth);
	}

	public int getMaxLeftSubTreeDepth() {
		return maxLeftSubTreeDepth;
	}

	public void incrementMaxLeftSubTreeDepth() {
		this.maxLeftSubTreeDepth++;
		if(this.parent != null && this.isBalanced())
		((AVLTreeNode<T>)this.parent).incrementMaxLeftSubTreeDepth();
	}
	
	public void decrementMaxLeftSubTreeDepth() {
		this.maxLeftSubTreeDepth--;
		if(this.parent != null && this.isBalanced())
		((AVLTreeNode<T>)this.parent).decrementMaxLeftSubTreeDepth();
	}
	
	public void incrementMaxRightSubTreeDepth() {
		this.maxRightSubTreeDepth++;
		if(this.parent != null && this.isBalanced())
		((AVLTreeNode<T>)this.parent).incrementMaxRightSubTreeDepth();
	}
	
	public void decrementMaxRightSubTreeDepth() {
		this.maxRightSubTreeDepth--;
		if(this.parent != null && this.isBalanced())
		((AVLTreeNode<T>)this.parent).decrementMaxRightSubTreeDepth();
	}
	
	public int getMaxRightSubTreeDepth() {
		return maxRightSubTreeDepth;
	}

	
	public void setMaxLeftSubTreeDepth(int maxLeftSubTreeDepth) {
		this.maxLeftSubTreeDepth = maxLeftSubTreeDepth;
	}
	public void setMaxRightSubTreeDepth(int maxRightSubTreeDepth) {
		this.maxRightSubTreeDepth = maxRightSubTreeDepth;
	}
	public boolean isBalanced() {
		return Math.abs(getBalanceFactor())<2;
	}
	
	@Override
	public String toString() {
		return "AVLTreeNode [balanceFactor=" + (maxLeftSubTreeDepth-maxRightSubTreeDepth) + ", and  value=" + this.getValue() + "]";
	}
	

}
