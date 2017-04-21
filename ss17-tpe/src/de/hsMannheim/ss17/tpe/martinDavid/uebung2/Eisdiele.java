package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public abstract class Eisdiele {
	/**
	 * Bedient einen Kunden komplet, 
	 * von der Begrüßung über die Bestellung bis zur Verabschiedung
	 */
	public void kundenBedienen() {
		
	}
	/**
	 * Begrüßt den Kunden 
	 */
	public void begruessen(){
		
	}
	/**
	 * Versucht ein Eis von der gegebene Sorte zu bestellen, bereitet es zu und kassiert direkt beim Kunden.
	 * Entschuldigt sich wenn es nicht verfügbar ist
	 * @param eissorte Eissorte die bestellt werden soll
	 * @return
	 */
	public boolean bestellen(String eissorte) {
		return false;
	}
	/**
	 * Versucht ein Eis von der gegeben Sorte zu erstellen, 
	 * wenn es in der Eisdiele verfügbar ist
	 * @param eissorte Eissorte die erstellt werden soll
	 * @return Ein neues Eis von der gegebenen Sorte oder null wenn es nicht verfügbar ist
	 */
	public abstract Eis erstellen(String eissorte);
	/**
	 * Vordert den Kunden auf den gegebenen Preis zu bezahlen und gibt gegebenfalls Rückgeld
	 * @param preis Der Preis des Eises
	 */
	public void kassieren(double preis) {
		
	}
	/**
	 * Verabschiedet den Kunden
	 */
	void verabschieden() {
		
	}
	/**
	 * Entschuldig sich beim Kunden das eine bestimmte Eissorte nicht verfügbar ist
	 */
	void entschuldigen(String eisSorte) {
		
	}
}
