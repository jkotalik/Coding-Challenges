package TreesAndGraphs;


public class Tree {
	private final Node root;

    public Tree() {
        root = new Node();
    }

    /*
     * Problem 1
     */
	public static boolean isBalanced(Node root) {
		if (root == null)
			return false;
		else
			return height(root) != -1;
	}

	private static int height(Node n) {
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
	 * Default Node class
	 */
	public static class Node {
		private int data;
		private Node left;
		private Node right;
	}
}
