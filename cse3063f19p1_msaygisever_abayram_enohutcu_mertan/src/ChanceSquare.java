import java.util.ArrayList;

public class ChanceSquare extends Square{
    private ArrayList<ChanceCard> changeCardList;
    private String name;
    private int id;

    public ChanceSquare(ArrayList<ChanceCard> changeCardList, String name, int id){
        this.changeCardList = changeCardList;
        this.name = name;
        this.id = id;
    }



    // Return square id
    public int getSquareID(){
        return id;
    };

    // Set square id
    public void setSquareID(int squareID){
        this.id = squareID;
    };

    // Return square name
    public String getSquareName(){
        return name;
    };

    // Set square name
    public void setSquareName(String squareName){
        this.name = squareName;
    };

}
