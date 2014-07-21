package TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.DirectedGraph;

public class Graph {
	/*
	 * Problem 2
	 */
	public static boolean isDirectedRoute(Vertex start, Vertex end, DirectedGraph<Vertex, Edge> g) {
		for (Vertex v : g.vertexSet())
			v.visited = false;

		Vertex v;
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(start);
		while(!q.isEmpty()) {
			v = q.remove();
			for (Edge e : g.outgoingEdgesOf(v)) {
				Vertex v2 = e.v;
			}
		}
		return false;
	}

	/*
	 * Default Edge & Vertex classes
	 */
	private static class Vertex {
		private boolean visited;
	}

	private static class Edge {
		private Vertex v;
	}
}
