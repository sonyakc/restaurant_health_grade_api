package com.restaurant.resources;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.restaurant.api.Restaurant;
import com.restaurant.dao.RestaurantDao;
import com.sun.jersey.api.NotFoundException;

@Path("restaurants/grades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantResource.class);
	private final RestaurantDao restaurantDao;

	public RestaurantResource(RestaurantDao restaurantDao) {
		super();
		this.restaurantDao = restaurantDao;
	}
	
	@GET
	@Timed
	public List<Restaurant> query(@QueryParam("name") Optional<String> name, 
			@QueryParam("zip") Optional<Integer> zipcode, 
			@QueryParam("rating") Optional<String> rating) {
		LOGGER.info("Querying for restaurant inspection grade data");
		List<Restaurant> restaurants = restaurantDao.queryForGrades(name.orNull(), zipcode.orNull(), rating.orNull());
		if(restaurants == null || restaurants.isEmpty()) 
		{
			throw new NotFoundException("No restaurants found for provided search, please submit "
					+ "updated search criteria");
		}
		return restaurants;
	}
	
	@GET
	@Path("/{camis}")
	public Restaurant getRestaurant(@PathParam("camis") Optional<Long> camis) {
		LOGGER.info("Querying for specific restaurant with camis={}", camis.orNull());
		return null;
	}
}
