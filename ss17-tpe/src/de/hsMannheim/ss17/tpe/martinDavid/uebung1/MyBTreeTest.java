package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;

public class MyBTreeTest {

	@Test 
	public void insertFromFileTest() {
		String directory = "src/de/hsMannheim/ss17/tpe/martinDavid/uebung1/";
		String filename = "integers.txt";
		String filepath = directory + filename;
		MyBTree tree = new MyBTree(1);
		tree.insert(filepath);
		assertEquals("size shoudl be equal", 12, tree.size());
		System.out.println(tree.size());
		tree.printInorder();
	}
	@Test
	public void insertTest() {
		Integer[] elements = {5, 1, 9, 15, 18, 20, 22, 25, 30, 2, 3, 99, -1};
		MyBTree tree = new MyBTree(1);
		for(Integer element: elements) {
			boolean didInsertElement = tree.insert(element);
			assertTrue("should insert element " + element.toString(), didInsertElement);
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
		}
		assertEquals(elements.length, tree.size());
	}
	@Test
	public void containTest() {
		Integer[] elements = {5, 1, 9};
		MyBTree tree = new MyBTree(1);
		for(Integer element: elements) {
			boolean didInsertElement = tree.insert(element);
			assertTrue("should insert element " + element.toString(), didInsertElement);
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
		}
		for(Integer element: elements) {
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
		}
		
		Integer[] elementsNotInTree = {4, 0, 10};
		for(Integer element: elementsNotInTree) {
			boolean treeContainsElement = tree.contains(element);
			assertFalse("should not contain element " + element.toString(), treeContainsElement);
		}
	}
	@Test
	public void sizeTest() {
		Integer[] elements = {5, 1, 9, 4, 0, 99};
		MyBTree tree = new MyBTree(1);
		assertTrue("should insert all elements", tree.insertAll(elements));
		assertEquals("should have the correct size", elements.length, tree.size());
	}
	@Test
	public void heightTest() {
		Integer[] elements = {5, 1};
		MyBTree tree = new MyBTree(1);
		assertTrue("should insert all elements", tree.insertAll(elements));
		assertEquals("should have the correct height", 1, tree.height());
		Integer[] elements2 = {5, 1, 3, 4, 0, 6};
		MyBTree tree2 = new MyBTree(1);
		assertTrue("should insert all elements", tree2.insertAll(elements2));
		assertEquals("should have the correct height", 2, tree2.height());
	}
	
	@Test
	public void minMaxTest() {
		Integer[] elements = {5, 1, 9, 15, 18, 20, 22, 25, 30, 2, 3, 99, -1};
		Integer expectedMin = ArrayUtility.min(elements);
		Integer expectedMax = ArrayUtility.max(elements);
		MyBTree tree = new MyBTree(1);
		tree.insertAll(elements);
		Integer actualMin = tree.getMin();
		Integer actualMax = tree.getMax();
		assertEquals(expectedMin, actualMin);
		assertEquals(expectedMax, actualMax);
	}
	
	@Test
	public void testEverthingWithDifferentOrdinals() {
		Integer[] elements = {5, 1, 9, 15, 18, 20, 22, 25, 30, 2, 3, 99, -1, 21, 11, 17, -2, -4, -5, -9, -20, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 200, -200, 210, 211, 212, 213, 214};
		int[] ordinals = {1,2,3,4,5,6, 10, 20};
		for(int ordinal: ordinals) {
			testEverthing(elements, ordinal);
		}
	}
	
	private void testEverthing(Integer[] elements, int ordinal) {
		MyBTree tree = new MyBTree(ordinal);
		for(Integer element: elements) {
			boolean didInsertElement = tree.insert(element);
			assertTrue("should insert element " + element.toString(), didInsertElement);
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
		}
		
		assertEquals("should have the correct size", elements.length, tree.size());
		
		for(Integer element: elements) {
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
		}
		
		for(Integer element: elements) {
			boolean didInsertElement = tree.insert(element);
			assertFalse("should not insert element " + element.toString() + " again", didInsertElement);
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should still contain element " + element.toString(), treeContainsElement);
		}
		
		Integer expectedMin = ArrayUtility.min(elements);
		Integer expectedMax = ArrayUtility.max(elements);
		Integer actualMin = tree.getMin();
		Integer actualMax = tree.getMax();
		assertEquals(expectedMin, actualMin);
		assertEquals(expectedMax, actualMax);
	}
	
	

}
