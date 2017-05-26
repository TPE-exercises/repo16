package de.hsMannheim.ss17.tpe.martinDavid.utilitiies.stack;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.OverflowException;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.UnderflowException;

public interface Stack {
	/**
	 * pushes an element on top of the stack
	 * @param element element to push on top of the stack
	 */
	public void push(Object element) throws OverflowException;
	/**
	 * removes and returns the top most element of the stack
	 */
	public Object pop() throws UnderflowException;
	/**
	 * calculates the element count on the stack
	 * @return the element count of the stack
	 */
	public int size();
	/**
	 * gets the current max size of the stack
	 * @return the max size of the Stack
	 */
	public int maxSize();
	/**
	 * checks if the Stack is empty or not
	 * @return true if the size is 0, otherwise false
	 */
	default public boolean isEmpty() {
		return this.size() == 0;
	}
}
