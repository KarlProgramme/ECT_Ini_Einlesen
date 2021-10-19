package com.karl.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvUtils {
	public void writeCsvFile(String fileName) {
		try(PrintWriter pw = new PrintWriter(fileName)){
			
			pw.println("SystemName;KÖPFE;REAKTION;SCHNELLIGKEIT");
			pw.println("SE012;;5;3");
			
			System.out.println("Finished writing to file");
			
		} catch(FileNotFoundException e) {
			System.out.println("File Name wurde in WriteCsvFile nicht gefunden");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void writeHeader() {
		
	}
}
