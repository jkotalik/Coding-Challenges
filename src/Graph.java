import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <b>Graph</b> is a mutable representation of a directed labeled multi-graph.
 * Graph is represented as an adjacency list such that it contains a collection
 * of unordered sets, one for each vertex in the graph. For more information
 * see the Wikipedia article http://en.wikipedia.org/wiki/Adjacency_list
 *
 * Specification fields:
 *    @specfield node : N			// Vertice in the graph
 *    @specfield edge : Pair<N,L>   // Connects two nodes in the graph and
 *                                     indicate a node is directly reachable by another (one-way)
 *
 * @author Kyle Steiner
 */
public class Graph<N extends Comparable<N>, L extends Comparable<L>> {

	/** Enable/Disable checkRep() **/
	private static final boolean USE_CHECK_REP = false;

    /** Holds all of the nodes and their edges represented as HashMap<node, HashSet<Pair<N,L>(childName, edgeName)>> **/
    private final HashMap<N, HashSet<Pair<N,L>>> hm;

    // Abstraction Function:
    // Graph, g, represents the graph of nodes and edges contained in 'hm' such that:
    // g.hm.keySet = List<N> = nodes of g
    // g.hm.get(key) = Set<Pair<N,L>> = edges of g such that Pair<N,L>.getKey() = childNode Pair<N,L>.getValue() = edgeName && key = parentNode
    //
    // Representation Invariant:
    // hm != null
    // !hm.containsKey(null)
    // forall values of hm, values != null && !values.contains(null)

    /**
     * @effects Constructs a new Graph that is initially empty.
     */
    public Graph() {
        hm = new HashMap<N, HashSet<Pair<N,L>>>();
        checkRep();
    }

    /**
     * @param nodes set of nodes that will be added to the new graph
     * @requires nodes contains all unique values && no nulls in nodes
     * @effects Constructs a new Graph that is initially empty.
     */
    public Graph(Set<N> nodes) {
    	hm = new HashMap<N, HashSet<Pair<N,L>>>();
    	for (N node : nodes) {
    		hm.put(node, new HashSet<Pair<N,L>>());
    	}
        checkRep();
    }

    /**
     * Adds a node represented by 'node' to this Graph.
     *
     * @param node The node that will be added to this Graph.
     * @requires node != null
     * @modifies this
     * @effects adds the node to this Graph
     * @return True if the node is successfully added (not contained in Graph prior to method call), false otherwise.
     */
    public boolean addNode(N node) {
    	checkRep();
        if (!hm.containsKey(node)) {
    	    hm.put(node, new HashSet<Pair<N,L>>());
    	    checkRep();
    	    return true;
        }
        checkRep();
        return false;
    }

    /**
     * Removes a node represented by 'node' from this Graph.
     *
     * @param node The node that will be removed from this Graph.
     * @requires node != null
     * @modifies this
     * @effects removes the node from this Graph
     * 			removes any edges that have the node as its childNode
     * @return True if the node is successfully removed, false otherwise.
     */
    public boolean removeNode(N node) {
    	checkRep();
    	boolean b = false;
    	if (hm.containsKey(node)) {
            // Remove the node from this graph
            hm.remove(node);
            b = true;
        }
        // Remove any edges that have the node as its childNode
        for (N key : hm.keySet()) {
            HashSet<Pair<N,L>> hs = hm.get(key);
            for (Pair<N,L> p : hs) {
            	if (p.getValue().equals(node)) {
                    hs.remove(p);
                    b = true;
                }
            }
        }
        checkRep();
        return b;
    }

    /**
     * Returns true if the node is in the Graph, false otherwise
     *
     * @param node The node that will be checked if it exists in the Graph
     * @requires node != null
     * @return True if the node is in the Graph, false otherwise
     */
    public boolean containsNode(N node) {
    	return hm.containsKey(node);
    }

    /**
     * Adds an edge represented by the name 'edgeLabel' with the given parent node,
     * 'parentNode', and child node, 'childNode', to this Graph.
     *
     * Note: The Graph is not allowed to have identical edges
     *
     * @param edgeLabel The name of the edge label that will be added to this Graph
     * @param parentNode The parent node who's edges will be modified
     * @param childNode The name of the child node that will be added to this Graph
     * @requires edgeLabel != null && parentNode != null && childNode != null
     * @modifies this
     * @effects adds the edge to the associated parent node represented by Pair<N,L>(childNode, edgeLabel).
     * @return True if the edge is successfully added (not contained in Graph prior to method call), false otherwise.
     */
    public boolean addEdge(L edgeLabel, N parentNode, N childNode) {
    	checkRep();
    	if (hm.containsKey(parentNode)) {
    	    HashSet<Pair<N,L>> edges = hm.get(parentNode);
            boolean b = edges.add(new Pair<N,L>(childNode, edgeLabel));
            checkRep();
            return b;
        }
    	checkRep();
        return false;
    }

    /**
     * Removes an edge represented by the name 'edgeLabel' with the given parent node,
     * 'parentNode', and child node, 'childNode', from this Graph.
     *
     * @param edgeLabel The name of the edge label that will be removed from this Graph
     * @param parentNode The parent node who's edges will be modified
     * @param childNode The name of the child node that will be removed from this Graph
     * @requires edgeLabel != null && parentNode != null && childNode != null
     * @modifies this
     * @effects removes the edge from the associated parent node represented by Pair<N,L>(childNode, edgeLabel).
     * @return True if the edge is successfully removed, false otherwise.
     */
    public boolean removeEdge(L edgeLabel, N parentNode, N childNode) {
    	checkRep();
    	if (hm.containsKey(parentNode)) {
    	    HashSet<Pair<N,L>> edges = hm.get(parentNode);
    	    // Traverse edges of parentNode for correct edge represented by Pair<N,L>(childNode, edgeLabel)
    	    Pair<N,L> edge = new Pair<N,L>(childNode, edgeLabel);
    	    if (edges.contains(edge)) {
    	    	edges.remove(edge);
	    		return true;
	    	}
        }
    	checkRep();
        return false;
    }

    /**
     * Returns true if the edge is in the Graph, false otherwise
     *
     * @param edgeLabel The name of the edge label that will be checked if it exists in the Graph
     * @param parentNode The name of the parent node that will be checked if it exists in the Graph
     * @param childNode The name of the child node that will be checked if it exists in the Graph
     * @requires edgeLabel != null && parentNode != null && childNode != null
     * @return True if the edge is in the Graph, false otherwise
     */
    public boolean containsEdge(L edgeLabel, N parentNode, N childNode) {
    	checkRep();
    	if (hm.containsKey(parentNode)) {
    		HashSet<Pair<N,L>> edges = hm.get(parentNode);
    		Pair<N,L> edge = new Pair<N,L>(childNode, edgeLabel);
    		if (edges.contains(edge)) {
	    		return true;
	    	}
        }
    	checkRep();
    	return false;
    }

    /**
     * Returns a set of the nodes in the graph
     */
    public Set<N> getNodes() {
    	return hm.keySet();
    }

    /**
     * Returns a string representation of the nodes
     *
     * @returns A String representation of the nodes of this Graph by this,
     *          with the format "firstNode secondNode thirdNode ..."
     *          <p>
     *          The nodes should be in alphabetical order by node name.
     */
    public String listNodes() {
    	TreeSet<String> ts = new TreeSet<String>();
    	for (N node : hm.keySet()) {
    		ts.add(node.toString());
    	}
    	return treeSetToString(ts);
    }

    /**
     * Returns a TreeSet<Pair<N,L>> that contains all of the edges for the node
     *
     * @param node Node of the Pair<N,L>s that will be retrieved
     * @return TreeSet<Pair<N,L>> that contains all of the edges for the node
     */
    public TreeSet<Pair<N,L>> getEdges(N node) {
    	if (hm.containsKey(node)) {
    		return new TreeSet<Pair<N,L>>(hm.get(node));
    	} else {
    		return new TreeSet<Pair<N,L>>();
    	}
    }

    /**
     * Helper procedure: Returns a String that represents a TreeSet<String> as a sequence of terms,
     * preserving the sorted nature of the sequence.
     *
     * @param ts The TreeSet<String> that will be returned as a string.
     * @return A String representation of the TreeSet<String> by this,
     *         with the format "string_1 string_2 string_3 ..."
     */
    private String treeSetToString(TreeSet<String> ts) {
    	String output = "";
    	for (String s : ts) {
    		output += s.toString() + " ";
    	}
    	return output.trim();
    }

    /**
     * Return a count of nodes in the Graph
     *
     * @return An integer representing the number of nodes in the Graph
     */
    public int nodeCount() {
        return hm.keySet().size();
    }

    /**
     * Return a count of edges in the Graph
     *
     * @return An integer representing the number of edges in the Graph
     */
    public int edgeCount() {
    	int count = 0;
    	for (N key : hm.keySet()) {
    	    count += hm.get(key).size();
    	}
    	return count;
    }

    /**
     * Return the edge label between the given parent and child nodes
     *
     * @param parentNode parent node of desired label
     * @param childNode child node of desired label
     * @return Edge label between parent and child node, false otherwise
     */
    public /*@Nullable*/ L getLabel(N parentNode, N childNode) {
    	if (hm.containsKey(parentNode)) {
    		for (Pair<N, L> edge : hm.get(parentNode)) {
    			if (edge.getKey().equals(childNode)) {
    				return edge.getValue();
    			}
    		}
    	}
    	return null;
    }

    /**
     * Checks that the representation invariant holds (if any).
     */
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
    	// Duplicate values do not need to be checked because HashMaps and HashSets cannot contain duplicate keys.
    	if (USE_CHECK_REP) {
	    	if (hm == null) {
	            throw new RuntimeException("hm == null");
	        }
	        if (hm.containsKey(null)) {
	            throw new RuntimeException("null node");
	        }
	        for (N node : hm.keySet()) {
	            HashSet<Pair<N,L>> pairs = hm.get(node);
	            if (pairs == null) {
	                throw new RuntimeException("null HashSet");
	            }
	            if (pairs.contains(null)) {
	            	throw new RuntimeException("null edge represented by Pair<N,L>");
	            }
	        }
	    }
    }
}
