package com.restaurant;

import java.net.UnknownHostException;

import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoClientFactory {
	@JsonProperty
	@NotEmpty
    private String host;

	@JsonProperty
    @Min(1)
    @Max(65535)
    private int port = 5672;
    
	@JsonProperty
	@NotEmpty
	private String db = "health";
	
    public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public DB build(Environment environment) throws UnknownHostException {
    	final MongoClient client = new MongoClient(getHost(), getPort());
        environment.lifecycle().manage(new Managed() {
            @Override
            public void start() {
            }

            @Override
            public void stop() {
                client.close();
            }
        });
        return client.getDB(getDb());
    }
}
