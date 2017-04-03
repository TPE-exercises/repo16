package de.hsMannheim.ss17.tpe.martinDavid.uebung1;
import static gdi.MakeItSimple.*;

public class Aufgabe2 {
	private static final int ordinal = 1;
	private static final int treeCount = 3;
	
	private static MyBTree[] trees;
	private static int workingTreeIndex = 0;
	
	public static void main(String[] args) {
		trees = new MyBTree[treeCount];
		for(int i = 0; i < trees.length; i++) {
			trees[i] = new MyBTree(ordinal);
		}
		
		inputLoop();
	}
	
	private static void inputLoop() {
		
		
		
		int userActionID = readInt();
		
		switch (userActionID) {
		case 0:
			println("Enter a number between 0 and " + (treeCount - 1) + ":");
			int newWorkingTreeIndex = readInt();
			if (newWorkingTreeIndex < 0 || newWorkingTreeIndex >= trees.length) {
				println("Tree with index " + newWorkingTreeIndex + " does not exists");
				break;
			}
			workingTreeIndex = newWorkingTreeIndex;
			println("Successful changed working tree to index: " + workingTreeIndex);
		default:
			println("Action with ID(" + userActionID + ") does not exists");
		}
	}
	
	private static MyBTree getWorkingTree() {
		return trees[workingTreeIndex];
	}
	
	private void printMenu() {
		println("**************************");
		println("0) change working tree");
		println("1) insert( Integer )");
		println("2) insert( File-path )");
		println("3) contains");
		println("4) size");
		println("5) height");
		println("6) getMax");
		println("7) getMin");
		println("8) isEmpty");
		println("9) addAll");
		println("10) clone");
		println("11) printInOrder");
		println("12) printPostOrder");
		println("13) printPreOrder");
		println("14) printLevelOrder");
	}

}