package search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NonrecursiveBST<K extends Comparable<K>, V> {
	
	private Node root;
	
	public Iterable<K> keys() {
		if(null == root) return null;
		Stack<Node> stack = new Stack<>();
		Queue<K> queue = new LinkedList<>();
		Node tree = root;
		while(null != tree || !stack.isEmpty()) {
			if(null != tree) {
				stack.push(tree);
				tree = tree.left;
			}else {
				tree = stack.pop();
				queue.add(tree.getKey());
				tree = tree.right;
			}
		}
		return queue;
	}
	
	public V get(K key) {
		if(null == root) return null;
		Node tree = root;
		int cmp = tree.getKey().compareTo(key);
		while(cmp != 0) {
			if(cmp > 0) {
				tree = tree.getLeft();
				if(null == tree) return null;
				cmp = tree.getKey().compareTo(key);
			}else {
				tree = tree.getRight();
				if(null == tree) return null;
				cmp = tree.getKey().compareTo(key);
			}
		}
		return tree.getValue();
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
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void put(K key, V value) {
		if(null == root) {
			root = new Node(key, value);
			return;
		}
		Node tree = root;
		while(null != tree) {
			int cmp = tree.getKey().compareTo(key);
			if(cmp == 0) {
				tree.setValue(value);
				return;
			}else if(cmp > 0) {
				if(null == tree.getLeft()) {
					tree.setLeft(new Node(key, value));
					break;
				}else {
					tree = tree.getLeft();
				}
			}else {
				if(null == tree.getRight()) {
					tree.setRight(new Node(key, value));
					break;
				}else {
					tree = tree.getRight();
				}
			}
		}
		
	}
	
	public class Node{
		private K key;
		private V value;
		private Node left;
		private Node right;

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

		public Node(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
	}

}
