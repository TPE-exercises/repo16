package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

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
	
}
