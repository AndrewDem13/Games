package com.javarush.test.level25.lesson16.big01;

/**
 * Class for the space ship
 */
public class SpaceShip extends BaseObject
{
    // picture of the ship
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };

    // direction (0 steady, -1 left, 1 right)
    private double dx = 0;

    public SpaceShip(int x, int y)
    {
        super(x, y, 3);
    }

    public void moveLeft()
    {
        dx = -1;
    }

    public void moveRight()
    {
        dx = 1;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }

    @Override
    public void move()
    {
        x = x + dx;
        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() + 1);
    }

    /**
     * method shoots 2 new rockets
     */
    public void fire()
    {
        Space.game.getRockets().add(new Rocket(x - 2, y));
        Space.game.getRockets().add(new Rocket(x + 2, y));
    }
}
