package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public class Aufgabe1Teil2 {

	private static String decryptedMessage = "XHMSNYYXYJQQJS";
	private static int keyCaesar = 5;
	
	public static void main(String[] args) {
		Crypter reverser = new CrypterReverse();
		Crypter caesar = new CrypterCaesar(keyCaesar);
		
		String afterFirstReverse = reverser.decrypt(decryptedMessage);
		String afterCaesar = caesar.decrypt(afterFirstReverse);
		String message = reverser.decrypt(afterCaesar);
		
		System.out.println("The message is: " + message);
		
		//Test
		String temp1 = reverser.encrypt(message);
		String temp2 = caesar.encrypt(temp1);
		String decryptedMessageProbe = reverser.encrypt(temp2);
		
		if(decryptedMessageProbe.equals(decryptedMessage)) {
			System.out.println("Probe was succesfull");
		} else {
			System.out.println("Probe was not succesfull");
		}
	}

}
