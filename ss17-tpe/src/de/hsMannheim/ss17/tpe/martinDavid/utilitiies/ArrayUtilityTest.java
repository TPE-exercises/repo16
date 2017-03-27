package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsMannheim.ss17.tpe.martinDavid.uebung1.BTreeNode;

public class ArrayUtilityTest {

	@Test
	public void BTreeNodeBestInsertPositionContainsElementTest() {
		Integer[] elements = {1, 4, 8, 9, null};
		for(Integer element : elements) {
			int index = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
			assertEquals(-1, index);
		}
	}
	
	@Test
	public void BTreeNodeBestInsertPositionTest() {
		Integer[] elements = {1, 4, 8, 10, null};
		
		Integer[] elementsToInsert = { 0, 3, 5, 7, 9, 10, 11};
		int[] rightIndexForInserts = { 0, 1, 2, 2, 3, -1, 4 };
		
		for(int index = 0; index < elements.length; index++) {
			Integer elementToInsert = elementsToInsert[index];
			int rightIndexForInsert = rightIndexForInserts[index];
			int insertIndex = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, elementToInsert);
			assertEquals(rightIndexForInsert, insertIndex);
		}
	}

}
