import java.util.ArrayList;

public class CommunityChestSquare extends Square{
    private ArrayList<CommunityChest> communityChestCardList;
    private String name;
    private int id;

    public CommunityChestSquare(ArrayList<CommunityChest> communityChestCardList, String name, int id){
        this.communityChestCardList = communityChestCardList;
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
