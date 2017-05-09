package de.hsMannheim.ss17.tpe.martinDavid.utilitiies;

import java.io.File;

import javax.swing.JFileChooser;

public class Dialogs {
	
	public static File chooseFolder() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(null);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    return fc.getSelectedFile();
		}
		
		return null;
	}
}
