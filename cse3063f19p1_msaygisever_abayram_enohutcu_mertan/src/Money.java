//Money class.
public class Money {

    private int money; //Money amount

    //Constructor of Money Class.
    public Money(int money) {
        this.money = money;
    }

    //Getter method for money amount
    public int getCurrentMoney() {
        return this.money;
    }

    //Method for increase the amount of money with a given parameter.
    public void increaseMoney(int increase) {
        this.money += increase;
    }

    //Method for decrease the amount of money with a given parameter.
    public void decreaseMoney(int decrease) {
        this.money -= decrease;
    }

}