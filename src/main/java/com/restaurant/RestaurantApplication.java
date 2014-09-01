package com.restaurant;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.mongodb.DB;
import com.restaurant.dao.CuisineDao;
import com.restaurant.dao.RestaurantDao;
import com.restaurant.health.MongoClientHealthCheck;
import com.restaurant.health.TemplateHealthCheck;
import com.restaurant.resources.CuisineResource;
import com.restaurant.resources.RestaurantResource;

public class RestaurantApplication extends Application<RestaurantConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApplication.class);
	final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
	
	public static void main(String[] args) throws Exception {
        new RestaurantApplication().run(args);
    }

    @Override
    public String getName() {
        return "restaurant-health";
    }
    
	@Override
	public void initialize(Bootstrap<RestaurantConfiguration> bootstrap) {
		GuiceBundle<RestaurantConfiguration> guiceBundle = GuiceBundle
				.<RestaurantConfiguration> newBuilder()
				.addModule(new RestaurantModule())
				.setConfigClass(RestaurantConfiguration.class).build();

		bootstrap.addBundle(guiceBundle);
		swaggerDropwizard.onInitialize(bootstrap);
	}

	@Override
	public void run(RestaurantConfiguration configuration,
			Environment environment) throws Exception {
		LOGGER.info("Method RestaurantApplication#run called");
		final DB db = configuration.getMongoClientFactory().build(environment);
		final RestaurantResource resource = new RestaurantResource(new RestaurantDao(db));
		environment.jersey().register(resource);
		environment.jersey().register(new CuisineResource(new CuisineDao(db)));
		swaggerDropwizard.onRun(configuration, environment);
		
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		final MongoClientHealthCheck mongoHealthCheck = new MongoClientHealthCheck(db);
		environment.healthChecks().register("database", mongoHealthCheck);
		environment.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
}
