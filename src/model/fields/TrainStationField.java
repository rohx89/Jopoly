package model.fields;

import static org.junit.Assert.assertTrue;
import model.Settings;

public class TrainStationField extends AbstractBuyableField implements
		BuyableFieldInterface {
	private int _startRent;

	public TrainStationField(int price, String name, int redemptionOfMortage,
			int mortage, int startRent) {
		super(price, name, redemptionOfMortage, mortage);
		assertTrue("Condition was not ok:startRent>0", startRent > 0);

		_startRent = startRent;

	}

	// TODO: Check start Rent when Parsing the Trainstation file. StartRent
	// should never be >MAXINT
	/**
	 * Calculates the rent
	 * 
	 * @param ownedTrainStations
	 * @return the rent
	 */
	public int calculateRent(int ownedTrainStations) {
		assertTrue("Condition was not ok:ownedTrainstations>0",
				ownedTrainStations > 0);
		assertTrue("Condition was not ok:ownedTrainstations<5",
				ownedTrainStations < 5);
		int rent = _startRent;
		if (super._hasMortage) {
			rent = 0;
		} else {
			for (int iterator = 1; iterator <= ownedTrainStations - 1; iterator++) {

				rent = rent * 2;
			}

		}
		Settings.LOGGER.debug("Rent is :" + rent);
		return rent;
	}
}
