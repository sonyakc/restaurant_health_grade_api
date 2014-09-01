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
import com.restaurant.api.Cuisine;

public class CuisineDaoTest {

	private CuisineDao dao;
	@Mock private DB db;
	@Mock private DBCollection collection;
	@Mock private DBCursor dbCursor;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		dao = new CuisineDao(db);
		dao.collection = collection;
		Mockito.when(db.getCollection(Mockito.eq("cuisinecode"))).thenReturn(collection);
	}

	@Test
	public void successfullyQueryForCuisineByIdentifier() {
		DBObject dbObject = new BasicDBObject("codedesc", "Mediterranean");
		dbObject.put("_id", "123");
		Mockito.when(collection.findOne(Mockito.any(DBObject.class), Mockito.any(DBObject.class))).thenReturn(dbObject);
		
		Cuisine cuisine = dao.findByCuisineIdentifier(1);
		
		assertThat(cuisine).labeled("cuisine").isNotNull();
		assertThat(cuisine.getCuisineCode()).labeled("cuisinecode").isEqualTo(1);
		assertThat(cuisine.getCuisineDesc()).labeled("cuisinedesc").isEqualTo("Mediterranean");
		assertThat(cuisine.getId()).labeled("id").isEqualTo("123");
	}
	
	@Test
	public void successfullyQueryForAllCuisines() {
		DBObject dbObject = new BasicDBObject("codedesc", "Mediterranean");
		dbObject.put("_id", "123");
		dbObject.put("cuisinecode", "1");
		Mockito.when(dbCursor.hasNext()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		Mockito.when(dbCursor.next()).thenReturn(dbObject);
		Mockito.when(dbCursor.maxTime(1, TimeUnit.MINUTES)).thenReturn(dbCursor);
		Mockito.doNothing().when(dbCursor).close();
		Mockito.when(collection.find()).thenReturn(dbCursor);
		
		List<Cuisine> allCuisines = dao.getAllCuisines();
		
		Mockito.verify(dbCursor).close();
		Mockito.verify(dbCursor).maxTime(1, TimeUnit.MINUTES);
		Mockito.verify(collection).find();
		assertThat(allCuisines).labeled("cuisines").isNotEmpty();
		assertThat(allCuisines.get(0).getId()).isEqualTo("123");
		assertThat(allCuisines.get(0).getCuisineCode()).isEqualTo(1);
		assertThat(allCuisines.get(0).getCuisineDesc()).isEqualTo("Mediterranean");
	}
}
