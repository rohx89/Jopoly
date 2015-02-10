package model;

import java.util.Observable;

/**
 * The Bank represents the bank in Monopoly it also knows how much houses and
 * Hotels are available
 * 
 * @author rohx89
 *
 */
public class Bank extends Observable {
	/**
	 * The amount of money, which the bank ha
	 */
	private int		_money;
	/**
	 * The amount of houses, which are available
	 */
	private int		_houses;
	/**
	 * The amount of available hotels
	 */
	private int		_hotels;
	/**
	 * Contains the properties owned by the bank Every index represents a
	 * monopoly field. If the banks owns a property the value is true, else it
	 * is false;
	 */
	private boolean	_properties[];

	public Bank() {
		_money = Integer.MAX_VALUE;
		_houses = 32;
		_hotels = 12;
		_properties = new boolean[] { false, true, false, true, false, true, true, false, true, true, false, true,
				true, true, true, true, true, false, true, true, false, true, false, true, true, true, true, true,
				true, true, false, true, true, false, true, true, false, true, false, true };
	}

	/**
	 * tells whether the field is owned by the bank
	 * 
	 * @param position
	 *            the position on the board
	 * @return whether the field is owned by the bank
	 * @require position < 40 * @require position >= 40
	 */
	public boolean owns(int position) {
		assert position < 40 : "Condition was not ok: position<40";
		assert position >= 0 : "Condition was not ok: position>0";
		return _properties[position];
	}

	public int getAvailableHouses() {
		return _houses;
	}

	public int getAvailableHotels() {
		return _hotels;
	}

	/**
	 * withdraws money from the bank
	 * 
	 * @param amount
	 * @require amount>0
	 */
	public void withdraw(int amount) {
		assert amount > 0 : "Condition was not ok: amount>0";
		_money = _money - amount;
	}

	/**
	 * deposit money
	 * 
	 * @param amount
	 * @require amount>0
	 */
	public void deposit(int amount) {
		assert amount > 0 : "Condition was not ok: amount>0";
		_money = _money + amount;
	}
}