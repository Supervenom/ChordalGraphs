package vanoni.chordalGraphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.google.common.base.Supplier;

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
    	String s;
    	Scanner scanner = new Scanner(System.in);
    	App app = new App();
        ChordalAlgorithms a = new ChordalAlgorithms(app.read());
        System.out.println("pronto");
        s = scanner.nextLine();
        a.maximumCardinalitySearch();
        isChordal = a.isChordal();
        System.out.println("finito");
        s = scanner.nextLine();
        System.out.println(isChordal);
        		
    }
    
    public UndirectedGraph<Vertex, Integer> read() {
    	
    	InputStream is = this.getClass().getClassLoader().getResourceAsStream("YeastChordal.net");
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
        return graph;
    }
}
