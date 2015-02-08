package model.fields;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrainStationFieldTest {

	@Test
	public void calculateRentTest() {
		TrainStationField field = new TrainStationField(100, "mdksm", 110, 100,
				25);
		assertTrue(field.calculateRent(1) == 25);
		assertTrue(field.calculateRent(2) == 50);
		assertTrue(field.calculateRent(3) == 100);
		assertTrue(field.calculateRent(4) == 200);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void calculateRent0OwenedTrainstationsTest() {
		TrainStationField field = new TrainStationField(100, "mdksm", 110, 100,
				25);
		assertTrue(field.calculateRent(0) == 25);
	}

	@Test(expected = java.lang.AssertionError.class)
	public void calculateRent5OwenedTrainstationsTest() {
		TrainStationField field = new TrainStationField(100, "mdksm", 110, 100,
				25);
		assertTrue(field.calculateRent(5) == 25);
	}

	@Test
	public void calculateRentwithMortageTest() {

		TrainStationField field = new TrainStationField(100, "mdksm", 110, 100,
				25);
		field.setMortage(true);
		assertTrue(field.calculateRent(4) == 0);
	}

}
