package algorithm.tree.two_three_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TwoThreeTreeNode<T extends Comparable<T>> {
	public static final int MAX_SIZE = 3;
	private List<T> keys = new ArrayList<>();
	/*
	 * the reason behind choosing a List to store all the children vs storing each child as individual reference 
	 * is to make the node more generic as well as to let this node handle the operations of setting and getting the child
	 * e.g getLeftChild computes the left child from the list of children
	 * and the NodeComparator sets the min node as left child,the consumer should not care about this internal computations.
	 * */
	private List<TwoThreeTreeNode<T>> nodeArr = new ArrayList<>();
	private TwoThreeTreeNode<T> parent;

	public List<T> getKeys() {
		return keys;
	}

	public void addKey(T key) {
		if (this.keys.size() < MAX_SIZE) {
			this.keys.add(key);
			Collections.sort(this.keys);
		}
	}


	public void addNode(TwoThreeTreeNode<T> node) {
		if (this.nodeArr.size() < MAX_SIZE)
		{
			this.nodeArr.add(node);
			Collections.sort(this.nodeArr,new NodeComparator<T>());
		}
	}

	public TwoThreeTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TwoThreeTreeNode<T> parent) {
		this.parent = parent;
	}
	
	public T getMinKey() {
		if(this.keys == null || this.keys.isEmpty()) {
			return null;
		}
		else {
			return this.keys.get(0);
		}

	}
	
	public T getMaxKey() {
		if(this.keys == null || this.keys.isEmpty()) {
			return null;
		}
		else {
			return this.keys.get(this.keys.size()-1);
		}

	}
	
	public TwoThreeTreeNode<T> getLeftChild() {
		T leftKey=this.keys.get(0);
		for(TwoThreeTreeNode<T>  child : this.nodeArr) {
			if(leftKey.compareTo(child.getKeys().get(0)) > 0) {
				return child;
			}
			
		}
		return null;
	}
	
	public TwoThreeTreeNode<T> getMiddleChild() {
		if(this.keys.size() == 1) {
			return null;
		}
		else {
			T leftKey=this.keys.get(0);
			T rightKey=this.keys.get(this.keys.size()-1);
			for(TwoThreeTreeNode<T>  child : this.nodeArr) {
				if((leftKey.compareTo(child.getKeys().get(0)) < 0) && (rightKey.compareTo(child.getKeys().get(0)) > 0)) {
					return child;
				}
				
			}
		}
		return null;
	}
	
	public TwoThreeTreeNode<T> getRightChild() {
		T rightKey=this.keys.get(this.keys.size()-1);
		for(TwoThreeTreeNode<T>  child : this.nodeArr) {
			if(rightKey.compareTo(child.getKeys().get(0)) < 0) {
				return child;
			}
			
		}
		return null;
	}
	public void clearChildren() {
		if(this.nodeArr != null)
		this.nodeArr.clear();
	}
	
	
	public boolean removeKey(T key) {
		return this.keys.remove(key);
	}
	
	public boolean removeChild(TwoThreeTreeNode<T> child) {
		return this.nodeArr.remove(child);
	}
	
	public boolean hasChildren() {
		if(this.nodeArr != null && !this.nodeArr.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result + ((nodeArr == null) ? 0 : nodeArr.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwoThreeTreeNode<T> other = (TwoThreeTreeNode<T>) obj;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;

		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (parent !=(other.parent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TwoThreeTreeNode [  keys=" + keys + "]";
	}
	
	class NodeComparator<E extends Comparable<E>> implements Comparator<TwoThreeTreeNode<E>>
	{

		@Override
		public int compare(TwoThreeTreeNode<E> node1, TwoThreeTreeNode<E> node2) {
			if(node1.getKeys().get(0).compareTo(node2.getKeys().get(0))<0)
				return -1;
			else if(node1.getKeys().get(0).compareTo(node2.getKeys().get(0))>0)
				return 1;
			else 
				return 0;
		}
		
	}

}
