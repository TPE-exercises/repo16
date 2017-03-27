package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilityTest {

	@Test
	public void bestInsertPositionBinaryContainsElementTest() {
		Integer[] elements = {1, 4, 8, 9, null};
		for(Integer element : elements) {
			int index = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, element);
			assertEquals(-1, index);
		}
	}
	
	@Test
	public void bestInsertPositionBinaryTest() {
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
	@Test
	public void bestInsertPositionBinaryOnEmptyArray() {
		Integer[] elements = {null, null, null};
		int index = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, 1);
		assertEquals(0, index);
	}
	@Test
	public void bestInsertPositionBinaryOnArrayWithOneElement() {
		Integer[] elements = {1, null, null};
		int index = ArrayUtility.bestInsertPositionToLeftByBinarySearch(elements, elements.length, 2);
		assertEquals(1, index);
	}
	
	@Test
	public void bestInsertPositionLinearContainsElementTest() {
		Integer[] elements = {1, 4, 8, 9, null};
		for(Integer element : elements) {
			int index = ArrayUtility.bestInsertPositionToLeftByLinearSearch(elements, elements.length, element);
			assertEquals(-1, index);
		}
	}
	
	@Test
	public void bestInsertPositionLinearTest() {
		Integer[] elements = {1, 4, 8, 10, null};
		
		Integer[] elementsToInsert = { 0, 3, 5, 7, 9, 10, 11};
		int[] rightIndexForInserts = { 0, 1, 2, 2, 3, -1, 4 };
		
		for(int index = 0; index < elements.length; index++) {
			Integer elementToInsert = elementsToInsert[index];
			int rightIndexForInsert = rightIndexForInserts[index];
			int insertIndex = ArrayUtility.bestInsertPositionToLeftByLinearSearch(elements, elements.length, elementToInsert);
			assertEquals(rightIndexForInsert, insertIndex);
		}
	}
	@Test
	public void bestInsertPositionLinearOnEmptyArray() {
		Integer[] elements = {null, null, null};
		int index = ArrayUtility.bestInsertPositionToLeftByLinearSearch(elements, elements.length, 1);
		assertEquals(0, index);
	}
	@Test
	public void bestInsertPositionLinearOnArrayWithOneElement() {
		Integer[] elements = {1, null, null};
		int index = ArrayUtility.bestInsertPositionToLeftByLinearSearch(elements, elements.length, 2);
		assertEquals(1, index);
	}

}
