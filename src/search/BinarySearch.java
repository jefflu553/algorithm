package search;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BinarySearch<K extends Comparable<K>, V> {
	private Node[] arr;
	private int size;
	
	
	
	@SuppressWarnings("unchecked")
	public BinarySearch(int capacity) {
		super();
		this.arr = (BinarySearch<K, V>.Node[]) Array.newInstance(Node.class, capacity);
	}
	
	public int size() {
		return this.size;
	}

	public void put(K key, V value) {
		if(this.size == 0) {
			arr[0] = new Node(key, value);
			this.size++;
			return;
		}
		int i = search(key);
		if(i >= 0) {
			arr[i].setValue(value);
			return;
		}
		i = size;
		while(i > 0) {
			if(arr[i-1].getKey().compareTo(key) < 0) {
				break;
			}
			arr[i] = arr[i-1];
			i--;
		}
		arr[i] = new Node(key, value);
		this.size++;
		return;
	}
	
	public V get(K key) {
		int pos = search(key);
		if(pos >= 0) return arr[pos].getValue();
		return null;
	}
	
	public boolean contain(K key) {
		if(search(key) >= 0) return true;
		return false;
	}
	
	public K min() {
		if(this.size >= 0) return this.arr[0].getKey();
		return null;
	}
	
	public K max() {
		if(this.size >= 0) return this.arr[this.size-1].getKey();
		return null;
	}
	
	public K floor(K key) {
		int pos = searchMost(key, true);
		if(pos < 0) return null;
		return arr[pos].getKey();
	}
	
	public K ceiling(K key) {
		int pos = searchMost(key, false);
		if(pos >= size) return null;
		return arr[pos].getKey();
	}
	
	public int rank(K key) {
		K floor = floor(key);
		if(floor == null) return 0;
		int pos = search(floor);
		if(pos < 0) return 0;
		if(floor.equals(key)) return pos;
		return pos + 1;
	}
	
	public K select(int num) {
		if(num <= 0 && size >= 0) return arr[0].getKey();
		if(num >= size) return null;
		if(size >= 0) return arr[num].getKey();
		return null;
	}
	
	public int size(K lo, K hi) {
		if(lo.compareTo(hi) > 0) return 0;
		K loCeiling = ceiling(lo);
		K hiFloor = floor(hi);
		if(null != loCeiling && null != hiFloor) {
			if(loCeiling.compareTo(hiFloor) > 0) return 0;
			int rangeNum = search(hiFloor) - search(loCeiling) + 1;
			return rangeNum;
		}
		return 0;
	}
	
	public Iterable<K> keys(K lo, K hi) {
		List<K> results = new ArrayList<>();
		if(lo.compareTo(hi) > 0) return results;
		K loCeiling = ceiling(lo);
		K hiFloor = floor(hi);
		if(null != loCeiling && null != hiFloor) {
			if(loCeiling.compareTo(hiFloor) > 0) return results;
			int end = search(hiFloor);
			int start = search(loCeiling);
			for(int i=start; i<=end; i++) {
				results.add(arr[i].getKey());
			}
		}
		return results;
	}
	
	public void delete(K key) {
		int pos = search(key);
		if(pos == this.size - 1) {
			this.size--;
			return;
		}
		if(pos >= 0) {
			for(int i=pos; i<size-1; i++) {
				arr[i] = arr[i+1];
			}
			this.size--;
		}
	}
		//return the pos of the key
	private int search(K key) {
		if(key == null) return -1;
		if(this.size <= 0) return -1;
		return get(key, 0, this.size-1);
	}
		//true : floor; false : ceil
	private int searchMost(K key, boolean isFloor) {
		if(this.size <= 0) return -1;
		return getMost(key, 0, this.size-1, isFloor);
	}
	
	private int getMost(K key, int start, int end, boolean isFloor) {
		if(start > end) {
			if(isFloor) return end;
			return start;
		}
		int mid = start + (end - start) / 2;
		if(arr[mid].getKey().compareTo(key) == 0) {
			return mid;
		}
		if(arr[mid].getKey().compareTo(key) > 0) return getMost(key, start, mid-1, isFloor);
		return getMost(key, mid+1, end, isFloor);
	}
	
	private int get(K key, int start, int end) {
		if(start > end) return -1;
		int mid = start + (end - start) / 2;
		if(arr[mid].getKey().compareTo(key) == 0) return mid;
		if(arr[mid].getKey().compareTo(key) > 0) return get(key, start, mid-1);
		return get(key, mid+1, end);
	}
	
	public Node[] getArr() {
		return arr;
	}

	public void setArr(Node[] arr) {
		this.arr = arr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinarySearch<K, V> other = (BinarySearch<K, V>)o;
        if(other.size() != this.size) return false;
        Node[] otherArr = other.getArr();
        for(int i=0; i<size; i++) {
        	if(arr[i].key != otherArr[i].key || arr[i].value != otherArr[i].value) {
        		return false;
        	}
        }
        return true;
	}
	
	@Override
	public String toString() {
		StringBuilder strKey = new StringBuilder();
		StringBuilder strValue = new StringBuilder();
		for(int i=0; i<size; i++) {
			strKey.append(arr[i].key);
			strValue.append(arr[i].value);
        	if(i < size) {
        		strKey.append(" ");
        		strValue.append(" ");
        	}
        }
		strKey.append(" / ");
		strKey.append(strValue.toString());
		return strKey.toString();
	}

	public class Node {

		private K key;
		private V value;

		@Override
		public String toString() {
			return this.key.toString() + " / " + this.value.toString();
		}

		public Node(K key, V value) {
			super();
			this.key = key;
			this.value = value;
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

	}
}
