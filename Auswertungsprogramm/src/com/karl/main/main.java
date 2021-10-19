package com.karl.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.karl.utils.AuswertungsUtils;
import com.karl.utils.CsvUtils;
import com.karl.utils.FileUtils;
import com.karl.utils.PrintUtils;
import com.karl.utils.ScannerUtils;

public class main {
	public static ArrayList<String> daten = new ArrayList<>();
	private static ArrayList<String> ausgewerteteDaten = new ArrayList<>();
	
	public static void main(String[] args) {
		CsvUtils csvUtil = new CsvUtils();
		
		//csvUtil.writeCsvFile("TestDokument.csv");
		iniToCsv();
		
		
	}
	
	
	public static void iniToCsv() {
		FileUtils fileUtil = new FileUtils();
		ScannerUtils scannerUtil = new ScannerUtils();
		PrintUtils printUtil = new PrintUtils();
		AuswertungsUtils auswertungUtil = new AuswertungsUtils(daten);
		
		Scanner sc = null;
		File file = null;
		
		// user chooses first ini file
		try {
			file = fileUtil.chooseFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		sc = scannerUtil.initialisiereScanner(sc, file);
		scannerUtil.ladeCode(daten, sc);
		printUtil.printDatenBlatt(daten);
		System.out.println("NEUER ABSCHNITT");
		ausgewerteteDaten = auswertungUtil.werteDatenAus(file.getName());
		System.out.println("AUSWERTUNG----------------");
		for(String s: ausgewerteteDaten) {
			System.out.println(s);
		}
		//printUtil.printDatenBlatt(ausgewerteteDaten);
	}
}
