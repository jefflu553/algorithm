package entity;

public interface INode<K extends Comparable<K>, V> {
	void setKey(K key);
	K getKey();
	void setValue(V value);
	V getValue();
	INode<K, V> getLeft();
	void setLeft(INode<K, V> node);
	INode<K, V> getRight();
	void setRight(INode<K, V> node);
}
