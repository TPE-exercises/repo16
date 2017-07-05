package klausurvorbereitung.ss07.aufgabe4;

public class Drucker extends Thread {

	private WarteschlangenKontrolle warteschlange;
	
	Drucker(WarteschlangenKontrolle warteschlange) {
		this.warteschlange = warteschlange;
	}
	
	public void run() {
		while(true) {
			
		}
	}
	
}
