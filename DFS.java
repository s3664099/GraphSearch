import java.util.*;
import java.io.*;

/**
 * Compute depth first search traversal on a graph.
 * 
 *  @author jefcha
 */
public class DFS 
{
    /** Name of the class. */
    private static final String progName = "DFS";       
    private static ArrayList<Integer> visited = new ArrayList<Integer>();
    
    /** 
     * Depth first search traversal of input graph g from seed vertex s.
     * 
     * @param g Input graph.
     * @param s Seed vertex to start traversal from.
     * 
     * @returns ArrayList of vertices, stored in the order they were visited in 
     *      DFS traversal.
     */
    public static ArrayList<Integer> traverse(Graph g, int s) {
		ArrayList<Integer> traversalOrder = new ArrayList<Integer>();
		
		//The current vertex is added to the order
		traversalOrder.add(s);
		visited.add(s);

		//get the edges from the vertex
		Iterable<Integer> edges = g.neighbours(s);
			
		//iterate through the edges
		for(Integer edge:edges)
		{
			boolean foundVertex = false;
			//checks to see if the connecting vertex is in the order
			for(Integer vertex:visited)
			{
				if(vertex == edge)
					foundVertex = true;
			}
				
			//Checks to see if the edge was found
			if(!foundVertex)
					
				//If not we will traverse to the next vertex
				traversalOrder.addAll(traverse(g,edge));

		}
    	
    	return traversalOrder;
    } // end of traverse()



    
    /**
     * Print out usage messge and exits from program.
     */    
    protected static void usage() {
    	System.err.println(DFS.progName + ": <input graph file> <starting vertex>");
    	System.exit(1);
    }
    
    
    /**
     * Perform DFS traversal for input graph.
     */
    public static void main(String[] args) {
    	if (args.length != 2) {
    		usage();
    	}
    	
    	try {
            // input graph
            InputStream in = new FileInputStream(args[0]);
            Graph g = new Graph(in);
        
            // seed vertex
            int s = Integer.parseInt(args[1]);
    		
            // perform DFS
            ArrayList<Integer> traversalOrder = DFS.traverse(g, s);

            // print out the traversal order of the BFS
            Iterator<Integer> it = traversalOrder.iterator();
            while (it.hasNext()) {
    		System.out.print(it.next() + " ");
            }
            
            System.out.println("");
    	} 
    	catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
    	}
    } // end of main()

} // end of class DFS
