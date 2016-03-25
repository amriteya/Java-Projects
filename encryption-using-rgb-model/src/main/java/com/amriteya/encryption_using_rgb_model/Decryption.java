package com.amriteya.encryption_using_rgb_model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Decryption {

	private static String KEY1 = "5638b7aa57c94fe4dcbc021e";
	private static String KEY2 = "5639c74757c953a7192b185f";
	private static String KEY3 = "5639c74757c953a7192b1860";
	private static String key = "011001110000010011000011010110000011011101111001100000101011100101111001001011001101011000001001010000101001100111010110001001000011";
	private static String cipherText = "ACGGUCCUGUCCAAUUUUAAUUUUCGCCUGCCUUUGUCAACCGUGUUGCCGGGCAGCCACUAGGCG";

	public static void main(String[] args) throws Exception {

		String message = DNATranslation.convertFromRNA(cipherText);

		String messageBinary = DNATranslation.convertFromDNA(message);
		// System.out.println(CompressionEncryption.getXOR(messageBinary,
		// key).length());
		String plainTextBinary = CompressionEncryption.getXOR(messageBinary,
				key) + key;
		// System.out.println(plainTextBinary.length());

		String plainText = decodePlainText(plainTextBinary);
		System.out.println(plainText);
	}

	public static String decodePlainText(String plainTextBinary)
			throws Exception {

		String plainText = null;

		List<Integer> colorList = new ArrayList<Integer>();
		colorList = getColorList();

		if (KEY2 == null) {

			plainText = QueryCharacter.getCharacters(colorList, KEY1);

		} else {
			
			plainText = QueryCharacter.getRepeatedCharacters(colorList,KEY1,KEY2);
			
		}
		// TODO Auto-generated method stub
		return plainText;
	}

	public static List<Integer> getColorList() throws Exception {

		List<Integer> colorList = new ArrayList<Integer>();

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(KEY3));
		DBObject dbObj = DatabaseConnection.getDatabase()
				.getCollection("compressedColors").findOne(query);

		Set<String> set = dbObj.keySet();

		for (int i = 1; i <= set.size() - 1; i++) {
			colorList.add((Integer) dbObj.get(String.valueOf(i)));
		}

		return colorList;
	}
}
