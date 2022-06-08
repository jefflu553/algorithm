package search;

public class BinarySearchTree<K extends Comparable<K>, V> {
	
	private Node root;
	
	public int size() {
		if(null == root) return 0;
		return size(root);
	}
	
	private int size(Node tree) {
		if(null == tree) return 0;
		return tree.getSize();
	}
	
	public void delete(K key) {
		delete(key, root);
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
				replace.setSize( 1 + size(replace.getLeft()) + size(replace.getRight()) );
				return replace;
			}else if(null == tree.getLeft()) return tree.getRight();
			else return tree.getLeft();
		}
		if(cmp > 0) tree.setLeft(delete(key, tree.getLeft()));
		else if(cmp < 0) tree.setRight(delete(key, tree.getRight()));
		tree.setSize( 1 + size(tree.getLeft()) + size(tree.getRight()) );
		return tree;
	}

	public void deleteMin() {
		if(null == root) return;
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node tree) {
		if(null == tree.getLeft()) return tree.getRight();
		else tree.setLeft(deleteMin(tree.getLeft()));
		tree.setSize( 1 + size(tree.getLeft()) + size(tree.getRight()) );
		return tree;
	}
	
	public void deleteMax() {
		if(null == root) return;
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node tree) {
		if(null == tree.getRight()) return tree.getLeft();
		else tree.setRight(deleteMax(tree.getRight()));
		tree.setSize( 1 + size(tree.getLeft()) + size(tree.getRight()) );
		return tree;
	}
	
	public K select(int num) {
		if(num < 0) return null;
		return selectTree(num, root);
	}
	
	private K selectTree(int num, Node tree) {
		if(null == tree) return null;
		int leftAmount = 0;
		if(null != tree.getLeft()) leftAmount = tree.getLeft().getSize();
		if(leftAmount == num) return tree.getKey();
		else if(leftAmount > num) return selectTree(num, tree.getLeft());
		return selectTree(num - 1 - leftAmount, tree.getRight());
	}
	
	public int rank(K key) {
		return rankTree(key, root);
	}
	
	private int rankTree(K key, Node root) {
		if(null == root) return 0;
		int cmp = root.getKey().compareTo(key);
		if(0 == cmp) return (null == root.getLeft()) ? 0 : root.getLeft().getSize();
		else if(cmp > 0) return rankTree(key, root.getLeft());
		int left = (null == root.getLeft()) ? 0 : root.getLeft().getSize();
		return 1 + left + rankTree(key, root.getRight());
	}
	
	public K floor(K key) {
		if(null == key || null == root) return null;
		return floorTree(key, root);
	}
	
	public K ceiling(K key) {
		if(null == key || null == root) return null;
		return ceilingTree(key, root);
	}
	
	private K ceilingTree(K key, Node tree) {
		if(null == tree) return null;
		int compared = tree.getKey().compareTo(key);
		if(compared == 0) return tree.getKey();
		if(compared < 0) return ceilingTree(key, tree.getRight());
		K ceiling = ceilingTree(key, tree.getLeft());
		if(null == ceiling) return tree.getKey();
		return ceiling;
	}
	
	private K floorTree(K key, Node tree) {
		if(null == tree) return null;
		int compared = tree.getKey().compareTo(key);
		if(compared == 0) return tree.getKey();
		if(compared > 0) return floorTree(key, tree.getLeft());
		K floor = floorTree(key, tree.getRight());
		if(null == floor) return tree.getKey();
		return floor;
	}
	
	public K min() {
		return minTree(root);
	}
	
	private K minTree(Node tree) {
		if(tree == null) return null;
		if(tree.getLeft() != null) return minTree(tree.getLeft());
		return tree.getKey();
	}
	
	public K max() {
		return maxTree(root);
	}
	
	private K maxTree(Node tree) {
		if(tree == null) return null;
		if(tree.getRight() != null) return minTree(tree.getRight());
		return tree.getKey();
	}
	
	public void put(K key, V value) {
		root = putTree(key, value, root);
		return;
	}
	
	private void inOrder(StringBuilder sb, Node tree) {
		if(null == tree) return;
		inOrder(sb, tree.getLeft());
		sb.append(tree.getKey().toString());
		sb.append(" ");
		inOrder(sb, tree.getRight());
	}
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		inOrder(sBuilder, root);
		return sBuilder.toString();
	}
	
	private Node putTree(K key, V value, Node tree) {
		if(tree == null) {
			return new Node(key, value, 1);
		}
		int cmp = tree.getKey().compareTo(key);
		if(cmp == 0) {
			tree.setValue(value);
		}else if(cmp < 0) {
			tree.setRight(putTree(key, value, tree.getRight()));
		}else {
			tree.setLeft(putTree(key, value, tree.getLeft()));
		}
		tree.setSize( 1 + size(tree.getLeft()) + size(tree.getRight()) );
		return tree;
	}
	
	public V get(K key) {
		return getTree(key, root);
	}
	
	private V getTree(K key, Node tree) {
		if(tree == null) return null;
		if(tree.getKey().compareTo(key) == 0) return tree.getValue();
		else if(tree.getKey().compareTo(key) > 0) return getTree(key, tree.getLeft());
		return getTree(key, tree.getRight());
	}
	
	
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}

	public class Node{
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
			this.left = null;
			this.right = null;
		}
		
		
	}

}
