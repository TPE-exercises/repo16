package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class RingBuffer {
	private Object[] array;
	private int lowerBound = 0;
	private int upperBound = 0;
	RingBuffer(int size) {
		this.array = new Object[size + 1];
	}
	/**
	 * add the given element to the end of the ring buffer
	 * @param element
	 * @throws OverflowException if the ring buffer full a OverflowException will be thrown
	 */
	public void addLast(Object element) throws OverflowException {
		if (this.size() == this.maxSize()) 
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
	}
}
