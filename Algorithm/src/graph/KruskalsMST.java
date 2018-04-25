package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalsMST {
	private EdgeList edgeList;
	List<ItemsToDelete> itemsToDel = new ArrayList<>();
	DisjointSetPool pool=new DisjointSetPool();
	final int NUM_OF_NODES=5;
	
	public KruskalsMST() throws CloneNotSupportedException {
		super();
		edgeList = new EdgeList();
		this.populateEdgeList();
		this.removeAntiparallelEdges();
		this.removeSelfLoop();
		this.deleteUnwantedEdges();
		System.out.println(edgeList);
	}

	public void findMST()
	{
		for (Edge edge : edgeList.getEdgeList()) {
			if(pool.getEdgeSet().size()<NUM_OF_NODES-1)
			pool.addEgdetoSet(edge);
		}
		
		System.out.println("MST " +pool.getEdgeSet());
		
		
	}
	
	private void removeAntiparallelEdges() throws CloneNotSupportedException {
		for (Edge e1 : edgeList.getEdgeList()) {
			for (Edge e2 : edgeList.getEdgeList()) {
				// e1 source A target T
				// e2 source T target A
				if (!e1.equals(e2)) {
					if ((e1.getSource().equals(e2.getSource()) || e1.getSource().equals(e2.getTarget()))
							&& (e1.getTarget().equals(e2.getSource()) || e1.getTarget().equals(e2.getTarget()))) {

						ItemsToDelete del = new ItemsToDelete("");
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

	private void removeSelfLoop() throws CloneNotSupportedException {
		for (Edge e1 : edgeList.getEdgeList()) {
			if (e1.getSource().equals(e1.getTarget())) {
				ItemsToDelete del = new ItemsToDelete("");
				del.addEdge((Edge) e1.clone());
				System.out.println("Edge to delete " + e1);
				this.itemsToDel.add(del);
			}

		}
	}

	private void deleteUnwantedEdges() {
		for (ItemsToDelete item : itemsToDel) {
			for (Edge edge : item.getEdgeList()) {
				edgeList.removeEdge(edge);
			}
		}
	}

	private void populateEdgeList() {
		Edge at = new Edge("A", "T", 9);
		Edge ac = new Edge("A", "C", -3);
		Edge ab = new Edge("A", "B", 8);
		Edge as = new Edge("A", "S", 7);
		Edge bc = new Edge("B", "C", 2);
		Edge bt = new Edge("B", "T", -4);
		Edge bs = new Edge("B", "S", 6);
		Edge tc = new Edge("T", "C", 7);
		Edge ts = new Edge("T", "S", 2);
		edgeList.addEdge(ac);
		edgeList.addEdge(at);
		edgeList.addEdge(ab);
		edgeList.addEdge(as);
		edgeList.addEdge(bc);
		edgeList.addEdge(bt);
		edgeList.addEdge(bs);
		edgeList.addEdge(tc);
		edgeList.addEdge(ts);
		Collections.sort(edgeList.getEdgeList(), new EdgeCmp());

	}
	
	public static void main(String[] args) {
		try {
			new KruskalsMST().findMST();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}
}

class DisjointSet {
	private Set<String> set = new HashSet<>();

	public DisjointSet() {
		super();
	}

	public Set<String> getSet() {
		return set;
	}

	public void addToSet(String ele) {
		set.add(ele);
	}

	@Override
	public String toString() {
		return "DisjointSet [set=" + set + "]";
	}
	
	
}

class DisjointSetPool {
	private List<DisjointSet> list = new ArrayList<>();
    private Set<Edge> edgeSet=new HashSet<>();
	public DisjointSetPool() {
		super();
	}

	public void addEgdetoSet(Edge edge) {
		boolean contains = false;
		boolean isCyclic = false;
		for (DisjointSet ds : list) {
			if ((ds.getSet().contains(edge.getSource()) && !ds.getSet().contains(edge.getTarget()))
					|| (!ds.getSet().contains(edge.getSource()) && ds.getSet().contains(edge.getTarget()))) {
				System.out.println(edge.getSource() + " & " + edge.getTarget() + " added to existing disjoint set");
				ds.addToSet(edge.getSource());
				ds.addToSet(edge.getTarget());
				edgeSet.add(edge);
				contains = true;
				break;
			} else if (ds.getSet().contains(edge.getSource()) && !ds.getSet().contains(edge.getTarget())) {
				System.out.println("Will cause cycle");
				isCyclic = true;
				break;
			}
		}
		if (!contains && !isCyclic) {
			System.out.println(edge.getSource() + " & " + edge.getTarget() + " added to new disjoint set");
			DisjointSet ds = new DisjointSet();
			ds.addToSet(edge.getSource());
			ds.addToSet(edge.getTarget());
			list.add(ds);
			edgeSet.add(edge);
		}
	}

	public Set<Edge> getEdgeSet() {
		return edgeSet;
	}


}
