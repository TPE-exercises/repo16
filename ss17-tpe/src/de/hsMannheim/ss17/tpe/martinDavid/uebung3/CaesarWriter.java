package de.hsMannheim.ss17.tpe.martinDavid.uebung3;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CaesarWriter extends FilterWriter {

	private CrypterCaesar crypter;
	
	protected CaesarWriter(Writer out, int key) {
		super(out);
		crypter = new CrypterCaesar(key);
	}

	@Override
	public void write(int c) throws IOException {
		c = crypter.encrypt((char) c);
		
		super.write(c);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		write(new String(cbuf), off, len);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		String encrpyted = crypter.encrypt(str);
		
		super.write(encrpyted, off, len);
	}
	
	

}
