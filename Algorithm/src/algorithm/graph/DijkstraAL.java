package algorithm.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraAL {
	Map<NodeName, Node> map = new HashMap<NodeName, Node>();
	Queue<Node> priorityQueue = new PriorityQueue<>(new NodeCmp());
	List<Node> visited = new ArrayList<>();

	public static void main(String[] a) {
		DijkstraAL al = new DijkstraAL();
		al.populateAL();
		al.findShortestDistance(NodeName.A);
	}

	public void findShortestDistance(NodeName source) {

		Node srcNode = map.get(source);
		List<AdjNode> adjNodeList = srcNode.getListAdjNode();
		for (AdjNode adjNode : adjNodeList) {

			Node node = map.get(adjNode.getName());
			if(!priorityQueue.contains(node) && !visited.contains(node))
			priorityQueue.add(node);
			
			if (node.getDistanceFromSource() > (map.get(source).getDistanceFromSource() + adjNode.getDistance())) {
				node.setDistanceFromSource(map.get(source).getDistanceFromSource() + adjNode.getDistance());
				System.out.println("Distance of " + adjNode.getName() + " changed to " + node.getDistanceFromSource());
			}

		}
		visited.add(map.get(source));
		Node polledNode=priorityQueue.poll();
		if(polledNode!=null)
		findShortestDistance(polledNode.getName());
	}

	public void populateAL() {

		Node a = new Node();
		a.setDistanceFromSource(0);
		a.setName(NodeName.A);
		a.addAdjNode(new AdjNode(NodeName.F, 14));
		a.addAdjNode(new AdjNode(NodeName.C, 9));
		a.addAdjNode(new AdjNode(NodeName.B, 7));
		map.put(NodeName.A, a);

		Node b = new Node();
		b.setName(NodeName.B);
		b.setDistanceFromSource(Integer.MAX_VALUE);
		b.addAdjNode(new AdjNode(NodeName.A, 7));
		b.addAdjNode(new AdjNode(NodeName.C, 10));
		b.addAdjNode(new AdjNode(NodeName.D, 16));
		map.put(NodeName.B, b);

		Node c = new Node();
		c.setName(NodeName.C);
		c.setDistanceFromSource(Integer.MAX_VALUE);
		c.addAdjNode(new AdjNode(NodeName.A, 9));
		c.addAdjNode(new AdjNode(NodeName.B, 10));
		c.addAdjNode(new AdjNode(NodeName.D, 11));
		map.put(NodeName.C, c);

		Node d = new Node();
		d.setName(NodeName.D);
		d.setDistanceFromSource(Integer.MAX_VALUE);
		d.addAdjNode(new AdjNode(NodeName.E, 6));
		d.addAdjNode(new AdjNode(NodeName.B, 16));
		d.addAdjNode(new AdjNode(NodeName.C, 11));
		map.put(NodeName.D, d);

		Node e = new Node();
		e.setName(NodeName.E);
		e.setDistanceFromSource(Integer.MAX_VALUE);
		e.addAdjNode(new AdjNode(NodeName.D, 6));
		e.addAdjNode(new AdjNode(NodeName.F, 9));
		map.put(NodeName.E, e);

		Node f = new Node();
		f.setName(NodeName.F);
		f.setDistanceFromSource(Integer.MAX_VALUE);
		f.addAdjNode(new AdjNode(NodeName.A, 14));
		f.addAdjNode(new AdjNode(NodeName.E, 9));
		map.put(NodeName.F, f);

	}
}

class Node {
	private List<AdjNode> listAdjNode=new ArrayList<>();
	private int distanceFromSource;
	private NodeName name;

	public NodeName getName() {
		return name;
	}

	public void setName(NodeName name) {
		this.name = name;
	}

	public List<AdjNode> getListAdjNode() {
		return listAdjNode;
	}

	public void addAdjNode(AdjNode adjNode) {
		this.listAdjNode.add(adjNode);
	}

	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Node other = (Node) obj;
		if (name != other.name)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [distanceFromSource=" + distanceFromSource + ", name=" + name + "]";
	}

}

enum NodeName {
	A, B, C, D, E, F
}

class AdjNode {
	private NodeName name;
	private int distance;

	public AdjNode(NodeName name, int distance) {
		super();
		this.name = name;
		this.distance = distance;
	}

	public NodeName getName() {
		return name;
	}

	public void setName(NodeName name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}

class AdjNodeCmp implements Comparator<AdjNode> {

	@Override
	public int compare(AdjNode node1, AdjNode node2) {
		if (node1.getDistance() > node2.getDistance())
			return -1;
		else if (node1.getDistance() < node2.getDistance())
			return 1;
		else
			return 0;
	}

}

class NodeCmp implements Comparator<Node> {

	@Override
	public int compare(Node node1, Node node2) {
		if (node1.getDistanceFromSource() > node2.getDistanceFromSource())
			return -1;
		else if (node1.getDistanceFromSource() < node2.getDistanceFromSource())
			return 1;
		else
			return 0;
	}

}