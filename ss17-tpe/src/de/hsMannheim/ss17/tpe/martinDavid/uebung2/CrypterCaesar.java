package de.hsMannheim.ss17.tpe.martinDavid.uebung2;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.StringUtils;

public class CrypterCaesar implements Crypter {

	private static final int COUNT_ALPHABET = 26;
	
	private int key;
	
	/**
	 * This class can encrypt / decrypt all letters in the English alphabet
	 * @param key when you call the methods encrypt or decrypt 
	 * they will be enrcypted or decrypted with this key
	 */
	public CrypterCaesar(int key) {
		while(key > COUNT_ALPHABET) {
			key -= COUNT_ALPHABET;
		}
		
		while(key < 0) {
			key += COUNT_ALPHABET;
		}
		
		this.key = key;
	}
	
	@Override
	public String encrypt(String message) {	
		return StringUtils.toUpperCase(encryptMessage(message, key));
	}

	@Override
	public String decrypt(String cypherText) {
		//encrypting the text with the negative key is decrypting
		return encryptMessage(cypherText, -key);
	}
	
	/**
	 * Will change the message into lowerCase and encrypt it in lowerCase
	 * @param message to encrypt
	 * @param key 
	 * @return encrypted message in lowerCase
	 */
	private String encryptMessage(String message, int key) {
		if(message == null)
			return null;
		
		message = StringUtils.toLowerCase(message);
		char[] encryptedMessage = message.toCharArray();
		
		for(int i = 0; i < encryptedMessage.length; i++) {
			char toEncrypt = encryptedMessage[i];
			encryptedMessage[i] = encryptChar(toEncrypt, key);
		}
		
		return new String(encryptedMessage);
	}
	
	/**
	 * Will encrypt lowerCase chars into a lowerCase char and the given key
	 * @param original to encrypt
	 * @param key to encrypt the message
	 * @return encrypted char
	 */
	private char encryptChar(char original, int key) {
		if(original >= 'a' && original <= 'z') {
			char encryptedCar = original;
			encryptedCar += key;
			
			return checkIfCharIsOutOfAlphabet(encryptedCar, true);			
		} else {
			return original;
		}
	}
	
	/**
	 * Checks if a char is "higher" than 'Z' or 'z' or lower than 'A' or 'a'
	 * @param toCheck original char
	 * @param lowerCase should this char be in lowerCase
	 * @return the char represented in the alphabet
	 */
	private char checkIfCharIsOutOfAlphabet(char toCheck, boolean lowerCase) {	
		char highestPossibleChar, lowestPossibleChar;
		
		if(lowerCase) {
			highestPossibleChar = 'z';
			lowestPossibleChar = 'a';
		} else {
			highestPossibleChar = 'Z';
			lowestPossibleChar = 'A';
		}
		
		while(toCheck > highestPossibleChar) {
			toCheck -= COUNT_ALPHABET;
		}
		
		while(toCheck < lowestPossibleChar) {
			toCheck += COUNT_ALPHABET;
		}
		
		return toCheck;
		
	}

}
