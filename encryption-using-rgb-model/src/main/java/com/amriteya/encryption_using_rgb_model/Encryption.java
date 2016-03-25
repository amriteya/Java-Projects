package com.amriteya.encryption_using_rgb_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Encryption {
	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));
	private static String KEY1;
	private static String KEY2;
	private static String KEY3;

	public static void main(String[] args) throws Exception {
		// Input in Java is natively in UTF-16 format

		// Entering plaintext
		String plainText = in.readLine();

		KeySet keySet = new KeySet();

		/*ColorModel colorModel = new ColorModel();
		colorModel.generateColorModel();*/

		KEY1 = keySet.setFirstKey();
		System.out.println("KEY1:"+KEY1);

		KEY2 = keySet.setSecondKey(plainText, KEY1);
		System.out.println("KEY2:"+KEY2);

		KEY3 = keySet.setThirdKey(plainText, KEY1, KEY2);
		System.out.println("KEY3:"+KEY3);

		String binaryEquivalent = CompressionEncryption.getCompressedPlainText(
				plainText, KEY3);
		// System.out.print(binaryEquivalent);

		// System.out.println(binaryEquivalent);

		int length = binaryEquivalent.length();
		String key = binaryEquivalent.substring(length / 2, length);
		System.out.println("key: "+key);

		String message = binaryEquivalent.substring(0, length / 2);

		// System.out.println(key + "\n" + message);

		message = CompressionEncryption.getXOR(message, key);

		//System.out.println(message);

		message = DNATranslation.convertToDNA(message);
		//System.out.println(message);

		message = DNATranslation.convertTomRNA(message);
		System.out.println("RNA Cipher Text:"+message);

		String cipherText = DNATranslation.getFinalCipherText(message);
		//System.out.println(cipherText);
	}
}
