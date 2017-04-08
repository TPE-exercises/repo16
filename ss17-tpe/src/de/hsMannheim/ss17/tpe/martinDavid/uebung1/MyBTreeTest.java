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
	public void insertSameElementsTest() {
		Integer[] elements = {5, 1, 9, 15, 18, 20, 5, 22} ;
		MyBTree tree = new MyBTree(1);
		for(int i = 0; i < elements.length; i++) {
			Integer element = elements[i];
			
			boolean didInsertElement = tree.insert(element);
			if(i != 6) { //6 is the index of the second 5
				assertTrue("should insert element " + element.toString(), didInsertElement);
			} else {
				assertFalse("sould not insert element " + element.toString(), didInsertElement);
			}
			
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
			tree.printInorder();
		}
		assertEquals(elements.length - 1, tree.size());
	}
	
	 @Test
	 public void addAllTest() {
		Integer[] elements = {5, 1, 9, 15, 18, 20, 22, 25, 30};
		MyBTree tree = new MyBTree(1);
		for(Integer element: elements) {
			tree.insert(element);
		}
			
		Integer[] elementsOtherTree = {2, 3, 99, -1};
		MyBTree otherTree = new MyBTree(1);
		for(Integer element : elementsOtherTree) {
			otherTree.insert(element);
		}
		
		boolean didInsertAllElements = tree.addAll(otherTree);
		assertTrue("should insert all elements", didInsertAllElements);
		
		assertEquals(tree.size(), elements.length + elementsOtherTree.length);
		
		didInsertAllElements = tree.addAll(otherTree);
		assertFalse("should not insert all elements", didInsertAllElements);
		
		assertEquals(tree.size(), elements.length + elementsOtherTree.length);
			
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
	
	@Test
	public void deepCloneTest() {
		Integer[] elements = {5, 1, 9, 15, 18, 20, 22, 25, 30, 2, 3, 99, -1};
		BTree tree = new MyBTree(2);
		for(Integer element: elements) {
			tree.insert(element);
		}
		
		BTree clonedTree = tree.clone();
		
		clonedTree.insert(100);
		clonedTree.insert(101);
		clonedTree.insert(102);
		clonedTree.insert(-2);
		clonedTree.insert(-3);
		clonedTree.insert(-4);
		assertEquals(clonedTree.size(), tree.size() + 6);
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
