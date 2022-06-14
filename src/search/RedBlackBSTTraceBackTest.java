package search;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.BinaryTreeUtils;

public class RedBlackBSTTraceBackTest {

	@Test
	public void testInsert() {
		RedBlackBSTTraceBack<Integer, Integer> p = new RedBlackBSTTraceBack<>();
		RedBlackBSTTraceBack<Integer, Integer> answer = new RedBlackBSTTraceBack<>();
		p.insert(3, 0);
		p.insert(21, 0);
		p.insert(32, 0);
		p.insert(15, 0);
		p.displayTree();
		answer.insert(21, 0);
		answer.insert(3, 0);
		answer.insert(32, 0);
		answer.insert(15, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), answer.getRoot()) );
		assertTrue( !p.getRoot().isRed() );
		assertTrue( !p.getRoot().getLeft().isRed() );
		assertTrue( !p.getRoot().getRight().isRed() );
		assertTrue( p.getRoot().getLeft().getRight().isRed() );
		
		p = new RedBlackBSTTraceBack<>();
		answer = new RedBlackBSTTraceBack<>();
		p.insert(50, 0);
		p.insert(25, 0);
		p.insert(80, 0);
		p.insert(35, 0);
		p.insert(10, 0);
		p.insert(6, 0);
		p.insert(7, 0);
		p.insert(90, 0);
		p.insert(100, 0);
		p.insert(95, 0);
		p.displayTree();
		answer.insert(50, 0);
		answer.insert(25, 0);
		answer.insert(90, 0);
		answer.insert(7, 0);
		answer.insert(35, 0);
		answer.insert(80, 0);
		answer.insert(100, 0);
		answer.insert(6, 0);
		answer.insert(10, 0);
		answer.insert(95, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), answer.getRoot()) );
		assertTrue( !p.getRoot().isRed() );
		assertTrue( p.getRoot().getLeft().isRed() );
		assertTrue( p.getRoot().getRight().isRed() );
		assertTrue( !p.getRoot().getLeft().getRight().isRed() );
		assertTrue( !p.getRoot().getLeft().getLeft().isRed() );
		assertTrue( p.getRoot().getLeft().getLeft().getLeft().isRed() );
		assertTrue( p.getRoot().getLeft().getLeft().getRight().isRed() );
	}

}
