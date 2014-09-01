package com.restaurant.api;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {
	private long id;

	@Length(max = 3)
	private String content;

	public Saying() {
		super();
	}

	public Saying(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public String getContent() {
		return content;
	}

}
