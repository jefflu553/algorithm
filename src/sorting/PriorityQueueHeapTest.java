package sorting;

import org.junit.Test;

public class PriorityQueueHeapTest {

	@Test
	public void test() {
		PriorityQueueHeap p = new PriorityQueueHeap(10);
		p.insert(2);
		p.insert(1);
		p.insert(4);
		p.insert(3);
		while (!p.isEmpty()) 
            System.out.println(p.remove());
	}

}
