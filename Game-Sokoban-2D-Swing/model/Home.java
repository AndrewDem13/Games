package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Home extends GameObject
{
    public Home(int x, int y) {
        super (x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        graphics.drawOval(getX()-1, getY()-1, getWidth(), getHeight());
        graphics.setColor(Color.GRAY);
    }
}
