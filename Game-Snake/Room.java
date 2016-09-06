package com.javarush.test.level23.lesson13.big01;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Room
{
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public Room(int width, int height, Snake snake)
    {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
    }
    public Mouse getMouse() {
        return mouse;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setSnake(Snake snake) {
        this.snake = snake;
    }
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    // Main process of the game
    public void run()
    {
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (snake.isAlive())
        {
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();

                if (event.getKeyChar() == 'q') return;

                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   // Move the snake
            print();        // Print current frame of the game
            sleep();        // Delay between game moves(frames)
        }

        // End of the game
        System.out.println("Game Over!");
    }


    public void print()
    {
        // Create game (frame) matrix filled with '0'
        int[][] matrix = new int[height][width];

        // Print snake with '1'
        ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
        for (SnakeSection snakeSection : sections)
            matrix[snakeSection.getY()][snakeSection.getX()] = 1;

        // Print snake's head with '2' or '4' if snake is dead
        matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;

        // Print mouse
        matrix[mouse.getY()][mouse.getX()] = 3;

        // Print matrix to the console
        String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                System.out.print(symbols[matrix[y][x]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    // When current mouse has been eaten (create new mouse)
    public void eatMouse()
    {
        createMouse();
    }

    // Creating new mouse with random coordinates
    public void createMouse()
    {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }


    public static Room game; // CURRENT GAME

    public static void main(String[] args)
    {
        game = new Room(20, 20, new Snake(10, 10)); // Set size of game field
        game.snake.setDirection(SnakeDirection.DOWN); // Set default direction for the snake at the beginning of the game
        game.createMouse();
        game.run(); // START
    }

    //Array of delays for the levels
    private static int[] levelDelay = {1000, 600, 550, 500, 480, 460, 440, 420, 400, 380, 360, 340, 320, 300, 285, 270};

    // Method calculates delay for the current level and make pause
    public void sleep()
    {
        try
        {
            int level = snake.getSections().size();
            int delay = level < 15 ? levelDelay[level] : 250;
            Thread.sleep(delay);
        }
        catch (InterruptedException e) { }
    }
}
