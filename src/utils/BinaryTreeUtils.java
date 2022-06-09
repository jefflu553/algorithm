package utils;

import search.AVLTree;

public class BinaryTreeUtils {
	public static <K extends Comparable<K>, V> boolean structualIdenticalTrees(AVLTree<K,V>.Node a, AVLTree<K,V>.Node b)
    {
        /*1. both empty */
        if (a == null && b == null)
            return true;
             
        /* 2. both non-empty -> compare them */
        if (a != null && b != null)
            return (a.getKey() == b.getKey()
                    && structualIdenticalTrees(a.getLeft(), b.getLeft())
                    && structualIdenticalTrees(a.getRight(), b.getRight()));
  
        /* 3. one empty, one not -> false */
        return false;
    }
}
