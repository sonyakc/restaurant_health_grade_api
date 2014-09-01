package com.restaurant.api;

import io.dropwizard.jackson.JsonSnakeCase;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Different cuisine types
 * 
 * @author sonya
 * 
 */
@JsonSnakeCase
public final class Cuisine {
	private String id;
	private Integer cuisineCode;
	private String cuisineDesc;

	public Cuisine() { }
	
	/**
	 * Constructs a new cuisine type
	 * @param cuisineCode
	 * @param codeDesc
	 * @param objectid 
	 */
	public Cuisine(Integer cuisineCode, String codeDesc, String objectid) {
		this.cuisineCode = cuisineCode;
		this.cuisineDesc = codeDesc;
		this.id = objectid;
	}

	@JsonProperty
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty
	public Integer getCuisineCode() {
		return cuisineCode;
	}

	public void setCuisineCode(Integer cuisinecode) {
		this.cuisineCode = cuisinecode;
	}

	@JsonProperty
	public String getCuisineDesc() {
		return cuisineDesc;
	}

	public void setCuisineDesc(String codeDesc) {
		this.cuisineDesc = codeDesc;
	}

}
