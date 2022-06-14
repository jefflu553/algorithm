package utils;

import java.util.Stack;

import entity.INode;
import search.RedBlackBST;
import search.RedBlackBSTTraceBack;

public class BinaryTreeUtils {
	
	public static <K extends Comparable<K>, V> boolean structualIdenticalTrees(INode<K,V> a, INode<K,V> b)
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
	
	public static <K extends Comparable<K>, V> boolean structualIdenticalTrees(RedBlackBST<K,V>.Node a, RedBlackBST<K,V>.Node b)
    {
        /*1. both empty */
        if (a == null && b == null)
            return true;
             
        /* 2. both non-empty -> compare them */
        if (a != null && b != null)
            return (a.getKey() == b.getKey() && 0 == Boolean.compare(a.isRed(), b.isRed())
                    && structualIdenticalTrees(a.getLeft(), b.getLeft())
                    && structualIdenticalTrees(a.getRight(), b.getRight()));
  
        /* 3. one empty, one not -> false */
        return false;
    }
	
	public static <K extends Comparable<K>, V> boolean structualIdenticalTrees(RedBlackBSTTraceBack<K,V>.Node a, RedBlackBSTTraceBack<K,V>.Node b)
    {
        /*1. both empty */
        if (a == null && b == null)
            return true;
             
        /* 2. both non-empty -> compare them */
        if (a != null && b != null)
            return (a.getKey() == b.getKey() && 0 == Boolean.compare(a.isRed(), b.isRed())
                    && structualIdenticalTrees(a.getLeft(), b.getLeft())
                    && structualIdenticalTrees(a.getRight(), b.getRight()));
  
        /* 3. one empty, one not -> false */
        return false;
    }

	public static <K extends Comparable<K>, V> void displayTree(INode<K, V> tree) {
		Stack<INode<K, V>> globalStack = new Stack<>();
		globalStack.push(tree);
		int emptyLeaf = 32;
		boolean isRowEmpty = false;
		System.out.println("****......................................................****");
		while (isRowEmpty == false) {

			Stack<INode<K, V>> localStack = new Stack<>();
			isRowEmpty = true;
			for (int j = 0; j < emptyLeaf; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				INode<K, V> temp = globalStack.pop();
				if (temp != null) {
					System.out.print(temp.getKey());
					localStack.push(temp.getLeft());
					localStack.push(temp.getRight());
					if (temp.getLeft() != null || temp.getRight() != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < emptyLeaf * 2 - 2; j++)
					System.out.print(' ');
			}
			System.out.println();
			emptyLeaf /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		}
		System.out.println("****......................................................****");
	}
}
