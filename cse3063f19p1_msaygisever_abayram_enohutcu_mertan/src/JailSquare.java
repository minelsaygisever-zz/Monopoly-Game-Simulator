// This class represents Jail Square on the board
public class JailSquare extends Square {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private int jailFine;

    // Constructor
    public JailSquare(int id, String name, int jailFine) {
        this.squareID = id;
        this.squareName = name;
        this.jailFine = jailFine;
    }

    // Getter and setter methods of the class
    @Override
    public int getSquareID() {
        return this.squareID;
    }

    @Override
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    @Override
    public String getSquareName() {
        return this.squareName;
    }

    @Override
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    public void playerWantsPayForJail(Player player) {
        // Check if player have enough money
        if (player.getMoney().getCurrentMoney() > jailFine) {

            player.getMoney().decreaseMoney(jailFine);
            System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + jailFine
                    + "$\' TO THE BANK FOR GO OUT FROM JAIL***");
            player.setInJail(false);

        }
    }

    public boolean playerDoesntWantPayForJail(Player player, Board board, int i) {
        // Check the player how many turns in the jail
        if (player.getJailTurnCounter() < 3) {
            player.increaseJailTurnCounter();
            System.out.println(player.getPlayerName() + " stays in the jail! JailCount = " + player.getJailTurnCounter());

            return true;
        } else {

            if (player.isAbleDecreaseMoney(jailFine, board)) {
                player.getMoney().decreaseMoney(jailFine);
                System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + jailFine
                        + "$\' TO THE BANK FOR GO OUT FROM JAIL***");
                player.setInJail(false);
            } else {
                System.out.println("!!! " + player.getPlayerName() + " HAS GONE BANKRUPT!!!\n");
                player.setIsBankrupted(true);
            }

        }

        return false;
    }


}
