package vanoni.ChordalGraphs;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int i,j,k=0;
        int[][] matrix = {{0,1,0,1,1},{1,0,1,0,1},{0,1,0,1,1},{1,0,1,0,1},{1,1,1,1,0}};
        for (i=0; i<5;i++) {
        	for (j=0;j<5;j++)
        		System.out.print(matrix[i][j]+" ");
        	System.out.println();
        }
        UndirectedGraph<Vertex,Integer> graph = new UndirectedSparseGraph<>();
        List<Vertex> vertices = new LinkedList<>();
        Vertex v;
		for (i=0;i<5;i++) {
        	v = new Vertex();
        	vertices.add(v);
        	graph.addVertex(v);
		}
        
        for (i=0; i<5;i++) 
        	for (j=i+1;j<5;j++)
        		if (matrix[i][j]==1) {
        			graph.addEdge(new Integer(k), vertices.get(i), vertices.get(j));
        			k++;
        		}
        ChordalAlgorithms a = new ChordalAlgorithms();
        a.maximumCardinalitySearch(graph);
        System.out.println(graph);  				
        		
    }
}
