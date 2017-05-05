package controller;

import model.Direction;

public interface EventListener
{
    void move(Direction direction); // – передвинуть объект в определенном направлении.
    void restart(); // – начать заново текущий уровень.
    void startNextLevel(); // – начать следующий уровень.
    void levelCompleted(int level); // – уровень с номером level завершён.
}
