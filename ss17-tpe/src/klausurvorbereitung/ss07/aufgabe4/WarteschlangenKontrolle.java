package klausurvorbereitung.ss07.aufgabe4;

public class WarteschlangenKontrolle {

	Warteschlange q = new Warteschlange(); 
	
	// Methode zum Einfügen eines neu vom Graphikprogramm  
	// produzierten Druckjobs in die Schlange 
	synchronized void put(DruckJob dj) { 
		q.put(dj);
		
		notifyAll();
	} 
	//Methode zum Bereitstellen des vordersten Druckjobs aus der 
	//Schlange an den Drucker 
	synchronized DruckJob get() throws InterruptedException{ 
		while(q.isEmpty()) {
			wait();
		}
		
		DruckJob druckJob = q.get();
		
		notifyAll();
		
		return druckJob;
	} 
	
}
