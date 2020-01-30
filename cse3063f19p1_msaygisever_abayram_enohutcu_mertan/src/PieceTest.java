import org.junit.Test;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PieceTest {

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



    String pieceName = "DOG";
    int squareID = 13;
    String squareName = "Over the Garden Wall";
    int fine = 20;
    Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
            transportFine, transportPrice, taxFine, taxSquares, goToJailNumber,jailFine,rent1,rent2,rent3,rent4,hotelRent,mortgage,pricePerHouse,houseNumber,
            hotelNumber,comChest,chanceCard,utilityMortgage,transportMortgage);
    Square square = new TaxSquare(squareID,squareName, fine);
    Piece piece = new Piece(pieceName,board);


    @Test
    public void TestGetPieceType() {
        assertEquals("Piece type is not assigned properly.", piece.getPieceType(),pieceName);
    }

    @Test
    public void TestSetSquare() {
        piece.setSquare(this.square);
        assertEquals("Piece position set is not assigned properly.", this.square,piece.getSquare());
    }

}