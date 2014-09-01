package com.restaurant.api;

import org.joda.time.DateTime;

public class RestaurantBuilder {
	private String id;
	private Long camis;
	private String dba;
	private Integer score;
	private String grade;
	private Integer zipcode;
	private Integer boroCode;
	private Borough boroName;
	private Integer cuisineCode;
	private DateTime gradedate;
	private String phoneNumber;

	public RestaurantBuilder() {}

	public RestaurantBuilder withId(String id) {
		this.id = id;
		return this;
	}

	public RestaurantBuilder withCamis(Long camis) {
		this.camis = camis;
		return this;
	}

	public RestaurantBuilder withDba(String dba) {
		this.dba = dba;
		return this;
	}

	public RestaurantBuilder withScore(Integer score) {
		this.score = score;
		return this;
	}

	public RestaurantBuilder withGrade(String grade) {
		this.grade = grade;
		return this;
	}

	public RestaurantBuilder withZipCode(Integer zipcode) {
		this.zipcode = zipcode;
		return this;
	}

	public RestaurantBuilder withBoroCode(Integer boroCode) {
		this.boroCode = boroCode;
		return this;
	}

	public RestaurantBuilder withBorough(Borough borough) {
		this.boroName = borough;
		return this;
	}

	public RestaurantBuilder withCuisineCode(Integer cuisineCode) {
		this.cuisineCode = cuisineCode;
		return this;
	}

	public RestaurantBuilder withGradeDate(DateTime gradeDate) {
		this.gradedate = gradeDate;
		return this;
	}

	public String getId() {
		return id;
	}

	public Long getCamis() {
		return camis;
	}

	public String getDba() {
		return dba;
	}

	public Integer getScore() {
		return score;
	}

	public String getGrade() {
		return grade;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public Integer getBoroCode() {
		return boroCode;
	}

	public Borough getBoroName() {
		return boroName;
	}

	public Integer getCuisineCode() {
		return cuisineCode;
	}

	public DateTime getGradedate() {
		return gradedate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public RestaurantBuilder withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;	
	}
	
	/**
	 * Builds a (@link Restaurant)
	 * @return
	 */
	public Restaurant build() {
		Restaurant restaurant = new Restaurant();
		restaurant.setBoroCode(this.boroCode);
		restaurant.setBoroName(this.boroName);
		restaurant.setCamis(this.camis);
		restaurant.setCuisineCode(this.cuisineCode);
		restaurant.setDba(this.dba);
		restaurant.setGrade(this.grade);
		restaurant.setGradeDate(this.gradedate);
		restaurant.setId(this.id);
		restaurant.setScore(this.score);
		restaurant.setZipCode(this.zipcode);
		restaurant.setPhoneNumber(this.phoneNumber);
		return restaurant;
	}
}
