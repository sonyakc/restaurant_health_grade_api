package com.restaurant;

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.Environment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantApplicationTest {
	@Mock
	private Environment environment;
	@Mock
	private JerseyEnvironment jersey;
	private final HealthCheckRegistry healthCheckRegister = new HealthCheckRegistry();
	private final LifecycleEnvironment lifecycleEnvironment = new LifecycleEnvironment();
	private final RestaurantApplication application = new RestaurantApplication();
	private final RestaurantConfiguration config = new RestaurantConfiguration();
	private final MongoClientFactory factory = new MongoClientFactory();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		config.setMongoClient(factory);
		config.setDbName("health");
		Mockito.when(environment.jersey()).thenReturn(jersey);
		Mockito.when(environment.healthChecks())
				.thenReturn(healthCheckRegister);
		Mockito.when(environment.lifecycle()).thenReturn(lifecycleEnvironment);
		Mockito.when(environment.getObjectMapper()).thenReturn(objectMapper);
	}

	@Test
	public void runTheRestaurantApplication() throws Exception {
		application.run(config, environment);
		Mockito.verify(environment.jersey(), Mockito.times(3)).register(Mockito.any());
	}
}
