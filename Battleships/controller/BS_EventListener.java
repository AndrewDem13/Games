package controller;

import model.BS_Player;

public interface BS_EventListener {
    void shot(int x, int y);
    void restart();
    void gameOver(BS_Player winner);
}
