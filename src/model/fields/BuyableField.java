package model.fields;

import static org.junit.Assert.assertTrue;

/**
 * A Field which can be bought.
 * 
 * @author rohx89
 *
 */
abstract class BuyableField implements BuyableFieldInterface {
	/**
	 * price for the field
	 */
	private int _price;
	/**
	 * name of the field
	 */
	private String _name;
	/**
	 * Indicates, wether there is a mortage on that field
	 */
	public boolean _hasMortage;
	/**
	 * The amount of money which the player gets for the mortage
	 */
	private int _mortage;
	/**
	 * The price to redempt the mortage
	 */
	public int _redemptionOfMortage;

	/**
	 * sets field
	 * 
	 * @param price
	 *            Price of the field
	 * @param name
	 *            Name of the field
	 * @param redemptionOfMortage
	 *            redemption fee
	 */
	public BuyableField(int price, String name, int redemptionOfMortage,
			int mortage) {
		assertTrue("Condition was not ok:price>0", price > 0);
		assertTrue("Condition was not ok:redemptionOfMortage>0",
				redemptionOfMortage > 0);
		assertTrue("Condition was not ok:mortage>0", mortage > 0);
		_price = price;
		_name = name;
		_redemptionOfMortage = redemptionOfMortage;
		_mortage = mortage;
		_hasMortage = false;

	}

	public int getPrice() {
		return _price;
	}

	public String getName() {
		return _name;
	}

	public boolean hasMortage() {
		return _hasMortage;
	}

	public void setMortage(boolean mortage) {
		_hasMortage = mortage;
	}

	public int getRedemptionOfMortage() {
		return _redemptionOfMortage;
	}

	public int getMortage() {
		return _mortage;
	}
}
