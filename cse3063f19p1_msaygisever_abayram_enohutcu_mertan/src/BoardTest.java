import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class BoardTest {
    FileReaderJson fileReader = new FileReaderJson();


    String[] properties = fileReader.getProperties();
    int[] propertyFine = fileReader.getPropertyFine();
    int[] propertyPrice = fileReader.getPropertyPrice();
    String[] propertyColor = fileReader.getPropertyColor();
    String[] utilityName = fileReader.getUtilityName();
    int[] utilityRate = fileReader.getUtilityRate();
    int[] utilityPrice = fileReader.getUtilityPrice();
    String[] transportName = fileReader.getTransportName();
    int[] transportFine = fileReader.getTransportFine();
    int[] transportPrice = fileReader.getTransportPrice();
    int[] taxFine = fileReader.getTaxFine();
    String[] taxSquares = fileReader.getTaxSquares();
    int goToJailNumber = fileReader.getIntGoToJailNumber();
    int jailFine = fileReader.getIntJailFine();
    int houseNumber = fileReader.getMaxHouseNumber();
    int hotelNumber = fileReader.getMaxHotelNumber();
    int[] rent1 = fileReader.getRent1();
    int[] rent2 = fileReader.getRent2();
    int[] rent3 = fileReader.getRent3();
    int[] rent4 = fileReader.getRent4();
    int[] hotelRent = fileReader.getHotel();
    int[] mortgage = fileReader.getMortgage();
    int[] pricePerHouse = fileReader.getPricePerHouse();
    int[] utilityMortgage = fileReader.getUtilityMortgage();
    int[] transportMortgage = fileReader.getTransportMortgage();
    ArrayList<String> comChest = fileReader.getCommunityChestCards();
    ArrayList<String> chanceCard = fileReader.getChanceCards();

    @Test
    public void testGoSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber,jailFine,rent1,rent2,rent3,rent4,hotelRent,mortgage,pricePerHouse,houseNumber,
                hotelNumber,comChest,chanceCard,utilityMortgage,transportMortgage);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertEquals("First square is not GO square.", squares[0].getSquareName() , "GO");
    }

    @Test
    public void testGoJailSquares(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber,jailFine,rent1,rent2,rent3,rent4,hotelRent,mortgage,pricePerHouse,houseNumber,
                hotelNumber,comChest,chanceCard,utilityMortgage,transportMortgage);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        int evaluatedGoToJailNumber = 0;

        for(int i = 0; i < squares.length; i++){
            if(squares[i].getSquareName() == "GOTOJAIL"){
                evaluatedGoToJailNumber++;
            }
        }

        assertEquals("Created the wrong amount of GOTOJAIL squares.", goToJailNumber, evaluatedGoToJailNumber);
    }

    @Test
    public void testJailSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber,jailFine,rent1,rent2,rent3,rent4,hotelRent,mortgage,pricePerHouse,houseNumber,
                hotelNumber,comChest,chanceCard,utilityMortgage,transportMortgage);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertEquals("10th square is not JAIL square.",squares[10].getSquareName() , "JAIL");
    }

    @Test
    public void testFreeParkingSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber,jailFine,rent1,rent2,rent3,rent4,hotelRent,mortgage,pricePerHouse,houseNumber,
                hotelNumber,comChest,chanceCard,utilityMortgage,transportMortgage);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertEquals("20th square is not FREEPARKING square.",squares[20].getSquareName() , "FREEPARKING");
    }



}
