package tree.two_three_tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwoThreeTree<T extends Comparable<T>> {

	private TwoThreeTreeNode<T> root;
	private final String left="LEFT";
	private final String right="RIGHT";
	private final String current="CURRENT";

    public boolean isLeaf(TwoThreeTreeNode<T> node) {
		TwoThreeTreeNode<T> leftChild=node.getLeftChild();
		TwoThreeTreeNode<T> middleChild= node.getMiddleChild();
		TwoThreeTreeNode<T> rightChild= node.getRightChild();
		//a node with one value is either a leaf node or has exactly two children 
		//a node with two values is either a leaf node or has exactly three children
		if(leftChild == null && middleChild ==null && rightChild==null) {
			return true;
		}
		else {
			return false;
		}
    }
	
    public void insert(T value) {
    	insert(value,root);
    }
    
    
	private void insert(T value,TwoThreeTreeNode<T> currentNode) {
		TwoThreeTreeNode<T> node = new TwoThreeTreeNode<T>();
		node.addKey(value);
		if (currentNode == null) {
			root = node;
			System.out.println(node + " assigned as root");
		}
		else if(isLeaf(currentNode)) {
			if(currentNode.getKeys().size() == 1) {
				currentNode.addKey(value);
				System.out.println(currentNode + " inserted value "+value);
			}
			else if(currentNode.getKeys().size() == 2) {
				currentNode.addKey(value);
				System.out.println("splitting node "+currentNode);

				Map<String,TwoThreeTreeNode<T>> partitionedNode = this.partition(currentNode);
				TwoThreeTreeNode<T> left =partitionedNode.get(this.left);
				TwoThreeTreeNode<T> right =partitionedNode.get(this.right);
				currentNode =partitionedNode.get(this.current);
				
				TwoThreeTreeNode<T> joined = splitNode(currentNode);
				//joined.clearChildren();
				joined.addNode(left);
				left.setParent(joined);
				joined.addNode(right);
				right.setParent(joined);
			
			}
		}
		else {
			T minVal=currentNode.getMinKey();
			T maxVal=currentNode.getMaxKey();
			TwoThreeTreeNode<T> leftChild=currentNode.getLeftChild();
			TwoThreeTreeNode<T> middleChild= currentNode.getMiddleChild();
			TwoThreeTreeNode<T> rightChild= currentNode.getRightChild();
			
			if(leftChild !=null && value.compareTo(minVal) < 0) {
				insert(value,leftChild);	
			}
			else if(rightChild !=null && value.compareTo(maxVal) > 0) {
				insert(value,rightChild);
			}
			else if((leftChild !=null && value.compareTo(minVal) > 0) && (rightChild !=null && value.compareTo(maxVal) < 0) && middleChild!=null) {
				insert(value,middleChild);
			}
			

		}
		//printTree();
	}

	private Map<String,TwoThreeTreeNode<T>> partition(TwoThreeTreeNode<T> currentNode){
		
		TwoThreeTreeNode<T> leftChild=currentNode.getLeftChild();
		TwoThreeTreeNode<T> middleChild= currentNode.getMiddleChild();
		TwoThreeTreeNode<T> rightChild= currentNode.getRightChild();


		TwoThreeTreeNode<T> left = new TwoThreeTreeNode<>();
		T minKey = currentNode.getMinKey();
		left.addKey(minKey);
		currentNode.removeKey(minKey);
		
		if(leftChild != null) {
		left.addNode(leftChild);
		leftChild.setParent(left);
		currentNode.removeChild(leftChild);
		}
		
		if(middleChild != null) {
		left.addNode(middleChild);
		middleChild.setParent(left);
		currentNode.removeChild(middleChild);
		}

		TwoThreeTreeNode<T> right = new TwoThreeTreeNode<>();
		T maxKey = currentNode.getMaxKey();
		right.addKey(maxKey);
		currentNode.removeKey(maxKey);
		
		if(rightChild != null) {
		right.addNode(rightChild);
		rightChild.setParent(right);
		currentNode.removeChild(rightChild);
		}
		
		
		
		Map<String,TwoThreeTreeNode<T>> partitionedNode=new HashMap<>();
		partitionedNode.put(this.left, left);
		partitionedNode.put(this.right, right);
		partitionedNode.put(this.current, currentNode);
		return partitionedNode;
	}
	
	private TwoThreeTreeNode<T> splitNode(TwoThreeTreeNode<T> node) {
		TwoThreeTreeNode<T> parent = node;
			
		if (node.getParent() == null) {
			System.out.println(parent + " after splitting ");
			return parent;
		}else {
			parent=node.getParent();
			if(parent.getKeys().size() == 1) {
				T mergeWithParent=node.getMinKey();
				parent.addKey(mergeWithParent);
				parent.removeChild(node);
				System.out.println(parent + " after splitting ");
				return contains(mergeWithParent,parent);
			}
			else if (parent.getKeys().size() == 2) {
				T mergeWithParent=node.getMinKey();
				parent.addKey(mergeWithParent);
				parent.removeChild(node);
				Map<String,TwoThreeTreeNode<T>> partitionedNode = this.partition(parent);
				TwoThreeTreeNode<T> left =partitionedNode.get(this.left);
				TwoThreeTreeNode<T> right =partitionedNode.get(this.right);
				parent =partitionedNode.get(this.current);
				TwoThreeTreeNode<T> joined=splitNode(parent);
				joined.addNode(left);
				left.setParent(joined);
				joined.addNode(right);
				right.setParent(joined);
				return contains(mergeWithParent,joined);
			}
			else {
				return node;
			}
		}

	}

	public TwoThreeTreeNode<T> contains(T key, TwoThreeTreeNode<T> node) {
		if (node == null) {
			return null;
		} else if (node.getKeys().contains(key)) {
			return node;
		} else if (node.hasChildren()) {
			if (node.getKeys().size() == 1) {

				if (key.compareTo(node.getKeys().get(0)) < 0) {
					return contains(key, node.getLeftChild());
				} else {
					return contains(key, node.getRightChild());
				}
			} else if (node.getKeys().size() == 2) {
				T minKey = node.getMinKey();
				T maxKey = node.getMaxKey();
				if (key.compareTo(minKey) < 0) {
					return contains(key, node.getLeftChild());
				} else if (key.compareTo(maxKey) > 0) {
					return contains(key, node.getRightChild());
				} else if ((key.compareTo(minKey) > 0) && (key.compareTo(maxKey) < 0)
						&& node.getMiddleChild() != null) {
					return contains(key, node.getMiddleChild());
				} else {
					return null;
				}
			}

		} else {
			return null;
		}
		return null;
	}

	public void printTree() {
		new BFS().bfs(root);
	}

	class BFS {
		private Queue<TwoThreeTreeNode<T>> queue = new LinkedBlockingQueue<>();

		public void bfs(TwoThreeTreeNode<T> node) {
			if(node != null) {
			System.out.println(node.getKeys());
			TwoThreeTreeNode<T> leftChild=node.getLeftChild();
			if(leftChild != null) {
				queue.add(leftChild);
			}
			TwoThreeTreeNode<T> middleChild= node.getMiddleChild();
			if(middleChild != null) {
				queue.add(middleChild);
			}
			
			TwoThreeTreeNode<T> rightChild= node.getRightChild();
			if(rightChild != null) {
				queue.add(rightChild);
			}
			bfs(queue.poll());
			}
		}
	}

}
