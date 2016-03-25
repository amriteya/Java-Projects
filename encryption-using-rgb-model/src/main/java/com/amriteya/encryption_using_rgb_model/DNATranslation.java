package com.amriteya.encryption_using_rgb_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class DNATranslation {

	private static final HashMap<String, Character> DNARepresentationTable;
	static {
		DNARepresentationTable = new HashMap<String, Character>();
		DNARepresentationTable.put("00", 'A');
		DNARepresentationTable.put("01", 'T');
		DNARepresentationTable.put("10", 'G');
		DNARepresentationTable.put("11", 'C');
	}

	private static final HashMap<Character, String[]> alphabeticRepresentation;
	static {
		alphabeticRepresentation = new HashMap<Character, String[]>();
		alphabeticRepresentation.put('A', new String[] { "GCU", "GCC", "GCA",
				"GCG" });
		alphabeticRepresentation.put('B', new String[] { "UAA", "UAG", "UGA" });
		alphabeticRepresentation.put('C', new String[] { "UGU", "UGC" });
		alphabeticRepresentation.put('D', new String[] { "GAU", "GAC" });
		alphabeticRepresentation.put('E', new String[] { "GAA", "GAG" });
		alphabeticRepresentation.put('F', new String[] { "UUC", "UUU" });
		alphabeticRepresentation.put('G', new String[] { "GGU", "GGC", "GGA",
				"GGG" });
		alphabeticRepresentation.put('H', new String[] { "CAU", "CAC" });
		alphabeticRepresentation.put('I', new String[] { "AUU", "AUC", "AUA" });
		alphabeticRepresentation.put('J', new String[] {});
		alphabeticRepresentation.put('K', new String[] { "AAA", "CUC" });
		alphabeticRepresentation.put('L', new String[] { "UUA", "UUG", "CUU",
				"AAG", "CUA", "CUG" });
		alphabeticRepresentation.put('M', new String[] { "AUG" });
		alphabeticRepresentation.put('N', new String[] { "AAU", "AAC" });
		alphabeticRepresentation.put('O', new String[] { "UUA", "UUG" });
		alphabeticRepresentation.put('P', new String[] { "CCU", "CCC", "CCA",
				"CCG" });
		alphabeticRepresentation.put('Q', new String[] { "CAA", "CAG" });
		alphabeticRepresentation.put('R', new String[] { "CGU", "CGC", "CGA",
				"CGG", "AGA", "AGG" });
		alphabeticRepresentation.put('S', new String[] { "UCU", "UCC", "UCA",
				"UCG", "AGU", "AGC" });
		alphabeticRepresentation.put('T', new String[] { "ACU", "ACC", "ACA",
				"ACG" });
		alphabeticRepresentation.put('U', new String[] { "AGA", "AGG" });
		alphabeticRepresentation.put('V', new String[] { "GUU", "GUC", "GUA",
				"GUG" });
		alphabeticRepresentation.put('W', new String[] { "GUU", "GUC", "GUA",
				"GUG" });
		alphabeticRepresentation.put('X', new String[] { "AGU", "AGC" });
		alphabeticRepresentation.put('Y', new String[] { "UAU", "UAC" });
		alphabeticRepresentation.put('Z', new String[] { "UAC" });
	}

	public static String convertToDNA(String message) {

		StringBuffer dnaCodons = new StringBuffer();

		for (int i = 0; i < message.length(); i += 2) {
			dnaCodons.append(DNARepresentationTable.get(message.substring(i,
					i + 2)));
		}
		// TODO Auto-generated method stub
		return dnaCodons.toString();
	}

	public static String convertTomRNA(String message) {

		char[] messageArray = message.toCharArray();

		for (int i = 0; i < messageArray.length; i++) {
			if (messageArray[i] == 'T')
				messageArray[i] = 'U';
		}
		// TODO Auto-generated method stub
		return new String(messageArray);
	}

	public static String getFinalCipherText(String message) {

		StringBuffer finalCipherText = new StringBuffer();

		for (int i = 0; i < message.length() - 2; i += 3) {
			Boolean flag = false;
			for (char count = 'A'; count <= 'Z'; count++) {
				String[] temp = alphabeticRepresentation.get(count);
				for (String match : temp) {
					if (match.equals(message.substring(i, i + 3))) {
						finalCipherText.append((char) count);
						flag = true;
						break;
					}
				}
				if (flag)
					break;
			}
		}
		// TODO Auto-generated method stub
		return finalCipherText.toString();
	}

	public static String convertFromRNA(String cipherText) {

		char[] messageArray = cipherText.toCharArray();

		for (int i = 0; i < messageArray.length; i++) {
			if (messageArray[i] == 'U')
				messageArray[i] = 'T';
		}
		// TODO Auto-generated method stub
		return new String(messageArray);
	}
	
	public static String getKeyByValue(Character value) {
	    for (Entry<String, Character> entry : DNARepresentationTable.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	public static String convertFromDNA(String message) {
		
		char[] messageArray = message.toCharArray();
		StringBuilder binaryRep = new StringBuilder("1");
		
		for(int i=0;i<messageArray.length;i++){
				binaryRep.append(getKeyByValue(messageArray[i]));
		}
		// TODO Auto-generated method stub
		return binaryRep.toString().substring(1);
	}

}
