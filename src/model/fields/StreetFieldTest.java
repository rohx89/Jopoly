package model.fields;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StreetFieldTest {

	@Test
	public void buildHousesPositivTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(5);
		assertTrue(field.getAmountOfHouses() == 5);
	};

	@Test(expected = java.lang.AssertionError.class)
	public void buildHousesNegativAmountTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(-1);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void build0HousesTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(0);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void buildMoreThan5HouseNegativTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(6);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void buildMoreThan5HouseInStepsNegativTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(5);
		field.buildHouse(1);
	}

	@Test
	public void removeHousesPositivTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(5);
		assertTrue(field.getAmountOfHouses() == 5);
		field.removeHouse(1);
		assertTrue(field.getAmountOfHouses() == 4);
		field.removeHouse(4);
		assertTrue(field.getAmountOfHouses() == 0);
	};

	@Test(expected = java.lang.AssertionError.class)
	public void removedHousesNegativAmountTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(1);
		field.removeHouse(-1);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void removedHousesWithoutHousesTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.removeHouse(-1);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void remove0HousesTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(1);
		field.removeHouse(0);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void removeMoreThan5HouseNegativTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(5);
		field.removeHouse(6);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void removeMoreThan5HouseInStepsNegativTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.buildHouse(5);
		field.removeHouse(4);
		field.removeHouse(2);
	}

	@Test
	public void calculateRentTest() {
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10);
		field.setMortage(true);
		assertTrue(field.calculateRent(true) == 0);
		field.setMortage(false);
		assertTrue(field.calculateRent(false) == 10);
		assertTrue(field.calculateRent(true) == 20);
		field.buildHouse(1);
		assertTrue(field.calculateRent(true) == 20);
		field.buildHouse(1);
		assertTrue(field.calculateRent(true) == 30);
		field.buildHouse(1);
		assertTrue(field.calculateRent(true) == 40);
		field.buildHouse(1);
		assertTrue(field.calculateRent(true) == 50);
		field.buildHouse(1);
		assertTrue(field.calculateRent(true) == 60);
	}

}
