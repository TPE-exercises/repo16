package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import java.util.ArrayList;

public class NumberConsumer extends Thread {
	private ThreadSafeRingBuffer ringBuffer;
	private long delay;
	public ArrayList<Integer> consumedNumbers = new ArrayList<Integer>();
	public NumberConsumer(ThreadSafeRingBuffer ringBuffer, long dealy) {
		this.ringBuffer = ringBuffer;
		this.delay = dealy;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				Integer number = (Integer)ringBuffer.get();
				consumedNumbers.add(number);
				System.out.println("Consume Number:" + number.toString());
				sleep(delay);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
}
