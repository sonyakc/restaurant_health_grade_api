package com.restaurant.resources;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRequestExceptionMapper implements
		ExceptionMapper<InvalidRequestException> {

	@Override
	public Response toResponse(InvalidRequestException exception) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(exception.getMessage())
				.type(MediaType.APPLICATION_JSON).build();
	}

}
