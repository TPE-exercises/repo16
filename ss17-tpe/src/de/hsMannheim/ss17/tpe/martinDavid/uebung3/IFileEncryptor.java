package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import java.io.File;

public interface IFileEncryptor {

	/**
	 * Encrypts a folder and all subfolders. In the parent folder a folder 
	 * with the same name + "_encrpyted" will be created. If the folder already exists a
	 * new folder with the same name + "_encrypted(number)" will be created
	 * @param sourceDirectory 
	 * @return File to the encrypted directory
	 */
	public File encrypt(File sourceDirectory);
	
	/**
	 * Decrypts a folder and all subfolders. In the parent folder a folder 
	 * with the same name + "_decrypted" will be created. If the folder already exists a
	 * new folder with the same name + "_decrypted(number)" will be created
	 * @param sourceDirectory 
	 * @return File to the decrypted directory
	 */
	public File decrypt(File sourceDirectory);
	
}
