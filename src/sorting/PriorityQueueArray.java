package sorting;

public class PriorityQueueArray {
	private int[] keys;
	private int capacity;
	private int n;
	
	public PriorityQueueArray(int capacity) {
		keys = new int[capacity];
		this.capacity = capacity;
		n = 0;
	}
	public boolean isEmpty() {
		return n == 0;
	}
	public boolean insert(int data) {
		if(n == capacity) return false;
		keys[n++] = data;
		return true;
	}
	public int remove() {
		if(isEmpty()) return -1;
		int max = 0;
		for(int i=0; i<n; i++) {
			if(keys[i] > keys[max]) max = i; 
		}
		int swap = keys[max];
		keys[max] = keys[n-1];
		keys[n-1] = swap;
		return keys[--n];
	}

}
