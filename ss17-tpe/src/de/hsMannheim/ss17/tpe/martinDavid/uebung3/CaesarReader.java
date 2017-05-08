package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CaesarReader extends FilterReader {

	private CrypterCaesar crypter;
	
	protected CaesarReader(Reader in, int key) {
		super(in);
		
		crypter = new CrypterCaesar(key);
	}
	
	@Override
	public int read() throws IOException {
		int read = super.read();
		
		if(read == -1)
			return -1;
		
		Character readCharacter = new Character((char) read);
		
		Character decrypted = crypter.decrypt(readCharacter);
		
		return decrypted;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int returnValue = super.read(cbuf, off, len);
		
		String encrypted = crypter.encrypt(new String(cbuf));
		
		cbuf = encrypted.toCharArray();
		
		return returnValue;
	}
	

}
