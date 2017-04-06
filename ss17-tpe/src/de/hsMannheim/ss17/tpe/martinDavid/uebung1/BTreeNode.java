package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

import static gdi.MakeItSimple.*;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;

public class BTreeNode {
	
	private final int ordinal;
	private Integer[] elements;
	private BTreeNode[] children;
	private BTreeNode parent;
	
	BTreeNode(int ordinal) {
		this.ordinal = ordinal;
		// We need one element and children more that we can check if this node have to be splitted
		elements = new Integer[2 * ordinal + 1];
		children = new BTreeNode[2 * ordinal + 1 + 1];
	}
	
	/**
	 * Tries to insert an element into this subtree
	 * @param element to insert
	 * @return true if the element can be inserted, false if not
	 */
	boolean insert(Integer element) {
		
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
	
	boolean insert(Integer element, BTreeNode leftSubtree, BTreeNode rightSubtree) {
		int indexToInsert = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
		if(indexToInsert == -1) {
			return false;
		}
		Integer previousElement = elements[indexToInsert];
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
		for(BTreeNode childNode: children) {
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
		for(BTreeNode childNode: children) {
			if (childNode == null) {
				break;
			}
			int childNodeHeight = childNode.height();
			maxHeight = Math.max(maxHeight, childNodeHeight);
		}
		return maxHeight + 1;
	}
	
	Integer getMin() {
		if(children[0] != null) {
			return children[0].getMin();
		} else {
			if(elements[0] != null)
				return elements[0];
			else 
				return null;
		}
	}
	
	Integer getMax() {
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
	boolean contains(Integer o) {
		return linearContains(o);
	}
	/**
	 * Checks if the tree contains an object with the linear search algorithm
	 * @param object 
	 * @return true when the object is already in the tree and false if not
	 */
	private boolean linearContains(Integer o) {
		for(int index = 0; index < elements.length; index++) {
			Integer element = elements[index];
			
			if (element == null || o < element) {
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
	private boolean linearContainsInChildNode(int index, Integer element) {
		BTreeNode childNode = children[index];
		if (childNode != null) {
			return childNode.linearContains(element);
		}
		return false;
	}
	
	boolean isEmpty() {
		return elements[0] == null;
	}
	
	BTreeNode deepClone() {
		BTreeNode newNode = new BTreeNode(ordinal);
		
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
			}
		}
		
		return newNode;
	}
	
	Integer[] getAllElements() {
		Integer[] allElements = new Integer[size()];
		
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
		
		for(Integer element : elements) {	
			if(element != null)
				print(element + " ");
		}
	}
	
	void printPreorder() {
		for(Integer element : elements) {	
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
			for(Integer element : elements) {
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
	private BTreeNode cloneFromStartIndexToEndIndex(int startIndex, int endIndex) {
		BTreeNode treeNode = new BTreeNode(ordinal);
		
		//copy the elements
		for(int i = startIndex; i <= endIndex; i++) {
			treeNode.insert(elements[i]);
		}
		
		//copy the childs
		for(int i = startIndex; i <= endIndex + 1; i++) {
			BTreeNode child = children[i];
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
		int middleElement = elements[middleIndex];
		
		BTreeNode currentParent = parent;
		if (currentParent == null) {
			currentParent = new BTreeNode(ordinal);
		}
		
		BTreeNode rightSubtree = cloneFromStartIndexToEndIndex(middleIndex + 1, elements.length - 1);
		removeElementsAndChildrenFromIndex(middleIndex);
		BTreeNode leftSubtree = this;
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
	private void insertIntoNode(Integer element, int position) {
		
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

	public BTreeNode getParent() {
		return parent;
	}
}
