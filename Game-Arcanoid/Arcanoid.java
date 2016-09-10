package com.javarush.test.level24.lesson14.big01;

// Main class of the game

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Arcanoid
{
    // parameters of the future game field
    private int width;
    private int height;

    // list of bricks
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    // game ball unit
    private Ball ball;
    // playing stand unit
    private Stand stand;

    // current state of the game
    private boolean isGameOver = false;

    public Arcanoid(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks()
    {
        return bricks;
    }

    public Ball getBall()
    {
        return ball;
    }

    public void setBall(Ball ball)
    {
        this.ball = ball;
    }

    public Stand getStand()
    {
        return stand;
    }

    public void setStand(Stand stand)
    {
        this.stand = stand;
    }

    public void draw(Canvas canvas)
    {
        drawBoders(canvas);

        // draw bricks
        for (Brick brick : bricks)
            brick.draw(canvas);

        ball.draw(canvas);

        stand.draw(canvas);
    }

    private void drawBoders(Canvas canvas)
    {
        //draw game
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
    }

    // Game process
    public void run() throws Exception
    {
        // Creating the canvas for drawing
        Canvas canvas = new Canvas(width, height);

        // Creating keyboard observer
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        // play game untill it's over
        while (!isGameOver)
        {
            // is there any events in keyboard observer to move the stand?
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();

                // if arrow LEFT
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                // if arrow RIGHT
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                // space kay pushing the ball at the start
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            // move all the objects (1 step)
            move();

            // checking all possible bumps
            checkBricksBump();
            checkStandBump();

            // if the ball flew away through the bottom the game ends
            checkEndGame();

            // drawing current state of all the objects
            canvas.clear();
            draw(canvas);
            canvas.print();

            // pause between steps
            Thread.sleep(300);
        }

        System.out.println("Game Over!");
    }

    // moving movable objects
    public void move()
    {
        ball.move();
        stand.move();
    }

    // if ball bumps the brick it fly away in random dirrection
    public void checkBricksBump()
    {
        for (Brick brick : bricks)
        {
            if (ball.isIntersec(brick))
            {
                double angel = Math.random() * 360;
                ball.setDirection(angel);
                bricks.remove(brick);
                break;
            }
        }
    }

    // if ball bumps the stand ind fly away in random dirrection up
    public void checkStandBump()
    {
        if (ball.isIntersec(stand)) {
            double angel = 80 + Math.random()*20;
            ball.setDirection(angel);
        }
    }


    public void checkEndGame()
    {
        if (ball.getY() >= stand.getY())
            isGameOver = true;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public static Arcanoid game;

    public static void main(String[] args) throws Exception
    {
        game = new Arcanoid(20, 30);

        Ball ball = new Ball(10, 29, 2,  95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}
