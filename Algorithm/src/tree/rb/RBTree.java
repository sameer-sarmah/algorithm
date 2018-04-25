package tree.rb;

import tree.BFS;
import tree.TreeNode;

/*
 * newly created nodes are always red

1.If the root is empty insert the node ,recolor it to black and make it the root
2.if parent is black then add it 
3.if parent is red 
	a)the parent's sibling is black or absent then rotate(LL,LR,RL,RR) and recolor
	b)the parent's sibling is red then recolor,i.e make the parent and parent's sibling to black
	and parent's parent to red then repeat.If parent's parent is the root dont make it red.
*/
public class RBTree<T extends Comparable<T>> {
	private RBTreeNode<T> root;

	public TreeNode<T> insert(T value) {
		RBTreeNode<T> newNode = new RBTreeNode<>(value, null, null, null);
		if (root == null) {
			root = newNode;
			newNode.setBlack();
			System.out.println(newNode + " assigned as root");
		} else {

			RBTreeNode<T> currentNode = root;
			while (currentNode != null) {
				if (newNode.getValue().compareTo(currentNode.getValue()) < 0) {
					if (currentNode.getLeft() != null)
						currentNode = (RBTreeNode<T>) currentNode.getLeft();
					else {
						currentNode.setLeft(newNode);
						newNode.setParent(currentNode);
						System.out.println(newNode + " assigned as left child of " + currentNode);
                        break;

					}

				} else {
					if (currentNode.getRight() != null)
						currentNode = (RBTreeNode<T>) currentNode.getRight();
					else {
						currentNode.setRight(newNode);
						newNode.setParent(currentNode);
						System.out.println(newNode + " assigned as right child of " + currentNode);
						break;
					}
				}
			}
			balanceIfUnbalanced(newNode);

		}
		return newNode;
	}

	// pull down black color from parent to its chilren and then make the parent red
	private void recolor(RBTreeNode<T> violatorNode) {
		RBTreeNode<T> sibling = getSibling(violatorNode);
		RBTreeNode<T> violatorParent = (RBTreeNode<T>) violatorNode.getParent();
		if (violatorParent != root) {
			violatorParent.setRed();
		}
		sibling.setBlack();
		violatorNode.setBlack();
	}

	public void printTree() {
		new BFS<T>().bfs(root);
	}



	private RBTreeNode<T> getSibling(RBTreeNode<T> node) {
		RBTreeNode<T> parent = (RBTreeNode<T>) node.getParent();
		if (parent.getValue().compareTo(node.getValue()) >= 0) {
			return (RBTreeNode<T>) parent.getRight();
		} else if (parent.getValue().compareTo(node.getValue()) < 0) {
			return (RBTreeNode<T>) parent.getLeft();
		}
		return null;
	}

	private void performLL_Rotation(RBTreeNode<T> violatorNode) {

		RBTreeNode<T> leftNode = ((RBTreeNode<T>) (violatorNode.getLeft()));
		RBTreeNode<T> parent = ((RBTreeNode<T>) (violatorNode.getParent()));
		parent.setLeft(violatorNode.getRight());
		violatorNode.setRight(parent);
		violatorNode.setParent(parent.getParent());
		if (parent.getParent() != null) {
			parent.getParent().setLeft(violatorNode);
		}
		parent.setParent(violatorNode);
		if (root == parent) {
			root = violatorNode;
		}
		System.out.println("LL rotation required");
		System.out.println(violatorNode + " made root");
		System.out.println(parent + " made its right child");
		parent.setRed();
		leftNode.setRed();
		violatorNode.setBlack();

	}

	private void performLR_Rotation(RBTreeNode<T> violatorNode) {
		RBTreeNode<T> rightNode = ((RBTreeNode<T>) (violatorNode.getRight()));
		RBTreeNode<T> parent = ((RBTreeNode<T>) (violatorNode.getParent()));
		rightNode.setParent(parent.getParent());
		if (parent.getParent() != null) {
			parent.getParent().setLeft(rightNode);
		}
		parent.setParent(rightNode);
		violatorNode.setParent(rightNode);
		violatorNode.setRight(rightNode.getLeft());
		parent.setLeft(rightNode.getRight());
		rightNode.setRight(parent);
		rightNode.setLeft(violatorNode);
		if (root == parent)
			root = rightNode;
		System.out.println("LR rotation required");
		System.out.println(rightNode + " made root");
		System.out.println(parent + " made its right child");
		System.out.println(violatorNode + " made its left child");
		parent.setRed();
		rightNode.setBlack();
	}

	private void performRL_Rotation(RBTreeNode<T> violatorNode) {
		RBTreeNode<T> leftNode = ((RBTreeNode<T>) (violatorNode.getLeft()));
		RBTreeNode<T> parent = ((RBTreeNode<T>) (violatorNode.getParent()));
		leftNode.setParent(parent.getParent());
		if (parent.getParent() != null) {
			parent.getParent().setRight(leftNode);
		}
		parent.setParent(leftNode);
		violatorNode.setParent(leftNode);
		parent.setRight(leftNode.getLeft());
		violatorNode.setLeft(leftNode.getRight());
		leftNode.setLeft(parent);
		leftNode.setRight(violatorNode);

		if (root == parent) {
			root = leftNode;
		}
		System.out.println("RL rotation required");
		System.out.println(leftNode + " made root");
		System.out.println(parent + " made its left child");
		System.out.println(violatorNode + " made its right child");
		parent.setRed();
		violatorNode.setRed();
		leftNode.setBlack();
	}

	private void performRR_Rotation(RBTreeNode<T> violatorNode) {
		RBTreeNode<T> rightNode = ((RBTreeNode<T>) (violatorNode.getRight()));
		RBTreeNode<T> parent = ((RBTreeNode<T>) (violatorNode.getParent()));
		parent.setRight(violatorNode.getLeft());
		violatorNode.setLeft(parent);
		violatorNode.setParent(parent.getParent());
		if (parent.getParent() != null) {
			parent.getParent().setRight(violatorNode);
		}
		parent.setParent(violatorNode);
		if (root == parent) {
			root = violatorNode;

		}
		System.out.println("RR rotation required");
		System.out.println(violatorNode + " made root");
		System.out.println(parent + " made its left child");
		parent.setRed();
		violatorNode.setBlack();
		rightNode.setRed();

	}

	// accepts the node which violates any RB trees constraints,e.g when a new node
	// (which is red) is inserted as a child of red node
	// then the red node is the violator
	private void balanceTree(RBTreeNode<T> violatorNode) {
		// left sub tree is unbalanced
		if (violatorNode.getParent().getValue().compareTo(violatorNode.getValue()) > 0 && violatorNode.isRed()) {

			// LL rotation needed
			if (violatorNode.getLeft() != null && ((RBTreeNode<T>) (violatorNode.getLeft())).isRed()) {
				RBTreeNode<T> sibling = getSibling(violatorNode);
				if (sibling == null || !sibling.isRed()) {
					performLL_Rotation(violatorNode);
				} else if (sibling != null || sibling.isRed()) {
					recolor(violatorNode);
				}

			}
			// LR rotation needed
			else if (violatorNode.getRight() != null && ((RBTreeNode<T>) (violatorNode.getRight())).isRed()) {

				RBTreeNode<T> sibling = getSibling(violatorNode);
				if (sibling == null || !sibling.isRed()) {
					System.out.println("LR rotation required");
					performLR_Rotation(violatorNode);
				} else if (sibling != null || sibling.isRed()) {
					recolor(violatorNode);
				}
			}

		}
		// right subtree is unbalanced
		else if (violatorNode.getParent().getValue().compareTo(violatorNode.getValue()) < 0 && violatorNode.isRed()) {

			if (violatorNode.getRight() != null && ((RBTreeNode<T>) (violatorNode.getRight())).isRed()) {

				RBTreeNode<T> sibling = getSibling(violatorNode);
				// RR rotation required
				if (sibling == null || !sibling.isRed()) {
					performRR_Rotation(violatorNode);
				} else if (sibling != null || sibling.isRed()) {
					recolor(violatorNode);
				}

			} else if (violatorNode.getLeft() != null && ((RBTreeNode<T>) (violatorNode.getLeft())).isRed()) {
				// RL rotation required
				RBTreeNode<T> sibling = getSibling(violatorNode);
				if (sibling == null || !sibling.isRed()) {
					performRL_Rotation(violatorNode);
				} else if (sibling != null || sibling.isRed()) {
					recolor(violatorNode);
				}
			}
		}
	}
	
	private void balanceIfUnbalanced(RBTreeNode<T> node){
		if(node.isRed()) {
			if(((RBTreeNode<T>)node.getParent()).isRed()) {
				balanceTree((RBTreeNode<T>)node.getParent());
			}
		}
		else if(node.getParent() != null){
			balanceIfUnbalanced((RBTreeNode<T>)node.getParent());
		}

		}

	public RBTreeNode<T> search(T value){
		return search(value,root);
	}
	
	private RBTreeNode<T> search(T value,RBTreeNode<T>  currentNode) {
		if(currentNode.getValue().compareTo(value) == 0) {
			return currentNode;
		}
		else if(currentNode.getValue().compareTo(value)<0) {
			return search(value,(RBTreeNode<T>)currentNode.getRight());
		}
		else {
			return search(value,(RBTreeNode<T>)currentNode.getLeft());
		}
	}



}
