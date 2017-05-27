package de.hsMannheim.ss17.tpe.martinDavid.uebung4;

public class ProblemeThreads {

	public static void main(String[] args) {
		
		gemeinsameResourcen();
		ungueltigerZustand();
	}
	
	private static int gemeinsameVariable = 0;
	
	private static void ungueltigerZustand() {
		gemeinsameVariable = 0;
		
		//Kontostand ist 0. Abbuchen will warten bis Geld eingezahlt wurde
		Thread abbuchen = new Thread(new Runnable() {
			
			@Override
			public void run() {
				abbuchen(500);				
			}
		});
		abbuchen.start();
		
		//Geld soll eingezahlt werden. Einbezahlen ist aber blockiert von abbuchen
		Thread einbezahlen = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Möchte einzahlen");
				einbezahlen(500);				
			}
		});
		einbezahlen.start();
	}
	
	private static void gemeinsameResourcen() {
		System.out.println("Vorher: " + gemeinsameVariable);
		
		Thread add = add500();
		Thread sub = remove500();
		
		try {
			add.join();
			sub.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		//Nach dem addieren und subtrahieren von jeweils 500 sollte 0 als Ergebnis herauskommen
		//Aber es wird 500 sein.
		
		System.out.println("Danach: " + gemeinsameVariable);
	}
	
	/**
	 * Adds 500 to the gemeinsameVariable. 
	 * @return Thread which was started to add the 500
	 */
	private static Thread add500() {
		Thread add = new Thread(new Runnable() {
			
			@Override
			public void run() {
				/* die gemeinsame Variable wird in einen Zwischenspeicher geladen und dieser wird verändert
				 * In der Zwischenzeit (durch das Sleep provoziert) greift ein anderer Thread auf diese Variable zu und
				 * schreibt sein Ergebnis wieder in die gemeinsame Variable. Jetzt sollte dieser Thread mit dem 
				 * neuen Wert weiterrechnen aber in seinem Zwischenspeicher ist noch der alte Wert.
				 * 
				*/
				int temp = gemeinsameVariable;
				temp += 500;
				
				try {
					java.lang.Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				gemeinsameVariable = temp;
			}
		});
		add.start();
		
		return add;
	}
	
	/**
	 * Substracts 500 from the gemeinsameVariable. 
	 * @return Thread which was started to sub the 500
	 */
	private static Thread remove500() {
		Thread sub = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				//Warte damit der andere Thread auf jeden Fall früher beginnt
				try {
					java.lang.Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				int temp = gemeinsameVariable;
				temp -= 500;				
				gemeinsameVariable = temp;
			}
		});
		sub.start();
		
		return sub;
	} 
	
	synchronized private static void abbuchen(int betrag) {
		///warte solange bis genügend Geld vorhanden ist
		while(gemeinsameVariable < betrag) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Kontostand zu niedrig: warte...");
		}
	}
	
	synchronized private static void einbezahlen(int betrag) {
		gemeinsameVariable += betrag;
	}
}
