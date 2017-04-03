package de.hsMannheim.ss17.tpe.martinDavid.uebung1;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyBTreeTest {

	@Test
	public void insertTest() {
		Integer[] elements = {5, 1, 9};
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
		Integer[] elements = {5, 1};
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
