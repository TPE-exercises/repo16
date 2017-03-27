package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

public class BTreeNode {
	
	private Integer[] elements;
	private BTreeNode[] children;
	
	BTreeNode(int ordinal) {
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
			int indexToInsert = bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
			
			if(indexToInsert == -1) {
				//element is already in the node
				return false;
			} else {
				insertIntoNode(element, indexToInsert);
				return true;
			}
		}
		
		//this node is not a leaf -> search the node to insert this element
		int indexToInsert = bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
		
		if(indexToInsert == -1) {
			return false;
		} else {
			return children[indexToInsert].insert(element);
		}
	}
	
	int size() {
		return 0;
	}
	
	int height() {
		return 0;
	}
	
	Integer getMin() {
		return new Integer(0);
	}
	
	Integer getMax() {
		return new Integer(0);
	}
	
	boolean contains(Integer o) {
		return false;
	}
	
	boolean isEmpty() {
		return elements[0] == null;
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
				//TODO Array Utils insert array into array
			}
		}
		
		return allElements;
	}
	
	private boolean hasChildren() {
		return children[0] != null;
	}
	
	/**
	 * Checks if the node has too many elements / children
	 * @return true when the node has too many elements / children. False if not
	 */
	private boolean isBursting() {
		return elements[elements.length - 1] != null;
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
	 * finds the best insert position for an element in an ascending ordered
	 * subrange from 0 to `endIndex`
	 * 
	 * @param array
	 *            - array to find the inserting position
	 * @param endIndex
	 *            - last index of the ascending ordered subrange
	 * @param elementToInsert
	 *            - element to insert
	 * @return the index of the best insert position for the `elementToInsert`
	 */
	private static int bestInsertPositionToLeftByBinarySearch(Integer[] array, int endIndex, Integer elementToInsert) {

		int startIndex = 0;

		while (startIndex < endIndex) {
			int distanceBetweenStartAndEndIndex = endIndex - startIndex;
			int middleIndex = startIndex + (distanceBetweenStartAndEndIndex / 2);

			int middleElement = array[middleIndex];

			int rightIndex = middleIndex + 1;

			int rightElement = array[rightIndex];

			boolean isBestInsertPosition = middleElement <= elementToInsert && 
											rightElement >= elementToInsert;

			if (isBestInsertPosition) {
				return rightIndex;
			} else if (middleElement > elementToInsert) {
				endIndex = middleIndex;
			} else {
				startIndex = middleIndex;
			}
		}
		return endIndex;
	}
}
