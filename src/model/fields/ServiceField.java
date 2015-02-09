package model.fields;

import static org.junit.Assert.assertTrue;
import model.Settings;

/**
 * A Service Field
 * 
 * @author rohx89
 *
 */
public class ServiceField extends BuyableField {
	/**
	 * is used to calculate the rent, if only one service is owned
	 */
	private int _factor1;
	/**
	 * is used to calculate the rent, if two services are owned
	 */
	private int _factor2;

	public ServiceField(int price, String name, int redemptionOfMortage,
			int mortage, int factor1, int factor2) {
		super(price, name, redemptionOfMortage, mortage);
		assertTrue("Condition was not ok:factor1>0", factor1 > 0);
		assertTrue("Condition was not ok:factor2>0", factor2 > 0);
		_factor1 = factor1;
		_factor2 = factor2;
	}

	/**
	 * Calculates the rent
	 * 
	 * @param ownsAllServices
	 * @param valueOfDice
	 *            summe of all values of dices
	 * @return int the rent
	 */
	public int calculateRent(boolean ownsAllServices, int valueOfDice) {
		assertTrue("Condition was not ok:valueOfDice>0", valueOfDice > 0);
		int rent;
		if (super._hasMortage) {
			rent = 0;
		} else {
			if (ownsAllServices) {
				rent = valueOfDice * _factor2;
			} else {
				rent = valueOfDice * _factor1;
			}
		}
		Settings.LOGGER.debug("Rent is :" + rent);
		return rent;
	}
}
