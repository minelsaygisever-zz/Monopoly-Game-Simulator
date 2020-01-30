// This class represents the utility squares on the board
public class UtilitySquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private String utilityName;
    private int rate;
    private Player owner;
    private int price;
    private int fine;
    private boolean hasOwner;
    private int mortgage;
    private boolean isMortgaged;

    // Constructor
    public UtilitySquare(int squareID, String utilityName, int rate, int price, int mortgage) {
        this.squareID = squareID;
        this.utilityName = utilityName;
        this.rate = rate;
        this.price = price;
        this.hasOwner = false;
        this.mortgage = mortgage;
        this.isMortgaged = false;
    }

    // Return square id
    public int getSquareID() {
        return squareID;
    }

    // Set square id
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    // Return square name
    public String getSquareName() {
        return utilityName;
    }

    // Set square name
    public void setSquareName(String squareName) {
        this.utilityName = squareName;
    }

    // Calculate the exact fine for utilities
    public int getFine(int diceValue) {
        // RATE X DICE
        this.fine = rate * diceValue;
        return fine;
    }

    // Getter and setter methods
    public int getFine() {
        return fine;
    }

    public int getRate() {
        return rate;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        this.hasOwner = true;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    @Override
    public void payRent(Player player, Board board) {
        // Check if the owner is himself
        if (player != owner && isMortgaged == false) {
            // Check if the owner is in jail
            if (!owner.isInJail()) {
                int tempFine = getFine(player.getMoveDice().getTotal());;

                if(owner.getUtilityCount() == 2){
                    int tempRate = 10;
                    tempFine = tempRate * player.getMoveDice().getTotal();
                }

                if(player.isAbleDecreaseMoney(tempFine, board)){
                    player.payMoneyToOwner(owner, tempFine);
                }else{
                    player.playerGoesToBankrupt(tempFine, this);
                }
            }
            else{
                System.out.println("*The owner " + owner.getPlayerName() + " is in Jail*");
            }
        }
    }

    @Override
    public int getMortgage() {
        return mortgage;
    }

    @Override
    public boolean getIsMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }
}
