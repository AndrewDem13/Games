package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class BS_Player {
    private final Random random = new Random();
    private String name;
    private Set<Warship> warships;
    private char[][] ownField;
    private char[][] enemysField;
    private BS_Player enemy;

    public BS_Player(String name) {
        this.name = name;
        this.warships = (Set<Warship>) getRandomField()[0];
        this.ownField = (char[][]) getRandomField()[1];
        enemysField = getEnemysField();
    }

    void setEnemy(BS_Player enemy) {
        this.enemy = enemy;
    }

    // returns true if computer player missed the target
    boolean nextShot() {
        int x, y;
        // absolutely random shooting
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
            if (enemysField[x][y] == ' ') {   // player didn't shot here before
                if (enemy.checkShot(x, y)) {  // player missed
                    enemysField[x][y] = '.';
                    return true;
                }
                else {
                    enemysField[x][y] = 'X';
                    return false;
                }
            }
        }
        while (true);
    }

    // returns true if human player missed the target
    boolean nextShot(int x, int y) {
        if (enemy.checkShot(x, y)) {  // player missed
            enemysField[x][y] = '.';
            return true;
        }
        else {
            enemysField[x][y] = 'X';
            return false;
        }
    }


    // returns true if enemy missed the target
    boolean checkShot(int x, int y) {
        if (ownField[x][y] == '~')   // simple check if shot is missed
            return true;
        else {  // otherwise let every warship try to operate this shot
            for(Iterator<Warship> iterator = warships.iterator(); iterator.hasNext();) {
                Warship warship = iterator.next();
                if (warship.operateShot(x + 1, y + 1)) {  //  if one is done...
                    if (warship.isSunken())  // ...and if this warship is dead...
                        iterator.remove();   // ...remove it from collection
                    break;  // ...then break cycle and return false - enemy didn't missed
                }
            }
            return false;
        }
    }

    public boolean isAllDead() {
        return warships.stream().allMatch(Warship::isSunken);
    }

    // returns Set of Warships and it's char matrix representation
    private Object[] getRandomField() {
        int x, y, count;
        boolean horizontal;
        Set<Warship> warships = new HashSet<>();
        char[][] field = new char[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                field[i][j] = '~';

        // 4-cells ship
        x = random.nextInt(10) + 1;
        y = random.nextInt(10) + 1;
        horizontal = random.nextBoolean();
        if (horizontal && x > 7)
            x -= 3;
        else if (!horizontal && y > 7)
            y -= 3;
        warships.add(new Warship(x, y, 4, horizontal));
        if (horizontal)
            for (int i = x; i < x+4; i++)
                field[i-1][y-1] = 'X';
        else
            for (int i = y; i < y+4; i++)
                field[x-1][i-1] = 'X';

        // 3-cells ships
        count = 2;
        lab1:
        while (count > 0) {
            x = random.nextInt(10) + 1;
            y = random.nextInt(10) + 1;
            horizontal = random.nextBoolean();
            if (horizontal && x > 8)
                x -= 2;
            else if (!horizontal && y > 8)
                y -= 2;
            if (horizontal)
                for (int i = x; i < x+3; i++)
                    if (field[i-1][y-1] == 'X') continue lab1;
            if (!horizontal)
                for (int i = y; i < y+3; i++)
                    if (field[x-1][i-1] == 'X') continue lab1;
            warships.add(new Warship(x, y, 3, horizontal));
            if (horizontal)
                for (int i = x; i < x+3; i++)
                    field[i-1][y-1] = 'X';
            else
                for (int i = y; i < y+3; i++)
                    field[x-1][i-1] = 'X';
            count--;
        }

        // 2-cells ships
        count = 3;
        lab2:
        while (count > 0) {
            x = random.nextInt(10) + 1;
            y = random.nextInt(10) + 1;
            horizontal = random.nextBoolean();
            if (horizontal && x > 9)
                x -= 1;
            else if (!horizontal && y > 9)
                y -= 1;
            if (horizontal)
                for (int i = x; i < x+2; i++)
                    if (field[i-1][y-1] == 'X') continue lab2;
            if (!horizontal)
                for (int i = y; i < y+2; i++)
                    if (field[x-1][i-1] == 'X') continue lab2;
            warships.add(new Warship(x, y, 2, horizontal));
            if (horizontal)
                for (int i = x; i < x+2; i++)
                    field[i-1][y-1] = 'X';
            else
                for (int i = y; i < y+2; i++)
                    field[x-1][i-1] = 'X';
            count--;
        }

        // 1-cell ships
        count = 4;
        lab3:
        while (count > 0) {
            x = random.nextInt(10) + 1;
            y = random.nextInt(10) + 1;
            horizontal = random.nextBoolean();
            if (field[x-1][y-1] == 'X') continue lab3;
            warships.add(new Warship(x, y, 1, horizontal));
            field[x-1][y-1] = 'X';
            count--;
        }

        /*
        TEST
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                System.out.print(field[i][j]);
            System.out.println();
        }
        */
        Object[] result = new Object[2];
        result[0] = warships;
        result[1] = field;
        return result;
    }

    private char[][] getEnemysField() {
        char[][] result = new char[10][10];
        for (char[] array : result)
            for (char c : array)
                c = ' ';
        return result;
    }
}
