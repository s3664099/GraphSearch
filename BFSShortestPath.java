import java.util.*;
import java.io.*;

/**
 * Compute the shortest path distance using breadth first search.
 * 
 * @author jefcha
 */
public class BFSShortestPath
{
    /** Class name. */
    private static final String progName = "BFSShortestPath";       

    /** Distance value for source and target vertex pairs that are disconnected. */
    protected static final int disconnectedDist = -1;
	
	
    /**
     * Compute the shortest path distance between the source 
     * 
     * @param g Input Graph.
     * @param s Source vertex.
     * @param t Target vertex.
     * 
     * @return Shortest path distance between s and t.  If they are disconnected, then return BFSShortestPath.disconnectedDist.
     */
    public static int spd(Graph g, int s, int t) {
    	
    	//creates an array to record visited verticies
    	boolean[] visited = new boolean[g.vertNum()];
    	
    	//marks all entries in the array to not-visited (false)
    	for (int i=0;i<g.vertNum(); i++)
    		visited[i]=false;
    	
    	//initial distance = 0;
    	int dist = 0;
    	
    	//creates a queue to store the edges and the distance
    	Queue<Pair> q = new ArrayDeque<Pair>();
    	
    	//add the first node to the queue with a distance of 0
    	q.add(new Pair(s,dist));
    	
    	while(!q.isEmpty())
    	{
    		//creates a pair and removes the first node
    		Pair node = q.remove();
    		
    		//gets the distance and the vertex from the node
    		dist = node.getDist();
    		s = node.getVert();
    		
    		//checks to see if it is the target
    		if(s!=t)
    		{
    			visited[s]=true;
    			for (Integer edge:g.neighbours(s))
    			{
    				if(!visited[edge])
    					q.add(new Pair(edge,dist+1));
    			}
    		} else {
    			return dist;
    		}
    		
    	}
    	
    	// Placeholder -- REPLACE
    	return disconnectedDist;    	
    } // end of traverse()
    
    
    
    /**
     * Print out usage messge and exits from program.
     */    
    protected static void usage() {
    	System.err.println(BFSShortestPath.progName + ": <input graph file> <starting vertex>");
    	System.exit(1);
    }    


    /**
     * Compute shortest path distance using BFS for input graph.
     */
    public static void main(String[] args) {
    	if (args.length != 3) {
    		usage();
    	}    	
    	
    	try {
            // input graph
    		InputStream in = new FileInputStream(args[0]);
    		Graph g = new Graph(in);
    		
            // source vertex
    		int source = Integer.parseInt(args[1]);
            // target vertex
    		int target = Integer.parseInt(args[2]);
    		
            // compute the shortest path distance between source and target
    		int spDist = BFSShortestPath.spd(g, source, target);
    		
            // check if the two vertices are connected
    		if (spDist == disconnectedDist) {
    			System.out.println("Source " + source + "and Target " + target + " are disconnected.");
    		}
    		else {
    			System.out.println("Distance between Source " + source + " and Target " + target + " is " + spDist);
    		}
    		
    	} 
    	catch (FileNotFoundException e) {
    		System.err.println(e.getMessage());
    	}    	
    } // end of main()
    
} // end of class BFS-ShortestPaths


/**
 * Class implement a (vertex, distance) pair.
 * 
 * @author jefcha
 */
class Pair {
	private Integer mVert;
	private Integer mDist;
	
	public Pair(Integer v, Integer dist) {
		mVert = v;
		mDist = dist;
	}
	
	
	public Integer getVert() {
		return mVert;
	}
	
	public Integer getDist() {
		return mDist;
	}
	
} // end of class Pair
