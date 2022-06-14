package search;

import java.util.Stack;

public class RedBlackBST<K extends Comparable<K>, V> {

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
	
	private Node getMin(Node tree) {
		Node iterator = tree;
		while( null != iterator.getKey() ) {
			iterator = iterator.getLeft();
		}
		return iterator.getParent();
	}
	
	private Node createNewNode(Node x, K key, V value) {
		if (null == x)
			x = new Node(key, value);
		Node nilLeft = new Node(null, null);
		Node nilRight = new Node(null, null);
		nilLeft.setRed(false);
		nilRight.setRed(false);
		nilLeft.setParent(x);
		nilRight.setParent(x);
		x.setKey(key);
		x.setValue(value);
		x.setRed(true);
		x.setLeft(nilLeft);
		x.setRight(nilRight);
		return x;
	}

	public void put(K key, V value) {
		if (null == root) {
			root = createNewNode(null, key, value);
			root.setRed(false);
			displayTree();
		} else {
			Node x = root;
			K k = null;
			while (null != x && null != (k = x.getKey())) {
				int cmp = k.compareTo(key);
				if (cmp == 0) {
					x.setValue(value);
					return;
				} else if (cmp > 0) {
					x = x.getLeft();
				} else {
					x = x.getRight();
				}
			}
			// apply nill sentinel
			x = createNewNode(x, key, value);
			displayTree();
			if( null == x.getParent().getParent() ) {
				return;
			}
			rebalance(x);
		}
		return;
	}

	private void rebalance(Node x) {
		Node parent = null;
		while (null != x && null != (parent = x.getParent()) && parent.isRed) {
			Node grandParent = parent.getParent();
			if (parent.getKey().compareTo(grandParent.getKey()) < 0) { // left child
				Node uncle = grandParent.getRight();
				// case 1
				if (null != uncle && uncle.isRed) {
					grandParent.setRed(true);
					uncle.setRed(false);
					parent.setRed(false);
					x = grandParent;
				} else if (parent.getKey().compareTo(x.getKey()) < 0) { // case 2 : LR case
					x = parent;
					parent = leftRotate(parent);
				} else { // case 3 : LL case
					parent.setRed(false);
					grandParent.setRed(true);
					grandParent = rightRotate(grandParent);
				}
			} else {
				Node uncle = grandParent.getLeft();
				// case 1
				if (null != uncle && uncle.isRed) {
					grandParent.setRed(true);
					uncle.setRed(false);
					parent.setRed(false);
					x = grandParent;
				} else if (parent.getKey().compareTo(x.getKey()) > 0) { // case 2 : RL case
					x = parent;
					rightRotate(parent);
				} else {
					parent.setRed(false);
					grandParent.setRed(true);
					x = leftRotate(grandParent);
				}
			}
			
		}
		if( null == x.getParent()) root = x;
		root.setRed(false);
	}

	private Node rightRotate(Node tree) {
		Node parent = tree.getParent();
		Node result = tree.getLeft();
		tree.setLeft(result.getRight());
		result.getRight().setParent(tree);
		result.setRight(tree);
		tree.setParent(result);
		result.setParent(parent);
		return result;
	}

	private Node leftRotate(Node tree) {
		Node parent = tree.getParent();
		Node result = tree.getRight();
		tree.setRight(result.getLeft());
		result.getLeft().setParent(tree);
		result.setLeft(tree);
		tree.setParent(result);
		result.setParent(parent);
		return result;
	};

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
