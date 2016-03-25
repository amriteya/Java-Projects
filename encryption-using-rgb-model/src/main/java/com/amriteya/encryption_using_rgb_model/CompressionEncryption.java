package com.amriteya.encryption_using_rgb_model;

import java.awt.Color;
import java.math.BigInteger;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CompressionEncryption {

	public static String getCompressedPlainText(String plainText, String KEY3)
			throws Exception {

		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(KEY3));
		DBObject dbObj = DatabaseConnection.getDatabase()
				.getCollection("compressedColors").findOne(query);

		int length = plainText.length();

		StringBuffer hexRepresentation = new StringBuffer("1");

		if (length % 2 == 0) {
			for (int i = 0; i < length; i += 2) {

				// System.out.println((Integer) dbObj.get(String.valueOf(i+1)));
				Color c1 = new Color((Integer) dbObj.get(String.valueOf(i + 1)));
				Color c2 = new Color((Integer) dbObj.get(String.valueOf(i + 2)));

				int red = (c1.getRed() + c2.getRed()) / 2;
				int green = (c1.getGreen() + c2.getGreen()) / 2;
				int blue = (c1.getBlue() + c2.getBlue()) / 2;

				hexRepresentation.append(Integer.toHexString(red)
						+ Integer.toHexString(green)
						+ Integer.toHexString(blue));

				/*
				 * System.out.println(Integer.toHexString(red));
				 * System.out.println(Integer.toHexString(green));
				 * System.out.println(Integer.toHexString(blue));
				 */
			}
		} else {
			for (int i = 0; i < length - 1; i += 2) {

				// System.out.println((Integer) dbObj.get(String.valueOf(i+1)));
				Color c1 = new Color((Integer) dbObj.get(String.valueOf(i + 1)));
				Color c2 = new Color((Integer) dbObj.get(String.valueOf(i + 2)));

				int red = (c1.getRed() + c2.getRed()) / 2;
				int green = (c1.getGreen() + c2.getGreen()) / 2;
				int blue = (c1.getBlue() + c2.getBlue()) / 2;

				hexRepresentation.append(Integer.toHexString(red)
						+ Integer.toHexString(green)
						+ Integer.toHexString(blue));

				/*
				 * System.out.println(Integer.toHexString(red));
				 * System.out.println(Integer.toHexString(green));
				 * System.out.println(Integer.toHexString(blue));
				 */
			}

			Color c = new Color((Integer) dbObj.get(String.valueOf(length)));

			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			
			hexRepresentation.append(Integer.toHexString(red)
					+ Integer.toHexString(green)
					+ Integer.toHexString(blue));

		}

		// System.out.println(hexRepresentation);
		// TODO Auto-generated method stub
		return new BigInteger(hexRepresentation.toString(), 16).toString(2)
				.substring(1);
	}

	public static String getXOR(String message, String key) {

		StringBuilder xorValue = new StringBuilder("1");

		char[] m = message.toCharArray();
		char[] k = key.toCharArray();

		for (int i = 0; i < m.length; i++) {
			if (m[i] == k[i])
				m[i] = '0';
			else
				m[i] = '1';
		}

		return xorValue.append(m).toString().substring(1);
	}

}
