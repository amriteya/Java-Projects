package com.amriteya.encryption_using_rgb_model;

import java.awt.Color;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

import static java.util.Arrays.asList;

public class KeySet {
	public static Boolean repetitionCheck(String plainText) {

		Boolean repetition = false;
		char[] text = plainText.toCharArray();

		for (int c = 0; c < plainText.length() - 1; c++) {
			if (text[c] == text[c + 1]) {
				repetition = true;
				break;
			}
		}

		return repetition;
	}

	public String setFirstKey() throws Exception {

		DBCollection collection = DatabaseConnection.getDatabase()
				.getCollection("colorModel");

		String key1 = null;
		// TODO Auto-generated method stub

		long count = collection.count();
		// System.out.println(count);

		DBCursor dbCursor = collection.find()
				.skip((int) (Math.random() * count)).limit(1);

		try {
			while (dbCursor.hasNext()) {
				key1 = dbCursor.next().get("_id").toString();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return key1;
	}

	public String setSecondKey(String plainText, String KEY1) throws Exception {

		DBCollection collection = DatabaseConnection.getDatabase()
				.getCollection("colorModel");

		char[] text = plainText.toCharArray();

		DBObject mainDocument;
		DBObject subDocument = null;

		List<DBObject> documentList = new ArrayList<DBObject>();

		if (repetitionCheck(plainText)) {
			for (int i = 0; i < text.length - 1; i++) {
				if (text[i] == text[i + 1]) {

					subDocument = new BasicDBObject();

					subDocument.put("character", text[i]);
					subDocument.put("location", i + 1);
					subDocument.put("1",
							QueryColor.getColorList(plainText, KEY1).get(0));

					int R = (int) (Math.random() * 256);
					int G = (int) (Math.random() * 256);
					int B = (int) (Math.random() * 256);
					Color color = new Color(R, G, B);

					subDocument.put(String.valueOf(2), color.getRGB());

					documentList.add(subDocument);

				}
			}

			mainDocument = new BasicDBObject("data", documentList);

			DatabaseConnection.getDatabase().getCollection("repetitiveColors")
					.insert(mainDocument);

			return mainDocument.get("_id").toString();
		} else
			return null;
	}

	public String setThirdKey(String plainText, String KEY1, String KEY2)
			throws Exception {

		DBObject mainDocument = new BasicDBObject();
		ArrayList<Integer> colorList = null;

		char[] text = plainText.toCharArray();

		if (!repetitionCheck(plainText)) {

			colorList = QueryColor.getColorList(plainText, KEY1);
			for (int i = 0; i < colorList.size(); i++) {
				mainDocument.put(String.valueOf(i + 1), colorList.get(i));
			}
		} else {
			for (int i = 0; i < text.length - 1; i++) {
				if (text[i] != text[i + 1]) {
					int color = QueryColor.getColorList(
							plainText.substring(i, i + 1), KEY1).get(0);
					mainDocument.put(String.valueOf(i + 1), color);
				} else {
					int color = QueryColor.getColorList(
							plainText.substring(i, i + 1), KEY1).get(0);
					mainDocument.put(String.valueOf(i + 1), color);

					int repeatedColor = QueryColor.getRepeatedColor(KEY2,
							text[i + 1], i + 1);
					mainDocument.put(String.valueOf(i + 2), repeatedColor);
					i++;
					// System.out.println(repeatedColor);
				}
			}

			if (text[text.length - 1] != text[text.length - 2]) {
				int color = QueryColor.getColorList(
						plainText.substring(text.length - 1), KEY1).get(0);
				mainDocument.put(String.valueOf(text.length), color);
			}
		}

		DatabaseConnection.getDatabase().getCollection("compressedColors")
				.insert(mainDocument);
		// TODO Auto-generated method stub
		return mainDocument.get("_id").toString();
	}
}
