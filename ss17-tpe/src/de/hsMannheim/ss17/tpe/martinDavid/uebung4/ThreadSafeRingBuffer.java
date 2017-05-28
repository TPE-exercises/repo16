package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.OverflowException;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.UnderflowException;
import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.queue.RingBuffer;

public class ThreadSafeRingBuffer {
	private RingBuffer ringBuffer;
	public ThreadSafeRingBuffer(int maxSize) {
		ringBuffer = new RingBuffer(maxSize);
	}
	/**
	 * thread safe method to put an element into the buffer.
	 * this method will block if the ring buffer is full!
	 * @param element to put in the buffer
	 * @throws InterruptedException
	 */
	public synchronized void put(Object element) throws InterruptedException {
		while(ringBuffer.isFull()) {
			wait();
		}
		try {
			ringBuffer.addLast(element);
		} catch (OverflowException e) {
			//should never happen because we already checked the precondition in the while loop!
			//another option would be:
			/*
			while(true) {
				try {
					ringBuffer.addLast(element);
					break;
				} catch (OverflowException e1) {
					wait();
				}
				
			}
			*/
			//@tutor what is the better option or does another even better option exists?
			
			assert(false);
		}
		notifyAll();
	}
	/**
	 * thread safe method to get an element from the buffer.
	 * this method will block if the ring buffer is empty!
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized Object get() throws InterruptedException {
		while(ringBuffer.isEmpty()) {
			wait();
		}
		try {
			Object element = ringBuffer.removeFirst();
			notifyAll();
			return element;
			
		} catch(UnderflowException e) {
			//should never happen because we already checked the precondition in the while loop!
			//another option would be:
			/*
			while(true) {
				try {
					Object element = ringBuffer.removeFirst();
					notifyAll();
					return element;
				} catch (OverflowException e1) {
					wait();
				}
			}
			*/
			//@tutor what is the better option or does another even better option exists?
			assert(false);
			return null;
		}
	}
	/**
	 * blocks the current thread until the ring buffer is empty
	 * @throws InterruptedException
	 */
	public synchronized void waitUntilEmpty() throws InterruptedException {
		while(!ringBuffer.isEmpty()) {
			wait();
		}
	}
	/**
	 * blocks the current thread until the ring buffer is full
	 * @throws InterruptedException
	 */
	public synchronized void waitUntilFull() throws InterruptedException {
		while(!ringBuffer.isFull()) {
			wait();
		}
	}
	/**
	 * returns an array of all elements in the input order
	 * @return
	 */
	public synchronized Object[] toContinuesArray() {
		return ringBuffer.toContinuesArray();
	}
	/**
	 * returns the size of the ring buffer thread safe
	 * @return
	 */
	public synchronized int size() {
		return ringBuffer.size();
	}
	/**
	 * returns true if the ring buffer is empty, otherwise false
	 * @return
	 */
	public synchronized boolean isEmpty() {
		return ringBuffer.isEmpty();
	}
	
	/**
	 * return true if the ring buffer is full, otherwise false
	 * @return
	 */
	public synchronized boolean isFull() {
		return ringBuffer.isFull();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{" + ArrayUtility.join(toContinuesArray(), ",") + "}";
	}
}
