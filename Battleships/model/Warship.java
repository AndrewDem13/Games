package model;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Warship {
    private int x, y, size;
    private boolean horizontal;
    private List<String> bodyParts = new ArrayList<>();
    private List<String> deadBodyParts = new ArrayList<>();

    public Warship(int x, int y, int size, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.horizontal = horizontal;

        for (int i = 0; i < size; i++) {
            if (horizontal)
                bodyParts.add(x+i + " " + y);
            else
                bodyParts.add(x + " " + (y+i));
        }
    }

    // returns true if shot was successfully operated
    public boolean operateShot(int x, int y) {
        if (!horizontal && x == this.x) {
            if (y >= this.y && y <= this.y + size-1) {
                String part = x + " " + y;
                bodyParts.remove(part);
                deadBodyParts.add(part);
                return true;
            }
        }
        else if (horizontal && y == this.y) {
            if (x >= this.x && x <= this.x + size-1) {
                String part = x + " " + y;
                bodyParts.remove(part);
                deadBodyParts.add(part);
                return true;
            }
        }
        return false;
    }

    public boolean isSunken() {
        return bodyParts.size() == 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public List<String> getBodyParts() {
        return bodyParts;
    }

    public List<String> getDeadBodyParts() {
        return deadBodyParts;
    }

    @Override
    public String toString() {
        return String.format("Warship size: %d; Head coordinates: [%d, %d]; Horizontal: " + horizontal, size, x, y);
    }
}
