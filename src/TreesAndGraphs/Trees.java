package TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;

public class Trees {
	static boolean isBalanced(Node<String> n) {
		return true;
	}

	public class Tree<T> {
	    private final Node<T> root;

	    public Tree(T rootData) {
	        root = new Node<T>();
	        root.data = rootData;
	        root.children = new ArrayList<Node<T>>();
	    }
	}

	public class Node<T> {
		private T data;
		private Node<T> parent;
		private List<Node<T>> children;
	}
}
