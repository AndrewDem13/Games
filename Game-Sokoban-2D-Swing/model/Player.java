package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y)
    {
        setX(getX()+x);
        setY(getY()+y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.drawOval(getX()-Model.FIELD_CELL_SIZE /2, getY()-Model.FIELD_CELL_SIZE /2, getWidth(), getHeight());
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(getX()-Model.FIELD_CELL_SIZE /2, getY()-Model.FIELD_CELL_SIZE /2, getWidth(), getHeight());
        graphics.setColor(Color.GRAY);
    }
}
