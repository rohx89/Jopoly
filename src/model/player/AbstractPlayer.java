package model.player;

/**
 * A Player or the Bank
 * 
 * @author rohx89
 *
 */
public class AbstractPlayer {
	/**
	 * The amount of money, which the bank ha
	 */
	private int		_money;
	/**
	 * Contains the properties owned by the bank Every index represents a
	 * monopoly field. If the banks owns a property the value is true, else it
	 * is false;
	 */
	private boolean	_properties[];

	/**
	 * 
	 * @param money
	 *            the amount of money owned by the player
	 * @param properties
	 *            the properties owned by the player
	 * @require money>0
	 * @require properties != null
	 */
	public AbstractPlayer(int money, boolean[] properties) {
		assert money > 0 : "Condition was not ok: money>0";
		assert properties != null : "Precondition not fullfilled: properties";
		_money = money;
		_properties = properties;
	}

	/**
	 * tells whether the field is owned by the player
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

	/**
	 * withdraws money k
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
