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

	/*
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int returnValue = in.read(cbuf, off, len);
		
		if(returnValue != -1 ) {
			String decrypted = crypter.decrypt(new String(cbuf, off, returnValue));
			cbuf = decrypted.toCharArray();
		}
		
		return returnValue;
	}*/
	
	
	private String str = "";
	private int pos = 0;
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
	  if (pos == str.length()) {
		  // No leftovers from a previous call available, need to actually read more
		  int result = in.read(cbuf, off, len);
		  if( result <= 0 ){
			  return -1;
		  }
		  str = new String(cbuf, off, result);
		  str = crypter.decrypt(str);
		  pos = 0;
	  }

	  // Return as much as we have available, but not more than len
	  int available = Math.min(str.length() - pos, len);     
	  str.getChars(pos, pos + available, cbuf, off);
	  pos += available;
	  return available;
	}
	

}
