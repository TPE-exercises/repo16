package de.hsMannheim.ss17.tpe.martinDavid.uebung5;
import static gdi.MakeItSimple.*;

public class Aufgabe2 {
	private static final int ordinal = 1;
	private static final int treeCount = 3;
	private static final String rootDirectory = "src/de/hsMannheim/ss17/tpe/martinDavid/uebung1/";
	
	private static BTree<Integer>[] trees;
	private static int workingTreeIndex = 0;
	
	public static void main(String[] args) {
		trees = new MyBTree[treeCount];
		for(int i = 0; i < trees.length; i++) {
			trees[i] = new MyBTree<>(ordinal);
		}
		while(true) {
			inputLoop();
		}
	}
	/**
	 * executes the input loop once
	 */
	private static void inputLoop() {
		
		printMenu();
		
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
			break;
		case 1:
			println("Enter an Integer to insert into the tree:");
			int integerToInsert = readInt();
			if(getWorkingTree().insert(integerToInsert)) {
				println("Successful inserted Integer(" + integerToInsert + ")");
			} else {
				println("Failed to insert Integer(" + integerToInsert + ")");
			}
			break;
		case 2:
			println("Enter an Filepath:");
			//after a call to readInt() the next call to readLine() returns immediately with an empty string
			//we need to call readLine() twice and just ignore the first result
			readLine();
			String userFilepath = readLine();
			String filepath = rootDirectory + userFilepath;
			if(getWorkingTree().insert(filepath)) {
				println("Successful inserted file at path: " + filepath );
			} else {
				println("failed to insert file at path: " + filepath );
			}
			break;
		case 3:
			println("Enter an Integer to look up:");
			int integerToSearch = readInt();
			if(getWorkingTree().contains(integerToSearch)) {
				println("Tree does contain Integer(" + integerToSearch + ")");
			} else {
				println("Tree does not contain Integer(" + integerToSearch + ")");
			}
			break;
		case 4:
			println("The trees size is  " + getWorkingTree().size());
			break;
		case 5:
			println("The trees height is " + getWorkingTree().height());
			break;
		case 6:
			println("The maximum element of the tree is: " + getWorkingTree().getMax());
			break;
		case 7:
			println("Min: " + getWorkingTree().getMin());
			break;
		case 8:
			println("isEmpty: " + getWorkingTree().isEmpty());
			break;
		case 9:
			println("Which tree should be added to the working tree (between 0 and " + (treeCount - 1) + "): ");
			int treeToAddIndex = readInt();
			if(treeToAddIndex < 0 || treeToAddIndex >= treeCount) {
				println("Tree with index " + treeToAddIndex + " does not exist");
				break;
			}
			
			getWorkingTree().addAll(trees[treeToAddIndex]);
			println("Successful added tree " + treeToAddIndex + " to tree " + workingTreeIndex);
			break;
		case 10:
			println("Which tree should be override by cloning this tree (between 0 and " + (treeCount - 1) + "): ");
			int treeToWrite = readInt();
			if(treeToWrite < 0 || treeToWrite >= treeCount) {
				println("Tree with index " + treeToWrite + " does not exist");
				break;
			}
			
			trees[treeToWrite] = getWorkingTree().clone();
			println("Successful cloned tree " + workingTreeIndex + " to tree " + treeToWrite);
			break;
		case 11:
			getWorkingTree().printInorder();
			break;
		case 12:
			getWorkingTree().printPostorder();
			break;
		case 13:
			getWorkingTree().printPreorder();
			break;
		case 14:
			getWorkingTree().printLevelorder();
			break;
		case 15:
			println("start iterating over working tree:");
			for(Integer number: getWorkingTree()) {
				print(number);
				print(" ");
			}
			println();
			println("end iterating over working tree");
		default:
			println("Action with ID(" + userActionID + ") does not exists");
		}
	}
	/**
	 * @return the current selected working tree
	 */
	private static BTree<Integer> getWorkingTree() {
		return trees[workingTreeIndex];
	}
	/**
	 * prints the menu to the standard output
	 */
	private static void printMenu() {
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
		println("15) iterator");
	}

}