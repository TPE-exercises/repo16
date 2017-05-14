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
		int returnValue = in.read(cbuf, off, len);
		
		if(returnValue != -1 ) {
			for(int i = off; i < off + len; i++) {
				if(cbuf.length > i) {
					cbuf[i] = crypter.decrypt(cbuf[i]);
				}
			}
		}
		
		return returnValue;
	}
	

}
