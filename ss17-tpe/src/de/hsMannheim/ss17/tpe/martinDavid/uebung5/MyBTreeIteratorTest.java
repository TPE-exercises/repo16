package de.hsMannheim.ss17.tpe.martinDavid.uebung5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyBTreeIteratorTest {


	@Test
	public void testEmptyTree() {
		MyBTree tree = new MyBTree(2);
		for(Integer number: tree) {
			fail("Tree is empty and should not iterate at all");
		}
		
	}
	
	@Test
	public void testWithOneElement() {
		MyBTree tree = new MyBTree(2);
		
		Integer expectedNumber = 7;
		tree.insert(expectedNumber);
		
		int iterationCount = 0;
		for(Integer number: tree) {
			iterationCount++;
			assertEquals(expectedNumber, number);
		}
		
		assertEquals(1, iterationCount);
		
	}
	@Test
	public void testWithTenElements() {
		
		String directory = "src/de/hsMannheim/ss17/tpe/martinDavid/uebung5/";
		String filename = "integers.txt";
		String filepath = directory + filename;
		MyBTree tree = new MyBTree(1);
		tree.insert(filepath);
		
		int iterationCount = 0;
		for(Integer number: tree) {
			iterationCount++;
		}
		
		assertEquals(9, iterationCount);
		
	}

}
