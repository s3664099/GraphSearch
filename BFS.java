import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Compute breadth first search traversal on a graph.
 * 
 * @author jefcha
 */
public class BFS 
{   
    /** Class name. */
    private static final String progName = "BFS";
    private static ArrayList<Integer> visited = new ArrayList<Integer>();
    private static ArrayList<Integer> added = new ArrayList<Integer>();

    /** 
     * Breadth first search traversal of input graph g from seed vertex s.
     * 
     * @param g Input graph.
     * @param s Seed vertex to start traversal from.
     * 
     * @returns ArrayList of vertices, stored in the order they were visited in 
     *      BFS traversal.
     */
    public static ArrayList<Integer> traverse(Graph g, int s) {
    	ArrayList<Integer> traversalOrder = new ArrayList<Integer>();
    	
		//get the edges from the vertex
		Iterable<Integer> edges = g.neighbours(s);
		visited.add(s);
		
		//iterate through the edges
		for (Integer edge:edges)
		{
			//set the visited flag to false
			boolean visited = false;
			
			//iterate through the added list
			for(Integer haveAdded:added)
				
				//checks to see if the edge has already been added
				if (edge == haveAdded)
					visited=true;
			
			//if the flag has not been set
			if(!visited)
			{
				traversalOrder.add(edge);
				added.add(edge);
			}
		}

		//iterate through the visited list
		for(Integer edge:edges)
		{
			boolean visit = false;
			
			//checks to see if the edge has been visited
			//And flags it if it has
			for(Integer haveVisited:visited)
			{
				if (edge == haveVisited)
					visit = true;
			}
			
			//if not, the edge is then visited.
			if (!visit)
				traversalOrder.addAll(traverse(g, edge));
		}
    //go to each of the vertices and if not visited, then visit the vertex
          
        return traversalOrder;
    } // end of traverse()
    
    
    /**
     * Print out usage message and exits from program.
     */
    protected static void usage() {
    	System.err.println(BFS.progName + ": <input graph file> <starting vertex>");
    	System.exit(1);
    }

    
    /**
     * Perform BFS traversal for input graph.
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
    		
            // perform BFS
            ArrayList<Integer> traversalOrder = BFS.traverse(g, s);

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


} // end of class BFS
