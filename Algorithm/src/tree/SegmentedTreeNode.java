package tree;

public class SegmentedTreeNode<T extends Comparable<T>> extends TreeNode<T> {

	private int from,to;
	
	public SegmentedTreeNode(T value,int fromIndex,int toIndex) {
		super(value);
		this.from=fromIndex;
		this.to=toIndex;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "SegmentedTreeNode [" + from + "," + to + "] , value=" + value + "]";
	}

	
	
}
