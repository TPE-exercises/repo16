package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

public class InterruptTimer extends Thread {
	private Thread[] threads;
	private long timeout;
	/**
	 * Interrupts all threads after a given timeout and waits until all threads die
	 * @param threads threads to interrupt
	 * @param timeout timeout in milliseconds
	 */
	public InterruptTimer(Thread[] threads, long timeout) {
		this.threads = threads;
		this.timeout = timeout;
	}
	@Override
	public void run() {
		try {
			sleep(timeout);
		} catch (InterruptedException e) {
			//it's okay
		}
		for(Thread thread: threads) {
			thread.interrupt();
		}
		for(Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
