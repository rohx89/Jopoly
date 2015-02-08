package model;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the dice
 * 
 * @author rohx89
 *
 */
public class DiceTest {

	/**
	 * Checks whether a dice with 2 sides is correctly initialize. This means
	 * that no exception is thrown and the dice has a value which is 1 or 2
	 */
	@Test
	public void initializeDicePositivTest() {
		Dice dice = new Dice(2);
		assertNotNull("dice is Null", dice);
		assertTrue(((dice.getCurrentValue() == 1) || (dice.getCurrentValue() == 2)));
	}

	/**
	 * Test wether no Dice can be created,which has a less than 2 sides
	 */
	@Test(expected = java.lang.AssertionError.class)
	public void initializeDiceNegativeTest() {
		@SuppressWarnings("unused")
		Dice dice = new Dice(1);
	}

	/**
	 * Tests wether throwing the dice returns a plausible value (0<value<=sides)
	 */
	@Test
	public void throwDiceTest() {
		Dice dice = new Dice(6);
		for (int timesThrown = 0; timesThrown < 1000; timesThrown++) {
			int value = dice.throwDice();
			assertTrue(value > 0 && value <= 6);
		}

	}
}
