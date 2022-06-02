package sorting;

import org.junit.Test;

public class PriorityQueueArrayTest {

	@Test
	public void test() {
		PriorityQueueArray p = new PriorityQueueArray(10);
		p.insert(2);
		p.insert(1);
		p.insert(4);
		p.insert(3);
		while (!p.isEmpty()) 
            System.out.println(p.remove());
	}

}
