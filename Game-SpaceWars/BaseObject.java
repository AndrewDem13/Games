package com.javarush.test.level25.lesson16.big01;

/**
 * Abstract class for the future objects
 */
public abstract class BaseObject
{
    // coordinates
    protected double x;
    protected double y;
    // radius of the object
    protected double radius;
    // state of the object
    private boolean isAlive;

    public BaseObject(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isAlive = true;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    /**
     * method draws the object on the canvas
     */
    public void draw(Canvas canvas)
    {
        //do nothing
    }

    /**
     * method moves the object (1 step)
     */
    public void move()
    {
        //do nothing
    }

    /**
     * method don't let the object goes out of the game field
     */
    public void checkBorders(double minx, double maxx, double miny, double maxy)
    {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean alive)
    {
        isAlive = alive;
    }

    public void die()
    {
        isAlive = false;
    }

    /**
     * method check intersections of the objects
     */
    public boolean isIntersec(BaseObject o)
    {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }
}
