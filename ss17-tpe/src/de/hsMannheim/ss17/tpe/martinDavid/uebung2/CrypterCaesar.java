package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.StringUtils;

public class CrypterCaesar implements Crypter {

	private int key;
	
	/**
	 * 
	 * @param key when you call the methods encrypt or decrypt 
	 * they will be enrcypted or decrypted with this key
	 */
	public CrypterCaesar(int key) {
		this.key = key;
	}
	
	@Override
	public String encrypt(String message) {
		
		message = StringUtils.toLowerCase(message);
		
		return null;
	}

	@Override
	public String decrypt(String cypherText) {
		
		
		
		return null;
	}
	
	

}
