import java.util.ArrayList;
import java.util.Collections;

// This class represents a Monopoly board
public class Board {

    private final int BOARD_SIZE = 40; // Board size. It is fixed to 40.
    private Square[] squareList; // Square list to keep all squares on the board.
    private String[] properties; // String array that contains property names.
    private int[] propertyFine; // Integer array that contains properties fine values.
    private int[] propertyPrice; // Integer array that contains properties price for buying functionality
    private String[] propertyColor; // String array that contains properties colors.
    private String[] utilityName; // String array that contains utilities names.
    private int[] utilityRate; // Integer array that contains utilities rate.
    private int[] utilityPrice; // Integer array that contains utilities prices.
    private String[] transportName; // String array that contains transport names.
    private int[] transportFine; // Integer array that contains transports fine values.
    private int[] transportPrice; // Integer array that contains transports prices for buying.
    private int[] taxFine; // Integer array that contains tax squares fine values.
    private String[] taxSquares; // String array that contains type of taxes.
    private int goToJailNumber; // Go to jail squares numbers in the board.
    private int jailFine; // Amount for go out from the jail
    private int houseNumber;
    private int hotelNumber;
    private ArrayList<CommunityChest> comChest;
    private ArrayList<ChanceCard> chanceCard;
    private int[] rent1;
    private int[] rent2;
    private int[] rent3;
    private int[] rent4;
    private int[] hotelRent;
    private int[] mortgage;
    private int[] pricePerHouse;
    private int[] utilityMortgage;
    private int[] transportMortgage;


    // Default constructor of Board Class.
    public Board(String[] properties,
                 int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                 int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                 int[] transportPrice, int[] taxFine, String[] taxSquares, int goToJailNumber, int jailFine,
                 int[] rent1, int[] rent2, int[] rent3, int[] rent4, int[] hotelRent, int[] mortgage, int[] pricePerHouse,
                 int houseNumber, int hotelNumber, ArrayList<String> comChest, ArrayList<String> chanceCard,
                 int[] utilityMortgage, int[] transportMortgage) {
        this.squareList = new Square[BOARD_SIZE]; // Set the square list's size to board size.
        this.properties = properties;
        this.propertyFine = propertyFine;
        this.propertyColor = propertyColor;
        this.propertyPrice = propertyPrice;
        this.utilityName = utilityName;
        this.utilityPrice = utilityPrice;
        this.utilityRate = utilityRate;
        this.transportFine = transportFine;
        this.transportName = transportName;
        this.transportPrice = transportPrice;
        this.taxFine = taxFine;
        this.taxSquares = taxSquares;
        this.goToJailNumber = goToJailNumber;
        this.jailFine = jailFine;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.hotelRent = hotelRent;
        this.mortgage = mortgage;
        this.pricePerHouse = pricePerHouse;
        this.houseNumber = houseNumber;
        this.hotelNumber = hotelNumber;
        this.comChest = new ArrayList<>();
        this.chanceCard = new ArrayList<>();
        this.transportMortgage = transportMortgage;
        this.utilityMortgage = utilityMortgage;

        setChanceCards(chanceCard);
        setCommunityCards(comChest);

    }


    // Getter method for square list
    public Square[] getSquareList() {
        return this.squareList;
    }

    // Set the square list
    public void setSquareList() {

        int id = 0;
        int propertyIndex = 0;
        int colorIndex = 0;
        int colorCount = 0;
        int transportIndex = 0;
        int utilityIndex = 0;
        int taxIndex = 0;

        while (true) {

            //Set the first square as GO square
            if (id == 0) {
                this.squareList[id] = new GoSquare(0, "GO");
            }

            //Set the squares with id number 2,17,33 as COMMUNITY square.
            else if (id == 2 || id == 17 || id == 33) {
                this.squareList[id] = new CommunityChestSquare(comChest, "COMMUNITY", id);
            }

            //Set the squares with id number 7,22,36 as CHANCE square.
            else if (id == 7 || id == 22 || id == 36) {
                this.squareList[id] = new ChanceSquare(chanceCard, "CHANCE", id);
            }

            //Set the squares with id number 4 and 38 as TAX square.
            else if (id == 4 || id == 38) {
                this.squareList[id] = new TaxSquare(id, taxSquares[taxIndex], taxFine[taxIndex]);
                taxIndex++;
            }

            //Set the squares with id number 10 as JAIL square.
            else if (id == 10) {
                this.squareList[id] = new JailSquare(id, "JAIL", jailFine);
            }

            //Set the 20. square as FREE PARKING square.
            else if (id == 20) {
                this.squareList[id] = new FreeParkingSquare(id, "FREEPARKING");
            }

            //Set the squares with id number 5,15,25 and 35 as TRANSPORT sqaures.
            else if (id == 5 || id == 15 || id == 25 || id == 35) {

                this.squareList[id] = new TransportSquare(id, transportName[transportIndex],
                        transportFine[transportIndex], transportPrice[transportIndex], transportMortgage[transportIndex]);
                transportIndex++;
            }

            //Set the squares with id number 12 and 28 as UTILITY square.
            else if (id == 12 || id == 28) {
                this.squareList[id] = new UtilitySquare(id, utilityName[utilityIndex],
                        utilityRate[utilityIndex], utilityPrice[utilityIndex], utilityMortgage[utilityIndex]);
                utilityIndex++;
            }

            //Set the 30. square as GO TO JAIL square
            else if (id == 30) {
                //GO TO JAIL
                this.squareList[id] = new GoToJailSquare(id, "GOTOJAIL");
            }

            //Set the remaining squares as PROPERTY squares.
            else {
                this.squareList[id] = new PropertySquare(id, properties[propertyIndex], propertyFine[propertyIndex],
                        propertyColor[colorIndex], propertyPrice[propertyIndex], pricePerHouse[propertyIndex], hotelRent[propertyIndex],
                        rent1[propertyIndex], rent2[propertyIndex], rent3[propertyIndex], rent4[propertyIndex], mortgage[propertyIndex]);
                propertyIndex++;
                colorIndex++;
            }

            id++;

            if (id == 40) {
                break;
            }
        }

        //Place GO TO JAIL squares with the given input value of go to jail.
        while (true) {
            int rand = (int) (Math.random() * 38) + 1;
            if (goToJailNumber == 0) {
                System.out.println("goToJailNumber can be at least 1, the variable was set to 1.");
                goToJailNumber = 1;
                break;
            } else if (goToJailNumber == 1) {
                break;
            } else if (squareList[rand] instanceof PropertySquare || squareList[rand] instanceof TransportSquare || squareList[rand] instanceof UtilitySquare) {
                this.squareList[rand] = new GoToJailSquare(rand, "GOTOJAIL");
                goToJailNumber--;
            }
        }

        for (Square i : squareList) {
            System.out.print(i.getSquareID());
            System.out.print(" : " + i.getSquareName());
            if (i instanceof PropertySquare) {
                System.out.println(" Color : " + ((PropertySquare) i).getColor());
            } else {
                System.out.println();
            }
        }
    }

    public void setChanceCards(ArrayList<String> infoChance) {

        for (int i = 0; i < infoChance.size(); i++) {
            chanceCard.add(new ChanceCard(i + 1, infoChance.get(i)));
        }

    }

    public void setCommunityCards(ArrayList<String> infoCommunity) {

        for (int i = 0; i < infoCommunity.size(); i++) {
            comChest.add(new CommunityChest(i + 1, infoCommunity.get(i)));
        }

    }

    public JailSquare getJailSquare() {
        return (JailSquare) squareList[10];
    }


    public ArrayList<CommunityChest> getComChest() {
        return comChest;
    }

    public ArrayList<ChanceCard> getChanceCard() {
        return chanceCard;
    }

    public void setGoToJailNumber(int goToJailNumber) {
        this.goToJailNumber = goToJailNumber;
    }
}
