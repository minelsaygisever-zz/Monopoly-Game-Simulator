import java.util.ArrayList;
import java.util.Collections;

// Simulation continues in this class
public class MonopolyGame {
    //String array which contains name of players
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"};
    //String array which contains piece types of players
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"};
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private Player[] playerOldList; //Not ordered player list
    private int playerSize, threshold, startMoney;
    private Piece[] pieceList; //Piece array which contains Piece objects.
    private int goMoney; //Given money amount while passing through GO square
    private int cycle; //Cycle counter
    private int[] dices; //Dice values array to order turns of players
    private int jailFine; // Fine for go out from the jail
    private int goToJailNumber; // Number of GoToJail squares
    private int currentPlayerSize; // Non-bankrupted player number
    private int[] rent1; // rent values of 1 house
    private int[] rent2; // rent values of 2 houses
    private int[] rent3; // rent values of 3 houses
    private int[] rent4; // rent values of 4 houses
    private int[] hotel; // rent values of a hotel
    private int[] mortgage; // mortgage values of properties
    private int[] pricePerHouse; // price of a house to buy
    private int freeHouseNumber; // total number of houses in the game
    private int freeHotelNumber; // total number of hotels in the game
    private Dice moveDice; // Dice for moving
    private Dice choiceDice; // Dice for making a choice
    private ArrayList<House> houseList; // List that contains house objects
    private ArrayList<Hotel> hotelList; // List that contains hotel objects
    private static MonopolyGame instance; // instance of MonopolyGame (Singleton Design Pattern)
    private int[] utilityMortgage; // mortgage values of utilities
    private int[] transportMortgage; // mortgage values of transports

    //Constructor of MonopolyGame Class calling from Main Class. It is private because of the Singleton Design Pattern
    private MonopolyGame(int playerSize, int threshold, int startMoney, int goMoney, String[] properties,
                         int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                         int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                         int[] transportPrice, int[] taxFine, String[] taxSquares, int jailFine, int goToJailNumber,
                         int[] rent1, int[] rent2, int[] rent3, int[] rent4, int[] hotel, int[] mortgage, int[] pricePerHouse,
                         int houseNumber, int hotelNumber, ArrayList<String> comChest, ArrayList<String> chanceCard,
                         int[] utilityMortgage, int[] transportMortgage) {
        checkPlayerSize(playerSize);
        this.playerSize = playerSize;
        this.threshold = threshold;
        this.startMoney = startMoney;
        this.board = new Board(properties, propertyFine, propertyPrice, propertyColor,
                utilityName, utilityRate, utilityPrice, transportName, transportFine, transportPrice,
                taxFine, taxSquares, goToJailNumber, jailFine, rent1, rent2, rent3, rent4, hotel, mortgage,
                pricePerHouse, houseNumber, hotelNumber, comChest, chanceCard, utilityMortgage, transportMortgage); //Create Board object.
        this.goMoney = goMoney; //Assign GO money.
        this.jailFine = jailFine;
        this.goToJailNumber = goToJailNumber;
        this.currentPlayerSize = playerSize;
        this.freeHotelNumber = hotelNumber;
        this.freeHouseNumber = houseNumber;
        this.moveDice = new Dice();
        this.choiceDice = new Dice();
        this.houseList = new ArrayList<>();
        this.hotelList = new ArrayList<>();
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.mortgage = mortgage;
        this.pricePerHouse = pricePerHouse;
        this.hotel = hotel;
        this.utilityMortgage = utilityMortgage;
        this.transportMortgage = transportMortgage;

        for (int i = 0; i < houseNumber; i++) {
            this.houseList.add(new House());
        }

        for (int i = 0; i < hotelNumber; i++) {
            this.hotelList.add(new Hotel());
        }


        Collections.shuffle(board.getChanceCard());
        Collections.shuffle(board.getComChest());
    }

    // getInstance method for create a MonopolyGame object. It is static because of the Singleton Design Pattern
    static public MonopolyGame getInstance(int playerSize, int threshold, int startMoney, int goMoney, String[] properties,
                                           int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                                           int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                                           int[] transportPrice, int[] taxFine, String[] taxSquares, int jailFine, int goToJailNumber,
                                           int[] rent1, int[] rent2, int[] rent3, int[] rent4, int[] hotel, int[] mortgage, int[] pricePerHouse,
                                           int houseNumber, int hotelNumber, ArrayList<String> comChest, ArrayList<String> chanceCard,
                                           int[] utilityMortgage, int[] transportMortgage) {

        if (instance == null)
            instance = new MonopolyGame(playerSize, threshold, startMoney, goMoney, properties,
                    propertyFine, propertyPrice, propertyColor, utilityName,
                    utilityRate, utilityPrice, transportName, transportFine,
                    transportPrice, taxFine, taxSquares, jailFine, goToJailNumber,
                    rent1, rent2, rent3, rent4, hotel, mortgage, pricePerHouse,
                    houseNumber, hotelNumber, comChest, chanceCard, utilityMortgage, transportMortgage);

        return instance;

    }

    // Play method to play the game.
    public void Play() {

        // Create variables for dice results
        int diceValue;
        int firstDice;
        int secondDice;

        // Call some functions to create necessary objects
        createPlayerList();
        createPieceList();
        createPlayerOldList();
        createDices();

        // Set the board
        board.setSquareList();

        // Determine player turns
        for (int i = 0; i < playerSize; i++) {
            playerOldList[i] = new Player(NAMES[i], startMoney, moveDice, choiceDice, this); //Create first player list (not ordered).
            pieceList[i] = new Piece(PIECES[i], this.board); //Create Piece List.
            playerOldList[i].setPiece(pieceList[i]); //Set players' pieces'.
            //Roll dices for each player to set the turn order.
            dices[i] = playerOldList[i].getMoveDice().getFirstRandomValue()
                    + playerOldList[i].getMoveDice().getSecondRandomValue();
        }

        // Print some infos before the start
        printDiceRoll();
        rollDiceBeginning();
        printPlayerAndPiece();

        boolean check = true;
        boolean gameFinish;

        Square tempSquare;

        // The simulation continues in this loop
        while (check) {
            cycle++; //Increase cycle counter for each cycle.
            System.out.println("-----NEXT CYCLE-----");
            System.out.println("Cycle: " + cycle + "\n");

            //Check players.
            for (int i = 0; i < playerSize; ) {

                if (playerList[i] == null) {
                    i++;
                    continue;
                }

                if(playerList[i].getIsBankrupted()){
                    playerList[i] = null;
                    currentPlayerSize--;
                    if (currentPlayerSize == 1) {
                        check = false;
                        break;
                    }
                    i++;
                    continue;
                }

                //Check player for get back his/her mortgaged property. If he/she has enough money to get back, take it back.
                if (playerList[i].getHasMortgaged()) {
                    playerList[i].getBackMortgaged(this);
                }

                playerList[i].reportBeforeRoll();

                // Current player roll dices
                playerList[i].rollMoveDice();
                firstDice = playerList[i].getMoveDice().getFirstValue(); //Roll first dice
                secondDice = playerList[i].getMoveDice().getSecondValue(); //Roll second dice
                diceValue = firstDice + secondDice; //Add dice values to move the player.

                // Current square that player lands on
                tempSquare = playerList[i].getPiece().getSquare();

                // If player in the jail
                if (tempSquare instanceof JailSquare && playerList[i].isInJail()) {

                    // If player has go out from jail card, use it.
                    if (playerList[i].getChanceOutOfJail() != null) {
                        playerList[i].setInJail(false);
                        playerList[i].setOutOfJailCard(false);
                        playerList[i].getChanceOutOfJail().setHasOwner(false);
                        playerList[i].setChanceOutOfJail(null);
                        System.out.println(playerList[i].getPlayerName() + " used to change go out of jail card!!");
                    }

                    // If player has go out from jail card, use it.
                    else if (playerList[i].getCommunityOutOfJail() != null) {
                        playerList[i].setInJail(false);
                        playerList[i].setOutOfJailCard(false);
                        playerList[i].getCommunityOutOfJail().setHasOwner(false);
                        playerList[i].setCommunityOutOfJail(null);
                        System.out.println(playerList[i].getPlayerName() + " used to community go out of jail card!!");
                    }

                    //If player has no any card to go out from jail, then use the choice dice.
                    else {
                        if (firstDice != secondDice) {

                            // Check if player wants to pay fine and go out to jail
                            playerList[i].rollChoiceDice();

                            if (playerList[i].getChoiceDice().getTotal() > threshold) {
                                ((JailSquare) tempSquare).playerWantsPayForJail(playerList[i]);
                            } else {

                                // If player does not want pay for jail to go out, iterate turn to other player.
                                if(((JailSquare) tempSquare).playerDoesntWantPayForJail(playerList[i], board, i)){
                                    i++;
                                    break;
                                }

                                // Check player is bankrupted or not.
                                if (playerList[i].getIsBankrupted()) {
                                    currentPlayerSize--;

                                    playerList[i] = null;

                                    // If there is only one person in the game, finish the game.
                                    if (currentPlayerSize == 1) {
                                        check = false;
                                        break;
                                    }
                                    i++;
                                    continue;
                                }
                            }
                        } else {
                            playerList[i].setInJail(false);
                        }
                    }

                }

                //Move the player according to dice values.
                playerList[i].getPiece().move(diceValue, playerList[i], goMoney);
                System.out.println("\nFirst dice is : " + firstDice + " Second dice is : " + secondDice);
                System.out.println("Sum of dices is " + diceValue);
                playerList[i].reportAfterRoll();

                tempSquare = playerList[i].getPiece().getSquare();

                // Check current square and end of the game
                if (tempSquare instanceof PurchasableSquare) {
                    if (((PurchasableSquare) tempSquare).getHasOwner() && !((PurchasableSquare) tempSquare).getIsMortgaged()) {

                        if (((PurchasableSquare) tempSquare).getOwner() == playerList[i]) {
                            if (tempSquare instanceof PropertySquare)
                                playerList[i].buyHouseOrHotel((PropertySquare) tempSquare, this, board);
                        } else {
                            ((PurchasableSquare) tempSquare).payRent(playerList[i], board);
                        }

                        if (playerList[i].getIsBankrupted()) {
                            currentPlayerSize--;
                            playerList[i] = null;

                            if (currentPlayerSize == 1) {
                                check = false;
                                break;
                            }
                            i++;
                            continue;
                        }

                    } else {
                        playerList[i].buyProperty((PurchasableSquare) tempSquare, this);
                    }
                }

                // If player's new square is Go To Jail Square
                if (tempSquare instanceof GoToJailSquare) {
                    ((GoToJailSquare) playerList[i].getPiece().getSquare()).goToJail(playerList[i].getPiece().getSquare(), board.getJailSquare());
                    playerList[i].getPiece().setSquare(board.getSquareList()[10]);
                    playerList[i].setJailTurnCounter(0);
                    playerList[i].setInJail(true);
                    System.out.println("!!" + playerList[i].getPlayerName() + " HAS GONE TO JAIL!!");
                }

                if (tempSquare instanceof ChanceSquare) {
                    System.out.println("CHANCE CARD");
                    System.out.println("Card id=" + board.getChanceCard().get(0).getId() + "\n" + board.getChanceCard().get(0).getAction());
                    takeChangeCard(board.getChanceCard().get(0), playerList[i]);
                }

                if (tempSquare instanceof CommunityChestSquare) {
                    System.out.println("COMMUNITY CARD");
                    System.out.println("Card id=" + board.getComChest().get(0).getId() + "\n" + board.getComChest().get(0).getAction());
                    takeCommunityCard(board.getComChest().get(0), playerList[i]);
                }

                // If player's new square is Tax Square
                if (tempSquare instanceof TaxSquare) {
                    ((TaxSquare) tempSquare).payTax(playerList[i], board);

                    if (playerList[i].getIsBankrupted()) {
                        currentPlayerSize--;
                        playerList[i] = null;

                        if (currentPlayerSize == 1) {
                            check = false;
                            break;
                        }
                        i++;
                        continue;
                    }
                }

                if (playerList[i] != null) {
                    playerList[i].reportAfterAction();
                }

                if (i != playerSize - 1) {
                    System.out.println("***********");
                }

                //Check if dices are equal each other.
                if (firstDice != secondDice) {
                    i++;
                }

                if (currentPlayerSize == 1) {
                    check = false;
                    break;
                }
            }
        }

        // Print the winner
        for (int i = 0; i < playerSize; i++) {
            if (playerList[i] != null) {
                System.out.println("WINNER : " + playerList[i].getPlayerName());
            }
        }
    }

    //Create player list.
    private void createPlayerList() {
        this.playerList = new Player[this.playerSize];
    }

    //Create piece list.
    private void createPieceList() {
        this.pieceList = new Piece[this.playerSize];
    }

    //Create first player list.
    private void createPlayerOldList() {
        this.playerOldList = new Player[this.playerSize];
    }

    //Create dices.
    private void createDices() {
        this.dices = new int[this.playerSize];
    }

    //Print dice values.
    private void printDiceRoll() {
        System.out.println("Players roll dices to determine their turns.");
        for (int i = 0; i < playerSize; i++) {
            System.out.println(playerOldList[i].getPlayerName() + " rolled " + dices[i] + ".");
        }
        System.out.println("----------------");
    }

    //Roll the dice for turn order and set the turn order of players from biggest to smallest.
    private void rollDiceBeginning() {
        for (int i = 0; i < playerSize; i++) {
            int biggest = 0;
            int place = 0;

            for (int a = 0; a < playerSize; a++) {
                if (dices[a] >= biggest) {
                    biggest = dices[a];
                    place = a;
                }
            }
            playerList[i] = playerOldList[place];
            playerList[i].setTurn(i + 1);
            playerList[i].setPiece(pieceList[i]);
            dices[place] = 0;
        }
    }

    //Print Player name and Piece name.
    private void printPlayerAndPiece() {
        for (int i = 0; i < playerSize; i++) {
            System.out.print("Player : " + playerList[i].getPlayerName() + " -- ");
            System.out.println("Piece: " + pieceList[i].getPieceType());
        }
    }

    // Check if the given player size is wrong
    private void checkPlayerSize(int intPlayerSize) {
        if (intPlayerSize < 2 || intPlayerSize > 8) {
            System.out.println("Player size must be from 2 to 8.");
            System.exit(1);
        }
    }

    //Drawing a chance card method
    public void takeChangeCard(ChanceCard changeCard, Player player) {
        changeCard.chooseAction(changeCard.getId(), player, board, this);
        board.getChanceCard().add(board.getChanceCard().get(0));
        board.getChanceCard().remove(0);
    }

    //Drawing a community chest card method
    public void takeCommunityCard(CommunityChest communityCard, Player player) {
        communityCard.chooseAction(communityCard.getId(), player, board, this);
        board.getComChest().add(board.getComChest().get(0));
        board.getComChest().remove(0);
    }

    // Getter and setter methods
    public Player[] getPlayerList() {
        return playerList;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getGoMoney() {
        return goMoney;
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

}
