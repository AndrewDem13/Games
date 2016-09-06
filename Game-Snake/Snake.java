package com.javarush.test.level23.lesson13.big01;

import java.util.ArrayList;


public class Snake
{
    private SnakeDirection direction;
    private boolean isAlive;
    private ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>();

    public Snake(int x, int y)
    {
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }
    public int getX() {
        return sections.get(0).getX();
    }
    public int getY() {
        return sections.get(0).getY();
    }
    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }
    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    // Make 1 move of the snake in the defined direction
    public void move()
    {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP)
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT)
            move(1, 0);
        else if (direction == SnakeDirection.DOWN)
            move(0, 1);
        else if (direction == SnakeDirection.LEFT)
            move(-1, 0);
    }

    private void move(int dx, int dy)
    {
        // Creating new snake section - future snake's head
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        checkBorders(head);
        if (!isAlive) return;

        checkBody(head);
        if (!isAlive) return;

        //Check if snake eat mouse
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) // eat
        {
            sections.add(0, head);                  // Add new snake section
            Room.game.eatMouse();                   // remove current mouse and create new one
        }
        else //просто движется
        {
            sections.add(0, head);                  // Add new snake section
            sections.remove(sections.size() - 1);   // Delete snake's tail (imitation of move)
        }
    }

    private void checkBorders(SnakeSection head)
    {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() < 0 || head.getY() >= Room.game.getHeight())
            isAlive = false;
    }

    private void checkBody(SnakeSection head)
    {
        if (sections.contains(head))
            isAlive = false;
    }
}
