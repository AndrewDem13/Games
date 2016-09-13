package com.javarush.test.level25.lesson16.big01;

/**
 * Class for the rocket
 */
public class Rocket  extends BaseObject
{

    public Rocket(double x, double y)
    {
        super(x, y, 1);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x,y,'R');
    }

    // rocket moves only straight up
    @Override
    public void move()
    {
        y--;
    }
}
