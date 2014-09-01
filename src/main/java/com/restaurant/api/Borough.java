package com.restaurant.api;

/**
 * Enumeration representation of the five New York City Boroughs 
 * @author sonya
 *
 */
public enum Borough {
	MANHATTAN(1), 
	THE_BRONX(2), 
	BROOKLYN(3), 
	QUEENS(4), 
	STATEN_ISLAND(5);
	
	private Integer uniqueIdentifier;
	
	Borough(Integer uniqueIdentifier) {
		this.setUniqueIdentifier(uniqueIdentifier);
	}

	public Integer getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	private void setUniqueIdentifier(Integer uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
	
	public static Borough findBorough(Integer uniqueIdentifier)
	{
		Borough[] values = Borough.values();
		for(Borough value : values) {
			if(value.getUniqueIdentifier().equals(uniqueIdentifier)) {
				return value;
			}
		}
		throw new IllegalArgumentException("borough not found");
	}
}
