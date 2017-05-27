package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

import java.util.ArrayList;
import java.util.Random;

public class RandomNumberProducer extends Thread {
	private Random generator;
	private long delay;
	private ThreadSafeRingBuffer ringBuffer;
	public ArrayList<Integer> generatedNumbers = new ArrayList<Integer>();
	/**
	 * creates a thread which will produce a random number, 
	 * put it in the ring buffer and waits a given duration and 
	 * repeats it until it is interrupted
	 * @param ringBuffer ring buffer to put the numbers in
	 * @param seed random number generator seed
	 * @param delay wait duration
	 */
	public RandomNumberProducer(ThreadSafeRingBuffer ringBuffer, long seed, long delay) {
		this.ringBuffer = ringBuffer;
		this.generator = new Random(seed);
		this.delay = delay;
	}
	@Override
	public void run() {
		while(!isInterrupted()) {
			
			int randomInt = generator.nextInt();
			Integer randomInteger = new Integer(randomInt);
			
			try {
				ringBuffer.put(randomInteger);
				generatedNumbers.add(randomInteger);
				sleep(delay);
			} catch (InterruptedException e) {
				System.out.println("RandomNumberProducer interupted");
				interrupt();
			}
		}
	}
}
