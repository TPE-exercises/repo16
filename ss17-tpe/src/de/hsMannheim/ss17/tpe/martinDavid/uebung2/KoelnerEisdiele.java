package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import static gdi.MakeItSimple.*;

public class KoelnerEisdiele extends Eisdiele {
	
	public static void main(String[] args) {
		Eisdiele eisdiele = new KoelnerEisdiele();
		eisdiele.bedieneKunden();
	}
	
	@Override
	public void begruessen() {
		println("Morje!");
	}
	
	@Override
	void verabschieden() {
		println("Tschö!");
	}
	
	@Override
	public Eis erstellen(String eissorte) {
		switch (eissorte) {
		case "Bananasplitt":
			return new Eis(
					"Bananasplitt", 
					6.5, 
					"Schüssel", 
					"Bälchen", 
					new String[]{"Vanille", "Bannane"}, 
					new String[]{"Banane", "Schokoladen-Kokos-Soße"}
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
					new String[]{"Erbeeren", "Brombeeren", "Kirschen", "Sahne"}
				);
		}
		return null;
	}
}
