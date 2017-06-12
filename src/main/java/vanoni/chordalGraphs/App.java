package vanoni.chordalGraphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.google.common.base.Supplier;

import edu.uci.ics.jung.algorithms.filters.KNeighborhoodFilter;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.PajekNetReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	boolean isChordal;
    	//String s;
    	//Scanner scanner = new Scanner(System.in);
    	App app = new App();
        ChordalAlgorithms a = new ChordalAlgorithms(app.read(args[0]));
        //System.out.println("pronto");
        //s = scanner.nextLine();
        a.maximumCardinalitySearch();
        isChordal = a.isChordal();
        //System.out.println("finito");
        //s = scanner.nextLine();
        System.out.println(isChordal);
        		
    }
    
    public UndirectedGraph<Vertex, Integer> read(String name) {
    	
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream(name);
		BufferedReader br = new BufferedReader( new InputStreamReader( is ));
		
		try {
			return setUpView(br);
	    } catch (IOException e) {
            System.out.println("Error in loading graph");
            e.printStackTrace();
	    }
		
		return null;
    }
    
	private UndirectedGraph<Vertex,Integer> setUpView(BufferedReader br) throws IOException {
		
		Supplier<Vertex> vertexFactory = new Supplier<Vertex>() {
            public Vertex get() { return new Vertex(); }
        };
        Supplier<Integer> edgeFactory = new Supplier<Integer>()  {
            int n = 0;
            public Integer get() { return n++; }
        };

        PajekNetReader<UndirectedGraph<Vertex, Integer>, Vertex,Integer> pnr = 
            new PajekNetReader<>(vertexFactory, edgeFactory);
        
        final UndirectedGraph<Vertex,Integer> graph = new UndirectedSparseGraph<>();
        pnr.load(br, graph);
        /*Vertex v = null;
        for (Vertex vertex: graph.getVertices()) {
        	v = vertex;
        	break;
        }
        KNeighborhoodFilter<Vertex, Integer> filter = new KNeighborhoodFilter<Vertex, Integer>(v, 3, KNeighborhoodFilter.EdgeType.IN_OUT);
        UndirectedGraph<Vertex,Integer> graph2 = (UndirectedGraph<Vertex, Integer>) filter.apply(graph);
        System.out.println(graph2.getEdgeCount());
        System.out.println(graph2.getVertexCount());*/
        return graph;
    }
}
