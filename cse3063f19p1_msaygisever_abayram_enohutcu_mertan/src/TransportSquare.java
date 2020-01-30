// This class represents the transport squares on the board
public class TransportSquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private int transportFine;
    private Player owner;
    private int price;
    private boolean hasOwner;
    private int mortgage;
    private boolean isMortgaged;

    // Constructor
    public TransportSquare(int id, String name, int fine, int price, int mortgage) {
        this.squareID = id;
        this.squareName = name;
        this.transportFine = fine;
        this.hasOwner = false;
        this.price = price;
        this.mortgage = mortgage;
        this.isMortgaged = false;
    }

    // Getter and setter methods
    @Override
    public int getSquareID() {
        return this.squareID;
    }

    @Override
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    @Override
    public String getSquareName() {
        return this.squareName;
    }

    @Override
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    public int getFine() {
        return this.transportFine;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.hasOwner = true;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFine(int fine) {
        this.transportFine = fine;
    }

    @Override
    public void payRent(Player player, Board board) {
        // Check if the owner is himself
        if (player != owner && isMortgaged == false) {
            // Check if the owner is in jail
            if (!owner.isInJail()) {
                int tempFine = owner.getTransportCount() * transportFine;
                if (player.isAbleDecreaseMoney(tempFine, board)) {
                    player.payMoneyToOwner(owner, tempFine);
                } else {
                    player.playerGoesToBankrupt(tempFine, this);
                }
            } else {
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

