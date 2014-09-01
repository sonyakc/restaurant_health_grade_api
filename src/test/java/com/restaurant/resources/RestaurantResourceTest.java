package com.restaurant.resources;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.restaurant.api.Restaurant;
import com.restaurant.dao.RestaurantDao;
import com.sun.jersey.api.NotFoundException;

import static com.google.common.truth.Truth.*;

public class RestaurantResourceTest {

	@Mock private RestaurantDao dao; 
	private RestaurantResource resource;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		resource = new RestaurantResource(dao);
	}
	
	@Test
	public void successfullyQueryResultsForRestaurantData()
	{
		Optional<String> name =  Optional.of("Hummus Kitchen");
		Optional<Integer> zipcode = Optional.of(10003);
		Optional<String> rating = Optional.absent();
		List<Restaurant> restaurants = ImmutableList.of(new Restaurant());
		Mockito.when(dao.queryForGrades(name.orNull(), zipcode.orNull(), rating.orNull())).thenReturn(restaurants);
		
		List<Restaurant> resultset = resource.searchRestaurantHealthData(name, zipcode, rating);
		
		assertThat(resultset).labeled("query resultset").isEqualTo(restaurants);
	}
	
	@Test(expected = NotFoundException.class)
	public void queryReturnsEmptyResultSet()
	{
		Optional<String> name =  Optional.of("Hummus Kitchen");
		Optional<Integer> zipcode = Optional.of(10003);
		Optional<String> rating = Optional.absent();
		List<Restaurant> restaurants = ImmutableList.of();
		Mockito.when(dao.queryForGrades(name.orNull(), zipcode.orNull(), rating.orNull())).thenReturn(restaurants);
		
		resource.searchRestaurantHealthData(name, zipcode, rating);		
	}
	
	@Test(expected = InvalidRequestException.class)
	public void missingUniqueIdentifierSingleRestaurantQuery() throws InvalidRequestException {
		resource.getRestaurant(null);
	}
}
