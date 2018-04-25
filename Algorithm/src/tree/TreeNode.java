package tree;

public class TreeNode<T extends Comparable<T>>
{
protected T value;
protected TreeNode<T> left,right,parent;

public TreeNode<T> getParent() {
	return parent;
}
public void setParent(TreeNode<T> parent) {
	this.parent = parent;
}
public TreeNode(T value, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
	super();
	this.value = value;
	this.left = left;
	this.right = right;
	this.parent = parent;
}
public TreeNode(T value) {
	this.value = value;
}
public T getValue() {
	return value;
}
public void setValue(T value) {
	this.value = value;
}
public TreeNode<T> getLeft() {
	return left;
}
public void setLeft(TreeNode<T> left) {
	this.left = left;
}
public TreeNode<T> getRight() {
	return right;
}
public void setRight(TreeNode<T> right) {
	this.right = right;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((left == null) ? 0 : left.hashCode());
	result = prime * result + ((parent == null) ? 0 : parent.hashCode());
	result = prime * result + ((right == null) ? 0 : right.hashCode());
	result = prime * result + value.hashCode();
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
	TreeNode<T> other = (TreeNode<T>) obj;
	if (left == null) {
		if (other.left != null)
			return false;
	} else if (left!=(other.left))
		return false;
	if (parent == null) {
		if (other.parent != null)
			return false;
	} else if (parent!=(other.parent))
		return false;
	if (right == null) {
		if (other.right != null)
			return false;
	} else if (right!=(other.right))
		return false;
	if (value != other.value)
		return false;
	return true;
}
@Override
public String toString() {
	return "TreeNode [value=" + value + ", left=" + (left!=null?left.getValue():null) + ", right=" + (right!=null?right.getValue():null)+ ", parent=" +(parent!=null?parent.getValue():null) + "]";
}





}