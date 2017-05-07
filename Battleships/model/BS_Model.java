package model;

import controller.BS_EventListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BS_Model {
    private BS_EventListener eventListener;  // Controller
    private BS_Player player1;
    private BS_Player player2;
    private BS_Player winner;
    private boolean firstPlayerMove = true;
    boolean gameOver = false;

    public BS_Model(BS_Player player1, BS_Player player2) {
        this.player1 = player1;  // human player. Shots by transferring coordinates
        this.player2 = player2;  // computer player. Shots randomly
        player1.setEnemy(player2);
        player2.setEnemy(player1);
    }

    public void setEventListener(BS_EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public BS_Player getPlayer1() {
        return player1;
    }

    public BS_Player getPlayer2() {
        return player2;
    }

    public BS_Player getWinner() {
        return winner;
    }

    public boolean humanShot(int x, int y) {
        boolean isMissed;
        isMissed = player1.nextShot(x, y);
        if (!isMissed && player2.isAllDead())
            eventListener.gameOver(player1);
        return isMissed;
    }

    public boolean nextMove() {
        boolean isMissed;
        isMissed = player2.nextShot();
        if (!isMissed && player1.isAllDead())
            eventListener.gameOver(player2);
        return isMissed;
    }
}
