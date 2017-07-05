package klausurvorbereitung.ss07.aufgabe4;

public class Graphikprogramm extends Thread {

	private WarteschlangenKontrolle warteschlange;
	
	public Graphikprogramm(WarteschlangenKontrolle warteschlange) {
		this.warteschlange = warteschlange;
	}
	
	public void run() {
		while (true) {
			int timeToSleep = (int) (Math.random() * 20) + 5 * 1000;
			
			try {
				sleep(timeToSleep);
				
				warteschlange.put(new DruckJob());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
