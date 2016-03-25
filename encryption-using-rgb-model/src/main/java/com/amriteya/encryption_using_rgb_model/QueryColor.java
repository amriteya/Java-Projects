package com.amriteya.encryption_using_rgb_model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class QueryColor {

	public static ArrayList<Integer> getColorList(String plainText, String KEY1)
			throws Exception {
		
		ArrayList<Integer> colorList = new ArrayList<Integer>();
		
		DBCollection collection = DatabaseConnection.getDatabase()
				.getCollection("colorModel");

		char[] text = plainText.toCharArray();

		for (int i = 0; i < text.length; i++) {

			DBObject clause1 = new BasicDBObject("_id", new ObjectId(KEY1));
			DBObject clause2 = null;
		
			clause2 = new BasicDBObject("data.character",
					String.valueOf(text[i]));

			BasicDBList and = new BasicDBList();
			and.add(clause1);
			and.add(clause2);
			DBObject match = new BasicDBObject("$match", new BasicDBObject(
					"$and", and));
			DBObject unwind = new BasicDBObject("$unwind", "$data");
			DBObject project = new BasicDBObject("$project", new BasicDBObject(
					"data.color", 1).append("_id", 0));
			List<DBObject> pipeline = new ArrayList<DBObject>();

			pipeline.add(unwind);
			pipeline.add(match);
			pipeline.add(project);

			Iterable<DBObject> iterable = collection.aggregate(pipeline)
					.results();

			for (final DBObject result : iterable) {

				colorList.add((Integer) ((DBObject) result.get("data")).get("color"));
			}

		}

		return colorList;
	}

	public static int getRepeatedColor(String KEY2, char c, int location) throws Exception{
		
		DBCollection collection = DatabaseConnection.getDatabase()
				.getCollection("repetitiveColors");
		
		DBObject clause1 = new BasicDBObject("_id", new ObjectId(KEY2));
		DBObject clause2 = null;

		clause2 = new BasicDBObject("data.character",
				String.valueOf(c));
		
		DBObject clause3 = new BasicDBObject("data.location",location);

		BasicDBList and = new BasicDBList();
		and.add(clause1);
		and.add(clause2);
		and.add(clause3);
		DBObject match = new BasicDBObject("$match", new BasicDBObject(
				"$and", and));
		DBObject unwind = new BasicDBObject("$unwind", "$data");
		DBObject project = new BasicDBObject("$project", new BasicDBObject(
				"data.2", 1).append("_id", 0));
		List<DBObject> pipeline = new ArrayList<DBObject>();

		pipeline.add(unwind);
		pipeline.add(match);
		pipeline.add(project);

		Iterable<DBObject> iterable = collection.aggregate(pipeline)
				.results();
		
		int color = -1;
		
		for (final DBObject result : iterable) {
			
			color = (Integer) ((DBObject) result.get("data")).get("2");
		}

		// TODO Auto-generated method stub
		return color;
	}
}
