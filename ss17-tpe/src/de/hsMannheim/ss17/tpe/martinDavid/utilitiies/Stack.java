package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public interface Stack {
	/**
	 * pushes an element on top of the stack
	 * @param element
	 */
	public void push(Object element) throws OverflowException;
	/**
	 * removes and returns the top most element of the stack
	 * @param element
	 */
	public void pop(Object element) throws UnderflowException;
	/**
	 * calculates the element count on the stack
	 * @return the element count of the stack
	 */
	public int size();
}
