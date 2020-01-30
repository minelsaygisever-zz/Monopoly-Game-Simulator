// This class represents purchasable squares on the board
public abstract class PurchasableSquare extends Square {

    public abstract int getFine();

    public abstract void setOwner(Player player);

    public abstract Player getOwner();

    public abstract int getPrice();

    public abstract void setPrice(int price);

    public abstract void setHasOwner(boolean hasOwner);

    public abstract void payRent(Player player, Board board);

    public abstract boolean getHasOwner();

    public abstract int getMortgage();

    public abstract boolean getIsMortgaged();

    public abstract void setMortgaged(boolean bool);
}