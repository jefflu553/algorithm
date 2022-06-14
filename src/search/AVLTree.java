package search;

import entity.INode;

public class AVLTree<K extends Comparable<K>, V> {

	private INode<K, V> root;
	
	public void delete(K key) {
		root = delete(key, root);
	}
	
	private K minTree(INode<K, V> tree) {
		if(tree == null) return null;
		if(tree.getLeft() != null) return minTree(tree.getLeft());
		return tree.getKey();
	}
	
	private V getTree(K key, INode<K, V> tree) {
		if(tree == null) return null;
		if(tree.getKey().compareTo(key) == 0) return tree.getValue();
		else if(tree.getKey().compareTo(key) > 0) return getTree(key, tree.getLeft());
		return getTree(key, tree.getRight());
	}
	
	private INode<K, V> rebalanceOnDelete(K key, INode<K, V> tree) {
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
	
	private INode<K, V> delete(K key, INode<K, V> tree) {
		if(null == tree) return null;
		int cmp = tree.getKey().compareTo(key);
		if(0 == cmp) {
			if(null == tree.getLeft() && null == tree.getRight()) return null;
			else if(null != tree.getLeft() && null != tree.getRight()) {
				K minK = minTree(tree.getRight());
				V minV = getTree(key, tree.getRight());
				INode<K, V> replace = new Node(minK, minV, 1);
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

	public INode<K, V> getRoot() {
		return root;
	}

	public void setRoot(INode<K, V> root) {
		this.root = root;
	}

	public void put(K key, V value) {
		root = putTree(key, value, root);
		return;
	}

	private INode<K, V> rightRotate(INode<K, V> tree) {
		INode<K, V> result = tree.getLeft();
		tree.setLeft(result.getRight());
		result.setRight(tree);
		return result;
	}

	private INode<K, V> leftRotate(INode<K, V> tree) {
		INode<K, V> result = tree.getRight();
		tree.setRight(result.getLeft());
		result.setLeft(tree);
		return result;
	};
	
	private int factor(INode<K, V> tree) {
		int f = height(tree.getLeft()) - height(tree.getRight());
		return f;
	}

	private INode<K, V> rebalance(K key, INode<K, V> tree) {
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

	private INode<K, V> putTree(K key, V value, INode<K, V> tree) {
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

	public int height(INode<K, V> tree) {
		if (null == tree)
			return -1;
		return 1 + Math.max(height(tree.getLeft()), height(tree.getRight()));
	}

	public class Node implements INode<K, V> {
		private K key;
		private V value;
		private INode<K, V> left;
		private INode<K, V> right;
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

		public INode<K, V> getLeft() {
			return left;
		}

		@Override
		public void setLeft(INode<K, V> left) {
			this.left = left;
		}

		public INode<K, V> getRight() {
			return right;
		}

		public void setRight(INode<K, V> right) {
			this.right = right;
		}

		public Node(K key, V value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
		}

	}
}
