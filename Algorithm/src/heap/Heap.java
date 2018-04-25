package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import tree.BFS;
import tree.Tree;
import tree.TreeNode;

public class Heap<T extends Comparable<T>> implements Tree<T> {
	private TreeNode<T> root,lastInserted;
	private Queue<TreeNode<T>> queue = new LinkedBlockingQueue<>();
	private List<TreeNode<T>> visited = new ArrayList<>();

	enum Position {
		LEFT, RIGHT
	};

	@Override
	public TreeNode<T> search(T value) {

		TreeNode<T> traversalNode = root;
		queue.clear();
		visited.clear();
		while (traversalNode.getValue() != value) {
			if (traversalNode.getLeft() != null && !visited.contains(traversalNode.getLeft())
					&& !queue.contains(traversalNode.getLeft())) {
				queue.add(traversalNode.getLeft());
			}
			if (traversalNode.getRight() != null && !visited.contains(traversalNode.getRight())
					&& !queue.contains(traversalNode.getRight())) {
				queue.add(traversalNode.getRight());
			}

			if (!queue.isEmpty())
				traversalNode = queue.poll();
		}
		if (traversalNode != null && traversalNode.getValue() == value) {
			System.out.println(" Value found in node " + traversalNode);
			return traversalNode;
		} else
			return null;
	}

	@Override
	public boolean delete(T value) {
		return false;
	}

	public void getMinNode() {
		if(lastInserted!=null){
			TreeNode<T> leafParent=lastInserted.getParent();
			TreeNode<T> childLeft = root.getLeft();
			TreeNode<T> childRight = root.getRight();
			if(childLeft!=null)
			childLeft.setParent(lastInserted);
			if(childRight!=null)
			childRight.setParent(lastInserted);
			if (isLeftChild(leafParent, lastInserted)) {
				leafParent.setLeft(null);
			} else {
				leafParent.setRight(null);
			}
			lastInserted.setLeft(childLeft);
			lastInserted.setRight(childRight);
			root=lastInserted;
			while (!heapify()) {
				System.out.println("Heapify again");
			}

			printHeap();
		}
		
	}

	@Override
	public TreeNode<T> insert(T value) {
		TreeNode<T> node = new TreeNode<>(value, null, null, null);
		if (root == null) {
			root = node;
			System.out.println(node + " added as root");
		} else {
			TreeNode<T> traversalNode = root;
			queue.clear();
			visited.clear();
			while (traversalNode.getLeft() != null && traversalNode.getRight() != null) {
				if (traversalNode.getLeft() != null && !visited.contains(traversalNode.getLeft())
						&& !queue.contains(traversalNode.getLeft())) {
					queue.add(traversalNode.getLeft());
				}
				if (traversalNode.getRight() != null && !visited.contains(traversalNode.getRight())
						&& !queue.contains(traversalNode.getRight())) {
					queue.add(traversalNode.getRight());
				}

				if (!queue.isEmpty())
					traversalNode = queue.poll();
			}

			if (traversalNode.getLeft() != null) {
				traversalNode.setRight(node);
				node.setParent(traversalNode);
				System.out.println(node + " added as right child of " + traversalNode);
			} else if (traversalNode.getLeft() == null) {
				traversalNode.setLeft(node);
				node.setParent(traversalNode);
				System.out.println(node + " added as left child of " + traversalNode);
			}
		}

		while (!heapify()) {
			System.out.println("Heapify again");
		}

		printHeap();
		return node;
	}

	private void printHeap() {
		new BFS<T>().bfs(root);
	}

	private boolean heapify() {
		boolean isHeap = true;
		TreeNode<T> traversalNode = root;
		queue.clear();
		visited.clear();
		while (traversalNode.getLeft() != null || traversalNode.getRight() != null) {
			if (traversalNode.getLeft() != null && !visited.contains(traversalNode.getLeft())
					&& !queue.contains(traversalNode.getLeft())) {
				queue.add(traversalNode.getLeft());
				if (traversalNode.getValue().compareTo(traversalNode.getLeft().getValue())>0) {
					System.out.println("Swapping " + traversalNode + " and " + traversalNode.getLeft());
					swap(traversalNode, traversalNode.getLeft(), Position.LEFT);
					isHeap = false;
				}
			}
			if (traversalNode.getRight() != null && !visited.contains(traversalNode.getRight())
					&& !queue.contains(traversalNode.getRight())) {
				queue.add(traversalNode.getRight());
				if (traversalNode.getValue().compareTo(traversalNode.getRight().getValue())>0) {
					System.out.println("Swapping " + traversalNode + " and " + traversalNode.getRight());
					swap(traversalNode, traversalNode.getRight(), Position.RIGHT);
					isHeap = false;
				}
			}

			if (!queue.isEmpty())
				traversalNode = queue.poll();
		}
		return isHeap;
	}

	private void swap(TreeNode<T> parent, TreeNode<T> child, Position pos) {
		TreeNode<T> childLeft = child.getLeft();
		TreeNode<T> childRight = child.getRight();
		TreeNode<T> parentParent = parent.getParent();
		TreeNode<T> parentLeft = parent.getLeft();
		TreeNode<T> parentRight = parent.getRight();
		if (!isLeftChild(parent, child) && parentLeft != null)
			parentLeft.setParent(child);
		if (isLeftChild(parent, child) && parentRight != null)
			parentRight.setParent(child);
		
		child.setParent(parentParent);
		parent.setLeft(childLeft);
		parent.setRight(childRight);
		parent.setParent(child);
		if(childLeft!=null){
			childLeft.setParent(parent);
		}
		if(childRight!=null){
			childRight.setParent(parent);
		}
		
		if (pos.equals(Position.LEFT)) {
			child.setLeft(parent);
			child.setRight(parentRight);
		} else {
			child.setLeft(parentLeft);
			child.setRight(parent);
		}
		if (parentParent != null) {
			if (isLeftChild(parentParent, parent)) {
				parentParent.setLeft(child);
			} else {
				parentParent.setRight(child);
			}
		}

		if (root == parent) {
			root = child;
			System.out.println(child + " assigned as root");
		}
		if(parent.getRight()==null && parent.getLeft()==null)
		lastInserted=parent;
	}

	private boolean isLeftChild(TreeNode<T> parent, TreeNode<T> child) {
		return parent.getLeft() == child;
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	public TreeNode<T> getLastInserted() {
		return lastInserted;
	}

}
