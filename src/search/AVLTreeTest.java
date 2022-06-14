package search;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.BinaryTreeUtils;

public class AVLTreeTest {
	
	private AVLTree<Integer, Integer> createAVLTree(){
		AVLTree<Integer, Integer> p = new AVLTree<>();
		p.put(13, 0);
		p.put(10, 0);
		p.put(15, 0);
		p.put(5, 0);
		p.put(11, 0);
		p.put(16, 0);
		p.put(4, 0);
		p.put(6, 0);
		return p;
	}

	@Test
	public void testPut() {
		AVLTree<Integer, Integer> p = createAVLTree();
		AVLTree<Integer, Integer> a = new AVLTree<>();
		BinaryTreeUtils.displayTree(p.getRoot());
		p.put(14, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(13, 0);
		a.put(10, 0);
		a.put(15, 0);
		a.put(5, 0);
		a.put(11, 0);
		a.put(14, 0);
		a.put(16, 0);
		a.put(4, 0);
		a.put(6, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
		
		p = createAVLTree();
		a = new AVLTree<>();
		BinaryTreeUtils.displayTree(p.getRoot());
		p.put(3, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(13, 0);
		a.put(5, 0);
		a.put(15, 0);
		a.put(4, 0);
		a.put(10, 0);
		a.put(16, 0);
		a.put(3, 0);
		a.put(6, 0);
		a.put(11, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
	
		p = createAVLTree();
		a = new AVLTree<>();
		BinaryTreeUtils.displayTree(p.getRoot());
		p.put(7, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(13, 0);
		a.put(6, 0);
		a.put(15, 0);
		a.put(5, 0);
		a.put(10, 0);
		a.put(16, 0);
		a.put(4, 0);
		a.put(7, 0);
		a.put(11, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
	
		p = new AVLTree<>();
		a = new AVLTree<>();
		p.put(30, 0);
		p.put(5, 0);
		p.put(35, 0);
		p.put(32, 0);
		p.put(40, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		p.put(45, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(35, 0);
		a.put(30, 0);
		a.put(40, 0);
		a.put(5, 0);
		a.put(32, 0);
		a.put(45, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
		
		p = new AVLTree<>();
		a = new AVLTree<>();
		p.put(5, 0);
		p.put(2, 0);
		p.put(7, 0);
		p.put(1, 0);
		p.put(4, 0);
		p.put(6, 0);
		p.put(9, 0);
		p.put(3, 0);
		p.put(16, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		p.put(15, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(5, 0);
		a.put(2, 0);
		a.put(7, 0);
		a.put(1, 0);
		a.put(4, 0);
		a.put(6, 0);
		a.put(15, 0);
		a.put(3, 0);
		a.put(9, 0);
		a.put(16, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
	
	}
	
	@Test
	public void testDelete() {
		AVLTree<Integer, Integer> p = new AVLTree<>();
		AVLTree<Integer, Integer> a = new AVLTree<>();
		p.put(44, 0);
		p.put(17, 0);
		p.put(62, 0);
		p.put(32, 0);
		p.put(50, 0);
		p.put(78, 0);
		p.put(48, 0);
		p.put(54, 0);
		p.put(88, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		p.delete(32);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(62, 0);
		a.put(44, 0);
		a.put(78, 0);
		a.put(17, 0);
		a.put(50, 0);
		a.put(88, 0);
		a.put(48, 0);
		a.put(54, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
	
		p = new AVLTree<>();
		a = new AVLTree<>();
		p.put(20, 0);
		p.put(10, 0);
		p.put(30, 0);
		p.put(5, 0);
		p.put(15, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		p.delete(30);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(10, 0);
		a.put(5, 0);
		a.put(20, 0);
		a.put(15, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
		
		
		p = new AVLTree<>();
		a = new AVLTree<>();
		p.put(50, 0);
		p.put(40, 0);
		p.put(60, 0);
		p.put(30, 0);
		p.put(45, 0);
		p.put(55, 0);
		p.put(10, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		p.delete(55);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(40, 0);
		a.put(30, 0);
		a.put(50, 0);
		a.put(10, 0);
		a.put(45, 0);
		a.put(60, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
		
		p = new AVLTree<>();
		a = new AVLTree<>();
		p.put(50, 0);
		p.put(40, 0);
		p.put(60, 0);
		p.put(45, 0);
		BinaryTreeUtils.displayTree(p.getRoot());
		p.delete(60);
		BinaryTreeUtils.displayTree(p.getRoot());
		a.put(45, 0);
		a.put(40, 0);
		a.put(50, 0);
		assertTrue( BinaryTreeUtils.structualIdenticalTrees(p.getRoot(), a.getRoot()) );
	}

}
