package com.karl.utils;

import java.util.ArrayList;

public class AuswertungsUtils {
	private ArrayList<String> ausgewerteteDaten = new ArrayList<>();
	private ArrayList<String> daten = new ArrayList<>();
	private String seperator = ";";
	private String fileName = "";

	public AuswertungsUtils(ArrayList<String> daten) {
		this.daten = daten;
	}

	public ArrayList<String> werteDatenAus(String fileName) {

		this.fileName = fileName;
		werteSolarPanelAus();
		werteArithmeticsAus();
		werteAstroAus();
		werteRepnumbersAus();
		System.out.println("AUSWERTUNG IN :...............");
		for (String s : ausgewerteteDaten) {
			System.out.println(s);
		}
		return ausgewerteteDaten;
	}

	public ArrayList<String> getAusgewerteteDaten() {
		return ausgewerteteDaten;
	}

	private void werteSolarPanelAus() {
		boolean inSolarPanel = false;
		String s = "";
		String date = "";
		String exercise = "";
		String suggested = "";
		String result = "";
		
		for (String line : daten) {
			if (line.contains("[SOLAR")) {
				inSolarPanel = true;
				if (s.length() >= 35) {
					ausgewerteteDaten.add(s);
					s = fileName + seperator;
				}
				continue;
			}
			if (inSolarPanel) {
				if(line.contains("Date")) {
					date = line.substring(line.indexOf("=") + 1, line.length());
				}else if(line.contains("Exercise")) {
					line.substring(line.indexOf("=") + 1, line.length());
				}else if(line.contains("Suggested")) {
					line.substring(line.indexOf("=") + 1, line.length());
				}else if(line.contains("Result")) {
					line.substring(line.indexOf("=") + 1, line.length());
				}
				if (line.contains("[ARITHMETICS") || line.contains("[ASTRO") || line.contains("[REPNUMBERS")) {
					s = fileName + seperator + date + seperator + seperator + exercise + seperator + suggested + seperator + result;
					if (s.length() >= 35) {
						System.out.println("Adding s");
						ausgewerteteDaten.add(s);
						s = "";
					}
					return;
				}
			}
		}
	}

	private void werteArithmeticsAus() {
		boolean inArithmeticsPanel = false;
		String s = fileName + seperator;
		String appendResult = "";
		for (String line : daten) {
			if (line.contains("[ARITHMETICS")) {
				inArithmeticsPanel = true;
				if (s.length() >= 35) {
					s += appendResult;
					ausgewerteteDaten.add(s);
					s = fileName + seperator;
					appendResult = "";
				}
				continue;
			}
			if (inArithmeticsPanel) {
				if (line.contains("Date") || line.contains("Task") || line.contains("Expected")
						|| line.contains("Command")) {
					s += line.substring(line.indexOf("=") + 1, line.length());
					s += seperator;
				}
				if (line.contains("Result")) {
					appendResult += seperator;
					appendResult += line.substring(line.indexOf("=") + 1, line.length());
				}
				if (line.contains("[ASTRO") || line.contains("[REPNUMBERS")) {
					if (!s.isBlank()) {
						System.out.println("Adding s");
						ausgewerteteDaten.add(s);
						s = fileName + seperator;
					}
					return;
				}
			}
		}
	}

	private void addDaten(String s) {
		if (!s.isBlank()) {
			System.out.println("Adding s");
			ausgewerteteDaten.add(s);
			s = "";
		}
	}

	private void werteAstroAus() {
		boolean inAstroPanel = false;
		String s = fileName + seperator;
		for (String line : daten) {
			if (line.contains("[ASTRO")) {
				inAstroPanel = true;
				if (s.length() >= 35) {
					ausgewerteteDaten.add(s);
					s = fileName + seperator;
				}
				continue;
			}
			if (inAstroPanel) {
				if (line.contains("Date") || line.contains("Symbol") || line.contains("Suggested")
						|| line.contains("Result")) {
					s += line.substring(line.indexOf("=") + 1, line.length());
					s += seperator;
				}
				if (line.contains("[ASTRO")) {
					ausgewerteteDaten.add(s);
					s = fileName + seperator;
				}
				if (line.contains("[REPNUMBERS")) {
					if (!s.isBlank()) {
						System.out.println("Adding s");
						ausgewerteteDaten.add(s);
						s = fileName + seperator;
					}
					return;
				}
			}
		}
	}

	private void werteRepnumbersAus() {
		boolean inRepnumbersPanel = false;
		String s = fileName + seperator;
		String appendResult = "";
		for (String line : daten) {
			if (line.contains("[REPNUMBERS")) {
				inRepnumbersPanel = true;
				if (appendResult.isBlank()) {
					s += "99" + seperator;
				}
				
				if (s.length() >= 35) {
					s += appendResult;
					ausgewerteteDaten.add(s);
					s = fileName + seperator;
					appendResult = "";
				}
				continue;
			}
			if (inRepnumbersPanel) {
				if (line.contains("Date") || line.contains("Transponder") || line.contains("Recognized")
						|| line.contains("Result")) {
					s += line.substring(line.indexOf("=") + 1, line.length());
					s += seperator;
				}
				if (line.contains("[REPNUMBERS")) {
					ausgewerteteDaten.add(s);
					s = fileName + seperator;
				}
				if (line.contains("Result")) {
					appendResult += seperator;
					appendResult += line.substring(line.indexOf("=") + 1, line.length());
				}
				if (line.contains("[MANOMETER")) {
					if (!s.isBlank()) {
						System.out.println("Adding s");
						ausgewerteteDaten.add(s);
						s = fileName + seperator;
					}
					return;
				}
			}
		}
	}
}
