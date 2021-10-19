package com.karl.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ScannerUtils {
	public Scanner initialisiereScanner(Scanner sc, File f) {
		try {
			sc = new Scanner(f);
		}catch(Exception e) {
			System.out.println("Fehler bei der Scanner Initialisierung.");
			e.printStackTrace();
			System.exit(1);
		}
		return sc;
	}
	
	public void ladeCode(ArrayList<String> daten, Scanner sc) {
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			daten.add(s);
		}
	}
}
