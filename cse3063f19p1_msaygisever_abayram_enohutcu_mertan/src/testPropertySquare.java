
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class testPropertySquare {
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
    Player player = new Player("Minel", 200, moveDice, choiceDice, mpGame);



    @Test
    public void testOwnerPlayer(){
        board.setSquareList();
        ((PropertySquare) board.getSquareList()[3]).setOwner(player);
        assertEquals("Error occured while setting owner.", ((PropertySquare) board.getSquareList()[3]).getOwner(), player);
    }

    @Test
    public void testHasOwner(){
        board.setSquareList();
        ((PropertySquare) board.getSquareList()[3]).setOwner(player);
        assertTrue("Error occured while setting owner.", ((PropertySquare) board.getSquareList()[3]).getHasOwner());
    }

    @Test
    public void testBuyHouse(){

        board.setGoToJailNumber(0);
        board.setSquareList();
        player.getMoney().increaseMoney(1000);

        mpGame.setThreshold(1);
        player.buyProperty((PurchasableSquare) board.getSquareList()[1], mpGame);
        player.buyProperty((PurchasableSquare) board.getSquareList()[3], mpGame);

        player.buyHouseOrHotel((PropertySquare) board.getSquareList()[3], mpGame, board);
        assertEquals(1, player.getHouseCount());
    }

    @Test
    public void testBuyHotel(){
        board.setGoToJailNumber(0);
        mpGame.setThreshold(0);
        board.setSquareList();
        player.getMoney().increaseMoney(10000);
        player.buyProperty((PurchasableSquare) board.getSquareList()[1], mpGame);
        player.buyProperty(((PropertySquare) board.getSquareList()[3]), mpGame);

        player.buyHouseOrHotel(((PropertySquare) board.getSquareList()[3]), mpGame, board);
        player.buyHouseOrHotel(((PropertySquare) board.getSquareList()[3]), mpGame, board);
        player.buyHouseOrHotel(((PropertySquare) board.getSquareList()[3]), mpGame, board);
        player.buyHouseOrHotel(((PropertySquare) board.getSquareList()[3]), mpGame, board);
        player.buyHouseOrHotel(((PropertySquare) board.getSquareList()[3]), mpGame, board);
        assertEquals(1, player.getHotelCount());
    }


    @Test
    public void testSellHouse(){
        board.setGoToJailNumber(0);
        board.setSquareList();
        player.getMoney().increaseMoney(1000);

        mpGame.setThreshold(1);
        player.buyProperty((PurchasableSquare) board.getSquareList()[1], mpGame);
        player.buyProperty((PurchasableSquare) board.getSquareList()[3], mpGame);
        player.buyHouseOrHotel(((PropertySquare) board.getSquareList()[3]), mpGame, board);
        ((PropertySquare) board.getSquareList()[3]).sellHouse(player, mpGame);
        assertEquals(0, player.getHouseCount());
    }

}