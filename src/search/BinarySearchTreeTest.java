package search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
	private BinarySearchTree<Character, Integer> p;
	
	@Before
    public void init() {
		p = new BinarySearchTree<>();
		p.put('S', 0);
		p.put('E', 1);
		p.put('A', 2);
		p.put('R', 3);
		p.put('C', 4);
		p.put('H', 5);
		p.put('E', 6);
		p.put('X', 7);
		p.put('A', 8);
		p.put('M', 9);
		p.put('E', 12);
		System.out.println(p);
	}
	
	@Test
	public void testPut() {
		assertTrue("asdf", p.getSize() == 8);
		assertTrue("asdf", "A C E H M R S X ".equals(p.toString()));
	}

	@Test
	public void testGet() {
		assertTrue("asdf", p.get('E') == 12);
		assertTrue("asdf", p.get('Z') == null);
	}
	
	@Test
	public void testMin() {
		assertTrue("asdf", p.min().compareTo('A') == 0);
		BinarySearchTree<Character, Integer> n = new BinarySearchTree<>();
		assertTrue("asdf", n.min() == null);
	}
	
	@Test
	public void testMax() {
		assertTrue("asdf", p.max().compareTo('X') == 0);
		BinarySearchTree<Character, Integer> n = new BinarySearchTree<>();
		assertTrue("asdf", n.max() == null);
	}

}
