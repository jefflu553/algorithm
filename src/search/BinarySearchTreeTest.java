package search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
	private BinarySearchTree<Character, Integer> p;
	private BinarySearchTree<Character, Integer> fromBook;
	
	@Before
    public void init() {
		p = new BinarySearchTree<>();
		p.put('S', 0);
		p.put('E', 1);
		p.put('R', 3);
		p.put('C', 4);
		p.put('H', 5);
		p.put('E', 6);
		p.put('X', 7);
		p.put('M', 9);
		p.put('E', 12);
		System.out.println(p);
		
		fromBook = new BinarySearchTree<>();
		fromBook.put('S', 0);
		fromBook.put('E', 0);
		fromBook.put('X', 0);
		fromBook.put('A', 0);
		fromBook.put('C', 0);
		fromBook.put('R', 0);
		fromBook.put('H', 0);
		fromBook.put('M', 0);
		System.out.println(fromBook);
	}
	
	@Test
	public void testPut() {
		assertTrue("asdf", p.getSize() == 7);
		assertTrue("asdf", "C E H M R S X ".equals(p.toString()));
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
	
	@Test
	public void testFloor() {
		assertTrue("asdf", p.floor('A') == null);
		assertTrue("asdf", p.floor('G').compareTo('E') == 0);
		assertTrue("asdf", p.floor('E').compareTo('E') == 0);
		assertTrue("asdf", p.floor('Z').compareTo('X') == 0);
	}
	
	@Test
	public void testCeiling() {
		assertTrue("asdf", p.ceiling('G').compareTo('H') == 0);
		assertTrue("asdf", p.ceiling('H').compareTo('H') == 0);
		assertTrue("asdf", p.ceiling('Y') == null);
	}
	
	@Test
	public void testRank() {
		assertTrue("asdf", fromBook.rank('E') == 2);
		assertTrue("asdf", fromBook.rank('C') == 1);
		assertTrue("asdf", fromBook.rank('A') == 0);
		assertTrue("asdf", fromBook.rank('M') == 4);
		assertTrue("asdf", fromBook.rank('Z') == 8);
		assertTrue("asdf", fromBook.rank('I') == 4);
	}

}
