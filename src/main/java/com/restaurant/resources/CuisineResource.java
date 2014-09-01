package com.restaurant.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.restaurant.api.Cuisine;
import com.restaurant.dao.CuisineDao;

@Path("cuisines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CuisineResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CuisineResource.class);
	private final CuisineDao cuisineDao;

	public CuisineResource(CuisineDao cuisineDao) {
		super();
		this.cuisineDao = cuisineDao;
	}

	@GET
	@Path("/{id}: [0-9]")
	@Timed
	public Cuisine findCuisine(@PathParam("id") Integer cuisinecode) {
		LOGGER.info("Querying for cuisine with id=" + cuisinecode);
		Cuisine cuisine = cuisineDao.findByCuisineIdentifier(cuisinecode);
		if (cuisine == null) {
			throw new NotFoundException("cuisine for identifier: "
					+ cuisinecode + " was not found");
		}
		return cuisine;
	}
	
	@GET
	@Timed
	public List<Cuisine> getAllCuisines() {
		LOGGER.info("Querying for all cuisines");
		List<Cuisine> allCuisines = cuisineDao.getAllCuisines();
		if(allCuisines == null || allCuisines.isEmpty()) {
			throw new NotFoundException("no cuisines not found");
		}
		return allCuisines;
	}
}
