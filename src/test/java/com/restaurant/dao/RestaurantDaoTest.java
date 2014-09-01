package com.restaurant.dao;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.restaurant.api.Restaurant;

public class RestaurantDaoTest {

	private RestaurantDao dao;
	@Mock private DB db;
	@Mock private DBCollection collection;
	@Mock private DBCursor dbCursor;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		dao = new RestaurantDao(db);
		dao.collection = collection;
		Mockito.when(db.getCollection(Mockito.eq("ratings"))).thenReturn(collection);
	}
	
	@Test
	public void restaurantQueryForGrades() {
		DBObject value = new BasicDBObject("dba", "abc");
		value.put("currentgrade", "A");
		value.put("camis", 41557702);
		value.put("zipcode", 10003);
		value.put("cuisinecode", 1);
		value.put("gradedate", "2014-02-20 00:00:00");
		value.put("boro", 1);
		value.put("score", "8");
		Mockito.when(dbCursor.hasNext()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		Mockito.when(dbCursor.next()).thenReturn(value);
		Mockito.doNothing().when(dbCursor).close();
		Mockito.when(dbCursor.maxTime(1, TimeUnit.MINUTES)).thenReturn(dbCursor);
		Mockito.when(collection.find(Mockito.any(DBObject.class))).thenReturn(dbCursor);
		
		List<Restaurant> restaurants = dao.queryForGrades("restaurant", 10003, "A");
		
		Mockito.verify(dbCursor).close();
		Mockito.verify(dbCursor).maxTime(1, TimeUnit.MINUTES);
		Mockito.verify(collection).find(Mockito.any(DBObject.class));
		assertThat(restaurants).labeled("restaurants").isNotEmpty();
	}
}
