package model.fields;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

/**
 * A street field
 * 
 * @author rohx89
 *
 */
public class StreetField extends BuyableField {
	/**
	 * contains the rent 0->normal rent 1->rent for 1 house ... 5->rent for
	 * motel
	 */
	private int[] _rent;
	/**
	 * amount of houses. 5 is a hotel
	 */
	private int _houses;
	/**
	 * the price for one House
	 */
	private int _priceForHouse;
	/**
	 * position on the board
	 */
	private int _position;
	private Color _color;

	/**
	 * sets all fields
	 * 
	 * @param price
	 * @param name
	 * @param redemptionOfMortage
	 * @param mortage
	 * @param rent
	 * @param rent1House
	 * @param rent2Houses
	 * @param rent3Houses
	 * @param rent4Houses
	 * @param rentHotel
	 * @param priceHouse
	 * @param position
	 * @param color
	 *            TODO
	 */
	public StreetField(int price, String name, int redemptionOfMortage,
			int mortage, int rent, int rent1House, int rent2Houses,
			int rent3Houses, int rent4Houses, int rentHotel, int priceHouse,
			int position, Color color) {
		super(price, name, redemptionOfMortage, mortage);
		assert position > 0 : "Condition was not ok: position>0";
		assert position <= 39 : "Condition was not ok: position<=39";
		assert priceHouse > 0 : "Condition was not ok: priceHouse>0";
		assert rent > 0 : "Condition was not ok: rent>0";
		assert rent1House > 0 : "Condition was not ok: rent1House>0";
		assert rent2Houses > 0 : "Condition was not ok: rent2Houses>0";
		assert rent3Houses > 0 : "Condition was not ok: rent3Houses>0";
		assert rent4Houses > 0 : "Condition was not ok: rent4Houses>0";
		assert rentHotel > 0 : "Condition was not ok: rentHotel>0";
		assert color != null : "Precondition not fullfilled: color";
		_houses = 0;
		_priceForHouse = priceHouse;
		_rent = new int[] { rent, rent1House, rent2Houses, rent3Houses,
				rent4Houses, rentHotel };
		_position = position;
		_color = color;

	}

	public Color getColor() {
		return _color;
	}

	public int getPosition() {
		return _position;
	}

	/**
	 * builds Houses
	 * 
	 * @param amountOfHouses
	 */
	public void buildHouse(int amountOfHouses) {
		assertTrue("Condition was not ok:amountOfHouses<=5",
				amountOfHouses <= 5);
		assertTrue("Condition was not ok:amountOfHouses>0", amountOfHouses > 0);
		assertTrue("Condition was not ok:_houses+amountOfHouses<=5", _houses
				+ amountOfHouses <= 5);
		_houses = _houses + amountOfHouses;
	}

	public int getPriceForHouse() {
		return _priceForHouse;
	}

	public int getAmountOfHouses() {
		return _houses;
	}

	/**
	 * Removes houses
	 * 
	 * @param amountToRemove
	 */
	public void removeHouse(int amountToRemove) {
		assertTrue("Condition was not ok:amountToRemove<=5",
				amountToRemove <= 5);
		assertTrue("Condition was not ok:amountToRemove>0", amountToRemove > 0);
		assertTrue("Condition was not ok:_houses-amountToRemove<=0", _houses
				- amountToRemove >= 0);
		_houses = _houses - amountToRemove;
	}

	/**
	 * calculates the Rent
	 * 
	 * @param ownsAllStreets
	 *            indicates whether the person owns all streets of the street
	 *            group
	 * @return Rent
	 */
	public int calculateRent(boolean ownsAllStreets) {
		assertTrue("Condition was not ok:_rent.length()==6", _rent.length == 6);
		int rent;
		if (super._hasMortage) {
			rent = 0;
		} else if (ownsAllStreets && (_houses == 0)) {
			rent = _rent[0] * 2;
		} else {
			rent = _rent[_houses];

		}
		return rent;
	}
}
