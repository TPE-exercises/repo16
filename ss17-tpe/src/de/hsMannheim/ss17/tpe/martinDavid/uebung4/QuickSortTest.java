package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hsMannheim.ss17.tpe.martinDavid.uebung2.SortAlgorithms;

public class QuickSortTest {

	@Test
	public void quickSortSequenzTest() {
		Integer[] array = {5,9,4,3,8,2,1,7,6};
		
		QuickSortSequenz quickSort = new QuickSortSequenz();
		quickSort.sort(array);
		
		assertTrue(SortAlgorithms.isSorted(array));
	}
	
	@Test
	public void quickSortParallelTest() {
		Integer[] array = {5,9,4,3,8,2,1,7,6};
		
		SortAlgorithm quickSort = new QuickSortParallel();
		quickSort.sort(array);
		
		assertTrue(SortAlgorithms.isSorted(array));
	}
	
	@Test
	public void compareQuickSorts() {
		Integer[] array = getArrayToSort();
		
		long timeStartSequenz = System.currentTimeMillis();
		QuickSortSequenz quickSortSequenz = new QuickSortSequenz();
		quickSortSequenz.sort(array);
		long timeSequenze = System.currentTimeMillis() - timeStartSequenz;
		
		Integer[] array2 = getArrayToSort();
		
		long timeStartParallel = System.currentTimeMillis();
		QuickSortParallel quickSortParallel = new QuickSortParallel();
		quickSortParallel.sort(array2);
		long timeParallel = System.currentTimeMillis() - timeStartParallel;
		
		System.out.println("Time sequenze: " + timeSequenze);
		System.out.println("Time parallel: " + timeParallel);
		
		System.out.println("Compares sequenze: " + quickSortSequenz.getCounterComparisons());
		System.out.println("Compares parallel: " + quickSortParallel.getCounterComparisons());
		
		System.out.println("Swaps sequenze: " + quickSortSequenz.getCounterSwaps());
		System.out.println("Swaps parallel: " + quickSortParallel.getCounterSwaps());
		
		System.out.println("Rekursion sequenze: " + quickSortSequenz.getCounterRekursion());
		System.out.println("Rekursion parallel: " + quickSortParallel.getCounterRekursion());
		
		System.out.println("Threads created: " + quickSortParallel.getCounterThreadsUsed());
	}
	
	private Integer[] getArrayToSort() {
		Integer[] array = new Integer[10000];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = array.length - 1 - i;
		}
		
		return array;
	}
}
