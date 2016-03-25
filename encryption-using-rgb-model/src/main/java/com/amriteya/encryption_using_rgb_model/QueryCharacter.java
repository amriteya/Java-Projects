package com.amriteya.encryption_using_rgb_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class QueryCharacter {

	private static String KEY1 = null;

	public static String getCharacters(List<Integer> colorList, String key)
			throws Exception {

		KEY1 = key;

		StringBuilder plainText = new StringBuilder();

		for (Integer color : colorList) {
			// System.out.println(color);
			plainText.append(queryCharacter(color));
		}

		// System.out.println(plainText.toString());
		// TODO Auto-generated method stub
		return plainText.toString();
	}

	public static char queryCharacter(int color) throws Exception {

		char character = 0;

		DBObject clause1 = new BasicDBObject("_id", new ObjectId(KEY1));
		DBObject clause2 = null;

		clause2 = new BasicDBObject("data.color", color);

		BasicDBList and = new BasicDBList();
		and.add(clause1);
		and.add(clause2);
		DBObject match = new BasicDBObject("$match", new BasicDBObject("$and",
				and));
		DBObject unwind = new BasicDBObject("$unwind", "$data");
		DBObject project = new BasicDBObject("$project", new BasicDBObject(
				"data.character", 1).append("_id", 0));
		List<DBObject> pipeline = new ArrayList<DBObject>();

		pipeline.add(unwind);
		pipeline.add(match);
		pipeline.add(project);

		Iterable<DBObject> iterable = DatabaseConnection.getDatabase()
				.getCollection("colorModel").aggregate(pipeline).results();

		for (final DBObject result : iterable) {

			character = ((DBObject) result.get("data")).get("character")
					.toString().charAt(0);
		}

		// System.out.println(character);
		return character;
	}

	public static String getRepeatedCharacters(List<Integer> colorList,
			String key, String KEY2) throws Exception {

		StringBuilder plainText = new StringBuilder();
		// TODO Auto-generated method stub
		KEY1 = key;

		// KEY2 = "5638b87f57c9414f06ef4fa3";

		Map<Integer, Character> repetitionTable = new HashMap<Integer, Character>();

		DBObject clause1 = new BasicDBObject("_id", new ObjectId(KEY2));
		// DBObject clause2 = null;

		// clause2 = new BasicDBObject("data.color", color);

		BasicDBList and = new BasicDBList();
		and.add(clause1);
		// and.add(clause2);
		DBObject match = new BasicDBObject("$match", clause1);
		DBObject unwind = new BasicDBObject("$unwind", "$data");
		DBObject project = new BasicDBObject("$project", new BasicDBObject(
				"data.character", 1).append("data.location", 1)
				.append("_id", 0));
		List<DBObject> pipeline = new ArrayList<DBObject>();

		pipeline.add(unwind);
		pipeline.add(match);
		pipeline.add(project);

		Iterable<DBObject> iterable = DatabaseConnection.getDatabase()
				.getCollection("repetitiveColors").aggregate(pipeline)
				.results();

		for (final DBObject result : iterable) {

			repetitionTable.put(
					(Integer) ((DBObject) result.get("data")).get("location"),
					((DBObject) result.get("data")).get("character").toString()
							.charAt(0));
		}

		for (int i = 0; i < colorList.size(); i++) {
			if (repetitionTable.containsKey(i))
				plainText.append(repetitionTable.get(i));
			else
				plainText.append(queryCharacter(colorList.get(i)));
		}

		// System.out.println(plainText.toString());
		return plainText.toString();
	}
}
