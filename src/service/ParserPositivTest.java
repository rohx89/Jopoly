package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import model.ParserException;
import model.Settings;
import model.fields.BuyableFieldInterface;
import model.fields.ServiceField;
import model.fields.StreetField;
import model.fields.TrainStationField;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author rohx89
 *
 */
public class ParserPositivTest {
	Parser _parser;

	@Before
	public void before() throws ParserException {
		_parser = new Parser();
		_parser.parseSettings();
	}

	@Test
	public void SettingsTest() throws ParserException {
		Settings.LOGGER.info("---Start SettingsTest---");
		assertTrue(Settings.INITIAL_MONEY == 15000);
		assertTrue(Settings.CURRENCY.equals("€"));
		assertTrue(Settings.BOARD.equals("GermanEUR"));
		assertTrue(Settings.START_MONEY == 200);
	}

	@Test
	/**
	 * Test whether objects in board are of the right Insance
	 * @throws ParserException
	 */
	public void CheckFieldInstancesTest() throws ParserException {
		Settings.LOGGER.info("---Start CheckFieldInstanceTest---");
		int[] trainStation = { 5, 15, 25, 35 };
		int[] noBuyableField = { 0, 2, 7, 10, 17, 20, 22, 30, 33, 36, 38 };
		int[] serviceField = { 12, 28 };
		int[] streetField = { 1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23,
				24, 26, 27, 29, 31, 32, 34, 37, 39 };
		BuyableFieldInterface[] board = _parser.parseBoardFiles();
		for (int iterator : trainStation) {
			assertTrue("board[" + iterator + "] is not a trainStation",
					board[iterator] instanceof TrainStationField);
		}
		for (int iterator : noBuyableField) {
			assertNull("board[" + iterator + "] is not null", board[iterator]);
		}
		for (int iterator : serviceField) {
			assertTrue("board[" + iterator + "] is not a ServiceField",
					board[iterator] instanceof ServiceField);
		}
		for (int iterator : streetField) {
			assertTrue("board[" + iterator + "] is not a StreetField",
					board[iterator] instanceof StreetField);
		}
	}

	@Test
	public void checkBoardSizeTest() throws ParserException {
		Settings.LOGGER.info("---Start CheckBoardSizeTest---");
		BuyableFieldInterface[] board = _parser.parseBoardFiles();
		assertTrue("Boardsize has" + board.length + "instead of 40 fields",
				board.length == 40);
	}

	@Test
	/**
	 * Checks whether all properties of one {@link StreetField} are correct
	 */
	public void checkStreetFieldTest() throws ParserException {
		Settings.LOGGER.info("---Start checkStreetFieldTest---");
		int[] rent = { 24, 120, 360, 850, 1025, 1200 };
		BuyableFieldInterface[] board = _parser.parseBoardFiles();
		StreetField field = (StreetField) board[29];

		assertEquals("Wrong color", field.getColor().getRGB(),
				Color.yellow.getRGB());
		assertEquals("Goethestraße", field.getName());
		assertEquals("Price is wrong", 280, field.getPrice());
		assertEquals("PriceHouse is wrong", 150, field.getPriceForHouse());
		for (int house = 0; house < 6; house++) {
			assertEquals("Rent for " + house + "Houses is wrong", rent[house],
					field.calculateRent(false));
			if (house < 5) {
				field.buildHouse(1);
			}
		}
		assertEquals("Mortage is wrong", 140, field.getMortage());
		assertEquals("RedemptionOfMortage is wrong", 154,
				field.getRedemptionOfMortage());

	}

	@Test
	/**
	 * Checks whether all properties of one {@link StreetField} are correct
	 */
	public void checkTrainStationFieldTest() throws ParserException {
		Settings.LOGGER.info("---checkTrainStationFieldTest---");
		TrainStationField field = (TrainStationField) _parser.parseBoardFiles()[25];
		assertEquals("Name is wrong", field.getName(), "Nordbahnhof");
		assertEquals("Mortage is wrong", field.getMortage(), 100);
		assertEquals("RedemptionOfMortage is wrong",
				field.getRedemptionOfMortage(), 110);
		assertEquals("StartRent is wrong", field.calculateRent(1), 25);
		assertEquals("Price is wrong", field.getPrice(), 200);
	}

	@Test
	/**
	 * Checks whether all properties of one {@link ServiceField} are correct
	 */
	public void checkServiceFieldTest() throws ParserException {
		Settings.LOGGER.info("---checkServiceFieldTest---");
		ServiceField field = (ServiceField) _parser.parseBoardFiles()[28];
		assertEquals("Name is wrong", field.getName(), "Wasserwerk");
		assertEquals("Mortage is wrong", field.getMortage(), 75);
		assertEquals("RedemptionOfMortage is wrong", 82,
				field.getRedemptionOfMortage());
		assertEquals("StartRent is wrong", field.calculateRent(false, 12), 48);
		assertEquals("Price is wrong", field.getPrice(), 150);
	}
}
