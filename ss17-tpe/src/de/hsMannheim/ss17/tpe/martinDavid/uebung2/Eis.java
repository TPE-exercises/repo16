package de.hsMannheim.ss17.tpe.martinDavid.uebung2;
import static gdi.MakeItSimple.*;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;

public class Eis {
	/**
	 * Bezeichnung des Eises
	 */
	private String name;
	/**
	 * Der Preis in Euro
	 */
	private double preis;
	/**
	 * Typ des Behältnisses 
	 */
	private String behaeltnis;
	
	/**
	 * Art des Eises (Bällchen, Bälle, Spaghettis, usw)
	 */
	private String art;
	
	/**
	 * Alle Sorten die das Eis beinhaltet (Vanille, Schokolade, usw)
	 */
	private String[] sorten;
	
	/**
	 * Alle Besonderheiten die zusätzlich zu den Sorten dem Eis beigefügt werden (Sahne, Schockstäusel, Kekse, usw)
	 */
	private String[] extras;
	
	Eis(String name, double preis, String behaeltnis, String art, String[] sorten, String[] extras) {
		this.name = name;
		this.preis = preis;
		this.behaeltnis = behaeltnis;
		this.art = art;
		this.sorten = sorten;
		this.extras = extras;
	}
	
	/**
	 * Bereitet alles vor um mit dem befüllen zu beginnen
	 */
	void vorbereiten(){
		println("Nehme " + behaeltnis + " um " + name + " vorzubereiten.");
	}
	/**
	 * Behältnis mit Sorten nach Art füllen
	 */
	void fuellen(){
		print("Befülle behältnis mit ");
		print(ArrayUtility.join(this.sorten, ", "));
		println(" von der Art " + art + ".");
	}
	/**
	 * Eis mit Extras dekorieren
	 */
	void dekorieren(){
		print("Dekoriere " + name + " mit ");
		print(ArrayUtility.join(this.extras, ", "));
		println(".");
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
}
