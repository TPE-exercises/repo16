package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsMannheim.ss17.tpe.martinDavid.uebung2.SortAlgorithms.Alogirthm;

public class SortAlgorithmsTest {

	@Test
	public void insertionSortTest() {
		MyInt[] ints = {new MyInt(2), new MyInt(4),new MyInt(1),new MyInt(3)};
		SortAlgorithms.insertionSort(ints);
		assertTrue(SortAlgorithms.isSorted(ints));
		
		MyString[] strings = {new MyString("B"), new MyString("D"), new MyString("A"), new MyString("C")};
		SortAlgorithms.insertionSort(strings);
		assertTrue(SortAlgorithms.isSorted(strings));
	}
	@Test
	public void shakerSortTest() {
		MyInt[] ints = {new MyInt(2), new MyInt(4),new MyInt(1),new MyInt(3)};
		SortAlgorithms.shakerSort(ints);
		assertTrue(SortAlgorithms.isSorted(ints));
		
		MyString[] strings = {new MyString("B"), new MyString("D"), new MyString("A"), new MyString("C")};
		SortAlgorithms.shakerSort(strings);
		assertTrue(SortAlgorithms.isSorted(strings));
	}
}
