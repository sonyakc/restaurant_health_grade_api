package com.restaurant.api;

import io.dropwizard.jackson.JsonSnakeCase;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generic restaurant data and health inspection specific details e.g. current grade and score
 * 
 * @author sonya
 * 
 */
@JsonSnakeCase
public final class Restaurant {
	private String id;
	private Long camis;
	private String dba;
	private Integer score;
	private String grade;
	private Integer zipCode;
	private Integer boroCode;
	private Borough boroName;
	private Integer cuisineCode;
	private DateTime gradeDate;
	private String phoneNumber;
	
	@JsonProperty
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty
	public Long getCamis() {
		return camis;
	}

	public void setCamis(Long camis) {
		this.camis = camis;
	}

	@JsonProperty
	public String getDba() {
		return dba;
	}

	public void setDba(String dba) {
		this.dba = dba;
	}

	@JsonProperty
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@JsonProperty
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@JsonProperty
	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipcode) {
		this.zipCode = zipcode;
	}

	@JsonIgnore
	public Integer getBoroCode() {
		return boroCode;
	}

	public void setBoroCode(Integer boroCode) {
		this.boroCode = boroCode;
	}

	@JsonProperty
	public Borough getBoroName() {
		return boroName;
	}

	public void setBoroName(Borough boroName) {
		this.boroName = boroName;
	}

	@JsonProperty
	public Integer getCuisineCode() {
		return cuisineCode;
	}

	public void setCuisineCode(Integer cuisineCode) {
		this.cuisineCode = cuisineCode;
	}

	@JsonProperty
	public DateTime getGradeDate() {
		return gradeDate;
	}

	public void setGradeDate(DateTime gradedate) {
		this.gradeDate = gradedate;
	}

	@JsonProperty
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
