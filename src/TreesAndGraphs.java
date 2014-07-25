import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.DirectedGraph;

public class TreesAndGraphs {
	/*
     * Problem 1
     */
	static boolean isBalanced(TreeNode root) {
		if (root == null)
			return false;
		else
			return height(root) != -1;
	}

	static int height(TreeNode n) {
		if (n.left == null && n.right == null) {
			return 1;
		} else {
			int h_left = 0;
			int h_right = 0;
			if (n.left != null) h_left = height(n.left);
			if (n.right != null) h_right = height(n.right);
			if (h_left == -1 || h_right == -1 || Math.abs(h_left - h_right) > 1)
				return -1;
			else
				return Math.max(h_left, h_right) + 1;
		}

	}

	/*
	 * Problem 2
	 */
	static boolean isDirectedRoute(Vertex start, Vertex end, DirectedGraph<Vertex, Edge> g) {
		for (Vertex v : g.vertexSet())
			v.visited = false;

		Vertex v;
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(start);
		while(!q.isEmpty()) {
			v = q.remove();
			for (Edge e : g.outgoingEdgesOf(v)) {
				Vertex v2 = e.v;
				if (v2 == end)
					return true;
				else
					q.add(v2);
			}
		}
		return false;
	}

	/*
	 * Problem 3
	 */
	static TreeNode createMinimalBST(int[] arr) {
		return createMinimalBSTHelper(arr, 0, arr.length-1);
	}

	static TreeNode createMinimalBSTHelper(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		} else {
			int mid = (start + end) / 2;
			TreeNode n = new TreeNode();
			n.left = createMinimalBSTHelper(arr, start, mid-1);
			n.right = createMinimalBSTHelper(arr, mid+1, end);
			n.data = arr[mid];
			return n;
		}
	}

	/*
	 * Default Tree Structure
	 */
	static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
	}

	/*
	 * Default Graph Structure
	 */
	static class Vertex {
		private boolean visited;
	}

	static class Edge {
		private Vertex v;
	}
}
