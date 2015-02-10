package model.player;

import model.Settings;

/**
 * The Model of the llayer
 * 
 * @author rohx89
 *
 */
public class Player extends AbstractPlayer {
	/**
	 * the position on the field
	 */
	private int	_position;

	public Player() {
		super(Settings.INITIAL_MONEY, new boolean[40]);
		_position = 0;
	}

	public int getPosition() {
		return _position;
	}

	public void moveFieldsForward(int fields) {
		_position = _position + fields;
	}

	public void moveToField(int positionOfField) {
		assert positionOfField >= 0 : "Condition was not ok: positionOfField>=0";
		assert positionOfField < 40 : "Condition was not ok: positionOfField<40";
		_position = positionOfField;
	}
}
