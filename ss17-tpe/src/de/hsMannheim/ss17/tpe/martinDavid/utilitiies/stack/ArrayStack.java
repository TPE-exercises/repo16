package de.hsMannheim.ss17.tpe.martinDavid.utilitiies.stack;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.OverflowException;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.UnderflowException;

public class ArrayStack implements Stack {
	static private final int defaulMaxSize = 4;
	private Object[] array;
	private int lastElementIndex = -1;
	/**
	 * the max size of the stack with which it was created
	 */
	private final int initalMaxSize;
			
	ArrayStack() {
		this(defaulMaxSize);
	}
	ArrayStack(int maxSize) {
		this.initalMaxSize = maxSize;
		this.array = new Object[maxSize];
	}
	@Override
	public void push(Object element) throws OverflowException {
		if(this.size() == this.initalMaxSize) {
			this.pushAndDoubleSizeIfNeeded(element);
		} else {
			this.pushOrThrow(element);
		}
	}
	/**
	 * push the element on the stack. if it exceeds the max size it doubles the max size
	 * @param element element to push on top of the stack
	 */
	private void pushAndDoubleSizeIfNeeded(Object element) {
		try {
			this.pushOrThrow(element);
		} catch(OverflowException overflowException) {
			this.maxSize(this.maxSize() * 2);
			this.pushAndDoubleSizeIfNeeded(overflowException.getElementCausedOverflow());
		}
	}
	/**
	 * push the element on the stack. if it exceeds the max size it throws a OverflowException
	 * @param element element to push on top of the stack
	 */
	private void pushOrThrow(Object element) throws OverflowException {
		if(this.size() >= this.maxSize()) {
			throw new OverflowException("This element would exceede the maximum size of the Stack", element);
		}
		this.lastElementIndex += 1;
		this.array[this.lastElementIndex] = element;
	}

	@Override
	public Object pop() throws UnderflowException {
		if(this.isEmpty()) {
			throw new UnderflowException("The Stack is empty. Can not pop");
		}
		Object lastElement = this.array[this.lastElementIndex];
		this.array[this.lastElementIndex] = null;
		this.lastElementIndex -= 1;
		return lastElement;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.lastElementIndex + 1;
	}
	/**
	 * sets the max size to the given value and extends the array
	 * @param maxSize
	 */
	private void maxSize(int maxSize) {
		if(maxSize() == maxSize) {
			return;
		}
		Object[] oldArray = this.array;
		this.array = new Object[maxSize];
		ArrayUtility.copy(oldArray, array, 0, lastElementIndex);
	}
	@Override
	public int maxSize() {
		// TODO Auto-generated method stub
		return array.length;
	}

}
