package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import static gdi.MakeItSimple.*;

public class MonnemerEisdiele extends Eisdiele {

	public static void main(String[] args) {
		Eisdiele eisdiele = new MonnemerEisdiele();
		eisdiele.bedieneKunden();
	}
	
	@Override
	public void begruessen() {
		println("Hea! Was solls sein?");
	}
	
	@Override
	void verabschieden() {
		println("alla");
	}
	
	@Override
	public Eis erstellen(String eissorte) {
		switch (eissorte) {
		case "Spaghettieis":
			return new Eis(
					"Spaghettieis", 
					6, 
					"Schüssel", 
					"Spaghettis", 
					new String[]{"Vanille"}, 
					new String[]{"Monnemer Dreck"}
				);
		case "Erdbeereis":
			return new Eis(
					"Erdbeereis", 
					6.0, 
					"Schüssel", 
					"Kugeln", 
					new String[]{"Vanille", "Erbeer"}, 
					new String[]{"Erbeeren", "Sahne"}
				);
		case "Wald Becher":
			return new Eis(
					"Wald Becher", 
					7.0, 
					"Schüssel", 
					"Kugeln", 
					new String[]{"Vanille", "Erbeer", "Himbeer"}, 
					new String[]{"Erbeeren", "Himbeeren", "Brombeeren", "Kirschen", "Sahne"}
				);
		}
		return null;
	}
	

}
