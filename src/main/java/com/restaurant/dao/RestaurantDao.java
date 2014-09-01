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
import com.restaurant.api.Borough;
import com.restaurant.api.Restaurant;
import com.restaurant.api.RestaurantBuilder;

public class RestaurantDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantDao.class);
	DBCollection collection;

	public RestaurantDao(DB db) {
		super();
		this.collection = db.getCollection("ratings");
	}

	/**
	 * Query for grades using provided search criteria
	 * 
	 * @param name
	 * @param zipcode
	 * @param grade
	 * @return
	 */
	public List<Restaurant> queryForGrades(String name, Integer zipcode,
			String grade) {
		DBObject selection = new BasicDBObject();
		if (name != null) {
			selection.put("dba", name);
		}
		if (zipcode != null) {
			selection.put("zip", String.valueOf(zipcode));
		}
		if (grade != null) {
			selection.put("currentgrade", grade);
		}
		LOGGER.info("restaurant query submitted: {}", selection.toString());
		DBCursor cursor = collection.find(selection).maxTime(1,
				TimeUnit.MINUTES);
		if (cursor != null) {
			LOGGER.debug("Query returned {} restaurant records", cursor.count());
		}
		List<Restaurant> restaurants = Lists.newArrayList();
		try {
			while (cursor.hasNext()) {
				DBObject next = cursor.next();
				RestaurantBuilder builder = new RestaurantBuilder();
				Integer borocode = Integer.valueOf(next.get("boro").toString());
				builder.withBorough(Borough.findBorough(borocode));
				builder.withCamis(Long.valueOf(next.get("camis").toString()));
				builder.withCuisineCode(Integer.valueOf(next.get("cuisinecode")
						.toString()));
				builder.withDba(String.valueOf(next.get("dba")));
				builder.withGrade(next.get("currentgrade").toString());
				builder.withGradeDate(DateParser.parseTimestamp(next.get(
						"gradedate").toString()));
				builder.withScore(Integer.valueOf(next.get("score").toString()));
				builder.withZipCode(Integer.valueOf(next.get("zipcode")
						.toString()));
				builder.withPhoneNumber(String.valueOf(next.get("phone")));
				builder.withId(String.valueOf(next.get("_id")));
				Restaurant restaurant = builder.build();
				restaurants.add(restaurant);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cursor.close();
		}
		return restaurants;
	}
}
