package model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (level == 60)
            ;
        else if (level%60 == 0)
            level = level%60 + 1;
        else
            level = level%60;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(0, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toString())))
        {
            String line;
            int cellCenter = Model.FIELD_CELL_SIZE / 2;

            while ((line = reader.readLine()) != null)
            {
                if (("Maze: " + level).equals(line))
                {
                    break;
                }
            }

            reader.readLine();
            reader.readLine();

            int height = Integer.parseInt(reader.readLine().split(" ")[2]);

            reader.readLine();
            reader.readLine();
            reader.readLine();


            for (int i = 0; i < height; i++)
            {
                line = reader.readLine();
                for (int j = 0; j < line.length(); j++)
                {
                    switch (line.charAt(j))
                    {
                        case ' ': break;

                        case 'X':
                            walls.add(new Wall(j*Model.FIELD_CELL_SIZE + cellCenter, i*Model.FIELD_CELL_SIZE + cellCenter));
                            break;

                        case '*':
                            boxes.add(new Box(j*Model.FIELD_CELL_SIZE + cellCenter, i*Model.FIELD_CELL_SIZE + cellCenter));
                            break;

                        case '.':
                            homes.add(new Home(j*Model.FIELD_CELL_SIZE + cellCenter, i*Model.FIELD_CELL_SIZE + cellCenter));
                            break;

                        case '&':
                            boxes.add(new Box(j*Model.FIELD_CELL_SIZE + cellCenter, i*Model.FIELD_CELL_SIZE + cellCenter));
                            homes.add(new Home(j*Model.FIELD_CELL_SIZE + cellCenter, i*Model.FIELD_CELL_SIZE + cellCenter));

                            break;

                        case '@':
                            player = new Player(j*Model.FIELD_CELL_SIZE + cellCenter, i*Model.FIELD_CELL_SIZE + cellCenter);
                            break;
                    }
                }
            }
        }
        catch (IOException e) {}

        return new GameObjects(walls, boxes, homes, player);
    }
}
