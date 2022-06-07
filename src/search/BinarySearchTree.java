package search;

public class BinarySearchTree<K extends Comparable<K>, V> {
	
	private Node root;
	private int size;
	
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
			return new Node(key, value, null, null);
		}else if(tree.getKey().compareTo(key) == 0) {
			tree.setValue(value);
		}else if(tree.getKey().compareTo(key) < 0) {
			tree.setRight(putTree(key, value, tree.getRight()));
		}else {
			tree.setLeft(putTree(key, value, tree.getLeft()));
		}
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	private class Node{
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
			calSize();
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
			calSize();
		}
		private void calSize() {
			this.size = 1;
			if(null != left) this.size = this.size + left.getSize();
			if(null != right) this.size = this.size + right.getSize();
		}
		public Node(K key, V value, BinarySearchTree<K, V>.Node left, BinarySearchTree<K, V>.Node right) {
			super();
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			calSize();
		}
		
		
	}

}
