package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public interface Queue {
	/**
     * Adds the element to this queue.
     *
     * @param element - the element to add
     */
    public void enqueue(Object element) throws OverflowException;
    /**
     * Removes and return the last element of the queue
     * @return the last element of the queue
     */
    public Object dequeue() throws UnderflowException; 
	/**
	 * calculates the element count on the queue
	 * @return the element count of the queue
	 */
	public int size();
	/**
	 * gets the current max size of the queue
	 * @return
	 */
	public int maxSize();
	/**
	 * checks if the Queue is empty or not
	 * @return true if the size is 0, otherwise false
	 */
	default public boolean isEmpty() {
		return this.size() == 0;
	}
}
