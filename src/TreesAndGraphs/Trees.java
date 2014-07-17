package TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;

public class Trees {
	public static boolean isBalanced(Node<String> n) {
		return height(n) != -1;
	}

	private static int height(Node<String> n) {
		return 0;
	}


	/*
	 * Default Tree class
	 */
	public class Tree<T> {
	    private final Node<T> root;

	    public Tree(T rootData) {
	        root = new Node<T>();
	        root.data = rootData;
	        root.children = new ArrayList<Node<T>>();
	    }
	}

	/*
	 * Default Node class
	 */
	public class Node<T> {
		private T data;
		private Node<T> parent;
		private List<Node<T>> children;
	}
}
