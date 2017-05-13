package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;
import static gdi.MakeItSimple.*;

public class StackMenu {
	static Stack[] stacks;
	static int stackId = 0;
	public static void main(String[] args) {
		stacks = new Stack[]{
				new ArrayStack(),
				new LinkedListStack(),
		};
		
		while(true) {
			Stack stack = stacks[stackId];
			System.out.print("Current Stack implementation: ");
			if(stackId == 0) {
				System.out.println("ArrayStack()");
			} else if(stackId == 1) {
				System.out.println("LinkedListStack()");
			}
			printDescription(stack);
			printMenu();
			int selectOption = readInt();
			readLine();
			try{
				switch(selectOption) {
				case 1:
					System.out.println("Enter a string:");
					String elemntToPush = readLine();
					stack.push(elemntToPush);
					break;
				case 2:
					String popedElement = (String)stack.pop();
					System.out.println("Poped element:" + popedElement);
					break;
				case 3:
					stackId = 0;
					break;
				case 4:
					stackId = 1;
					break;
				default:
					System.out.println("Unkown option");
					break;
				}
			} catch(OverflowException | UnderflowException exception) {
				System.err.println(exception.getMessage());
				readLine();
			}
			
		}
	}
	
	static void printMenu() {
		System.out.println("1: stack.push(String);");
		System.out.println("2: stack.pop();");
		System.out.println("3: switch to ArrayStack() implementation");
		System.out.println("3: switch to LinkedListStack() implementation");
	}
	
	static void printDescription(Stack stack) {
		System.out.println("stack.isEmpty() = " + stack.isEmpty());
		System.out.println("stack.size() = " + stack.size());
		System.out.println("stack.maxSize() = " + stack.maxSize());
	}
}
