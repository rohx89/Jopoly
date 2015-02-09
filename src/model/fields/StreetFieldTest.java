package model.fields;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import model.Settings;

import org.junit.Test;

public class StreetFieldTest {

	@Test
	public void buildHousesPositivTest() {
		Settings.LOGGER.info("---buildHousesPositivTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(5);
		assertTrue(field.getAmountOfHouses() == 5);
	};

	@Test(expected = java.lang.AssertionError.class)
	public void buildHousesNegativAmountTest() {
		Settings.LOGGER.info("---buildHousesNegativAmountTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, -39, Color.green);
		field.buildHouse(-1);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void build0HousesTest() {
		Settings.LOGGER.info("---build0HousesTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(0);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void buildMoreThan5HouseNegativTest() {
		Settings.LOGGER.info("---buildMoreThan5HouseNegativTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(6);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void buildMoreThan5HouseInStepsNegativTest() {
		Settings.LOGGER.info("---buildMoreThan5HouseInStepsNegativTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(5);
		field.buildHouse(1);
	}

	@Test
	public void removeHousesPositivTest() {
		Settings.LOGGER.info("---removeHousesPositivTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(5);
		assertTrue(field.getAmountOfHouses() == 5);
		field.removeHouse(1);
		assertTrue(field.getAmountOfHouses() == 4);
		field.removeHouse(4);
		assertTrue(field.getAmountOfHouses() == 0);
	};

	@Test(expected = java.lang.AssertionError.class)
	public void removedHousesNegativAmountTest() {
		Settings.LOGGER.info("---removedHousesNegativAmountTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(1);
		field.removeHouse(-1);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void removedHousesWithoutHousesTest() {
		Settings.LOGGER.info("---removedHousesWithoutHousesTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.removeHouse(-1);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void remove0HousesTest() {
		Settings.LOGGER.info("---remove0HousesTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(1);
		field.removeHouse(0);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void removeMoreThan5HouseNegativTest() {
		Settings.LOGGER.info("---removeMoreThan5HouseNegativTestt---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(5);
		field.removeHouse(6);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void removeMoreThan5HouseInStepsNegativTest() {
		Settings.LOGGER.info("---removeMoreThan5HouseInStepsNegativTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
		field.buildHouse(5);
		field.removeHouse(4);
		field.removeHouse(2);
	}

	@Test
	public void calculateRentTest() {
		Settings.LOGGER.info("---calculateRentTest---");
		StreetField field = new StreetField(50, "jfsnf", 55, 50, 10, 20, 30,
				40, 50, 60, 10, 39, Color.green);
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
