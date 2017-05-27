package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import static gdi.MakeItSimple.println;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;

public class QuickSortSequenz implements SortAlgorithm {
	
	private final static String SEPARATOR = ",";

	private int counterSwaps;
	private int counterComparisons;
	private int counterRekursion;
	
	@Override
	public void sort(Comparable[] array) {
		counterSwaps = 0;
		counterComparisons = 0;
		//initialized by -1, because the first method-call is no rekursion
		counterRekursion = -1;
		
		quickSort(array, 0, array.length-1);
	}
	
	/**
	 * sorts by QuickSort algorithm
	 * @param array to sort
	 * @param lowerBorder sort from this index
	 * @param higherBorder sort to this index
	 */
	private void quickSort(Comparable[] array, int lowerBorder, int higherBorder) {
		counterRekursion++;
		
		if(DEBUG) {			
			println("");
			println("Neue Rekursion, Rekursionsschritte: " + counterRekursion);
			println(ArrayUtility.join(array, SEPARATOR));
		}
		
		if(higherBorder > lowerBorder) {
			int indexPiv = sortHalfs(array, lowerBorder, higherBorder);
			
			if(DEBUG) {
				println(ArrayUtility.join(array, SEPARATOR));
			}
			
			//sort lower half
			quickSort(array, lowerBorder, indexPiv - 1);
			if(DEBUG) {
				println("\nlower half sorted:");
				println(ArrayUtility.join(array, SEPARATOR));
			}
			
			//sort upper half
			quickSort(array, indexPiv + 1, higherBorder);
			if(DEBUG) {
				println("\nupper half sorted:");
				println(ArrayUtility.join(array, SEPARATOR));
			}
		}
	}
	
	/**
	 * moves every element < pivot to the left side and similar in the other way
	 * @param array the array half to be sorted
	 * @param lowerBorder marks lower end of the half
	 * @param higherBorder marks higher end of the half
	 * @return index of the pivot Element
	 */
	private int sortHalfs(Comparable[] array, int lowerBorder, int higherBorder) {
		int indexPivot = higherBorder;
		//Position to change element if pointer was smaller than pivot
		int index = lowerBorder;
		
		//pointer gives the position that should be compared with pivot
		for(int pointer = lowerBorder; pointer < higherBorder; pointer++){
			
			counterComparisons++;
			//if(array[pointer] <= array[indexPivot]){
			if(array[pointer].compareTo(array[indexPivot]) != 1) {
				counterSwaps++;
				ArrayUtility.swapElements(array, index, pointer);
				index++;
			}
		}
		counterSwaps++;
		ArrayUtility.swapElements(array, index, indexPivot);
		
		return index;
	}

	public int getCounterSwaps() {
		return counterSwaps;
	}

	public int getCounterComparisons() {
		return counterComparisons;
	}

	public int getCounterRekursion() {
		return counterRekursion;
	}

}
