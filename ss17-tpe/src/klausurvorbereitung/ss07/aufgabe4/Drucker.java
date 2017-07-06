package klausurvorbereitung.ss07.aufgabe4;

public class Drucker extends Thread {

	private WarteschlangenKontrolle warteschlange;
	private boolean running;
	
	Drucker(WarteschlangenKontrolle warteschlange) {
		this.warteschlange = warteschlange;
		running = true;
	}
	
	public void run() {
		while(running) {
			try {
				DruckJob druckJob = warteschlange.get();
				//Do something with druckJob
			
				sleep(20000); //wait 20 seconds (4 pages per minute)
			} catch (InterruptedException ignored) { }
		}
	}
	
}
