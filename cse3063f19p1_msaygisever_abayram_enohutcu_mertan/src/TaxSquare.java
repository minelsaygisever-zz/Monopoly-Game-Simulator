// Class of tax square
public class TaxSquare extends Square {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private int fine;

    // Constructor
    public TaxSquare(int squareID, String squareName, int fine) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.fine = fine;
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

    // Return fine
    public int getFine() {
        return this.fine;
    }

    public void payTax(Player player, Board board){

        if (player.isAbleDecreaseMoney(fine, board)) {
            player.getMoney().decreaseMoney(fine);
            System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + fine + "$\' TO BANK FOR TAXES***");
            player.setInJail(false);
        } else {
            System.out.println("!!! " + player.getPlayerName() + " HAS GONE BANKRUPT!!!\n");
            player.setIsBankrupted(true);
        }

        /*
        // Player sells his properties if he has not enough money to pay fine
        while (player.getMoney().getCurrentMoney() <= fine) {
            if (!player.mortgageProperty()){
                break;
            }
        }

        player.getMoney().decreaseMoney(fine);

        // If player goes to bankruptcy
        if (player.getMoney().getCurrentMoney() <= 0) {
            player.emptyOwnedSquares();
            System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + fine + "$\' TO BANK***");
            System.out.println("!!! " + player.getPlayerName() + " HAS GONE BANKRUPT!!!\n");

            player.setIsBankrupted(true);

        }else{
            System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + fine + "$\' TO BANK***");
        }*/
    }
}
