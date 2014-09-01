package com.restaurant;

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerResource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.restaurant.health.TemplateHealthCheck;
import com.restaurant.resources.RestaurantResource;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;

public class RestaurantApplicationTest {
	@Mock
	private Environment environment;
	@Mock
	private JerseyEnvironment jersey;
	private final HealthCheckRegistry healthCheckRegister = new HealthCheckRegistry();
	private final LifecycleEnvironment lifecycleEnvironment = new LifecycleEnvironment();
	private final RestaurantApplication application = new RestaurantApplication();
	private final RestaurantConfiguration config = new RestaurantConfiguration();
	private MongoClientFactory factory = new MongoClientFactory();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		config.setMongoClient(factory);
		config.setDbName("health");
		Mockito.when(environment.jersey()).thenReturn(jersey);
		Mockito.when(environment.healthChecks())
				.thenReturn(healthCheckRegister);
		Mockito.when(environment.lifecycle()).thenReturn(lifecycleEnvironment);
	}

	@Test
	public void runTheRestaurantApplication() throws Exception {
		application.run(config, environment);
		Mockito.verify(environment.jersey(), Mockito.times(2)).register(Mockito.any(RestaurantResource.class));
	}
}
