// This class represents Monopoly dices
public class Dice {

    private int firstValue; // First dice value.
    private int secondValue; // Second dice value.

    // Default constructor
    public Dice() {
    }

    // Getter method for first dice value.
    public int getFirstValue() {
        return firstValue;
    }

    // Getter method for second dice value.
    public int getSecondValue() {
        return secondValue;
    }

    // Method for rolling dice with random values.
    public void rollDice() {
        this.firstValue = (int) (6 * Math.random() + 1);
        this.secondValue = (int) (6 * Math.random() + 1);
    }

    // Getter method for sum of first and second value.
    public int getTotal() {
        int total;
        total = this.firstValue + this.secondValue;
        return total;
    }

    // Getter method for first random value.
    public int getFirstRandomValue() {
        this.firstValue = (int) (6 * Math.random() + 1);
        return firstValue;
    }

    // Getter method for second random value.
    public int getSecondRandomValue() {
        this.secondValue = (int) (6 * Math.random() + 1);
        return secondValue;
    }
}
