/**
 * 
 */
package com.restaurant;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

/**
 * @author sonya
 *
 */
public class RestaurantConfiguration extends Configuration {

	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName = "Stranger";
	
	@Valid
	@NotNull
	private MongoClientFactory mongoClient = new MongoClientFactory();

	@NotEmpty
	private String dbName = "health";
	
	@JsonProperty("mongoClient")
	public MongoClientFactory getMongoClientFactory() {
		return mongoClient;
	}

	@JsonProperty("mongoClient")
	public void setMongoClient(MongoClientFactory factory) {
		this.mongoClient = factory;
	}

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	@JsonProperty
	public String getDbName() {
		return dbName;
	}

	@JsonProperty
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
