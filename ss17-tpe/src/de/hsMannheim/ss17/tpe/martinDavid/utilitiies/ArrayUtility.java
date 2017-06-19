package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class ArrayUtility {
	/**
	 * finds the best insert position for an element in an ascending ordered
	 * subrange from 0 to `endIndex` with the binary search algorithm
	 * @param <E>
	 * 
	 * @param array
	 *            - array to find the inserting position
	 * @param endIndex
	 *            - last index of the ascending ordered subrange
	 * @param elementToInsert
	 *            - element to insert
	 * @return the index of the best insert position for the `elementToInsert` or -1 if the element is already present
	 */
	public static <E extends Comparable<E>> int bestInsertPositionToLeftByBinarySearch(E[] array, int endIndex, E elementToInsert) {
		if(elementToInsert == null) {
			return -1;
		}
		int startIndex = 0;

		while (startIndex < endIndex) {
			int distanceBetweenStartAndEndIndex = endIndex - startIndex;
			int middleIndex = startIndex + (distanceBetweenStartAndEndIndex / 2);

			E middleElement = array[middleIndex];
			if (middleElement == null){
				endIndex = middleIndex;
				continue;
			}
			if (middleElement.equals(elementToInsert) ) {
				return -1;
			}
			
			int rightIndex = middleIndex + 1;
		
			E rightElement = array[rightIndex];
			if (rightElement == null){
				if (middleElement.compareTo(elementToInsert) == -1) {
					return rightIndex;
				} else {
					endIndex = middleIndex;
					continue;
				}
			}
			if(rightElement.equals(elementToInsert)) {
				return -1;
			}
			
			boolean isBestInsertPosition = middleElement.compareTo(elementToInsert) == -1 && 
					rightElement.compareTo(elementToInsert) == 1;

			if (isBestInsertPosition) {
				return rightIndex;
			} else if (middleElement.compareTo(elementToInsert) == 1) {
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
	public static <E extends Comparable<E>> int bestInsertPositionToLeftByLinearSearch(E[] array, int endIndex, E elementToInsert) {
		if (elementToInsert == null) {
			return -1;
		}
		int insertIndex = 0;
		while(insertIndex < endIndex) {
			E currentElement = array[insertIndex];
			if (currentElement == null) {
				return insertIndex;
			}
			if (currentElement.equals(elementToInsert)) {
				return -1;
			}
			if (currentElement.compareTo(elementToInsert) == 1) {
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
	public static <E> int nonNullElementCount(E[] array) {
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
	public static <E> boolean insertArrayIntoArray(E[] originalArray, E[] toInsert) {
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
	public static <E extends Comparable<E>> E min(E[] array) {
        E min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) == -1) {
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
	public static <E extends Comparable<E>> E max(E[] array) {
        E max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) == 1) {
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
	/**
	 * copies all elements from `source` array to `destination` array between `startIndex` and `endIndex` (inclusive)
	 * @param source - source array
	 * @param destination - destination array
	 * @param startIndex - start index (inclusive)
	 * @param endIndex - end index (inclusive)
	 */
	public static void copy(Object[] source, Object[] destination, int startIndex, int endIndex) {
		for(int index = startIndex; index <= endIndex; index++) {
			destination[index] = source[index];
		}
	}
	
	/**
	 * swaps two elements in an array
	 * @param array 
	 * @param firstIndex index of first number to swap
	 * @param secondIndex index of second number to swap
	 */
	public static void swapElements(Object[] array, int firstIndex, int secondIndex) {
		Object firstElementToChange = array[firstIndex];
		
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = firstElementToChange;
	}
}
