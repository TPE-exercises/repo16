package de.hsMannheim.ss17.tpe.martinDavid.utilitiies.queue;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.OverflowException;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.UnderflowException;

public class RingBuffer {
	private Object[] array;
	private int lowerBound = 0;
	private int upperBound = 0;
	public RingBuffer(int size) {
		this.array = new Object[size + 1];
	}
	/**
	 * add the given element to the end of the ring buffer
	 * @param element
	 * @throws OverflowException if the ring buffer full a OverflowException will be thrown
	 */
	public void addLast(Object element) throws OverflowException {
		if (isFull()) 
			throw new OverflowException("RingBuffer overflow", element);
		else {
			array[upperBound] = element;
			upperBound = (upperBound+1) % array.length;
		}
	}
	/**
	 * removes the first element of the ring buffer and returns it
	 * @return the first element of the ring buffer
	 * @throws UnderflowException if the ring buffer is empty a UnderflowException will be thrown
	 */
	public Object removeFirst() throws UnderflowException {
		if(isEmpty()) {
			throw new UnderflowException("RingBuffer underflow");
		}
		Object element = array[lowerBound];
		array[lowerBound] = null;
		lowerBound = (lowerBound + 1) % array.length;
		return element;
	}
	/**
	 * gets the size of the ring buffer
	 * @return
	 */
	public int size() {
		return (array.length - lowerBound + upperBound) % array.length;
	}
	/**
	 * gets the max size of the ring buffer
	 * @return
	 */
	public int maxSize() {
		return array.length - 1;
	}
	/**
	 * returns true if the ring buffer is empty, otherwise false
	 * @return
	 */
	public boolean isEmpty() {
		return lowerBound == upperBound;
	}
	/**
	 * return true if the ring buffer is full, otherwise false
	 * @return
	 */
	public boolean isFull() {
		return size() == maxSize();
	}
	/**
	 * increase the max size to the given value and copies the elements to the new array.
	 * @param size - new max size
	 */
	public void increaseStorageTo(int size) {
		if((this.array.length - 1) >= size) {
			return;
		}
		int elementCount = this.size();
		Object[] oldArray = this.array;
		this.array = new Object[size + 1];
		for(int index = 0; index < elementCount; index++) {
			int oldIndex = (lowerBound + index) % oldArray.length;
			this.array[index] = oldArray[oldIndex];
		}
		
		lowerBound = 0;
		upperBound = elementCount;
	}
	/**
	 * creates an array with each element
	 * the first element is at index 0 followed by the next elements
	 * @return
	 */
	public Object[] toContinuesArray() {
		int elementCount = this.size();
		Object[] continuesArray = new Object[elementCount];
		for(int index = 0; index < elementCount; index++) {
			int elementIndex = (lowerBound + index) % array.length;
			continuesArray[index] = array[elementIndex];
		}
		return continuesArray;
	}
}
