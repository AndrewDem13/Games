package com.javarush.test.level25.lesson16.big01;

/**
 * Class for the UFO's bomb
 */
public class Bomb extends BaseObject
{
    public Bomb(double x, double y)
    {
        super(x, y, 1);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x,y,'B');
    }

    // bombs move only straight down
    @Override
    public void move()
    {
        y++;
    }
}
