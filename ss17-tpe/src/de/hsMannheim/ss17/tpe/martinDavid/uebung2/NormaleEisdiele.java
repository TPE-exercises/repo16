package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public class NormaleEisdiele extends Eisdiele {
	public static void main(String[] args) {
		Eisdiele eisdiele = new NormaleEisdiele();
		eisdiele.bedieneKunden();
	}
	@Override
	public Eis erstellen(String eissorte) {
		switch (eissorte) {
		case "Spaghettieis":
			return new Eis(
					"Spaghettieis", 
					5.5, 
					"Schüssel", 
					"Spaghettis", 
					new String[]{"Vanille"}, 
					new String[]{"Weiße Schokosträusel", "Sahne"}
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
