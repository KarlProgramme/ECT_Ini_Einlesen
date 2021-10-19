package com.karl.utils;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;


public class FileUtils {
	public File chooseFile() throws FileNotFoundException {
		File file = null;
		//Dateiauswahlfenster auf derzeitigen Projektordner
		JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));
		int state = fc.showOpenDialog(null);
		if (state == JFileChooser.APPROVE_OPTION) {
		  file = fc.getSelectedFile();
		} else {
		  throw new FileNotFoundException("Auswahl abgebrochen");
		}
		return file;
	}
}
