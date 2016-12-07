package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Wall extends CollisionObject
{
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.drawRect(getX()-Model.FIELD_CELL_SIZE /2, getY()-Model.FIELD_CELL_SIZE /2, getWidth(), getHeight());
        graphics.setColor(Color.GRAY);
        graphics.fillRect(getX()-Model.FIELD_CELL_SIZE /2, getY()-Model.FIELD_CELL_SIZE /2, getWidth(), getHeight());
        graphics.setColor(Color.GRAY);
    }
}
