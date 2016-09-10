package com.javarush.test.level24.lesson14.big01;

// Class for the playing stand

public class Stand extends BaseObject
{
    // pattern to draw
    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };


    private double speed = 1;

    private double direction = 0;

    public Stand(double x, double y)
    {
        super(x,y,3);
    }

    // moving the stand
    public void move()
    {
        double dx = speed * direction;
        x += dx;

        checkBorders(radius, Arcanoid.game.getWidth() - radius + 1, 1, Arcanoid.game.getHeight() + 1);
    }


    public void moveLeft()
    {
        direction = -1;
    }

    public void moveRight()
    {
        direction = 1;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDirection()
    {
        return direction;
    }

    // draw the stand
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
