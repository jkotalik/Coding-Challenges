Coding Challenges
=========
A selection of difficult coding problems that can be solved in any programming language. These problems are divided into multiple categories, and each category has its own folder. Solutions to these problems can be found in src.

To add your own solutions, feel free to fork this repo.

## Trees and Graphs
1. Implement a function to check if a tree is balanced. For the purposes of this question,
a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
from the root by more than one.  
```boolean isBalanced(TreeNode n)```

2. Given a directed graph, design an algorithm to find out whether there is a route
between two nodes.  
```boolean isDirectedRoute(Vertex start, Vertex end, Graph g)```

3. Given a sorted (increasing order) array, write an algorithm to create a binary tree with
minimal height.  
```TreeNode createMinimalBST(int[] arr)```

4. Given a binary search tree, design an algorithm which creates a linked list of all the
nodes at each depth (i.e., if you have a tree with depth D, you'll have D linked lists).  
```List<LinkedList<TreeNode>> listTreeByDepth(TreeNode head)```

5. Implement a function to check if a binary tree is a binary search tree.  
```boolean isBinarySearchTree(TreeNode root)```

6. Write an algorithm to find the 'next' node (i.e., in-order successor) of a given node in
a binary search tree where each node has a link to its parent.  
```TreeNode nextInOrder(TreeNode n)```

7. Design an algorithm and write code to find the first common ancestor of two nodes
in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
necessarily a binary search tree.  
```TreeNode ancestor(TreeNode c1, TreeNode c2, TreeNode root)```

8. You have two very large binary trees: T1, with millions of nodes, and T2, with hundreds
of nodes. Create an algorithm to decide if T2 is a subtree of T1.  
```boolean isSubtree(TreeNode t1, TreeNode t2)```

9. You are given a binary tree in which each node contains a value. Design an algorithm
to print all paths which sum up to that value. Note that it can be any path in the tree -
it does not have to start at the root.
