package search;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearchTreeTest {
	private static BinarySearchTree<Character, Integer> p;
	private static BinarySearchTree<Character, Integer> fromBook;
	
	@BeforeClass
    public static void init() {
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
		assertTrue("asdf", p.getRoot().getSize() == 7);
		assertTrue("asdf", "C E H M R S X ".equals(p.toString()));
	}

	@Test
	public void testGet() {
		assertTrue("asdf", p.get('E') == 12);
		assertTrue("asdf", p.get('Z') == null);
	}
	
	@Test
	public void testMin() {
		assertTrue("asdf", p.min().compareTo('C') == 0);
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
	
	@Test
	public void testSelect() {
		assertTrue("asdf", fromBook.select(3).compareTo('H') == 0);
		assertTrue("asdf", fromBook.select(0).compareTo('A') == 0);
		assertTrue("asdf", fromBook.select(-1) == null);
		assertTrue("asdf", fromBook.select(8) == null);
	}
	
	private BinarySearchTree<Character, Integer> createForTmp() {
		BinarySearchTree<Character, Integer> fromBook = new BinarySearchTree<>();
		fromBook.put('S', 0);
		fromBook.put('E', 0);
		fromBook.put('X', 0);
		fromBook.put('A', 0);
		fromBook.put('C', 0);
		fromBook.put('R', 0);
		fromBook.put('H', 0);
		fromBook.put('M', 0);
		return fromBook;
	}
	
	@Test
	public void testDeleteMin() {
		BinarySearchTree<Character, Integer> tmp = createForTmp();
		assertTrue("asdf", tmp.getRoot().getSize() == 8);
		tmp.deleteMin();
		assertTrue("asdf", tmp.getRoot().getSize() == 7);
		assertTrue("asdf", "C E H M R S X ".equals(tmp.toString()));
	}
	
	@Test
	public void testDeleteMax() {
		BinarySearchTree<Character, Integer> tmp = createForTmp();
		assertTrue("asdf", tmp.getRoot().getSize() == 8);
		tmp.deleteMax();
		assertTrue("asdf", tmp.getRoot().getSize() == 7);
		assertTrue("asdf", "A C E H M R S ".equals(tmp.toString()));
	}
	
	@Test
	public void testDelete() {
		BinarySearchTree<Character, Integer> tmp = createForTmp();
		assertTrue("asdf", tmp.getRoot().getSize() == 8);
		tmp.delete('H');
		assertTrue("asdf", tmp.getRoot().getSize() == 7);
		assertTrue("asdf", "A C E M R S X ".equals(tmp.toString()));
		tmp.delete('A');
		assertTrue("asdf", tmp.getRoot().getSize() == 6);
		assertTrue("asdf", "C E M R S X ".equals(tmp.toString()));
		tmp.delete('X');
		assertTrue("asdf", tmp.getRoot().getSize() == 5);
		assertTrue("asdf", "C E M R S ".equals(tmp.toString()));
		tmp.delete('F');
		assertTrue("asdf", tmp.getRoot().getSize() == 5);
		assertTrue("asdf", "C E M R S ".equals(tmp.toString()));
	}

	@Test
	public void testSize() {
		assertTrue("asdf", p.size() == 7);
		assertTrue("asdf", "C E H M R S X ".equals(p.toString()));
	}
	
	@Test
	public void testHeightRecursive() {
		BinarySearchTree<Character, Integer> tmp = createForTmp();
		assertTrue("asdf", tmp.heightRecursive() == 4);
		tmp.delete('M');
		assertTrue("asdf", tmp.heightRecursive() == 3);
		tmp.delete('C');
		assertTrue("asdf", tmp.heightRecursive() == 3);
	}
	
	@Test
	public void testHeight() {
		BinarySearchTree<Character, Integer> tmp = createForTmp();
		assertTrue("asdf", tmp.heightRecursive() == 4);
		tmp.delete('M');
		assertTrue("asdf", tmp.heightRecursive() == 3);
		tmp.delete('C');
		assertTrue("asdf", tmp.heightRecursive() == 3);
	}
}
