package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

public class RingBufferQueue implements Queue {
	
	RingBuffer ringBuffer;
	/**
	 * the max size of the queue with which it was created
	 */
	private final int initalMaxSize;
			
	RingBufferQueue() {
		this(4);
	}
	RingBufferQueue(int maxSize) {
		this.initalMaxSize = maxSize;
		ringBuffer = new RingBuffer(maxSize);
	}
	
	@Override
	public void enqueue(Object element) throws OverflowException {
		try {
			this.ringBuffer.addLast(element);
		} catch (OverflowException overflowException) {
			if (this.maxSize() == this.initalMaxSize) {
				this.ringBuffer.increaseStorageTo(this.ringBuffer.maxSize() * 2);
				this.ringBuffer.addLast(overflowException.getElementCausedOverflow());
			} else {
				throw overflowException;
			}
		}
	}

	@Override
	public Object dequeue() throws UnderflowException {
		return this.ringBuffer.removeFirst();
	}

	@Override
	public int size() {
		return this.ringBuffer.size();
	}

	@Override
	public int maxSize() {
		return this.ringBuffer.maxSize();
	}

}
