package tree.avl;

import tree.BFS;
import tree.Tree;
import tree.TreeNode;
/*
 * 
balance factor = (max depth of left subtree) - (max depth of right subtree)
balance factor of a leaf node is 0
if absolute(balance factor) > 1 then the node is unbalanced

balance factor = -2 means more nodes in right subtree	
balance factor = 2 means more nodes in left subtree	

if balance factor = -2 
	go to the right child of the unbalanced node and find its balance factor
			if balance factor of right child = -1 RR rotation
			if balance factor of right child = 1 RL rotation
			
if balance factor = 2 
	go to the left child of the unbalanced node and find its balance factor
			if balance factor of left child = -1 LR rotation
			if balance factor of left child = 1 LL rotation	
			
after each insertion propagate the updated balance factor of the node to each parent till it reaches the root of the tree			
 * */
public class AVLTree<T extends Comparable<T>>  {

	private AVLTreeNode<T> root;

	
	public AVLTreeNode<T> search(T value) {
		return null;
	}

    public void insert(T value) {
    	insert(value,root);
    }
	
	private void insert(T value,AVLTreeNode<T> currentNode) {
		AVLTreeNode<T> newNode=null;
		if (currentNode == null) {
			 newNode = new AVLTreeNode<T>(value, null, null, null);
			root = newNode;
			System.out.println(newNode +" assigned as root");
		} else {
			while (currentNode != null) {
				if (value.compareTo(currentNode.getValue())< 0 ) {
					if (currentNode.getLeft() != null)
						currentNode =  (AVLTreeNode<T>) currentNode.getLeft();
					else {
						newNode = new AVLTreeNode<T>(value, null, null, null);
						currentNode.setLeft(newNode);
						newNode.setParent(currentNode);
						currentNode.incrementMaxLeftSubTreeDepth();
						System.out.println(newNode +" assigned as left child of "+currentNode);
						break;
					}
				} else {
					if (currentNode.getRight() != null)
						currentNode = (AVLTreeNode<T>) currentNode.getRight();
					else {
						newNode = new AVLTreeNode<T>(value, null, null, null);
						currentNode.setRight(newNode);
						newNode.setParent(currentNode);
						currentNode.incrementMaxRightSubTreeDepth();
						System.out.println(newNode +" assigned as right child of "+currentNode);
						break;
					}
				}
			}
			
			AVLTreeNode<T> parent=(AVLTreeNode<T>) newNode.getParent();
			while(parent!=null && parent.isBalanced()) {
				parent=(AVLTreeNode<T>) parent.getParent();
			}
			
			//if we have reached the root then the tree is balanced else we have found the unbalanced node
			if(parent!=null ) {
				System.out.println(parent +" is unbalanced");
				balanceTree(parent);
			}

		}
	
		
	}

	public void printTree(){
		new BFS<T>().bfs(root);
	}


	private void balanceTree(AVLTreeNode<T> unbalancedNode) {
		if (unbalancedNode.getBalanceFactor() >= 2) {
			// left subtree is unbalanced
			if (((AVLTreeNode<T>) (unbalancedNode.getLeft())).getBalanceFactor() >= 1) {
				// LL rotation needed	
				System.out.println("LL rotation required");	
				
				AVLTreeNode<T> parent = ((AVLTreeNode<T>) (unbalancedNode.getLeft()));//will be the new root of the balanced tree
				AVLTreeNode<T> rightNode = unbalancedNode;//will be the new right node of the balanced tree
				
				rightNode.setMaxLeftSubTreeDepth(parent.getMaxRightSubTreeDepth());
				parent.setMaxRightSubTreeDepth(rightNode.getMaxRightSubTreeDepth()+1);
				
				if (parent.getRight() != null) {
					//the right subtree of the left node is lesser than the parent
					rightNode.setLeft(parent.getRight());
				}
				else {
					rightNode.setLeft(null);
				}
				//setting up the links between parent and left node
				parent.setParent(rightNode.getParent());
				parent.setRight(rightNode);
				rightNode.setParent(parent);			
				
				//setting up root reference incase parent was the root node
				if(root==rightNode) {
					root=parent;
				}
				System.out.println("LL rotation required");	
				System.out.println(parent+" made root");
				System.out.println(rightNode+" made its right child");

			} else if (((AVLTreeNode<T>) (unbalancedNode.getLeft())).getBalanceFactor() <= -1) {
				// LR rotation needed																		
				System.out.println("LR rotation required");																			
				AVLTreeNode<T> leftNode = ((AVLTreeNode<T>) (unbalancedNode.getLeft()));
				AVLTreeNode<T> parent = ((AVLTreeNode<T>) (leftNode.getRight()));//will be the new root of the balanced tree
				AVLTreeNode<T> rightNode = unbalancedNode;//will be the new right node of the balanced tree
				
				rightNode.setMaxLeftSubTreeDepth(parent.getMaxRightSubTreeDepth());
				parent.setMaxRightSubTreeDepth(rightNode.getMaxRightSubTreeDepth()+1);
				
				if (parent.getRight() != null) {
					rightNode.setLeft(leftNode.getRight());
				} else {
					rightNode.setLeft(null);
				}
				parent.setParent(rightNode.getParent());
				parent.setRight(rightNode);
				parent.setLeft(leftNode);
				leftNode.setRight(null);
				rightNode.setParent(parent);
				
				rightNode.setLeft(null);
				if(root==rightNode)
					root=parent;
				System.out.println("LR rotation required");	
				System.out.println(parent+" made root");
				System.out.println(parent+" made its right child");
			}

		} else if (unbalancedNode.getBalanceFactor() <= -2) {// right subtree is
													// unbalanced
			if (((AVLTreeNode<T>) (unbalancedNode.getRight())).getBalanceFactor() >= -1) {// RR
																			// rotation
																			// needed
				AVLTreeNode<T> parent = ((AVLTreeNode<T>) (unbalancedNode.getRight()));//will be the new root of the balanced tree
				AVLTreeNode<T> leftNode = unbalancedNode;//will be the new left node of the balanced tree
				
				leftNode.setMaxRightSubTreeDepth(parent.getMaxLeftSubTreeDepth());
				parent.setMaxLeftSubTreeDepth(leftNode.getMaxLeftSubTreeDepth()+1);
				
				if (parent.getLeft() != null) {
					leftNode.setRight(parent.getLeft());
				}
				else {
					leftNode.setRight(null);	
				}
				parent.setLeft(leftNode);
				parent.setParent(leftNode.getParent());
				leftNode.setParent(parent);
				if(root==leftNode) {
					root=parent;
				}
				System.out.println("RR rotation required");	
				System.out.println(parent+" made root");
				System.out.println(leftNode+" made its left child");

			} else if (((AVLTreeNode<T>) (unbalancedNode.getRight())).getBalanceFactor() <= 1) {// RL
																					// rotation
																					// needed
				AVLTreeNode<T> rightNode = ((AVLTreeNode<T>) (unbalancedNode.getRight()));
				AVLTreeNode<T> parent = ((AVLTreeNode<T>) (rightNode.getLeft()));//will be the new root of the balanced tree
				AVLTreeNode<T> leftNode = ((AVLTreeNode<T>) (rightNode.getParent()));//will be the new left node of the balanced tree
				
				parent.setMaxLeftSubTreeDepth(leftNode.getMaxLeftSubTreeDepth()+1);
				leftNode.setMaxRightSubTreeDepth(parent.getMaxRightSubTreeDepth());
				
				if (parent.getRight() != null) {
					rightNode.setLeft(parent.getRight());
				}
				else {
					rightNode.setLeft(null);
				}
				

				parent.setLeft(leftNode);
				parent.setParent(leftNode.getParent());
				leftNode.setParent(parent);
				leftNode.setRight(null);
				if(root==leftNode) {
					root=parent;
				}
				System.out.println("RL rotation required");	
				System.out.println(parent+" made root");
				System.out.println(leftNode+" made its left child");
			}
		}
	}

}
