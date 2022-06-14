package search;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utils.BinaryTreeUtils;

public class RedBlackBSTTest {

	@Test
	public void testPut() {
		RedBlackBST<Integer, Integer> p = new RedBlackBST<>();
		RedBlackBST<Integer, Integer> answer = new RedBlackBST<>();
		p.put(3, 0);
		p.put(21, 0);
		p.put(32, 0);
		p.put(15, 0);
		p.displayTree();
		answer.put(21, 0);
		answer.put(3, 0);
		answer.put(32, 0);
		answer.put(15, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), answer.getRoot()) );
		
		p = new RedBlackBST<>();
		answer = new RedBlackBST<>();
		p.put(7, 0);
		p.put(3, 0);
		p.put(18, 0);
		p.put(10, 0);
		p.put(22, 0);
		p.put(8, 0);
		p.put(11, 0);
		p.put(26, 0);
		p.displayTree();
		answer.put(7, 0);
		answer.put(3, 0);
		answer.put(18, 0);
		answer.put(10, 0);
		answer.put(22, 0);
		answer.put(8, 0);
		answer.put(11, 0);
		answer.put(26, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), answer.getRoot()) );
	}

}
