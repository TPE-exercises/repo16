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
	 * Inserts all elements from one array into an other array. The arrays have to be filled from left to right with elements (the other
	 * elements are null).
	 * @param originalArray
	 * @param toInsert
	 * @return true if the insertion was succesful and false if not
	 */
	public static boolean insertArrayIntoArray(Integer[] originalArray, Integer[] toInsert) {
		int indexCounterToInsert = 0;
		
		for(int i = 0; i < originalArray.length; i++) {
			if(originalArray[i] == null) {
				
				if(indexCounterToInsert < toInsert.length) {
					originalArray[i] = toInsert[indexCounterToInsert];
					indexCounterToInsert++;
				} else {
					return true;
				}
				
			}
		}
		
		//Check if all elemetns from toInsert where inserted
		if(indexCounterToInsert == toInsert.length) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * searches for the smallest element in the array and returns it
	 * @param array 
	 * @return smallest element
	 */
	public static Integer min(Integer[] array) {
        Integer min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
	/**
	 * searches for the biggest element in the array and returns it
	 * @param array 
	 * @return biggest element
	 */
	public static Integer max(Integer[] array) {
        Integer max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
            	max = array[i];
            }
        }
        return max;
    }
	/**
	 * Concatenates each element, separated with a given separator
	 * @param array - list of element
	 * @param seperator - separator string
	 * @return concatenated string
	 */
	public static String join(Object[] array, String separator) {
		String result = "";
		if (array == null) return result;
		
		int lastIndex = array.length - 1;
		for(int index = 0; index < array.length; index++) {
			Object element = array[index];
			result += element.toString();
			if (index != lastIndex) {
				result += separator;
			}
		}
		
		return result;
	}
	
	/**
	 * Searches for an object in an object array
	 * @param array
	 * @param object to search in the array
	 * @return the first index of the object. -1 if object is not in array
	 */
	public static int indexOf(Object[] array, Object object) {
		for(int i = 0; i < array.length; i++) {
			if(array[i].equals(object)) {
				return i;
			}
		}
		
		return -1;
	}
}
