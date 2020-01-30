import java.util.ArrayList;

//Player class.
public class Player {

    private Piece piece; //Piece types
    private Money money; //Money amount
    private Square square; //Square object
    private String playerName; //Player name
    private int turn; //Turn
    private boolean isInJail;
    private int jailTurnCounter;
    private ArrayList<PurchasableSquare> properties; // List of properties that player has
    private ArrayList<PurchasableSquare> transportList; // List of transports that player has
    private ArrayList<PurchasableSquare> utilityList; // List of utilities that player has
    private Dice moveDice; // Dice for moving
    private Dice choiceDice; // Dice for make a choice
    private boolean isBankrupted; // check for is player bankrupted
    private boolean outOfJailCard; // check for player has out of jail card
    private int houseCount; // house count that player has
    private int hotelCount; // hotel count that player has
    private CommunityChest communityOutOfJail; // out of jail card in the community chest cards
    private ChanceCard chanceOutOfJail; // out of jail card in the chance cards
    private boolean hasMortgaged;
    private MonopolyGame mpGame;

    //Constructor of Player Class with given parameters.
    public Player(String playerName, int startMoney, Dice moveDice, Dice choiceDice, MonopolyGame mpGame) {
        this.playerName = playerName;
        this.money = new Money(startMoney);
        this.properties = new ArrayList<PurchasableSquare>();
        this.utilityList = new ArrayList<PurchasableSquare>();
        this.transportList = new ArrayList<PurchasableSquare>();
        this.isInJail = false;
        this.moveDice = moveDice;
        this.choiceDice = choiceDice;
        this.isBankrupted = false;
        this.outOfJailCard = false;
        this.hotelCount = 0;
        this.houseCount = 0;
        this.hasMortgaged = false;
        this.mpGame = mpGame;
    }


    //Print report about current player before roll the dice
    public void reportBeforeRoll() {
        System.out.println("Player : " + this.playerName);
        System.out.println("Turn : " + this.turn);
        System.out.println("Current balance : " + this.money.getCurrentMoney());
        System.out.println("Location : Square " + this.piece.getSquare().getSquareID());
        System.out.println("Type of square : " + this.piece.getSquare().getSquareName());
        System.out.println("House count: " + this.getHouseCount());
        System.out.println("Hotel count: " + this.getHotelCount());
    }

    //Print report about current player after roll the dice
    public void reportAfterRoll() {
        System.out.println();
        System.out.println("New location : Square " + this.piece.getSquare().getSquareID());
        if (this.piece.getSquare() instanceof PurchasableSquare) {
            if (((PurchasableSquare) this.piece.getSquare()).getHasOwner()) {
                if (this.piece.getSquare() instanceof UtilitySquare) {
                    System.out.println("Utility count : " + ((UtilitySquare) this.piece.getSquare()).getOwner().utilityList.size());
                }
                if (this.piece.getSquare() instanceof TransportSquare) {
                    System.out.println("Transport count : " + ((TransportSquare) this.piece.getSquare()).getOwner().transportList.size());
                }
                System.out.println("Owner : " + ((PurchasableSquare) this.piece.getSquare()).getOwner().getPlayerName());
            } else {
                System.out.println("-This square has not an owner!-");
            }
        }
        System.out.println("Type of square : " + this.piece.getSquare().getSquareName());
        if (this.piece.getSquare() instanceof TaxSquare) {
            System.out.println("The amount of tax : " + ((TaxSquare) this.piece.getSquare()).getFine());
        }
        System.out.println();
    }

    // Print report after any action
    public void reportAfterAction() {
        if(isBankrupted == false){
            System.out.println(playerName + "'s New balance : " + this.money.getCurrentMoney());
            System.out.println();
        }
    }

    // Check if player has all same property colors
    public boolean hasItAll(PropertySquare square, Board board) {
        boolean hasItAll = true;
        for(int i = 0; i < board.getSquareList().length; i++){
            if(board.getSquareList()[i] instanceof PropertySquare && ((PropertySquare)board.getSquareList()[i]).getColor() == square.getColor() && square.getOwner() != this){
                hasItAll = false;
            }
        }

        /*
        PropertySquare tempProp;
        int tempIdPos;
        int tempIdNeg;
        for (int i = 1; i <= 5; ) {

            if (square.getSquareID() + i >= 40) {
                tempIdPos = square.getSquareID() + i - 40;
            } else {
                tempIdPos = square.getSquareID() + i;
            }

            if (board.getSquareList()[tempIdPos] instanceof PropertySquare) {
                tempProp = (PropertySquare) board.getSquareList()[tempIdPos];
                if (tempProp.getColor().equals(square.getColor())) {
                    if (tempProp.getHasOwner()) {
                        if (!(tempProp.getOwner().equals(square.getOwner()))) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }

            if (square.getSquareID() - i < 0) {
                tempIdNeg = square.getSquareID() - i + 40;
            } else {
                tempIdNeg = square.getSquareID() - i;
            }

            if (board.getSquareList()[tempIdNeg] instanceof PropertySquare) {
                tempProp = (PropertySquare) board.getSquareList()[tempIdNeg];
                if (tempProp.getColor().equals(square.getColor())) {
                    if (tempProp.getHasOwner()) {
                        if (!(tempProp.getOwner().equals(square.getOwner()))) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            i++;
        }
        return true;*/

        return hasItAll;

    }



    // Mortgage property if player has no money.
    public boolean mortgageProperty() {
        if (properties.size() == 0) {
            return false;
        }

        PurchasableSquare tempSquare = properties.get(0);

        for (int i = 1; i < properties.size(); i++) {
            if (tempSquare.getMortgage() > properties.get(i).getMortgage() && properties.get(i).getIsMortgaged() == false && properties.get(i).getOwner() == this) {
                if (properties.get(i) instanceof PropertySquare) {
                    if (!((PropertySquare) properties.get(i)).getHasAllColors()) {
                        tempSquare = properties.get(i);
                    }
                } else {
                    tempSquare = properties.get(i);
                }
            }
        }

        if (tempSquare.getIsMortgaged() == false) {
            money.increaseMoney(tempSquare.getMortgage());
            tempSquare.setMortgaged(true);
            this.hasMortgaged = true;
            System.out.println("PROPERTY MORTGAGE " + tempSquare.getSquareName());
            System.out.println(playerName + "'s new balance = " + money.getCurrentMoney());

        } else {
            System.out.println(playerName + " has not got any un-mortgaged property!!");
            return false;
        }

        return true;

    }

    // Mortgage set of colors if player needs money.
    public boolean mortgageSets() {
        if (properties.size() == 0) {
            return false;
        }

        PurchasableSquare tempSquare = properties.get(0);

        for (int i = 1; i < properties.size(); i++) {
            if (tempSquare.getMortgage() > properties.get(i).getMortgage() && properties.get(i).getIsMortgaged() == false) {

                tempSquare = properties.get(i);
            }
        }

        if (tempSquare.getIsMortgaged() == false) {
            money.increaseMoney(tempSquare.getMortgage());
            tempSquare.setMortgaged(true);
            this.hasMortgaged = true;
            System.out.println("PROPERTY MORTGAGE " + tempSquare.getSquareName());
            System.out.println(playerName + "'s new balance = " + money.getCurrentMoney());

        } else {
            System.out.println(playerName + " has not got any un-mortgaged sets!!");
            return false;
        }

        return true;

    }

    // Get back property from the mortgage.
    public void getBackMortgaged(MonopolyGame mpGame) {

        rollChoiceDice();
        boolean check = true;
        if (choiceDice.getTotal() > mpGame.getThreshold()) {
            for (int j = 0; j < properties.size(); j++) {
                if (properties.get(j).getIsMortgaged()) {
                    check = true;
                    if (money.getCurrentMoney() > properties.get(j).getMortgage()) {
                        check = false;
                        money.decreaseMoney(properties.get(j).getMortgage());
                        properties.get(j).setMortgaged(false);
                        System.out.println(playerName + " get back '" + properties.get(j).getSquareName() + "' from mortgage!");
                    }
                }
            }
        }

        hasMortgaged = check;
    }

    // Check properties that player has are set or not.
    public boolean isAllPropertiesSet(Board board) {
        boolean check = false;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i) instanceof PropertySquare) {
                if (hasItAll((PropertySquare) properties.get(i), board)) {
                    ((PropertySquare) properties.get(i)).setHasAllColors(true);
                }
            }
        }

        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i) instanceof PropertySquare) {
                if (((PropertySquare) properties.get(i)).getHasAllColors()) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
        }

        return check;
    }

    // Buy a property square
    public void buyProperty(PurchasableSquare pSquare, MonopolyGame mpGame) {
        // If square has not an owner
        // Player roll the choice dice
        this.rollChoiceDice();
        int choiceDiceValue = this.getChoiceDice().getTotal();

        // If player wants to take this square and has enough money to buy
        if (choiceDiceValue > mpGame.getThreshold() && this.getMoney().getCurrentMoney() > pSquare.getPrice()) {
            pSquare.setOwner(this);
            if (pSquare instanceof UtilitySquare) {
                this.addUtilityList(pSquare);
            }
            if (pSquare instanceof TransportSquare) {
                this.addTransportList(pSquare);
            }
            this.addProperty(pSquare);

            this.getMoney().decreaseMoney(pSquare.getPrice());
            System.out.println("***" + this.getPlayerName() + " BOUGHT " + pSquare.getSquareName() + "***");
        }
    }

    // Buy house our hotel
    public void buyHouseOrHotel(PropertySquare square, MonopolyGame mpGame, Board board) {
        this.rollChoiceDice();
        System.out.println("**SQUARE HOUSE COUNT: " + square.getHouseCount());
        System.out.println("**SQUARE HOTEL COUNT: " + square.getHotelCount());
        if (square.getHouseCount() < 4) {
            if (this.getMoney().getCurrentMoney() > square.getHousePrice() && this.getChoiceDice().getTotal() > mpGame.getThreshold() && this.hasItAll(square, board)) {

                for (int i = 0; i < mpGame.getHouseList().size(); i++) {
                    if (!mpGame.getHouseList().get(i).getHasOwner()) {
                        mpGame.getHouseList().get(i).setOwner(this);
                        mpGame.getHouseList().get(i).setSquare(square);
                        this.getMoney().decreaseMoney(square.getHousePrice());
                        square.increaseHouseCount();
                        this.houseCount++; // player için
                        System.out.println("SQUARE HOUSE COUNT: " + square.getHouseCount());
                        System.out.println("SQUARE HOTEL COUNT: " + square.getHotelCount());
                        System.out.println(playerName + " has bought a house on " + square.getSquareName());
                        break;
                    }
                    if (i == mpGame.getHouseList().size() - 1) {
                        System.out.println("There is no available house.");
                    }
                }
            }
        } else {
            if (this.getMoney().getCurrentMoney() > square.getHousePrice() && this.getChoiceDice().getTotal() > mpGame.getThreshold() && this.hasItAll(square, board)) {
                for (int i = 0; i < mpGame.getHouseList().size(); i++) {
                    if (mpGame.getHouseList().get(i).getSquare() == square) {
                        mpGame.getHouseList().get(i).setSquare(null);
                        square.decreaseHouseCount();
                        this.houseCount--; // player için
                    }
                }
                for (int i = 0; i < mpGame.getHotelList().size(); i++) {
                    if (!mpGame.getHotelList().get(i).getHasOwner()) {
                        mpGame.getHotelList().get(i).setOwner(this);
                        mpGame.getHotelList().get(i).setSquare(square);
                        this.getMoney().decreaseMoney(square.getHousePrice());
                        square.increaseHotelCount();
                        this.hotelCount++; //player
                        System.out.println("SQUARE HOUSE COUNT: " + square.getHouseCount());
                        System.out.println("SQUARE HOTEL COUNT: " + square.getHotelCount());
                        System.out.println(playerName + " has bought a hotel on " + square.getSquareName());
                        break;
                    }
                    if (i == mpGame.getHotelList().size() - 1) {
                        System.out.println("There is no available hotel.");
                    }
                }
            }
        }
    }

    // Check for player is able decrease his/her money or not.
    public boolean isAbleDecreaseMoney(int fine, Board board) {
        System.out.println(playerName + " needs " + fine + "$ and has " + money.getCurrentMoney() + "$");
        boolean mortgagePropertyCheck = false;
        boolean mortgageSetsCheck = false;
        if (money.getCurrentMoney() > fine) {
            return true;
        } else {
            // Player sells his properties if he has not enough money to pay fine
            while (money.getCurrentMoney() <= fine && isBankrupted == false) {

                if (mortgagePropertyCheck && mortgageSetsCheck && houseCount == 0 && hotelCount == 0) {
                    break;
                }

                if (!isAllPropertiesSet(board) && mortgagePropertyCheck == false) {
                    if (!mortgageProperty()) {
                        mortgagePropertyCheck = true;
                        continue;

                    }
                } else if (hotelCount > 0) {
                    for (int i = 0; i < properties.size(); i++) {
                        if (properties.get(i) instanceof PropertySquare && ((PropertySquare) properties.get(i)).getHotelCount() > 0 && properties.get(i).getOwner() == this) {
                            ((PropertySquare) properties.get(i)).sellHotel(this, mpGame);
                            break;
                        }
                    }
                } else if (houseCount > 0) {
                    for (int i = 0; i < properties.size(); i++) {
                        if (properties.get(i) instanceof PropertySquare && ((PropertySquare) properties.get(i)).getHouseCount() > 0 && properties.get(i).getOwner() == this) {
                            ((PropertySquare) properties.get(i)).sellHouse(this, mpGame);
                            break;
                        }
                    }
                } else if (mortgageSetsCheck == false) {
                    if (!mortgageSets()) {
                        mortgageSetsCheck = true;
                        continue;

                    }
                }


            }
            if (money.getCurrentMoney() > fine) {
                return true;
            } else {
                return false;
            }
        }
    }

    // If player has no money, go to bankrupt.
    public void playerGoesToBankrupt(int fine, Square square) {

        money.decreaseMoney(fine);

        if (square instanceof PurchasableSquare) {
            ((PurchasableSquare) square).getOwner().getMoney().increaseMoney(fine + money.getCurrentMoney());
            System.out.println("***" + playerName + " HAS PAID \'" + (fine + money.getCurrentMoney()) + "$\' TO "
                    + ((PurchasableSquare) square).getOwner().getPlayerName() + "***");
        } else {
            System.out.println("***" + playerName + " CAN NOT PAID \'" + fine
                    + "$\' TO THE BANK***");
        }

        System.out.println("!!! " + playerName + " HAS GONE BANKRUPT!!!\n");


        if (properties != null && properties.size() > 0) {
            for (PurchasableSquare pSquare : properties) {
                pSquare.setOwner(null);
                pSquare.setHasOwner(false);
                pSquare.setMortgaged(false);
            }
        }

        properties = null;
        transportList = null;
        utilityList = null;
        isBankrupted = true;

    }

    // Pay money to owner of the square
    public void payMoneyToOwner(Player owner, int fine) {
        money.decreaseMoney(fine);
        System.out.println("***" + playerName + " HAS PAID \'" + fine + "$\' TO " + owner.getPlayerName() + "***");
        owner.getMoney().increaseMoney(fine);
    }


    // Getter and Setter methods.
    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public CommunityChest getCommunityOutOfJail() {
        return communityOutOfJail;
    }

    public void setCommunityOutOfJail(CommunityChest communityOutOfJail) {
        this.communityOutOfJail = communityOutOfJail;
    }

    public ChanceCard getChanceOutOfJail() {
        return chanceOutOfJail;
    }

    public void setChanceOutOfJail(ChanceCard chanceOutOfJail) {
        this.chanceOutOfJail = chanceOutOfJail;
    }

    public boolean getHasMortgaged() {
        return this.hasMortgaged;
    }

    //Getter method of Piece
    public Piece getPiece() {
        return this.piece;
    }

    //Setter method of Piece
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //Getter method of Piece
    public Money getMoney() {
        return this.money;
    }

    //Getter method of player Name
    public String getPlayerName() {
        return playerName;
    }

    //Setter method of turn
    public void setTurn(int turn) {
        this.turn = turn;
    }

    // Some getter and setter methods
    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public int getJailTurnCounter() {
        return jailTurnCounter;
    }

    public void setJailTurnCounter(int jailTurnCounter) {
        this.jailTurnCounter = jailTurnCounter;
    }

    public ArrayList<PurchasableSquare> getProperties() {
        return properties;
    }

    public void increaseJailTurnCounter() {
        this.jailTurnCounter++;
    }

    public void addProperty(PurchasableSquare property) {
        this.properties.add(property);
    }

    public int getTransportCount() {
        return transportList.size();
    }

    public int getUtilityCount() {
        return utilityList.size();
    }

    public boolean getOutOfJailCard() {
        return outOfJailCard;
    }

    public void setOutOfJailCard(boolean outOfJailCard) {
        this.outOfJailCard = outOfJailCard;
    }

    // Add new properties to the transportList
    public void addTransportList(PurchasableSquare transportSquare) {

        this.transportList.add(transportSquare);
    }

    // Add new properties to the utilityList
    public void addUtilityList(PurchasableSquare utilitySquare) {

        this.utilityList.add(utilitySquare);
    }


    // Some getter and setter methods
    public Dice getMoveDice() {
        return moveDice;
    }

    public Dice getChoiceDice() {
        return choiceDice;
    }

    public boolean getIsBankrupted() {
        return this.isBankrupted;
    }

    public void setIsBankrupted(boolean set) {
        this.isBankrupted = set;
    }

    public void rollMoveDice() {
        this.moveDice.rollDice();
    }

    public void rollChoiceDice() {
        this.choiceDice.rollDice();
    }

    public void decreaseHouseCount() {
        this.houseCount--;
    }

    public void increaseHouseCount() {
        this.houseCount++;
    }

    public void decreaseHotelCount() {
        this.hotelCount--;
    }

}