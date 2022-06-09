package search;

import java.util.Stack;

public class AVLTree<K extends Comparable<K>, V> {

	private Node root;
	
	public void delete(K key) {
		root = delete(key, root);
	}
	
	private K minTree(Node tree) {
		if(tree == null) return null;
		if(tree.getLeft() != null) return minTree(tree.getLeft());
		return tree.getKey();
	}
	
	private V getTree(K key, Node tree) {
		if(tree == null) return null;
		if(tree.getKey().compareTo(key) == 0) return tree.getValue();
		else if(tree.getKey().compareTo(key) > 0) return getTree(key, tree.getLeft());
		return getTree(key, tree.getRight());
	}
	
	private Node rebalanceOnDelete(K key, Node tree) {
		if(tree.getKey().compareTo(key) < 0) { // key has been deleted on right
			int factor = factor(tree.getLeft());
			if(factor >= 0) {
				tree = rightRotate(tree);
			}else {
				tree.setLeft(leftRotate(tree.getLeft()));
				tree = rightRotate(tree);
			}
		}else {
			int factor = factor(tree.getRight());
			if(factor <= 0) {
				tree = leftRotate(tree);
			}else {
				tree.setRight(rightRotate(tree.getRight()));
				tree = leftRotate(tree);
			}
		}
		return tree;
	}
	
	private Node delete(K key, Node tree) {
		if(null == tree) return null;
		int cmp = tree.getKey().compareTo(key);
		if(0 == cmp) {
			if(null == tree.getLeft() && null == tree.getRight()) return null;
			else if(null != tree.getLeft() && null != tree.getRight()) {
				K minK = minTree(tree.getRight());
				V minV = getTree(key, tree.getRight());
				Node replace = new Node(minK, minV, 1);
				replace.setLeft(tree.getLeft());
				replace.setRight(tree.getRight());
				return replace;
			}else if(null == tree.getLeft()) return tree.getRight();
			else return tree.getLeft();
		}
		if(cmp > 0) tree.setLeft(delete(key, tree.getLeft()));
		else if(cmp < 0) tree.setRight(delete(key, tree.getRight()));
		int f = factor(tree);
		if( f > 1 || f < -1 ) tree = rebalanceOnDelete(key, tree);
		return tree;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void put(K key, V value) {
		root = putTree(key, value, root);
		return;
	}

	private Node rightRotate(Node tree) {
		Node result = tree.getLeft();
		tree.setLeft(result.getRight());
		result.setRight(tree);
		return result;
	}

	private Node leftRotate(Node tree) {
		Node result = tree.getRight();
		tree.setRight(result.getLeft());
		result.setLeft(tree);
		return result;
	};
	
	private int factor(Node tree) {
		int f = height(tree.getLeft()) - height(tree.getRight());
		return f;
	}

	private Node rebalance(K key, Node tree) {
		int factor = factor(tree);
		if (factor > 1) {
			int cmp = tree.getLeft().getKey().compareTo(key);
			if (cmp > 0) { // left-left case
				tree = rightRotate(tree);
			} else { // left-right case
				tree.setLeft(leftRotate(tree.getLeft()));
				tree = rightRotate(tree);
			}
		} else if (factor < -1) {
			int cmp = tree.getRight().getKey().compareTo(key);
			if (cmp < 0) { // right-right case
				tree = leftRotate(tree);
			} else { // right-left case
				tree.setRight(rightRotate(tree.getRight()));
				tree = leftRotate(tree);
			}
		}
		return tree;
	}

	private Node putTree(K key, V value, Node tree) {
		if (tree == null) {
			return new Node(key, value, 1);
		}
		int cmp = tree.getKey().compareTo(key);
		if (cmp == 0) {
			tree.setValue(value);
		} else if (cmp < 0) {
			tree.setRight(putTree(key, value, tree.getRight()));
			// tree.setSize( 1 + size(tree.getLeft()) + size(tree.getRight()) );
			// //由于左右子树已经产生变化，所以要重新计算size.
			// tree.setHeight( 1 + height(tree.getLeft()) + height(tree.getRight()) );
			tree = rebalance(key, tree);
		} else {
			tree.setLeft(putTree(key, value, tree.getLeft()));
			// tree.setSize( 1 + size(tree.getLeft()) + size(tree.getRight()) );
			// tree.setHeight( 1 + height(tree.getLeft()) + height(tree.getRight()) );
			tree = rebalance(key, tree);
		}
		return tree;
	}

	public int height() {
		if (null == root)
			return 0;
		return height(root);
	}

	public int height(Node tree) {
		if (null == tree)
			return -1;
		return 1 + Math.max(height(tree.getLeft()), height(tree.getRight()));
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
					System.out.print(temp.key);
					localStack.push(temp.left);
					localStack.push(temp.right);
					if (temp.left != null || temp.right != null)
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
		private int size;

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
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

		public Node(K key, V value, int size) {
			super();
			this.key = key;
			this.value = value;
			this.size = size;
		}

	}
}
