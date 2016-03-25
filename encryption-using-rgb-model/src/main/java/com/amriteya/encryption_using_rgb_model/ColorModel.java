package com.amriteya.encryption_using_rgb_model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class ColorModel {
	
	MongoClient mongoClient;
	
	private static int unicodeStart = 0x0020; //Space
	private static int unicodeEnd = 0x007E; //~
	
	public void generateColorModel() throws Exception{
		
		DBCollection collection = DatabaseConnection.getDatabase().getCollection("colorModel");
		ArrayList<Color> colorsList = new ArrayList<Color>();
		int count = 1;
		
		DBObject mainDocument;
		DBObject subDocument = null;
		
		List<DBObject> documentList = new ArrayList<DBObject>();
		
		for(int i = unicodeStart;i<=unicodeEnd;i++){
			
			
			
			while(true)
			{	int R = (int)(Math.random()*256);
				int G = (int)(Math.random()*256);
				int B= (int)(Math.random()*256);
				Color color = new Color(R, G, B);
				
				if(!colorsList.contains(color))
				{
					
					subDocument = new BasicDBObject();
					
					subDocument.put("uid", count);
					subDocument.put("character",(char)i);
					subDocument.put("unicode",i);
					subDocument.put("color",color.getRGB());
					
					documentList.add(subDocument);
					
					count++;
					break;
				}
			}
			
			
		}
		
		mainDocument = new BasicDBObject("data",documentList);
		
		collection.insert(mainDocument);
		
	}
}
