package sorting;

public class PriorityQueueHeap {
	private int[] keys;
	private int capacity;
	private int n;
	
	public PriorityQueueHeap(int capacity) {
		keys = new int[capacity];
		this.capacity = capacity;
		n = 0;
	}
	public boolean isEmpty() {
		return n == 0;
	}
	
	public void insert(int data) {
		if(n == capacity - 1) return;
		keys[++n] = data;
		swim(n);
	}
	
	public int remove() {
		if(isEmpty()) return -1;
		int result = keys[1];
		keys[1] = keys[n--];
		sink(1);
		return result;
	}
	
	public void swim(int k) {
		int swap = 0;
		while(k > 1) {
			if(keys[k] > keys[k/2]) {
				swap = keys[k];
				keys[k] = keys[k/2];
				keys[k/2] = swap;
				k = k/2;
				continue;
			}
			break;
		}
	}
	
	public void sink(int k) {
		int swap = 0;
		while(k < n) {
			if(keys[k] < keys[k*2]) {
				swap = keys[k];
				keys[k] = keys[k*2];
				keys[k*2] = swap;
				k = k * 2;
				continue;
			}else if(keys[k] < keys[k*2+1]) {
				swap = keys[k];
				keys[k] = keys[k*2+1];
				keys[k*2+1] = swap;
				k = k * 2 + 1;
				continue;
			}
			break;
		}
	}
}
