package com.restaurant.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.restaurant.api.Saying;

@Path("saying")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SayingResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;

	public SayingResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Path("/hello")
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		final String value = String.format(template, name.or(defaultName));
		return new Saying(counter.getAndIncrement(), value);
	}
}
