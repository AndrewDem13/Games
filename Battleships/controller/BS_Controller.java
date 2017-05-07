package controller;

import model.BS_Model;
import model.BS_Player;
import model.Warship;
import view.BS_View;

import java.util.HashSet;
import java.util.Set;

public class BS_Controller implements BS_EventListener {
    private BS_Model model;
    private BS_View view;

    public BS_Controller() {
        restart();
    }

    public BS_Player[] getPlayers() {
        BS_Player[] players = new BS_Player[2];
        players[0] = model.getPlayer1();
        players[1] = model.getPlayer2();
        return players;
    }

    @Override
    public void shot(int x, int y) {
        if (!model.humanShot(x, y))  //if didn't missed
            view.update();  // update and wait for another human shot
        else
            while (true) {  // otherwise let the computer shot while don't miss
                if (!model.nextMove())
                    view.update();
                else
                    break;
            }
    }

    @Override
    public void restart() {
        BS_Player player1 = new BS_Player("Player1");
        BS_Player player2 = new BS_Player("Player2");
        model = new BS_Model(player1, player2);
        model.setEventListener(this);

        view = new BS_View(this);
        view.init();
    }

    @Override
    public void gameOver(BS_Player winner) {
        view.gameOver(winner);
    }

    public static void main(String[] args) {
        BS_Controller controller = new BS_Controller();
    }
}
