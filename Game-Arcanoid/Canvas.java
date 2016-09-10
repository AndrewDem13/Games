package com.javarush.test.level24.lesson14.big01;

// Canvas class for drawing

public class Canvas
{
    // parameters
    private int width;
    private int height;
    // matrix to draw with characters (mean colour)
    private char[][] matrix;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.matrix = new char[height + 2][width + 2];
    }

    public void clear()
    {
        this.matrix = new char[height + 2][width + 2];
    }

    // Drawing received figure
    public void drawMatrix(double x, double y, int[][] matrix, char c)
    {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (matrix[i][j] == 1)
                    setPoint(x + j, y + i, c);
            }
        }
    }

    // Set 1 point on coordinates (x, y) with (c) colour
    public void setPoint(double x, double y, char c)
    {
        int x0 = (int) Math.round(x);
        int y0 = (int) Math.round(y);
        if (y0 < 0 || y0 >= matrix.length) return;
        if (x0 < 0 || x0 >= matrix[y0].length) return;

        matrix[y0][x0] = c;
    }

    // Print the canvas to console
    public void print()
    {
        System.out.println();

        for (int i = 0; i < height + 2; i++)
        {
            for (int j = 0; j < width + 2; j++)
            {
                System.out.print(" ");
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public char[][] getMatrix()
    {
        return matrix;
    }
}
