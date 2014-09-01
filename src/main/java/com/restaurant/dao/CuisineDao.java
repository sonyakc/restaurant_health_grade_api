package com.restaurant.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.restaurant.api.Cuisine;

public class CuisineDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(CuisineDao.class);
	private DB db;
	private DBCollection collection;
	
	public CuisineDao(MongoClient mongoClient, String dbName) {
		super();
		this.db = mongoClient.getDB(dbName);
		this.collection = db.getCollection("cuisinecode");
	}
	
	public Cuisine findByCuisineIdentifier(Optional<Integer> cuisinecode) 
	{
		Cuisine cuisine = null;
		if(cuisinecode.isPresent())
		{
			LOGGER.debug("Find cuisine type description for cuisinecode {}");
			DBObject ref = new BasicDBObject();
			ref.put("cuisinecode", cuisinecode);
			DBObject keys = new BasicDBObject(1);
			DBObject dbObject = collection.findOne(ref, keys);
			cuisine = new Cuisine(cuisinecode.get(), String.valueOf(dbObject.get("codedesc")),
					String.valueOf(dbObject.get("_id")));
		}
		return cuisine;
	}
	
	public List<Cuisine> getAllCuisines()
	{
		List<Cuisine> cuisineTypes = Lists.newArrayList();
		DBCursor dbCursor = collection.find();
		try {
			while(dbCursor.hasNext()) {
				DBObject next = dbCursor.next();
				Cuisine cuisine = new Cuisine();
				cuisine.setCuisineDesc(String.valueOf(next.get("codedesc")));
				cuisine.setCuisineCode(Integer.valueOf(next.get("cuisinecode").toString()));
				cuisineTypes.add(cuisine);
			}
		} finally {
			dbCursor.close();
		}
		return cuisineTypes;
	}
}
