package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import java.io.File;
import java.io.IOException;

public interface IFileEncryptor {

	/**
	 * Encrypts a folder and all subfolders. In the parent folder a folder 
	 * with the same name + "_encrpyted" will be created. If the folder already exists a
	 * new folder with the same name + "_encrypted(number)" will be created
	 * @param sourceDirectory 
	 * @return File to the encrypted directory
	 * @throws IOException 
	 */
	public File encrypt(File sourceDirectory) throws IOException;
	
	/**
	 * Decrypts a folder and all subfolders. In the parent folder a folder 
	 * with the same name + "_decrypted" will be created. If the folder already exists a
	 * new folder with the same name + "_decrypted(number)" will be created
	 * @param sourceDirectory 
	 * @return File to the decrypted directory
	 * @throws IOException
	 */
	public File decrypt(File sourceDirectory) throws IOException;
	
}
