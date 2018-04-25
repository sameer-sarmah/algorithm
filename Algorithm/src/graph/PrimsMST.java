package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrimsMST {

	Map<String, TreeNode> map = new HashMap<>();
	List<Edge> mst = new ArrayList<>();
	List<Edge> edgeList = new ArrayList<>();
	List<String> visited = new ArrayList<>();
	List<ItemsToDelete> itemsToDel = new ArrayList<>();

	public PrimsMST() throws CloneNotSupportedException {
		this.populateAL();
		this.removeAntiparallelEdges();
		this.removeSelfLoop();
		this.deleteUnwantedEdges();
	}

	public static void main(String[] args) {
		try {
			new PrimsMST().findMST("S");
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void findMST(String source) {
		System.out.println("Source " + source);
		TreeNode srcNode = map.get(source);
		for (Edge e : srcNode.getEdgeList()) {
			edgeList.add(e);
		}

		Collections.sort(edgeList, new EdgeCmp());
		System.out.println("Edge list " + edgeList);
		for (int index = 0; index < edgeList.size(); index++) {
			if (!visited.contains(edgeList.get(index).getTarget())) {
				mst.add(edgeList.get(index));
				edgeList.remove(edgeList.get(index));
				break;
			}
		}
		visited.add(source);
		System.out.println("MST " + mst);
		if (mst.size() > 0 && mst.size() < map.keySet().size() - 1) {
			String newSrc = mst.get(mst.size() - 1).getTarget();
			if(newSrc.equals(source))
			{
		    newSrc = mst.get(mst.size() - 1).getSource();
			}
			findMST(newSrc);
		}
	}

	private void removeAntiparallelEdges() throws CloneNotSupportedException {
		Set<String> setKeys = map.keySet();
		Iterator<String> it = setKeys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			TreeNode node = map.get(key);
			for (Edge e1 : node.getEdgeList()) {
				for (Edge e2 : node.getEdgeList()) {
					// e1 source A target T
					// e2 source T target A
					if (!e1.equals(e2)) {
						if ((e1.getSource().equals(e2.getSource()) || e1.getSource().equals(e2.getTarget()))
								&& (e1.getTarget().equals(e2.getSource()) || e1.getTarget().equals(e2.getTarget()))) {

							ItemsToDelete del = new ItemsToDelete(node.getName());
							if (e1.getWeight() < e2.getWeight()) {
								del.addEdge((Edge) e2.clone());// delete e2
								System.out.println("Edge to delete " + e2);
							} else {
								del.addEdge((Edge) e1.clone());// delete e1
								System.out.println("Edge to delete " + e1);
							}

							this.itemsToDel.add(del);
						}
					}
				}
			}

		}
	}

	private void removeSelfLoop() throws CloneNotSupportedException {
		Set<String> setKeys = map.keySet();
		Iterator<String> it = setKeys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			TreeNode node = map.get(key);
			for (Edge e1 : node.getEdgeList()) {
				if (e1.getSource().equals(e1.getTarget())) {
					ItemsToDelete del = new ItemsToDelete(node.getName());
					del.addEdge((Edge) e1.clone());
					System.out.println("Edge to delete " + e1);
					this.itemsToDel.add(del);
				}

			}

		}
	}

	private void deleteUnwantedEdges() {
		for (ItemsToDelete item : itemsToDel) {
			List<Edge> edgeList = map.get(item.getName()).getEdgeList();
			for (Edge edge : item.getEdgeList()) {
				edgeList.remove(edge);
			}
		}
	}


	private void populateAL() {
		TreeNode a = new TreeNode("A");
		Edge at = new Edge("A", "T", 9);
		Edge ac = new Edge("A", "C", -3);
		Edge ab = new Edge("A", "B", 8);
		Edge as = new Edge("A", "S", 7);
		a.addEdge(ac);
		a.addEdge(at);
		a.addEdge(ab);
		a.addEdge(as);
		map.put("A", a);

		TreeNode b = new TreeNode("B");
		Edge bc = new Edge("B", "C", 2);
		Edge bt = new Edge("B", "T", -4);
		Edge bs = new Edge("B", "S", 6);
		b.addEdge(ab);
		b.addEdge(bc);
		b.addEdge(bt);
		b.addEdge(bs);
		map.put("B", b);

		TreeNode c = new TreeNode("C");
		Edge tc = new Edge("T", "C", 7);
		c.addEdge(bc);
		c.addEdge(ac);
		c.addEdge(tc);
		map.put("C", c);

		TreeNode s = new TreeNode("S");
		Edge ts = new Edge("T", "S", 2);
		s.addEdge(as);
		s.addEdge(bs);
		s.addEdge(ts);
		map.put("S", s);

		TreeNode t = new TreeNode("T");
		t.addEdge(ts);
		t.addEdge(tc);
		t.addEdge(at);
		t.addEdge(bt);
		map.put("T", t);

	}
}

class Edge implements Cloneable {
	private String source, target;
	private int weight;

	public Edge(String source, String target, int weight) {
		super();
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + weight;
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
		Edge other = (Edge) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge [source=" + source + ", target=" + target + ", weight=" + weight + "]";
	}

}

class ItemsToDelete {
	private String name;
	private EdgeList edgeList;

	public ItemsToDelete(String name) {
		super();
		this.name = name;
		edgeList = new EdgeList();
	}

	public void addEdge(Edge edge) {
		this.edgeList.addEdge(edge);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getEdgeList() {
		return edgeList.getEdgeList();
	}

}

class TreeNode {
	private String name;
	private EdgeList edgeList;

	public TreeNode(String name) {
		this.name = name;
		edgeList = new EdgeList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getEdgeList() {
		return edgeList.getEdgeList();
	}

	public void addEdge(Edge edge) {
		this.edgeList.addEdge(edge);
	}

	public void removeEdge(Edge edge) {
		this.edgeList.removeEdge(edge);
	}

}

class EdgeList 
{
	private List<Edge> edgeList;

	public EdgeList() {
		super();
		edgeList=new ArrayList<>();
	}
	
	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void addEdge(Edge edge) {
		if(!edgeList.contains(edge))
		this.edgeList.add(edge);
	}

	public void removeEdge(Edge edge) {
		this.edgeList.remove(edge);
	}

	@Override
	public String toString() {
		return "EdgeList [edgeList=" + edgeList + "]";
	}
	
	
	
}

class EdgeCmp implements Comparator<Edge> {

	@Override
	public int compare(Edge edge1, Edge edge2) {
		if (edge1.getWeight() < edge2.getWeight())
			return -1;
		else if (edge1.getWeight() > edge2.getWeight())
			return 1;
		else
			return 0;
	}

}