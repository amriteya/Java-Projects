package com.amriteya.encryption_using_rgb_model;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class DatabaseConnection {
	
	public static DB getDatabase() throws Exception{
		
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost",27017));
		
		DB database = mongoClient.getDB("ColorModel");
		return database;
	}

}
