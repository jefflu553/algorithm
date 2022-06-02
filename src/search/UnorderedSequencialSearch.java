package search;

public class UnorderedSequencialSearch<K extends Comparable<K>, V> implements Comparable<UnorderedSequencialSearch<K,V>> {
	private Node head;
	private int size;
	
	
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int size() {
		return this.size;
	}
	
	public Node min() {
		Node minimum = head;
		Node iterator = head;
		while(iterator != null) {
			if(iterator.getKey().compareTo(minimum.getKey()) < 0) {
				minimum = iterator;
			}
			iterator = iterator.getNext();
		}
		return minimum;
	}
	
	public Node max() {
		Node maximum = head;
		Node iterator = head;
		while(iterator != null) {
			if(iterator.getKey().compareTo(maximum.getKey()) > 0) {
				maximum = iterator;
			}
			iterator = iterator.getNext();
		}
		return maximum;
	}
	
	public void put(K key, V value) {
		Node item = head;
		while(item != null) {
			if(item.getKey() == key) {
				item.setValue(value);
				return;
			}
			item = item.getNext();
		}
		item = new Node(key, value, head);
		this.size++;
		head = item;
	}
	
	public V get(K key) {
		Node item = head;
		while(item != null) {
			if(item.getKey() == key) {
				return item.getValue();
			}
			item = item.getNext();
		}
		return null;
	}
	
	public class Node {
		
		private K key;
		private V value;
		private Node next;
		
		@Override
	    public String toString() {
	        return this.key.toString() + " / " + this.value.toString();
	    }

		public Node(K key, V value, Node next) {
			super();
			this.key = key;
			this.value = value;
			this.next = next;
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



		public Node getNext() {
			return next;
		}



		public void setNext(Node next) {
			this.next = next;
		}

	
	}

	@Override
	public int compareTo(UnorderedSequencialSearch<K,V> o) {
		// TODO Auto-generated method stub
		if(o == null || o.getSize() == 0) return -1;
		if(this == o) return 0;
		if(this.size != o.getSize()) return -1;
		Node item = this.head;
		Node comparedItem = o.getHead();
		while(item != null && comparedItem != null) {
			System.out.printf("%s vs %s -- %d vs %d %n", item.getKey(), comparedItem.getKey(), item.getValue(), comparedItem.getValue());
			if(item.getKey() != comparedItem.getKey() || item.getValue() != comparedItem.getValue()) return -1;
			item = item.getNext();
			comparedItem = comparedItem.getNext();
		}
		return 0;
	}
}
