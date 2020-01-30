

import org.junit.Test;
import static org.junit.Assert.*;

public class testTransportSquare {


    Dice moveDice = new Dice();
    Dice choiceDice =  new Dice();
    MonopolyGame mpGame;
    TransportSquare ts = new TransportSquare(15, "Reading Railroad", 50, 200,100 );
    Player player = new Player("Minel", 200,moveDice,choiceDice,mpGame );


    @Test
    public void testOwnerPlayer(){
        ts.setOwner(player);
        assertEquals("Error occured while setting owner.", ts.getOwner(), player);
    }

    @Test
    public void testHasOwner(){
        ts.setOwner(player);
        assertTrue("Error occured while setting owner.", ts.getHasOwner());
    }

    public void payRent(Player player, Board board){

    }

}
