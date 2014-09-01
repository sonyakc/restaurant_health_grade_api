package com.restaurant.dao;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.restaurant.api.Cuisine;

public class CuisineDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(CuisineDao.class);
	DBCollection collection;
	
	public CuisineDao(DB db) {
		super();
		this.collection = db.getCollection("cuisinecode");
	}
	
	/**
	 * Find cuisine by identifier
	 * 
	 * @param cuisinecode
	 * @return
	 */
	public Cuisine findByCuisineIdentifier(Integer cuisinecode) 
	{
		Cuisine cuisine = null;
		if(cuisinecode != null)
		{
			LOGGER.info("Find cuisine type description for cuisinecode {}");
			DBObject selection = new BasicDBObject();
			selection.put("cuisinecode", cuisinecode);
			DBObject keys = new BasicDBObject(1);
			LOGGER.info("cuisine by id query submitted: {}", selection.toString());
			DBObject dbObject = collection.findOne(selection, keys);
			cuisine = new Cuisine(cuisinecode, String.valueOf(dbObject.get("codedesc")),
					String.valueOf(dbObject.get("_id")));
		}
		return cuisine;
	}
	
	/**
	 * Gets all available cuisines
	 * @return
	 */
	public List<Cuisine> getAllCuisines()
	{
		List<Cuisine> cuisineTypes = Lists.newArrayList();
		DBCursor cursor = collection.find().maxTime(1, TimeUnit.MINUTES);
		try {
			while(cursor.hasNext()) {
				DBObject next = cursor.next();
				Cuisine cuisine = new Cuisine();
				cuisine.setCuisineDesc(String.valueOf(next.get("codedesc")));
				cuisine.setCuisineCode(Integer.valueOf(next.get("cuisinecode").toString()));
				cuisine.setId(next.get("_id").toString());
				cuisineTypes.add(cuisine);
			}
		} finally {
			cursor.close();
		}
		return cuisineTypes;
	}
}
