package vanoni.chordalGraphs;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.PajekNetWriter;

public class ChordalAlgorithms {
	
	private List<Vertex> order;
	private UndirectedGraph<Vertex,Integer> graph;

	public ChordalAlgorithms(UndirectedGraph<Vertex,Integer> graph) {
		
		order = new LinkedList<>();
		this.graph = graph;
		
	}
	
	public void maximumCardinalitySearch() {
		
		List<List<Vertex>> set = new LinkedList<>();
		int i,j = 0,n = graph.getVertexCount();
		
		for (i = 0; i < n; i++)
			set.add(i, new LinkedList<Vertex>());
		
		for (Vertex v: graph.getVertices()) {
			v.setSize(0);
			set.get(0).add(v);
		}
		
		for (i = 0; i < n; i++) { //Ordering starts from 0
			Vertex v = set.get(j).remove(0); // any, I chose to remove the first one
			v.setPosition(i);
			order.add(i, v);
			v.setSize(-1);
			
			for (Integer e: graph.getIncidentEdges(v)) {
				Vertex u = graph.getEndpoints(e).getSecond().equals(v) 
						   ? graph.getEndpoints(e).getFirst() : graph.getEndpoints(e).getSecond();
				if (u.getSize() > -1) {
					set.get(u.getSize()).remove(u);
					u.setSize(u.getSize()+1);
					set.get(u.getSize()).add(u);
				}
			}
			j++;
			
			while (j >= 0 && set.get(j).isEmpty())
				j--;
		}
		
	}
	
	public boolean isChordal() {
		
		int n = graph.getVertexCount(),i;
		Vertex w,x;
		/*UndirectedGraph<Vertex,Integer> augmentedGraph = new UndirectedSparseGraph<>();
		
		for (Vertex v: graph.getVertices())
			augmentedGraph.addVertex(v);
		
		for (Integer in: graph.getEdges())
			augmentedGraph.addEdge(in, graph.getEndpoints(in).getFirst(), graph.getEndpoints(in).getSecond());*/
		
		
		for (i = n-1; i >= 0; i--) {
			w = order.get(i);
			w.setFollower(w);
			w.setIndex(i);
			
			for (Vertex v: graph.getNeighbors(w)) {
				if (v.getAlpha() > i) {
					x = v;
					while (x.getIndex() > i) {
						x.setIndex(i);
						if (graph.findEdge(x, w) == null && x != w)
							return false;//augmentedGraph.addEdge(augmentedGraph.getEdgeCount()+100000, x, w);//
						x = x.getFollower();
					}
					
					if (x.getFollower().equals(x))
						x.setFollower(w);
				}
			}
		}
		/*PajekNetWriter<Vertex, Integer> wr = new PajekNetWriter<>();
		try {
			wr.save(augmentedGraph, "MiniDaysAllChordal.net");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return true;
		
	}
	
}
