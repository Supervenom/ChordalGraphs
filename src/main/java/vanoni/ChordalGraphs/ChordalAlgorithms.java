package vanoni.ChordalGraphs;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedGraph;

public class ChordalAlgorithms {
	
	private List<Vertex> order;

	public ChordalAlgorithms() {
		
		order = new LinkedList<>();
		
	}
	
	public void maximumCardinalitySearch(UndirectedGraph<Vertex,Integer> graph) {
		
		List<List<Vertex>> set = new LinkedList<>();
		
		
		int i,j = 0;
		
		int n = graph.getVertexCount();
		
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
	
}
