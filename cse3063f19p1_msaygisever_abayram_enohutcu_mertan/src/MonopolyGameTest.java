import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MonopolyGameTest {

    int playerSize = 5;
    int threshold = 8;
    int startMoney = 200;
    int goMoney = 5;
    String[] properties = {"KADIKÖY", "TAKSİM", "KARAKÖY", "ÇANAKKALE", "4.LEVENT", "BAĞDAT CADDESİ", "ACIBADEM", "EMİNÖNÜ", "FATİH", "BURSA",
            "ESKİŞEHİR", "ANKARA", "BEŞİKTAŞ", "OSMANBEY", "HALKALI", "GÖZTEPE", "ERENKÖY","BEYLİKDÜZÜ", "BEYOĞLU", "ŞİŞLİ", "BEYKOZ", "ÜMRANİYE"};
    int[] propertyFine = {3, 3, 5, 5, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 13, 13, 14, 15, 15, 16, 18, 20};
    int[] propertyPrice = {6, 6, 10, 10, 12, 14, 14, 16, 18, 18, 20, 22, 22, 24, 26, 26, 28, 30, 30, 32, 36, 40};
    String[] propertyColor = {"Blue", "Brown", "Red", "Yellow", "Orange", "Green", "Black", "Gray"};
    String[] utilityName = {"ELECTRIC", "WATER"};
    int[] utilityRate = {1, 1};
    int[] utilityPrice = {15, 15};
    String[] transportName = {"OTOBUS","METRO","METROBUS","UCAK"};
    int[] transportFine = {5, 5, 5, 5};
    int[] transportPrice = {20, 20, 20, 20};
    int[] taxFine = {20, 10};
    String[] taxSquares = {"INCOME TAX", "LUXURY TAX"};
    int jailFine = 50;
    int goToJailNumber = 3;
/*
    MonopolyGame monopolyGame = new MonopolyGame(playerSize, threshold, startMoney, goMoney,
            properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
            transportFine, transportPrice, taxFine, taxSquares, jailFine, goToJailNumber);

    @Test
    public void testPlayerMoneys(){
        monopolyGame.Play();
        Player[] playerList = monopolyGame.getPlayerOldList();
        ArrayList<Player> nonBankruptPlayers = new ArrayList<>();
        for (int i = 0; i < playerSize; i++){
            if(playerList[i].getMoney().getCurrentMoney() > 0){
                nonBankruptPlayers.add(playerList[i]);
            }
        }

        Player richestPlayer = new Player("Temp", 0);
        for (int i = 0; i < nonBankruptPlayers.size(); i++){
            if(nonBankruptPlayers.get(i).getMoney().getCurrentMoney() > richestPlayer.getMoney().getCurrentMoney()){
                richestPlayer = nonBankruptPlayers.get(i);
            }
        }

        Player winnerPlayer = new Player("Temp", 0);
        for (int i = 0; i < playerSize; i++){
            if(monopolyGame.getPlayerList()[i] != null){
                winnerPlayer = monopolyGame.getPlayerList()[i];
            }
        }

        assertEquals("Winner player is not richest player.", richestPlayer, winnerPlayer);
    }
*/



}