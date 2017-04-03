package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

public interface BTree {

	/**
	 * Inserts an object into the tree
	 * @param object to insert
	 * @return if the object can be inserted and false if not (the object alreay exists in the tree)
	 */
	boolean insert(Integer object);
	
	/**
	 * Inserts all Integers in the given file. They have to be separated by ',' or space
	 * @param filename 
	 * @return true when all integers could be inserted and false if one or more integers cound not be inserted
	 */
	boolean insert (String filename);
	
	/**
	 * Checks if the tree contains an object
	 * @param object 
	 * @return true when the object is already in the tree and false if not
	 */
	boolean contains(Integer object);
	
	/**
	 * Counts of all objects in the tree
	 * @return count of all objects in the tree
	 */
	int size();
	
	/**
	 * Calculates the height of the tree. No element is height 0 and only root element is height 1
	 * @return the height of the tree
	 */
	int height();
	
	/**
	 * Searches for the largest element in the tree
	 * @return the largest element in the tree. Null when there is no element
	 */
	Integer getMax();
	
	/**
	 * Searches for the smallest element in the tree
	 * @return the smallest element in the tree. Null when there is no element
	 */
	Integer getMin();
	
	/**
	 * Checks if the tree is empty (has no elements)
	 * @return true when the tree is empty, false if not
	 */
	boolean isEmpty();
	
	/**
	 * Adds all elements from an other tree to this tree
	 * @param otherTree all elements from this tree will be inserted
	 * @return true if all elements could be inserted and false if one or more elements could not be inserted
	 */
	boolean addAll(BTree otherTree);
	
	/**
	 * Returns all elements without a specific order
	 * @return all elements
	 */
	Integer[] getAllElements();
	
	/**
	 * creates a deep clone of the BTree 
	 * @return cloned tree
	 */
	BTree clone();
	
	void printInorder();
	void printPostorder();
	void printPreorder();
	void printLevelorder();
	
}
