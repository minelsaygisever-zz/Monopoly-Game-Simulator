import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    FileReaderJson fileReader = new FileReaderJson();

    // Read necessary variables with the FileReaderJson object
    int intPlayerSize = fileReader.getIntPlayerSize();
    int intThreshold = fileReader.getIntThreshold();
    int intStartMoney = fileReader.getIntStartMoney();
    int intGoMoney = fileReader.getIntGoMoney();
    int intJailFine = fileReader.getIntJailFine();
    String[] properties = fileReader.getProperties();
    String[] propertyColor = fileReader.getPropertyColor();
    int[] propertyFine = fileReader.getPropertyFine();
    int[] propertyPrice = fileReader.getPropertyPrice();
    String[] utilityName = fileReader.getUtilityName();
    int[] utilityRate = fileReader.getUtilityRate();
    int[] utilityPrice = fileReader.getUtilityPrice();
    String[] transportName = fileReader.getTransportName();
    int[] transportFine = fileReader.getTransportFine();
    int[] transportPrice = fileReader.getTransportPrice();
    String[] taxSquares = fileReader.getTaxSquares();
    int[] taxFine = fileReader.getTaxFine();
    int intGoToJailNumber = fileReader.getIntGoToJailNumber();
    int[] rent1 = fileReader.getRent1();
    int[] rent2 = fileReader.getRent2();
    int[] rent3 = fileReader.getRent3();
    int[] rent4 = fileReader.getRent4();
    int[] hotel = fileReader.getHotel();
    int[] mortgage = fileReader.getMortgage();
    int[] pricePerHouse = fileReader.getPricePerHouse();
    int maxHouseNumber = fileReader.getMaxHouseNumber();
    int maxHotelNumber = fileReader.getMaxHotelNumber();
    ArrayList<String> communityChest = fileReader.getCommunityChestCards();
    ArrayList<String> chanceCards = fileReader.getChanceCards();
    int[] transportMortgage = fileReader.getTransportMortgage();
    int[] utilityMortgage = fileReader.getUtilityMortgage();


    // Create MonopolyGame object with given parameters
    MonopolyGame mpGame = MonopolyGame.getInstance(intPlayerSize, intThreshold, intStartMoney, intGoMoney,
            properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
            transportFine, transportPrice, taxFine, taxSquares, intJailFine, intGoToJailNumber, rent1, rent2, rent3, rent4, hotel,
            mortgage, pricePerHouse, maxHouseNumber, maxHotelNumber, communityChest, chanceCards, utilityMortgage, transportMortgage);

    Board board = new Board ( properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName, transportFine,
            transportPrice, taxFine, taxSquares, intGoToJailNumber, intJailFine, rent1, rent2, rent3, rent4, hotel, mortgage, pricePerHouse,
            maxHouseNumber, maxHotelNumber, communityChest, chanceCards, utilityMortgage, transportMortgage);

    Dice moveDice = new Dice();
    Dice choiceDice = new Dice();
    Player player = new Player("Minel", intStartMoney, moveDice, choiceDice, mpGame);


    @Test
    public void testHasItAllFalse(){
        board.setSquareList();
        Square[] squares = board.getSquareList();
        int a = 0;
        int number = 0;
        String color = propertyColor[0];

        for(int j = 1; j < propertyColor.length; j++){
            for(int i = 0; i < squares.length; i++){
                if(squares[i] instanceof PropertySquare && ((PropertySquare) squares[i]).getColor().equals(color)) {
                    number++;
                }
            }

            if(number <= 1){
                color = propertyColor[j];
            }
            else {
                break;
            }
        }


        for(int i = 0; i < squares.length; i++){
            if(squares[i] instanceof PropertySquare && ((PropertySquare) squares[i]).getColor().equals(color)){
                ((PropertySquare)squares[i]).setOwner(player);
                System.out.println("Player owns: " + squares[i].getSquareName() + ", square ID: " + squares[i].getSquareID() +  ", color: "+ ((PropertySquare)squares[i]).getColor());
                a = i;
                break;
            }
        }

        System.out.println("Color " + color + " squares:");
        for (int i = 0; i < squares.length; i++){
            if(squares[i] instanceof PropertySquare && ((PropertySquare) squares[i]).getColor().equals(color)) {
                System.out.println("Square ID: " + squares[i].getSquareID());
                if(((PropertySquare)squares[i]).getHasOwner()){
                    System.out.println("Owner = " + ((PropertySquare)squares[i]).getOwner().getPlayerName());
                }
            }
        }

        assertTrue("hasItAll method does not work correctly.", !player.hasItAll((PropertySquare) squares[a], board));
    }


    @Test
    public void testPlayerMoney(){
        controlPlayerMoney(player, intStartMoney);
    }

    @Test
    public void testPlayerName(){
        controlPlayerName(player, "Minel");
    }

    void controlPlayerName(Player player, String name){
        assertEquals("Player name is not assigned properly.", player.getPlayerName(), name);
    }
    void controlPlayerMoney(Player player, int money){
        assertEquals("Player money is not assigned properly.", player.getMoney().getCurrentMoney(), money);
    }
}