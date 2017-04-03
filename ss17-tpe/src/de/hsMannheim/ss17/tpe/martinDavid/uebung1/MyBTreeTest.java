package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

import static org.junit.Assert.*;

import org.junit.Test;

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
		Integer[] elements = {5, 1, 9, 15, 18, 20, 22, 25, 30} ;
		MyBTree tree = new MyBTree(1);
		for(Integer element: elements) {
			boolean didInsertElement = tree.insert(element);
			assertTrue("should insert element " + element.toString(), didInsertElement);
			boolean treeContainsElement = tree.contains(element);
			assertTrue("should contain element " + element.toString(), treeContainsElement);
			tree.printInorder();
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
		Integer[] elements = {5, 1, 9};
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
	}

}
