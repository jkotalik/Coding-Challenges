import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreesAndGraphs {
	/*
	 * Problem 1
	 */
	public static boolean isBalanced(TreeNode root) {
		if (root == null)
			return false;
		else
			return isBalancedHelper(root) != -1;
	}

	private static int isBalancedHelper(TreeNode n) {
		if (n.left == null && n.right == null) {
			return 1;
		} else {
			int h_left = 0;
			int h_right = 0;
			if (n.left != null) h_left = isBalancedHelper(n.left);
			if (n.right != null) h_right = isBalancedHelper(n.right);
			if (h_left == -1 || h_right == -1 || Math.abs(h_left - h_right) > 1)
				return -1;
			else
				return Math.max(h_left, h_right) + 1;
		}

	}

	/*
	 * Problem 2
	 */
	public static boolean isDirectedRoute(GraphNode start, GraphNode end,
			Graph<GraphNode, GraphNode> g) {
		for (GraphNode n : g.getNodes())
			n.visited = false;

		GraphNode v;
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.add(start);
		while(!q.isEmpty()) {
			v = q.remove();
			for (Pair<GraphNode, GraphNode> e : g.getEdges(v)) {
				GraphNode v2 = e.getValue();
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
	public static TreeNode createMinimalBST(int[] arr) {
		return createMinimalBSTHelper(arr, 0, arr.length-1);
	}

	private static TreeNode createMinimalBSTHelper(int[] arr, int start, int end) {
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
	 * Problem 4
	 */
	public static List<LinkedList<TreeNode>> listTreeByDepth(TreeNode root) {
		List<LinkedList<TreeNode>> list = new ArrayList<LinkedList<TreeNode>>();
		listTreeByDepthHelper(root, 0, list);
		return list;
	}

	private static void listTreeByDepthHelper(TreeNode n, int level,
			List<LinkedList<TreeNode>> list) {
		if (n != null) {
			LinkedList<TreeNode> ll = list.get(level);
			if (ll == null) {
				ll = new LinkedList<TreeNode>();
				list.add(ll);
			}
			ll.add(n);
			listTreeByDepthHelper(n.left, level + 1, list);
			listTreeByDepthHelper(n.right, level + 1, list);
		}

	}

	/*
	 * Problem 5
	 */
	public static boolean isBinarySearchTree(TreeNode root) {
		return isBinarySearchTreeHelper(root, null, null);
	}

	private static boolean isBinarySearchTreeHelper(TreeNode n, Integer min, Integer max) {
		if (n == null) {
			return true;
		} else {
			if ((min != null && n.data < min) || (max != null && n.data >= max)) {
				return false;
			} else {
				return isBinarySearchTreeHelper(n.left, min, n.data) &&
					   isBinarySearchTreeHelper(n.right, n.data, max);
			}

		}
	}

	/*
	 * Problem 6
	 * Note: Can keep track of parent and child in last while loop instead of visited,
	 * 		 same solution
	 */
	TreeNode nextInOrder(TreeNode n) {
		if (n == null) {
			return null;
		} else if (n.right != null) {
			TreeNode n2 = n.right;
			while (n2.left != null)
				n2 = n2.left;
			n2.visited = true;
			return n2;
		} else {
			TreeNode n2 = n.parent;
			while (n2 != null && !n2.visited)
				n2 = n2.parent;
			return n2;
		}
	}
}
