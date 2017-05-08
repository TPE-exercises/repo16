package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.ArrayUtility;

public class CrypterCaesar {

	private Character[] alphabet = {
			'A', 'B', 'C', 'D', 'E' ,'F' ,'G' ,'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e' ,'f' ,'g' ,'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'Ä', 'Ö', 'Ü',
			'ä', 'ö', 'ü'};
	
	private int key;
	
	public CrypterCaesar(int key) {
		//key has to be in range of the alphabet length
		this.key = checkIfIndexIsOutOfRange(key);
	}
	
	/**
	 * encrypts a string with the given key in the given alphabet
	 * @param toEncrypt string will be encrypted
	 * @return encrypted string
	 */
	public String encrypt(String toEncrypt) {
		return encryptMessage(toEncrypt, key);
	}
	
	/**
	 * decrypts a string with the given key in the given alphabet
	 * @param toDecrypt string will be decrpted
	 * @return decrypted string
	 */
	public String decrypt(String toDecrypt) {
		//decrypting is encrypting with the negative key
		return encryptMessage(toDecrypt, -key);
	}
	
	/**
	 * encrypts a character with the given key in the given alphabet
	 * @param toEncrypt character will be encrypted
	 * @return encrypted character
	 */
	public Character encrypt(Character toEncrypt) {
		return encryptChar(toEncrypt, key);
	}
	
	/**
	 * decrypts a character with the given key in the given alphabet
	 * @param toDecrypt character will be decrpted
	 * @return decrypted character
	 */
	public Character decrypt(Character toDecrypt) {
		return decryptChar(toDecrypt, key);
	}
	
	/**
	 * Encrypts a message with the key in the params and the alphabet in this class
	 * @param message that will be encrypted
	 * @param key for encryption
	 * @return encrypted message
	 */
	private String encryptMessage(String message, int key) {
		if(message == null)
			return null;
		
		char[] encryptedMessage = message.toCharArray();
		
		for(int i = 0; i < encryptedMessage.length; i++) {
			char toEncrypt = encryptedMessage[i];
			encryptedMessage[i] = encryptChar(toEncrypt, key);
		}
		
		return new String(encryptedMessage);
	}
	
	/**
	 * Encrypts a character with the given key in the alphabet of this class
	 * @param charToEncrpyt
	 * @param key
	 * @return encrypted character
	 */
	private Character encryptChar(Character charToEncrpyt, int key) {
		int indexCharacter = ArrayUtility.indexOf(alphabet, charToEncrpyt);
		
		if(indexCharacter == -1)
			return charToEncrpyt;
		
		indexCharacter += key;
		
		indexCharacter = checkIfIndexIsOutOfRange(indexCharacter);
		
		return alphabet[indexCharacter];
	}
	
	/**
	 * Decrypts a character with the given key in the alphabet of this class
	 * @param charToDecrypt
	 * @param key
	 * @return decrypted character
	 */
	private Character decryptChar(Character charToDecrypt, int key) {
		//decrypting is like encrypting wiht the negative key
		return encryptChar(charToDecrypt, -key);
	}
	
	private int checkIfIndexIsOutOfRange(int toCheck) {
		while(toCheck < 0) {
			toCheck += alphabet.length;
		}
		
		while(toCheck >= alphabet.length) {
			toCheck -= alphabet.length;
		}
		
		return toCheck;
	}
}
