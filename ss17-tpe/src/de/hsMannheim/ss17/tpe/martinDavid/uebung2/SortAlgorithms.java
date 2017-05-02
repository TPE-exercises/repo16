package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public class SortAlgorithms {
	
	public enum Alogirthm {
		insertionSort, shakerSort;
	}
	
	public static <T extends Comparable<T>> void sortArray(T[] array, Alogirthm algorithm) {
		switch (algorithm) {
		case insertionSort:
			insertionSort(array);
			break;
		case shakerSort:
			shakerSort(array);
			break;
		}
	}
	/**
	 * verify that an array is correctly sorted in ascending order
	 * 
	 * @param array
	 *            - array to check
	 * @return true if the array is sorted ascending, otherwise false
	 */
	public static <T extends Comparable<T>> boolean isSorted(T[] array) {
		T lastElement = array[0];
		for (T currentElement : array) {
			// the previous element is not allowed to be smaller
			if (lastElement.compareTo(currentElement) > 0) {
				return false;
			}
			lastElement = currentElement;
		}
		return true;
	}
	/**
	 * sorts the array with the normal insertion sort algorithm. sorting is done
	 * in-place and in ascending order, the smallest number will be on the
	 * left(first index) and the highest on the right(last index)
	 * 
	 * @param array
	 *            - the array to sort, sorting is done in-place (the given array
	 *            is modified).
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] array) {
		// the first element is already sorted, start at the second element and
		// iterate over all other elements to insert them at the correct
		// position
		for (int index = 1; index < array.length; index++) {
			// current element
			T elementToInsert = array[index];
			// insertion cursor
			int indexToInsert = index;
			// look from the current element position to the left
			while (indexToInsert > 0) {
				int leftIndex = indexToInsert - 1;
				T leftElement = array[leftIndex];
				// elements on the left are already sorted if we reach a smaller
				// number on the left
				// we found the right position to insert the current element
				// (leftElement <= elementToInsert)
				if (leftElement.compareTo(elementToInsert) <= 0) {
					break;
				}

				// shift left element to the right because it is higher than our
				// current element
				array[indexToInsert] = leftElement;
				// shift insertion cursor to the left
				indexToInsert--;
			}

			// insert element at the calculated position
			array[indexToInsert] = elementToInsert;
		}
	}
	
	/**
	 * defines the bubble sort run direction for the next iteration, used by the
	 * shaker sort algorithm
	 */
	private enum BubbleSortRunDirection {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT
	}

	/**
	 * sorts the array with the shaker sort algorithm. sorting is done in-place
	 * and in ascending order, the smallest number will be on the left(first
	 * index) and the highest on the right(last index).
	 * 
	 * @param array
	 *            - the array to sort, sorting is done in-place (the given array
	 *            is modified)
	 */
	public static <T extends Comparable<T>>void shakerSort(T[] array) {
		boolean swapHappened = false;
		// start iteration direction
		BubbleSortRunDirection direction = BubbleSortRunDirection.LEFT_TO_RIGHT;
		do {
			swapHappened = false;

			if (direction == BubbleSortRunDirection.LEFT_TO_RIGHT) {
				// iterate over all element except the first from left to right
				for (int rightIndex = 1; rightIndex < array.length; rightIndex++) {
					int leftIndex = rightIndex - 1;
					if (bubbleSortCompareAndSwap(array, leftIndex, rightIndex)) {
						swapHappened = true;
					}
				}
				// change the run direction into the opposite direction
				direction = BubbleSortRunDirection.RIGHT_TO_LEFT;
			} else {
				// iterate over all element except the first from right to left
				for (int rightIndex = array.length - 1; rightIndex > 0; rightIndex--) {
					int leftIndex = rightIndex - 1;
					if (bubbleSortCompareAndSwap(array, leftIndex, rightIndex)) {
						swapHappened = true;
					}
				}
				// change the run direction into the opposite direction
				direction = BubbleSortRunDirection.LEFT_TO_RIGHT;
			}
		} while (swapHappened);
	}

	/**
	 * compares the value at the leftIndex and the rightIndex, if the left value
	 * is greater than the right value, they are swapped
	 * 
	 * @param array
	 * @param leftIndex
	 *            - element that should be smaller
	 * @param rightIndex
	 *            - element that should be larger
	 * @return true if a swap happened otherwise false
	 */
	private static <T extends Comparable<T>> boolean bubbleSortCompareAndSwap(T[] array, int leftIndex, int rightIndex) {
		T leftElement = array[leftIndex];
		T rightElement = array[rightIndex];

		// (leftElement > rightElement)
		if(leftElement.compareTo(rightElement) > 0){
			// swap elements
			array[leftIndex] = rightElement;
			array[rightIndex] = leftElement;
			return true;
		}
		return false;
	}
}
