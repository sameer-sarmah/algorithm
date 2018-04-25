package algorithm.tree;

public class SummableSegmentedTreeNode {

	private int from, to;
	private SummableSegmentedTreeNode parent, left, right;
	private Number value;

	public SummableSegmentedTreeNode(Number value, int fromIndex, int toIndex) {
		this.from = fromIndex;
		this.to = toIndex;
		this.value=value;
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

	public SummableSegmentedTreeNode getParent() {
		return parent;
	}

	public void setParent(SummableSegmentedTreeNode parent) {
		this.parent = parent;
	}

	public SummableSegmentedTreeNode getLeft() {
		return left;
	}

	public void setLeft(SummableSegmentedTreeNode left) {
		this.left = left;
	}

	public SummableSegmentedTreeNode getRight() {
		return right;
	}

	public void setRight(SummableSegmentedTreeNode right) {
		this.right = right;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SummableSegmentedTreeNode [" + from + ", " + to + "] , value=" + value.toString() + "]";
	}

	
	
}
