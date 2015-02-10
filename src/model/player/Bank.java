package model.player;

/**
 * The Bank represents the bank in Monopoly it also knows how much houses and
 * Hotels are available
 * 
 * @author rohx89
 *
 */
public class Bank extends AbstractPlayer {

	/**
	 * The amount of houses, which are available
	 */
	private int	_houses;
	/**
	 * The amount of available hotels
	 */
	private int	_hotels;

	public Bank() {
		super(Integer.MAX_VALUE, new boolean[] { false, true, false, true, false, true, true, false, true, true, false,
				true, true, true, true, true, true, false, true, true, false, true, false, true, true, true, true,
				true, true, true, false, true, true, false, true, true, false, true, false, true });
		_houses = 32;
		_hotels = 12;

	}

	public int getAvailableHouses() {
		return _houses;
	}

	public int getAvailableHotels() {
		return _hotels;
	}

}