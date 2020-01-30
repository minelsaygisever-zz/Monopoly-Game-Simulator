import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

//Main class to play the game
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
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
        MonopolyGame monopolyGame = MonopolyGame.getInstance(intPlayerSize, intThreshold, intStartMoney, intGoMoney,
                properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, intJailFine, intGoToJailNumber, rent1, rent2, rent3, rent4, hotel,
                mortgage, pricePerHouse, maxHouseNumber, maxHotelNumber, communityChest, chanceCards, utilityMortgage, transportMortgage);
        // Call the Play function of MonopolyGame to start the game
        monopolyGame.Play();

    }
}
