package com.restaurant.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class NotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 */
	public NotFoundException() {
		super(Responses.notFound().build());
	}

	public NotFoundException(String message) {
		super(Response.status(Responses.NOT_FOUND).entity(message)
				.type(MediaType.APPLICATION_JSON).build());
	}
}
