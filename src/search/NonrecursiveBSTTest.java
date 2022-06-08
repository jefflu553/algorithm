package search;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class NonrecursiveBSTTest {
	private static NonrecursiveBST<Character, Integer> p;
	
	@BeforeClass
    public static void init() {
		p = new NonrecursiveBST<>();
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

	}
	
	@Test
	public void testPut() {
		assertTrue("asdf", "C E H M R S X ".equals(p.toString()));
	}
	
	@Test
	public void testGet() {
		assertTrue("asdf", p.get('E') == 12);
		assertTrue("asdf", p.get('C') == 4);
		assertTrue("asdf", p.get('Z') == null);
	}
	
	@Test
	public void testKeys() {
		Iterable<Character> q = p.keys();
		LinkedList<Character> answer = new LinkedList<>();
		answer.add('C');
		answer.add('E');
		answer.add('H');
		answer.add('M');
		answer.add('R');
		answer.add('S');
		answer.add('X');
		assertTrue("asdf", q.equals(answer) == true);
	}
}
