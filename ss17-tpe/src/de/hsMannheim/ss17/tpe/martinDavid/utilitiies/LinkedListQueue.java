package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class LinkedListQueue implements Queue {
	static private final int defaulMaxSize = 4;
	private int maxSize;
	private LinkedList list = new LinkedList();
	/**
	 * the max size of the queue with which it was created
	 */
	private final int initalMaxSize;
			
	LinkedListQueue() {
		this(defaulMaxSize);
	}
	LinkedListQueue(int maxSize) {
		this.initalMaxSize = maxSize;
		this.maxSize(maxSize);
	}
	
	@Override
	public void enqueue(Object element) throws OverflowException {
		if(this.size() == this.initalMaxSize) {
			this.enqueuAndDoubleSizeIfNeeded(element);
		} else {
			this.enqueuOrThrow(element);
		}
	}
	
	/**
	 * push the element on the queue. if it exceeds the max size it doubles the max size
	 * @param element element to push on top of the queue
	 */
	private void enqueuAndDoubleSizeIfNeeded(Object element) {
		try {
			this.enqueuOrThrow(element);
		} catch(OverflowException overflowException) {
			//TODO ich verstehe aus der Aufgabenstellung, dass diese Exception auch bis zu enqueue durchgereicht werden soll und nicht nur hier abgefangen werden soll.
			
			this.maxSize(this.maxSize() * 2);
			this.enqueuAndDoubleSizeIfNeeded(overflowException.getElementCausedOverflow());
		}
	}
	/**
	 * push the element on the queue. if it exceeds the max size it throws a OverflowException
	 * @param element element to push on top of the queue
	 */
	private void enqueuOrThrow(Object element) throws OverflowException {
		if(this.size() >= this.maxSize()) {
			throw new OverflowException("This element would exceede the maximum size of the Queue", element);
		}
		this.list.addLast(element);
	}

	@Override
	public Object dequeue() throws UnderflowException {
		if(this.isEmpty()) {
			throw new UnderflowException("The Queue is empty. Can not dequeue");
		}
		return this.list.removeFirst();
	}

	@Override
	public int size() {
		return this.list.size();
	}
	/**
	 * sets the max size to the given value.
	 * @param maxSize
	 */
	private void maxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	@Override
	public int maxSize() {
		return this.maxSize;
	}

}
