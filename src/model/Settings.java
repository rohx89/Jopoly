package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;

public class Settings {
	public static Logger LOGGER = Logger.getRootLogger();
	/**
	 * This contains the name of the board. The files, describing the board
	 */
	public static String BOARD = null;
	/**
	 * The amount of money, which every Player gets at the Beginning of the game
	 */
	public static int INITIAL_MONEY = -1;
	/**
	 * The name of the currency used in the game
	 */
	public static String CURRENCY = null;
	/**
	 * The amount of money, which a user get by passing the Startfield
	 */
	public static int START_MONEY = -1;

	/**
	 * Sets INITIAL_MONEY
	 * 
	 * @param money
	 *            The amount of money, which every Player gets at the Beginning
	 *            of the game
	 */
	public static void setInitialMoney(int money) {
		assertTrue("Condition was not ok:money>0", money > 0);
		INITIAL_MONEY = money;
	}

	/**
	 * Sets the board field
	 * 
	 * @param boardName
	 *            A {@link String} containing the Name of the board.
	 */
	public static void setBoard(String boardName) {
		assertNotNull("boardName is Null", boardName);
		BOARD = boardName;
	}

	public static void setCurreny(String currency) {
		assertNotNull("currency is Null", currency);
		CURRENCY = currency;
	}

	public static void setStartMoney(int startMoney) {
		assertTrue("Condition was not ok:startMoney>0", startMoney > 0);
		START_MONEY = startMoney;
	}

}
