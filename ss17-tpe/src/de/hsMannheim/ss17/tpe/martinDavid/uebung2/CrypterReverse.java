package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public class CrypterReverse implements Crypter {

	@Override
	public String encrypt(String message) {
		if(message == null)
			return null;
		
		char[] encrypted = message.toCharArray();
	
		char temp;
		int lastIndex = encrypted.length - 1;
		for(int i = 0; i < encrypted.length / 2; i++) {
			temp = encrypted[i];
			encrypted[i] = encrypted[lastIndex - i];
			encrypted[lastIndex - i] = temp;
		}
		
		return new String(encrypted);
	}

	@Override
	public String decrypt(String cypherText) {
		//en- and decrypting is the same for this class
		return encrypt(cypherText);
	}

}
