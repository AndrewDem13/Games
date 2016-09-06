package com.javarush.test.level23.lesson13.big01;

public class SnakeSection
{
    private int x;
    private int y;

    public SnakeSection(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnakeSection that = (SnakeSection) o;

        if (getX() != that.getX()) return false;
        return getY() == that.getY();

    }

    @Override
    public int hashCode()
    {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }
}
