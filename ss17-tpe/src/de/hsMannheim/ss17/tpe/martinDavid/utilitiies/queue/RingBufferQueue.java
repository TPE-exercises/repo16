package de.hsMannheim.ss17.tpe.martinDavid.utilitiies.queue;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.OverflowException;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.UnderflowException;

public class RingBufferQueue implements Queue {
	static private final int defaulMaxSize = 4;
	RingBuffer ringBuffer;
	/**
	 * the max size of the queue with which it was created
	 */
	private final int initalMaxSize;
			
	RingBufferQueue() {
		this(defaulMaxSize);
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
				//TOOD das selbe wie in der anderen Queue: ich würde die exception auch noch zusätzlich weiterreichen
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
