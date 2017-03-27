package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

public class BTreeNode {
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
