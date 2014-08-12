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
	public static TreeNode nextInOrder(TreeNode n) {
		if (n == null) {
			return null;
		} else if (n.right != null) {
			TreeNode n2 = n.right;
			while (n2.left != null) {
				n2 = n2.left;
			}
			n2.visited = true;
			return n2;
		} else {
			TreeNode n2 = n.parent;
			while (n2 != null && !n2.visited) {
				n2 = n2.parent;
			}
			return n2;
		}
	}

	/*
	 * Problem 7
	 */
	public static TreeNode ancestor(TreeNode c1, TreeNode c2, TreeNode root) {
		if (!containsNode(c1, root) || !containsNode(c2, root))
			return null;
		return ancestorHelper(c1, c2, root);
	}

	private static boolean containsNode(TreeNode n, TreeNode root) {
		if (n == null) {
			return false;
		} else if (n == root) {
			return true;
		} else {
			return containsNode(n, root.left) || containsNode(n, root.right);
		}
	}

	private static TreeNode ancestorHelper(TreeNode c1, TreeNode c2,
			TreeNode root) {
		if (root == null)
			return null;

		if (root == c1 || root == c2)
			return root;

		boolean con1 = containsNode(c1, root.left);
		boolean con2 = containsNode(c2, root.left);

		if (con1 && con2) {
			return ancestorHelper(c1, c2, root.left);
		} else if (con1) {
			return root;
		} else {
			return ancestorHelper(c1, c2, root.right);
		}
	}

	/*
	 * Problem 8
	 */
	public static boolean isSubtree(TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true;
		} else if (t1 == null) {
			return false;
		} else if (t1.data == t2.data && isSameTree(t1, t2)) {
			return true;
		} else {
			return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
		}
	}

	private static boolean isSameTree(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		} else if (t1 == null || t2 == null) {
			return false;
		} else {
			return t1.data == t2.data &&
				isSameTree(t1.left, t2.left) &&
				isSameTree(t1.right, t2.right);
		}
	}
}
