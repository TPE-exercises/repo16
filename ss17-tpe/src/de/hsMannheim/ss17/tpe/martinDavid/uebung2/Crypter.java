package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

public interface Crypter {

	/**
	 * encrypts a message
	 * @param message original message
	 * @return encrypted message
	 */
	public String encrypt(String message);
	
	/**
	 * decrypts a message
	 * @param cypherText encrypted message
	 * @return encrypted message
	 */
	public String decrypt(String cypherText);
	
}
