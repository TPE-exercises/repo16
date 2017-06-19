package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import static gdi.MakeItSimple.*;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;

public class BTreeNode<E extends Comparable<E>> {
	
	private final int ordinal;
	private E[] elements;
	private BTreeNode<E>[] children;
	private BTreeNode<E> parent;
	
	BTreeNode(int ordinal) {
		this.ordinal = ordinal;
		// We need one element and children more that we can check if this node have to be splitted
		elements = (E[]) new Comparable[2 * ordinal + 1];
		children = new BTreeNode[2 * ordinal + 1 + 1];
	}
	
	/**
	 * Tries to insert an element into this subtree
	 * @param element to insert
	 * @return true if the element can be inserted, false if not
	 */
	boolean insert(E element) {
		
		if(isEmpty()) {
			elements[0] = element;
			return true;
		}
		
		if(!hasChildren()) {
			//insert the element into this node
			int indexToInsert = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
			
			if(indexToInsert == -1) {
				//element is already in the node
				return false;
			} else {
				insertIntoNode(element, indexToInsert);
				burstIfNeeded();
				return true;
			}
		}
		
		//this node is not a leaf -> search the node to insert this element
		int indexToInsert = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
		
		if(indexToInsert == -1) {
			return false;
		} else {
			return children[indexToInsert].insert(element);
		}
	}
	
	/**
	 * Inserts an element and two subtrees into this subtree
	 * @param element to insert
	 * @param leftSubtree subtree to insert at the left side of the inserted element
	 * @param rightSubtree subtree to insert at the right side of the inserted element
	 * @return
	 */
	boolean insert(E element, BTreeNode<E> leftSubtree, BTreeNode<E> rightSubtree) {
		int indexToInsert = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
		if(indexToInsert == -1) {
			return false;
		}
		E previousElement = elements[indexToInsert];
		if (previousElement != null) {
			rightShiftElementsAndChildren(indexToInsert);
		}
		elements[indexToInsert] = element;
		
		
		int leftChildNodeIndex = indexToInsert;
		int rightChildNodeIndex = indexToInsert + 1;
		
		children[leftChildNodeIndex] = leftSubtree;
		leftSubtree.parent = this;
		
		children[rightChildNodeIndex] = rightSubtree;
		rightSubtree.parent = this;
		
		burstIfNeeded();
		
		return true;
	}
	
	/**
	 * Count of all objects in this node and all of its children summed
	 * @return count of all objects in the tree
	 */
	int size() {
		int size = ArrayUtility.nonNullElementCount(elements);
		for(BTreeNode<E> childNode: children) {
			if (childNode == null) {
				break;
			}
			size += childNode.size();
		}
		return size;
	}
	
	/**
	 * Calculates the height of the tree. No element is height 0 and only root element is height 1
	 * @return the height of the tree
	 */
	int height() {
		int maxHeight = 0;
		for(BTreeNode<E> childNode: children) {
			if (childNode == null) {
				break;
			}
			int childNodeHeight = childNode.height();
			maxHeight = Math.max(maxHeight, childNodeHeight);
		}
		return maxHeight + 1;
	}
	
	/**
	 * Searches the min value in this TreeNode (and sub-nodes)
	 * @return the min value in this TreeNode
	 */
	E getMin() {
		if(children[0] != null) {
			return children[0].getMin();
		} else {
			if(elements[0] != null)
				return elements[0];
			else 
				return null;
		}
	}
	
	/**
	 * Searches the max value in this TreeNode (and sub-nodes)
	 * @return the max value in this TreeNode
	 */
	E getMax() {
		if(hasChildren()) {
			//get the last children
			for(int i = children.length - 1; i >= 0; i--) {
				if(children[i] != null) {
					return children[i].getMax();
				}
			}
		} else {
			//get the last element
			for(int i = elements.length - 1; i >= 0; i--) {
				if(elements[i] != null) {
					return elements[i];
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Checks if the tree contains an object
	 * @param object 
	 * @return true when the object is already in the tree and false if not
	 */
	boolean contains(E o) {
		return linearContains(o);
	}
	
	/**
	 * Checks if the tree contains an object with the linear search algorithm
	 * @param object 
	 * @return true when the object is already in the tree and false if not
	 */
	private boolean linearContains(E o) {
		for(int index = 0; index < elements.length; index++) {
			E element = elements[index];
			
			if (element == null || o.compareTo(element) == -1) {
				//continue search in the child node at the same index
				return linearContainsInChildNode(index, o);
			}
			else if (element.equals(o)) {
				return true;
			}
			//else
			//continue search at the next element
		}
		//should never be reached because the last element should always be null
		assert(false);
		return false;
	}
	
	/**
	 * Checks if the subtree contains an object with the linear search algorithm
	 * @param object 
	 * @return true when the object is already in the subtree and false if not
	 */
	private boolean linearContainsInChildNode(int index, E element) {
		BTreeNode<E> childNode = children[index];
		if (childNode != null) {
			return childNode.linearContains(element);
		}
		return false;
	}
	
	/**
	 * checks if the TreeNode is empty
	 * @return true if the tree is empty and false if not
	 */
	boolean isEmpty() {
		return elements[0] == null;
	}
	
	/**
	 * Creates a deep clone of this BTreeNode
	 * @return deep clone
	 */
	BTreeNode<E> deepClone() {
		BTreeNode<E> newNode = new BTreeNode<E>(ordinal);
		
		//Clone elements
		for(int i = 0; i< elements.length; i++) {
			if(elements[i] != null) {
				newNode.elements[i] = elements[i];
			}
		}
		
		//clone children
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {
				newNode.children[i] = children[i].deepClone();
				newNode.children[i].parent = newNode;
			}
		}
		
		return newNode;
	}
	
	/**
	 * Returns all elements without a specific order
	 * @return all elements
	 */
	E[] getAllElements() {
		E[] allElements = (E[]) new Comparable[size()];
		
		for(int i = 0; i < elements.length; i++) {
			if(elements[i] != null) {
				allElements[i] = elements[i];
			}
		}
		
		//All all elements from the childrens 
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {
				ArrayUtility.insertArrayIntoArray(allElements, children[i].getAllElements());
			}
		}
		
		return allElements;
	}
	
	void printInorder() {
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {
				children[i].printInorder();
			}
			
			if(i < elements.length && elements[i] != null) {
				print(elements[i] + " ");
			}
		}
	}
	
	void printPostorder() {		
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {				
				children[i].printPostorder();
			}
		}
		
		for(E element : elements) {	
			if(element != null)
				print(element + " ");
		}
	}
	
	void printPreorder() {
		for(E element : elements) {	
			if(element != null)
				print(element + " ");
		}
		
		for(int i = 0; i < children.length; i++) {
			if(children[i] != null) {
				children[i].printPreorder();
			}
		}
	}
	
	void printLevel(int level) {
		if(level == 0) {
			for(E element : elements) {
				if(element != null) {					
					print(element + " ");
				}
			}
		} else {
			for(int i = 0; i < children.length; i++) {
				if(children[i] != null) {
					
					if(i != 0) { //if the child is not the first child
						print(" | ");
					}
						
					children[i].printLevel(level - 1);
				}
			}
		}
	}
	
	private boolean hasChildren() {
		return children[0] != null;
	}
	
	/**
	 * removes all elements and children from the given index (inclusive)
	 * @param index
	 */
	private void removeElementsAndChildrenFromIndex(int index) {
		for(int i = index; i < elements.length; i++) {
			elements[i] = null;
			children[i + 1] = null;
		}
	}
	/**
	 * copies all elements and children from a given start index (inclusive) to a given end index (inclusive)
	 * @param startIndex
	 * @param endIndex
	 */
	private BTreeNode<E> cloneFromStartIndexToEndIndex(int startIndex, int endIndex) {
		BTreeNode<E> treeNode = new BTreeNode<E>(ordinal);
		
		//copy the elements
		for(int i = startIndex; i <= endIndex; i++) {
			treeNode.insert(elements[i]);
		}
		
		//copy the childs
		for(int i = startIndex; i <= endIndex + 1; i++) {
			BTreeNode<E> child = children[i];
			if (child == null) {
				break;
			}
			child.parent = treeNode;
			treeNode.children[i - startIndex] = child;
		}
		
		return treeNode;
	}
	
	/**
	 * Checks if the node has too many elements / children
	 * @return true when the node has too many elements / children. False if not
	 */
	private boolean isBursting() {
		return elements[elements.length - 1] != null;
	}
	
	private void burst() {
		int middleIndex = elements.length/2;
		E middleElement = elements[middleIndex];
		
		BTreeNode<E> currentParent = parent;
		if (currentParent == null) {
			currentParent = new BTreeNode<E>(ordinal);
		}
		
		BTreeNode<E> rightSubtree = cloneFromStartIndexToEndIndex(middleIndex + 1, elements.length - 1);
		removeElementsAndChildrenFromIndex(middleIndex);
		BTreeNode<E> leftSubtree = this;
		currentParent.insert(middleElement, leftSubtree, rightSubtree);
	}
	
	private void burstIfNeeded() {
		if (isBursting())
			burst();
	}
	
	/**
	 * Inserts an element into this node (not into a children) at the given position
	 * @param element to insert
	 * @param position to insert the element
	 */
	private void insertIntoNode(E element, int position) {
		
		//Move all elements from index position (with the old element) to the right
		for(int i = elements.length - 1; i > position; i--) {
			elements[i] = elements[i - 1];
		}
		
		elements[position] = element;
	}
	/**
	 * moves all elements and children one position to the right starting at the given index (inclusive)
	 * @param startIndex right shift start index
	 */
	private void rightShiftElementsAndChildren(int startIndex) {
		for(int index = elements.length - 1; index > startIndex; index--) {
			elements[index] = elements[index - 1];
			children[index + 1] = children[index];
		}
	}

	/**
	 * Returns the parent of this TreeNode
	 * @return parent of this TreeNode
	 */
	public BTreeNode<E> getParent() {
		return parent;
	}
	public BTreeNode<E>[] getChildren() {
		return children;
	}
	public E[] getElements() {
		return elements;
	}
}
