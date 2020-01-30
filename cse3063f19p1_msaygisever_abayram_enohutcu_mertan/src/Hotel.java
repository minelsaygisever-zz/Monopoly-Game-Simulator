public class Hotel {
    private String name;
    private Player owner;
    private boolean hasOwner;
    private PropertySquare square;

    public Hotel() {

        this.name = "HOTEL";
        this.hasOwner = false;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        if (player == null) {
            this.hasOwner = false;
        } else {
            this.hasOwner = true;
        }
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setSquare(PropertySquare square) {
        if (square == null) {
            setOwner(null);
        } else {
            setOwner(square.getOwner());
        }
        this.square = square;
    }

    public PropertySquare getSquare() {
        return square;
    }


}
