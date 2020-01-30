public class ChanceCard {

    private String action;
    private int id;
    private boolean hasOwner;

    public ChanceCard(int id, String action) {
        this.action = action;
        this.id = id;
        this.hasOwner = false;
    }

    public void chooseAction(int id, Player player, Board board, MonopolyGame mpGame) {
        int tempFine;
        switch (id) {
            case 1:
                player.getPiece().setSquare(board.getSquareList()[0]);
                player.getMoney().increaseMoney(mpGame.getGoMoney());
                break;
            case 2:
                if (board.getSquareList()[24] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    if (player.getPiece().getSquare().getSquareID() > 24) {
                        player.getMoney().increaseMoney(mpGame.getGoMoney());
                    }
                    player.getPiece().setSquare(board.getSquareList()[24]);
                    break;
                }
            case 3:
                if (board.getSquareList()[11] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    if (player.getPiece().getSquare().getSquareID() > 11) {
                        player.getMoney().increaseMoney(mpGame.getGoMoney());
                    }
                    player.getPiece().setSquare(board.getSquareList()[11]);
                    break;
                }
            case 4:
                if (player.getPiece().getSquare().getSquareID() < 12 || player.getPiece().getSquare().getSquareID() > 28) {
                    if (board.getSquareList()[12] instanceof GoToJailSquare) {
                        if (board.getSquareList()[28] instanceof GoToJailSquare) {
                            board.getChanceCard().add(board.getChanceCard().get(0));
                            board.getChanceCard().remove(0);
                            mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                            break;
                        } else {

                            payUtility((UtilitySquare) board.getSquareList()[28], player, board);

                            break;
                        }
                    } else {
                        payUtility((UtilitySquare) board.getSquareList()[12], player, board);


                        break;
                    }
                } else if (player.getPiece().getSquare().getSquareID() > 12 && player.getPiece().getSquare().getSquareID() < 28) {
                    if (board.getSquareList()[28] instanceof GoToJailSquare) {
                        if (board.getSquareList()[12] instanceof GoToJailSquare) {
                            board.getChanceCard().add(board.getChanceCard().get(0));
                            board.getChanceCard().remove(0);
                            mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                        } else {
                            payUtility((UtilitySquare) board.getSquareList()[12], player, board);

                            break;
                        }
                    } else {
                        payUtility((UtilitySquare) board.getSquareList()[28], player, board);

                        break;
                    }
                }
                break;
            case 5:
                if (player.getPiece().getSquare().getSquareID() < 5 || player.getPiece().getSquare().getSquareID() > 35) {
                    nearestTransport(player, board, 5, mpGame);
                    break;
                } else if (player.getPiece().getSquare().getSquareID() > 5 && player.getPiece().getSquare().getSquareID() < 15) {
                    nearestTransport(player, board, 15, mpGame);
                    break;
                } else if (player.getPiece().getSquare().getSquareID() > 15 && player.getPiece().getSquare().getSquareID() < 25) {
                    nearestTransport(player, board, 25, mpGame);
                    break;
                } else if (player.getPiece().getSquare().getSquareID() > 25 && player.getPiece().getSquare().getSquareID() < 35) {
                    nearestTransport(player, board, 35, mpGame);
                    break;
                }
            case 6:
                player.getMoney().increaseMoney(50);
                break;
            case 7:
                if (this.hasOwner == false) {
                    this.hasOwner = true;
                    player.setOutOfJailCard(true);
                    player.setChanceOutOfJail(this);
                    System.out.println(player.getPlayerName() + " get change go out of jail card!!");
                } else {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                }
                break;
            case 8:
                if (player.getPiece().getSquare().getSquareID() >= 3) {
                    player.getPiece().setSquare(board.getSquareList()[player.getPiece().getSquare().getSquareID() - 3]);
                } else {
                    player.getPiece().setSquare(board.getSquareList()[player.getPiece().getSquare().getSquareID() + 40 - 3]);
                }
                break;
            case 9:
                player.getPiece().setSquare(board.getSquareList()[10]);
                break;
            case 10:
                tempFine = player.getHouseCount() * 40 + player.getHotelCount() * 115;
                if (player.isAbleDecreaseMoney(tempFine, board)) {
                    player.getMoney().decreaseMoney(tempFine);
                    System.out.println(player.getPlayerName() + " HAS PAID " + tempFine + "$ TO BANK!!");
                } else {

                    player.playerGoesToBankrupt(tempFine, player.getPiece().getSquare());
                }
                break;
            case 11:
                if (player.isAbleDecreaseMoney(15, board)) {
                    player.getMoney().decreaseMoney(15);
                } else {

                    player.playerGoesToBankrupt(15, player.getPiece().getSquare());
                }
                break;
            case 12:
                if (board.getSquareList()[25] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    if (player.getPiece().getSquare().getSquareID() > 25) {
                        player.getMoney().increaseMoney(mpGame.getGoMoney());
                    }
                    player.getPiece().setSquare(board.getSquareList()[25]);
                    break;
                }
            case 13:
                if (board.getSquareList()[39] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    player.getPiece().setSquare(board.getSquareList()[39]);
                    break;
                }
            case 14:
                for (int i = 0; i < mpGame.getPlayerList().length; i++) {
                    if (mpGame.getPlayerList()[i] != null && mpGame.getPlayerList()[i] != player) {
                        mpGame.getPlayerList()[i].getMoney().increaseMoney(50);
                        if (player.isAbleDecreaseMoney(50, board)) {
                            player.getMoney().decreaseMoney(50);
                        } else {

                            player.playerGoesToBankrupt(50, player.getPiece().getSquare());
                        }
                    }
                }
                break;
            case 15:
                player.getMoney().increaseMoney(150);
                break;
            case 16:
                player.getMoney().increaseMoney(100);
                break;

        }
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    private void nearestTransport(Player player, Board board, int temp, MonopolyGame mpGame) {
        int control = 0;
        while (board.getSquareList()[temp] instanceof GoToJailSquare) {
            temp += 10;
            temp = temp % 40;
            control++;
            if (control == 4) {
                break;
            }
        }
        if (control != 4) {
            player.getPiece().setSquare(board.getSquareList()[temp]);
            if (((TransportSquare) board.getSquareList()[temp]).getHasOwner()) {
                ((TransportSquare) board.getSquareList()[temp]).getOwner().getMoney().increaseMoney(((TransportSquare) board.getSquareList()[temp]).getFine());
                if (player.isAbleDecreaseMoney(((TransportSquare) board.getSquareList()[temp]).getFine(), board)) {
                    player.getMoney().decreaseMoney(((TransportSquare) board.getSquareList()[temp]).getFine());
                } else {
                    player.setIsBankrupted(true);
                }
            }
            return;
        } else {
            mpGame.takeChangeCard(this, player);
            return;
        }

    }

    public void payUtility(UtilitySquare utility, Player player, Board board) {
        player.getPiece().setSquare(utility);
        int tempFine;
        if (utility.getHasOwner()) {
            tempFine = 10 * player.getChoiceDice().getTotal() - utility.getFine();
            if (player.isAbleDecreaseMoney(tempFine, board)) {
                player.getMoney().decreaseMoney(tempFine);
                utility.getOwner().getMoney().increaseMoney(tempFine);
            } else {

                player.playerGoesToBankrupt(tempFine, utility);
            }
        }
    }


    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }
}
