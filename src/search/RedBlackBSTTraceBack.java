package search;

import java.util.Stack;

//maintain balance while traceback
public class RedBlackBSTTraceBack<K extends Comparable<K>, V> {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private Node root;
	
		
	public void insert(K key, V value) {
		if(null == root) {
			root = new Node(key, value);
		}else {
			root = insertHelp(root, key, value);
		}
		root.setRed(false);
	}
		// remind to set parent
	private Node rotateLeft(Node tree) {
		Node holder = tree.getRight();
		tree.setRight(holder.getLeft());
		if(null != holder.getLeft())	holder.getLeft().setParent(tree);
		holder.setLeft(tree);
		tree.setParent(holder);
		return holder;
	}
	
	private Node rotateRight(Node tree) {
		Node holder = tree.getLeft();
		tree.setLeft(holder.getRight());
		if(null != holder.getRight())	holder.getRight().setParent(tree);
		holder.setRight(tree);
		tree.setParent(holder);
		return holder;
	}
	
	private static boolean ll = false;
	private static boolean rr = false;
	private static boolean lr = false;
	private static boolean rl = false;
	private Node insertHelp(Node tree, K key, V value) {
		if(null == tree) {
			return new Node(key, value);
		}
		int cmp = tree.getKey().compareTo(key);
			// 1, insert nodes as like bst
		boolean isBalanceViolated = false;
		if( 0 == cmp ) {
			tree.setValue(value);
			return tree;
		}else if(cmp > 0) {
			tree.left = insertHelp(tree.getLeft(), key, value);
			tree.getLeft().setParent(tree);
			if(tree.isRed && tree.getLeft().isRed) {
				isBalanceViolated = true;
			}
		}else {
			tree.right = insertHelp(tree.getRight(), key, value);
			tree.getRight().setParent(tree);
			if(tree.isRed && tree.getRight().isRed) {
				isBalanceViolated = true;
			}
		}
			// rotate while the root is grandparent.
		if(ll) {
			tree = rotateRight(tree);
			tree.setRed(false);
			tree.getRight().setRed(true);
			ll = false;
		}else if(rr) {
			tree = rotateLeft(tree);
			tree.setRed(false);
			tree.getLeft().setRed(true);
			rr = false;
		}else if(lr) {
			tree.setLeft(rotateLeft(tree.getLeft()));
			tree = rotateRight(tree);
			tree.setRed(false);
			tree.getRight().setRed(true);
			lr = false;
		}else if(rl) {
			tree.setRight(rotateRight(tree.getRight()));
			tree = rotateLeft(tree);
			tree.setRed(false);
			tree.getLeft().setRed(true);
			rl = false;
		}
			// condition on uncle. will not be happened on root?
		if(isBalanceViolated) {
			Node grandParent = tree.getParent();
			if(grandParent.getLeft() == tree) {	// uncle is on the right
				if( null == grandParent.getRight() || !grandParent.getRight().isRed ) { // uncle is black or null
					if(cmp > 0) { // new node is on left
						ll = true;
					}else {
						lr = true;
					}
				}else {	// uncle is red, just change recolor
					tree.setRed(false);
					grandParent.getRight().setRed(false);
					grandParent.setRed(true);
				}
			}else { // uncle is on the left
				if( null == grandParent.getLeft() || !grandParent.getLeft().isRed ) { // uncle is black or null
					if(cmp > 0) { // new node is on left
						rl = true;
					}else {
						rr = true;
					}
				}else {	// uncle is red, just change recolor
					tree.setRed(false);
					grandParent.getLeft().setRed(false);
					grandParent.setRed(true);
				}
			}
		}
		return tree;
	}
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public boolean structualIdenticalTrees(Node a, Node b) {
		/* 1. both empty */
		if (b == null && a == null)
			return true;

		/* 2. both non-empty -> compare them */
		if (a != null && b != null)
			return (a.getKey() == b.getKey() && 0 == Boolean.compare(a.isRed(), b.isRed())
					&& structualIdenticalTrees(a.getLeft(), b.getLeft())
					&& structualIdenticalTrees(a.getRight(), b.getRight()));

		/* 3. one empty, one not -> false */
		return false;
	}

	public void displayTree() {
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(root);
		int emptyLeaf = 32;
		boolean isRowEmpty = false;
		System.out.println("****......................................................****");
		while (isRowEmpty == false) {

			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;
			for (int j = 0; j < emptyLeaf; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				Node temp = globalStack.pop();
				if (temp != null) {
					if (temp.isRed)
						System.out.print(ANSI_WHITE + temp.getKey() + ANSI_RESET);
					else
						System.out.print(temp.getKey());
					localStack.push(temp.getLeft());
					localStack.push(temp.getRight());
					if (temp.getLeft() != null || temp.getRight() != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < emptyLeaf * 2 - 2; j++)
					System.out.print(' ');
			}
			System.out.println();
			emptyLeaf /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		}
		System.out.println("****......................................................****");
	}

	public class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;
		private Node parent;
		private boolean isRed;

		public boolean isRed() {
			return isRed;
		}

		public void setRed(boolean isRed) {
			this.isRed = isRed;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.isRed = true;
		}

	}
}
