package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class ArrayUtility {
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
			
			int rightIndex = middleIndex + 1;
		
			Integer rightElement = array[rightIndex];
			if (rightElement == null){
				endIndex = rightIndex;
				continue;
			}
			if (middleElement.equals(elementToInsert) || rightElement.equals(elementToInsert)) {
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
