package klausurvorbereitung.ss07.aufgabe4;

public class Druckprozess {

	public static void main(String [] args) { 
		WarteschlangenKontrolle c = new WarteschlangenKontrolle(); 
		//Erzeugen und Starten des Drucker − Prozesses 
		Drucker drucker = new Drucker(c);
		drucker.start();
		//Erzeugen und Starten des Graphikprogramm - Prozesses 
		Graphikprogramm graphikprogramm = new Graphikprogramm(c);
		graphikprogramm.start();
	} 
	
}
