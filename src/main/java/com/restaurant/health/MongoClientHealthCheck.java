package com.restaurant.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoClientHealthCheck extends HealthCheck {
	private DB db;

	public MongoClientHealthCheck(DB db) {
		super();
		this.db = db;
	}
	
	@Override
	protected Result check() throws Exception {
		DBCollection collection = db.getCollection("ratings");
		DBObject dbObject = collection.findOne();
		if(dbObject != null) {
			return Result.healthy();
		}
		return Result.unhealthy("cannot query restaurant health database");
	}

}
