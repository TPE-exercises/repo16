package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.hsMannheim.ss17.tpe.martinDavid.utilitiies.Dialogs;

public class CaesarFileEncryptor implements IFileEncryptor {
	
	public static void main(String[] params) throws IOException {
		File directory = Dialogs.chooseFolder();
		CaesarFileEncryptor caesar = new CaesarFileEncryptor(3);
		File encrypted = caesar.encrypt(directory);
		
		caesar.decrypt(encrypted);
	}

	private BufferedWriter writer;
	private BufferedReader reader;
	private int key;
	
	public CaesarFileEncryptor(int key) {
		this.key = key;
	}
	
	@Override
	public File encrypt(File sourceDirectory) throws IOException {
	
		if (!sourceDirectory.exists()) {
			throw new FileNotFoundException("source directory does not exists");
		}
		
		File rootEncryped = createRootDirectory(sourceDirectory, true);
		
		goThroughtAllFiles(sourceDirectory, rootEncryped, true);
		
		return rootEncryped;
	}

	@Override
	public File decrypt(File sourceDirectory) throws IOException {
		if (!sourceDirectory.exists()) {
			throw new FileNotFoundException("source directory does not exists");
		}
		
		File rootDecrypted = createRootDirectory(sourceDirectory, false);
		
		goThroughtAllFiles(sourceDirectory, rootDecrypted, false);
		
		return rootDecrypted;
	}

	/**
	 * Creates a directory in the parent folder with postfix "_encrypted" or "_decrypted" 
	 * and an index when the folder already exists
	 * @param sourceDirectory
	 * @param encrypt
	 * @return file to the new created directory
	 */
	private File createRootDirectory(File sourceDirectory, boolean encrypt) throws IOException {
		String newPath = sourceDirectory.getAbsolutePath();
		if(encrypt)
			newPath += "_encrypted";
		else
			newPath += "_decrypted";
		
		int counter = 0;
		File newFile;
		
		do {
			if(counter == 0) {
				newFile = new File(newPath);
			} else {
				//new directory already exists. Append numbers until a folder is found that was not created before
				newFile = new File(newPath + "(" + counter + ")");
			}
			
			counter++;
		} while(newFile.exists());
		
		if(!newFile.mkdirs()) {
			throw new IOException("Could not create directory");
		} else {
			return newFile;
		}
	}
	
	/**
	 * encrypts all files with all subdirectorys and files in it. Only files with the ending ".txt" 
	 * will be encrypted. The others will not be copied to the encrypted / decrypted folder
	 * @param directory
	 * @throws IOException 
	 */
	private void goThroughtAllFiles(File sourceRoot, File destinationRoot, boolean encrypt) throws IOException {
		File[] files = sourceRoot.listFiles();
		for(File file : files) {
			
			File destination = new File(destinationRoot.getAbsolutePath() + "/" + file.getName());
			
			if(file.isDirectory()) {
				//if subdirectory go recursive into it and create the folder in the destinationRoot
				if(!destination.mkdir()) {
					throw new IOException("Failed to create folder: " + destination.getAbsolutePath());
				}
				
				goThroughtAllFiles(file, destination, encrypt);
			} else if(file.isFile()) {
				
				if(getFileEnding(file).equals("txt")) {
					if(encrypt)
						encryptFile(file, destination);
					else
						decryptFile(file, destination);
				}
				
			}
		}
	}
	
	/**
	 * Find the file ending. For example "test.exe" -> "exe" is the file ending
	 * when there is no ending like "noEnding" then the file name is returned
	 * @param file
	 * @return
	 */
	private String getFileEnding(File file) {
		String path = file.getAbsolutePath();
		int indexLastDot = path.lastIndexOf('.');
		
		if(indexLastDot != -1)
			return path.substring(indexLastDot + 1);
		else
			return file.getName();
	}
	
	/**
	 * reads a file and saves the data encrypted into a new file. The file will be created or
	 * overwritten if it already exists
	 * @param sourceFile original file
	 * @param destinationFile 
	 * @throws IOException if an I/O Error occurs
	 */
	private void encryptFile(File sourceFile, File destinationFile) throws IOException {		
		reader = new BufferedReader(new FileReader(sourceFile));
		writer = new BufferedWriter(new CaesarWriter(new FileWriter(destinationFile), key));
		
		copyFile(reader, writer);
	}
	
	/**
	 * reads an encryted file and saves the data decrypted into a new file. The file will be created or
	 * overwritten if it already exists
	 * @param sourceFile original file
	 * @param destinationFile 
	 * @throws IOException if an I/O Error occurs
	 */
	private void decryptFile(File sourceFile, File destinationFile) throws IOException {		
		reader = new BufferedReader(new CaesarReader(new FileReader(sourceFile), key));
		writer = new BufferedWriter(new FileWriter(destinationFile));
		
		copyFile(reader, writer);
	}
	
	/**
	 * creates a copy from one file (reader) and writes it to an other file (writer)
	 * @param reader original file
	 * @param writer destination file
	 * @throws IOException
	 */
	private void copyFile(BufferedReader reader, BufferedWriter writer) throws IOException {
		String line;
		while((line = reader.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		
		writer.flush();
		writer.close();
		reader.close();
	}
	
}
