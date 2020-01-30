public class PropertySquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private int fine;
    private int price;
    private String squareName;
    private String color;
    private Player owner;
    private boolean hasOwner;
    private int houseCount;
    private int hotelCount;
    private int housePrice;
    private int hotelRent;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int mortgage;
    private boolean isMortgaged;
    private boolean hasAllColors;

    // Constructor
    public PropertySquare(int squareID, String squareName, int fine, String color, int price, int housePrice,
                          int hotelRent, int rent1, int rent2, int rent3, int rent4, int mortgage) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.color = color;
        this.fine = fine;
        this.price = price;
        this.hasOwner = false;
        this.housePrice = housePrice;
        this.hotelRent = hotelRent;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.mortgage = mortgage;
        this.houseCount = 0;
        this.hotelCount = 0;
        this.isMortgaged = false;
        this.hasAllColors = false;
    }

    // Return square id
    public int getSquareID() {
        return squareID;
    }

    // Set square id
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    // Return square name
    public String getSquareName() {
        return squareName;
    }

    // Set square name
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    // Getter and setter methods
    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getColor() {
        return color;
    }



    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.hasOwner = true;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public void payRent(Player player, Board board) {

        if (player != owner && isMortgaged == false) {
            if (!owner.isInJail()) {
                int tempFine = fine;

                if(owner.getHouseCount() == 1){
                    tempFine = getRent1();
                }else if(owner.getHouseCount() == 2){
                    tempFine = getRent2();
                }else if(owner.getHouseCount() == 3){
                    tempFine = getRent3();
                }else if(owner.getHouseCount() == 4){
                    tempFine = getRent4();
                }

                if(owner.getHotelCount() > 0){
                    tempFine = getHotelRent();
                }

                if (owner.hasItAll(this, board)) {
                    tempFine = 2 * tempFine;
                    System.out.println("Player " + owner.getPlayerName() + " has all " + color + " colors.");
                }

                if (player.isAbleDecreaseMoney(tempFine, board)) {
                    player.payMoneyToOwner(owner, tempFine);
                } else {
                    player.playerGoesToBankrupt(tempFine, this);
                }
            } else {
                System.out.println("*The owner " + owner.getPlayerName() + " is in Jail*");
            }
        }
    }


    public void sellHouse(Player player, MonopolyGame mpGame) {

        for (int i = 0; i < mpGame.getHouseList().size(); i++) {
            if (mpGame.getHouseList().get(i).getSquare() == this && mpGame.getHouseList().get(i).getSquare().getOwner() == player) {
                mpGame.getHouseList().get(i).setSquare(null);
                mpGame.getHouseList().get(i).setOwner(null);
                houseCount--;
                player.decreaseHouseCount();
                player.getMoney().increaseMoney(housePrice / 2);
                System.out.println(player.getPlayerName() + " has sold a house on " + this.getSquareName() + " and takes " + housePrice / 2 + "$");
                break;
            }
        }
        System.out.println(player.getPlayerName() + "'s new balance = " + player.getMoney().getCurrentMoney());

    }

    public void sellHotel(Player player, MonopolyGame mpGame) {

        for (int i = 0; i < mpGame.getHouseList().size(); i++) {
            if(houseCount == 4){
                break;
            }
            if (mpGame.getHouseList().get(i).getSquare() == null) {
                mpGame.getHouseList().get(i).setSquare(this);
                this.increaseHouseCount();
                player.increaseHouseCount(); // player için
            }
        }
        for (int i = 0; i < mpGame.getHotelList().size(); i++) {
            if (mpGame.getHotelList().get(i).getOwner() == player) {
                mpGame.getHotelList().get(i).setOwner(null);
                mpGame.getHotelList().get(i).setSquare(null);
                this.decreaseHotelCount();
                player.decreaseHotelCount(); //player
                System.out.println("SQUARE HOUSE COUNT: " + this.getHouseCount());
                System.out.println("SQUARE HOTEL COUNT: " + this.getHotelCount());
                System.out.println(player.getPlayerName() + " has sold a hotel on " + this.getSquareName());
                break;
            }
        }
        System.out.println("Square house count after sale = " + houseCount);
        player.getMoney().increaseMoney((housePrice / 2) * (5 - houseCount));
        System.out.println(player.getPlayerName() + " has taken " + (housePrice / 2) * (5 - houseCount) + "$ from selling hotel!");
        System.out.println(player.getPlayerName() + "'s new balance = " + player.getMoney().getCurrentMoney());

    }


    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public int getHousePrice() {
        return housePrice;
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

    public void increaseHotelCount() {
        this.hotelCount++;
    }

    public int getRent1() {
        return rent1;
    }

    public int getRent2() {
        return rent2;
    }

    public int getHotelRent() {
        return hotelRent;
    }

    public int getRent3() {
        return rent3;
    }

    public int getRent4() {
        return rent4;
    }

    public int getMortgage() {
        return mortgage;
    }

    @Override
    public boolean getIsMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public void setHasAllColors(boolean bool) {
        this.hasAllColors = bool;
    }

    public boolean getHasAllColors() {
        return this.hasAllColors;
    }
}
