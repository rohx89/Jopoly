package model.fields;

import static org.junit.Assert.assertTrue;
import model.Settings;

import org.junit.Test;

public class ServiceFieldTest {

	@Test
	public void calculateRentWithMortageTest() {
		Settings.LOGGER.info("---Start CalculateRentWithMortageTest---");
		ServiceField field = new ServiceField(500, "test", 500, 500, 4, 10);
		field.setMortage(true);
		assertTrue(field.calculateRent(true, 12) == 0);
	}

	@Test
	public void calculateRentOneServiceTest() {
		Settings.LOGGER.info("---Start calculateRentOneServiceTest---");
		ServiceField field = new ServiceField(500, "test", 500, 500, 4, 10);

		assertTrue(field.calculateRent(false, 10) == 40);
	}

	@Test
	public void calculateRentAllServicesTest() {
		Settings.LOGGER.info("---calculateRentAllServicesTest---");
		ServiceField field = new ServiceField(500, "test", 500, 500, 4, 10);

		assertTrue(field.calculateRent(true, 10) == 100);
	}

}
