package model;


import controller.EventListener;

import java.nio.file.Paths;

public class Model
{
    public static final int FIELD_CELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("E:\\Java\\JavaRushHomeWork\\src\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\levels.txt"));

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void startLevel(int level) {
        currentLevel = level;
        restartLevel(currentLevel);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls())
            if (gameObject.isCollision(wall, direction))
                return true;
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        Box nearbyBox = null;

        // Is there a box near the player in DIRECTION
        for (Box box : gameObjects.getBoxes())
        {
            if (player.isCollision(box, direction))
            {
                nearbyBox = box;
                break;
            }
        }

        // If there's no box and - player can move
        if (nearbyBox == null)
            return false;
        // If there's a box...
        else
        {
            // ...and it collides with the wall - player can't move
            if (checkWallCollision(nearbyBox, direction))
                return true;

            // ...or maybe it collides witn another box...
            for (Box box : gameObjects.getBoxes())
            {
                if (box == nearbyBox)
                    continue;
                // ...so player can't move
                if (nearbyBox.isCollision(box, direction))
                    return true;
            }
        }

        // ...otherwise player can move, but nearby box must be moved first
        switch (direction) {
            case LEFT:
                nearbyBox.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                nearbyBox.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                nearbyBox.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                nearbyBox.move(0, FIELD_CELL_SIZE);
                break;
        }
        return false;
    }

    public void checkCompletion() {
        boolean yes = true;

        for (Home home : getGameObjects().getHomes()) {
            boolean curent = false;
            for (Box box : getGameObjects().getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    curent = true;
            }
            if (!curent) {
                yes = false;
                break;
            }
        }

        if (yes)
            eventListener.levelCompleted(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction))
            return;

        if (checkBoxCollision(direction))
            return;

        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
        }
        checkCompletion();
    }
}
