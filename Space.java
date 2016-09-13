package com.javarush.test.level25.lesson16.big01;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Space - is the main class in the game, all important things happen here
 */
public class Space
{
    // parameters of game field
    private int width;
    private int height;

    // space ship - our object to play
    private SpaceShip ship;
    // list of UFOs - our antagonists
    private ArrayList<Ufo> ufos = new ArrayList<Ufo>();
    // list of enemy's bombs
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    // list of our rockets (weapons)
    private ArrayList<Rocket> rockets = new ArrayList<Rocket>();

    public Space(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     *  Main program cycle
     */
    public void run()
    {
        // creating Canvas to draw
        Canvas canvas = new Canvas(width, height);

        // creating keyboard observer
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        // playing game whille our ship is alive
        while (ship.isAlive())
        {
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();
                // move left is left arrow key pressed
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                // move right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                // fire 2 rockets when space key pressed
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }

            // moving all the items for 1 step in the game
            moveAllItems();

            // check for the collisions of rockets/UFOs and bombs/spaceship
            checkBombs();
            checkRockets();
            // deleting dead items
            removeDead();

            // creating new UFO (once in 10 moves)
            createUfo();

            // draw current positions of items in the Canvas and print it to the console
            canvas.clear();
            draw(canvas);
            canvas.print();

            // pause between frames (game speed)
            Space.sleep(300);
        }

        System.out.println("Game Over!");
    }

    /**
     * move all the items
     */
    public void moveAllItems()
    {
        for (BaseObject object : getAllItems())
        {
            object.move();
        }
    }

    /**
     * this method returns all items in the current game
     */
    public List<BaseObject> getAllItems()
    {
        ArrayList<BaseObject> list = new ArrayList<BaseObject>(ufos);
        list.add(ship);
        list.addAll(bombs);
        list.addAll(rockets);
        return list;
    }

    /**
     * Creating 1 new UFO once in 10 moves
     */
    public void createUfo()
    {
        if (ufos.size() > 0) return;

        int random10 = (int) (Math.random() * 10);
        if (random10 == 0)
        {
            double x = Math.random() * 20;
            double y = Math.random() * 10;
            ufos.add(new Ufo(x, y));
        }
    }

    /**
     * checking bombs (dies if fall out of the Space, if intersects the ship - dies and also the ship
     */
    public void checkBombs()
    {
        for (Bomb bomb : bombs)
        {
            if (ship.isIntersec(bomb))
            {
                ship.die();
                bomb.die();
            }

            if (bomb.getY() >= height)
                bomb.die();
        }
    }

    /**
     * same checking of rockets and UFOs
     */
    public void checkRockets()
    {
        for (Rocket rocket : rockets)
        {
            for (Ufo ufo : ufos)
            {
                if (ufo.isIntersec(rocket))
                {
                    ufo.die();
                    rocket.die();
                }
            }

            if (rocket.getY() <= 0)
                rocket.die();
        }
    }

    /**
     * removing of dead objects
     */
    public void removeDead()
    {
        for (BaseObject object : new ArrayList<BaseObject>(bombs))
        {
            if (!object.isAlive())
                bombs.remove(object);
        }

        for (BaseObject object : new ArrayList<BaseObject>(rockets))
        {
            if (!object.isAlive())
                rockets.remove(object);
        }

        for (BaseObject object : new ArrayList<BaseObject>(ufos))
        {
            if (!object.isAlive())
                ufos.remove(object);
        }
    }

    /**
     * drawing on the Canvas
     */
    public void draw(Canvas canvas)
    {
        //draw blank game field and its borders
        for (int i = 0; i < width + 2; i++)
        {
            for (int j = 0; j < height + 2; j++)
            {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++)
        {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++)
        {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }

        for (BaseObject object : getAllItems())
        {
            object.draw(canvas);
        }
    }

    public static void sleep(int delay)
    {
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException e)
        {
        }
    }

    public SpaceShip getShip()
    {
        return ship;
    }

    public void setShip(SpaceShip ship)
    {
        this.ship = ship;
    }

    public ArrayList<Ufo> getUfos()
    {
        return ufos;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }

    public ArrayList<Rocket> getRockets()
    {
        return rockets;
    }

    public static Space game;

    public static void main(String[] args) throws Exception
    {
        game = new Space(20, 20);
        game.setShip(new SpaceShip(10, 16));
        game.run();
    }
}
