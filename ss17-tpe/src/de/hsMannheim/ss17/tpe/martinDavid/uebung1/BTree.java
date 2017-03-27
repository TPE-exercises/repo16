package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

public interface BTree {

	boolean insert(Integer o);
	boolean insert (String filename);
	boolean contains(Integer o);
	int size();
	int height();
	Integer getMax();
	Integer getMin();
	boolean isEmpty();
	void addAll(BTree otherTree);
	
	void printInorder();
	void printPostorder();
	void printPreorder();
	void printLevelorder();
	
}
