package Model;
import static org.junit.Assert.assertTrue;

import java.util.Random;

/**
 * This class reperesents a dice.
 * 
 * @author rohx89
 *
 */
public class Dice {
	/**
	 * The amount of sides
	 */
	private int _sides;
	/**
	 * The current value of the dice
	 */
	private int _currentValue;

	/**
	 * initializes a dice
	 * 
	 * @param sides
	 *            A Dice must have at least 2 sides
	 */
	public Dice(int sides) {
		assertTrue(
				"The dice has a wrong amount of sides! A dice must have at least 2 sides",
				sides > 1);
		_sides = sides;
		_currentValue = throwDice();
		System.out.println("DEBUG: Dice was created");
	}

	/**
	 * Sets the _currentValue to a random number between 1 and the amount of
	 * sides
	 * 
	 * @return the current Value of the dice
	 */
	public int throwDice() {
		Random randomGenerator = new Random();
		_currentValue = randomGenerator.nextInt(_sides - 1) + 1;
		System.out.println("DEBUG: Dice was thrown. The value of the this is: "
				+ _currentValue);
		return _currentValue;
	}

	/**
	 * 
	 * @return the current Value of the dice
	 */
	public int getCurrentValue() {

		return _currentValue;
	}
}
