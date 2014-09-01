package com.restaurant.dao;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

public class DateParser {

	private final static String pattern = "yyyy-MM-dd HH:mm:ss";

	public static DateTime parseTimestamp(String input) {
		LocalDateTime localDateTime = LocalDateTime.parse(input, DateTimeFormat.forPattern(pattern));
		return localDateTime.toDateTime();
	}

}
