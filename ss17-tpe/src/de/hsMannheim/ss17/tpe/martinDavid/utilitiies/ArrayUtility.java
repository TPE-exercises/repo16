package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class ArrayUtility {
	/**
	 * finds the best insert position for an element in an ascending ordered
	 * subrange from 0 to `endIndex` with the binary search algorithm
	 * 
	 * @param array
	 *            - array to find the inserting position
	 * @param endIndex
	 *            - last index of the ascending ordered subrange
	 * @param elementToInsert
	 *            - element to insert
	 * @return the index of the best insert position for the `elementToInsert` or -1 if the element is already present
	 */
	public static int bestInsertPositionToLeftByBinarySearch(Integer[] array, int endIndex, Integer elementToInsert) {
		if(elementToInsert == null) {
			return -1;
		}
		int startIndex = 0;

		while (startIndex < endIndex) {
			int distanceBetweenStartAndEndIndex = endIndex - startIndex;
			int middleIndex = startIndex + (distanceBetweenStartAndEndIndex / 2);

			Integer middleElement = array[middleIndex];
			if (middleElement == null){
				endIndex = middleIndex;
				continue;
			}
			if (middleElement.equals(elementToInsert) ) {
				return -1;
			}
			
			int rightIndex = middleIndex + 1;
		
			Integer rightElement = array[rightIndex];
			if (rightElement == null){
				if (middleElement < elementToInsert) {
					return rightIndex;
				} else {
					endIndex = middleIndex;
					continue;
				}
			}
			if(rightElement.equals(elementToInsert)) {
				return -1;
			}
			
			boolean isBestInsertPosition = middleElement < elementToInsert && 
											rightElement > elementToInsert;

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
	
	/**
	 * finds the best insert position for an element in an ascending ordered
	 * subrange from 0 to `endIndex` with the linear search algorithm
	 * 
	 * @param array
	 *            - array to find the inserting position
	 * @param endIndex
	 *            - last index of the ascending ordered subrange
	 * @param elementToInsert
	 *            - element to insert
	 * @return the index of the best insert position for the `elementToInsert` or -1 if the element is already present
	 */
	public static int bestInsertPositionToLeftByLinearSearch(Integer[] array, int endIndex, Integer elementToInsert) {
		if (elementToInsert == null) {
			return -1;
		}
		int insertIndex = 0;
		while(insertIndex < endIndex) {
			Integer currentElement = array[insertIndex];
			if (currentElement == null) {
				return insertIndex;
			}
			if (currentElement.equals(elementToInsert)) {
				return -1;
			}
			if (currentElement > elementToInsert) {
				return insertIndex;
			}
			insertIndex++;
		}
		return insertIndex;
	}
	
	/**
	 * calculate the number of non null elements by searching for the first null element in the array from left to right in the given array
	 * @param array - array to search in
	 * @return number of non null elements in the array
	 */
	public static int nonNullElementCount(Integer[] array) {
		for(int index = 0; index < array.length; index++) {
			if (array[index] == null) {
				return index;
			}
		}
		return array.length;
	}
	
	
	/**
	 * Inserts all elements from one array into an other array. The arrays have to be filled from left to right with elements.
	 * @param originalArray
	 * @param toInsert
	 * @return
	 */
	public static boolean insertArrayIntoArray(Integer[] originalArray, Integer[] toInsert) {
		//TODO
		return false;
	}
}
