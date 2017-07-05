package klausurvorbereitung;

public class Graphikprogramm extends Thread {

	public Graphikprogramm() {
		
	}
	
	public void run() {
		int timeToSleep = (int) (Math.random() * 20) + 5 * 1000;
		
		sleep(timeToSleep);
	}
	
}
