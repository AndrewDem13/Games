package com.javarush.test.level34.lesson15.big01.model;


public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        boolean result = false;

        switch (direction) {
            case DOWN:
                if (getY()+Model.FIELD_SELL_SIZE == gameObject.getY() && gameObject.getX() == getX())
                    result = true;
                break;
            case UP:
                if (getY()-Model.FIELD_SELL_SIZE == gameObject.getY() && gameObject.getX() == getX())
                    result = true;
                break;
            case LEFT:
                if (getX()-Model.FIELD_SELL_SIZE == gameObject.getX() && gameObject.getY() == getY())
                    result = true;
                break;
            case RIGHT:
                if (getX()+Model.FIELD_SELL_SIZE == gameObject.getX() && gameObject.getY() == getY())
                    result = true;
                break;
        }

        return result;
    }
}
