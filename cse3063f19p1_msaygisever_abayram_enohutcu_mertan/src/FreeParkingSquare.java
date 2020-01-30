// This class represents Free Park square on the board
public class FreeParkingSquare extends Square {

    // Create necessary variables
    private int squareID;
    private String squareName;

    // Constructor
    public FreeParkingSquare(int id, String name) {
        this.squareID = id;
        this.squareName = name;
    }

    // Getter and setter methods of the class
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

}
