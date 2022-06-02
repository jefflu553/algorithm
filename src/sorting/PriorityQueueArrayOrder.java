package sorting;

public class PriorityQueueArrayOrder {
	private int[] keys;
	private int capacity;
	private int n;
	public PriorityQueueArrayOrder(int capacity) {
		keys = new int[capacity];
		this.capacity = capacity;
		n = 0;
	}
	public boolean isEmpty() {
		return n == 0;
	}
	public boolean insert(int data) {
		if(n == capacity) return false;
		int i = n-1;
		while(i>=0 && keys[i]>data) {
			keys[i+1] = keys[i];
			i--;
		}
		keys[i+1] = data;
		n++;
		return true;
	}
	public int remove() {
		if(isEmpty()) return -1;
		return keys[--n];
	}
}
