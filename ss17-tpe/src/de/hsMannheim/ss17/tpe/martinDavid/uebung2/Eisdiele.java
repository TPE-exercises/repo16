package de.hsMannheim.ss17.tpe.martinDavid.uebung2;
import static gdi.MakeItSimple.*;

import java.text.DecimalFormat;

public abstract class Eisdiele {
	/**
	 * Bedient einen Kunden komplet, 
	 * von der Begrüßung über die Bestellung bis zur Verabschiedung
	 */
	public void bedieneKunden() {
		this.begruessen();
		boolean hatEisErfolgreichBestellt = false;
		do {
			println("Welche Eissorte darf es sein?");
			String eissorte = readLine();
			hatEisErfolgreichBestellt = this.bestellen(eissorte);
		} while(!hatEisErfolgreichBestellt);
		
		this.verabschieden();
	}
	/**
	 * Begrüßt den Kunden 
	 */
	public void begruessen(){
		println("Willkommen in unserer Eisdiele.");
	}
	/**
	 * Versucht ein Eis von der gegebene Sorte zu bestellen, bereitet es zu und kassiert direkt beim Kunden.
	 * Entschuldigt sich wenn es nicht verfügbar ist
	 * @param eissorte Eissorte die bestellt werden soll
	 * @return
	 */
	public boolean bestellen(String eissorte) {
		Eis eis = this.erstellen(eissorte);
		if(eis == null) {
			this.entschuldigen(eissorte);
			return false;
		}
		eis.vorbereiten();
		eis.fuellen();
		eis.dekorieren();
		
		this.kassieren(eis.getPreis());
		
		return true;
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
		
		println("Das macht dann " + this.formatierePreis(preis) + "€.");
		double erhaltenerBetrag = 0;
		while(erhaltenerBetrag < preis) {
			double saldo = preis - erhaltenerBetrag;
			println("Bitte geben Sie mir noch " + this.formatierePreis(saldo) + "€ (Nur runde Beträge möglich):");
			try{
				erhaltenerBetrag += (double)readInt();
			} catch(Exception e) {
				println("Falsche eingabe.");
				//Überspringe ungültige eingabe
				readLine();
			}
			
		}
		if (erhaltenerBetrag > preis) {
			double rueckgeld = erhaltenerBetrag - preis;
			println("Sie erhalten " + this.formatierePreis(rueckgeld) + "€ Rückgeld.");
		}
	}
	
	private String formatierePreis(double preis) {
		DecimalFormat formatierer = new DecimalFormat("0.00");
		return formatierer.format(preis);
	}
	
	/**
	 * Verabschiedet den Kunden
	 */
	void verabschieden() {
		println("Auf Wiedersehen! Und lassen Sie sich ihr Eis schmecken!");
	}
	/**
	 * Entschuldig sich beim Kunden das eine bestimmte Eissorte nicht verfügbar ist
	 */
	void entschuldigen(String eissorte) {
		println("Entschuldigung, die Eissorte " + eissorte + " führen wir leider nicht.");
	}
}
