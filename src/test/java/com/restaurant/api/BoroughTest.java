package com.restaurant.api;

import org.junit.Test;
import static com.google.common.truth.Truth.*;

public class BoroughTest {

	@Test
	public void findBoroughByUniqueIdentifier()
	{
		Integer uniqueId = Integer.valueOf(2);
		Borough borough = Borough.findBorough(uniqueId);
		assertThat(borough).isEqualTo(Borough.THE_BRONX);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotFindBoroughForInvalidUniqueIdentifier()
	{
		Integer uniqueId = Integer.valueOf(99);
		Borough.findBorough(uniqueId);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotFindBoroughForNulldUniqueIdentifier()
	{
		Borough.findBorough(null);
	}
}
