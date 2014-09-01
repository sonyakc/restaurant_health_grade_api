package com.restaurant.dao;

import static com.google.common.truth.Truth.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

public class DateParserTest {

	@Test
	public void successfullyParseDatebaseTimestampUsingParser() {
		DateTime dateTime = DateParser
				.parseTimestamp("2014-02-20 00:00:00");
		assertThat(dateTime).labeled("parsed dateTime").isEqualTo(new DateTime(2014, 02, 20, 00, 00, 00, 00));
	}
}
