

// Class for the brick objects test Git

public class Brick extends BaseObject
{
    // pattern to draw
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y)
    {
        super(x,y,3);
    }

    // draw the brick
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }

    // brick is unmovable
    @Override
    public void move()
    {
        //do nothing
    }
}
