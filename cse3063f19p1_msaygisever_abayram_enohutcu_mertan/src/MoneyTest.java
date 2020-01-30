

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    int moneyValue = 200;
    Money money = new Money(moneyValue);

    @Test
    public void testCurrentMoney(){
        controlMoney(money, moneyValue);
    }

    void controlMoney(Money money, int moneyValue){
        assertEquals("Money is not assigned properly.", money.getCurrentMoney(), moneyValue);
    }
}
