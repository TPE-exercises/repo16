package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import java.util.ArrayList;

public class NumberConsumer extends Thread {
	private static int consumerCount = 0;
	private ThreadSafeRingBuffer ringBuffer;
	private long delay;
	public ArrayList<Integer> consumedNumbers = new ArrayList<Integer>();
	/**
	 * create a number consumer which will get numbers from the ring buffer and logs them
	 * @param ringBuffer
	 * @param dealy
	 */
	public NumberConsumer(ThreadSafeRingBuffer ringBuffer, long dealy) {
		this.ringBuffer = ringBuffer;
		this.delay = dealy;
		consumerCount++;
		this.setName("Consumer " + consumerCount);
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				Integer number = (Integer)ringBuffer.get();
				consumedNumbers.add(number);
				System.out.println(getName() + " did consum number " + number.toString() + " from ring buffer");
				sleep(delay);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
}
