package algorithm.tree;

public interface Tree<T extends Comparable<T>> {
public TreeNode<T> search(T value);
public boolean delete(T value);
public TreeNode<T> insert(T value);

}
