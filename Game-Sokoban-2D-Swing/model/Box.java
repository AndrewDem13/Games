package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y) {
        super (x, y);
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
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX()-Model.FIELD_SELL_SIZE/2, getY()-Model.FIELD_SELL_SIZE/2, getWidth(), getHeight());
        graphics.drawLine(getX()-Model.FIELD_SELL_SIZE/2, getY()-Model.FIELD_SELL_SIZE/2, getX()+Model.FIELD_SELL_SIZE/2, getY()+Model.FIELD_SELL_SIZE/2);
        graphics.drawLine(getX()+Model.FIELD_SELL_SIZE/2, getY()-Model.FIELD_SELL_SIZE/2, getX()-Model.FIELD_SELL_SIZE/2, getY()+Model.FIELD_SELL_SIZE/2);
        graphics.setColor(Color.GRAY);
    }
}
