package com.restaurant.dao;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class DateParserTest {

	@Test
	public void successfullyParseDatebaseTimestampUsingParser() {
		LocalDateTime localDateTime = DateParser
				.parseTimestamp("2014-02-20 00:00:00");
		assertThat(localDateTime).labeled("parsed localDateTime").isEqualTo(new LocalDateTime(2014, 02, 20, 00, 00, 00, 00));
	}
}
