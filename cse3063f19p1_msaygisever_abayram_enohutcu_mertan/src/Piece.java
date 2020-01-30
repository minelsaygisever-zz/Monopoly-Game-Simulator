//Piece class.
public class Piece {

    private final int INITIAL_POSITION = 0; //Initial position of each player.
    private String pieceType; //Type of the piece e.g. car.
    private Board board; //Board Object
    private Square position; //Position of the piece

    //Constructor of Piece Class.
    public Piece(String pieceType, Board board) {
        this.pieceType = pieceType;
        this.board = board;
        this.position = board.getSquareList()[INITIAL_POSITION];
    }

    //Getter method for piece.
    public String getPieceType() {
        return pieceType;
    }

    //Setter method for piece.

    //move method.
    public void move(int move, Player player, int goMoney) {
        int moving = getSquare().getSquareID() + move; //Move player

        //Check GO square. If player pass from the GO square, increase money amount with given parameter.
        if (moving >= 40) {
            moving = moving - 40;
            // player.getMoney().increaseMoney(goMoney);
            GoSquare gos = new GoSquare(0, "GO");
            gos.passGoMoney(goMoney, player);
        }

        this.position = board.getSquareList()[moving]; //update position
    }

    //Getter method for Square
    public Square getSquare() {
        return position;
    }

    //Setter method for Square
    public void setSquare(Square square) {
        this.position = square;
    }
}