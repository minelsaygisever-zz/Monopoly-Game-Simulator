import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    Dice dice = new Dice();

    @Test
    public void getFirstValue() {
        dice.rollDice();
        int firstValue = dice.getFirstValue();
        assertTrue("Dice's first value is not between 1 and 6.", firstValue <= 6 && firstValue>=1);
    }

    @Test
    public void getSecondValue() {
        dice.rollDice();
        int secondValue = dice.getSecondValue();
        assertTrue("Dice's second value is not between 1 and 6.",secondValue <= 6 && secondValue >= 1);
    }
}