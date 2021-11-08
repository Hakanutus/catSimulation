import java.awt.*;

/**
 * Cell class to draw the world
 * @author Hakan Utus
 * @since Date: 08.01.2021
 */
public class Cell {
    public static int cellSize; //size of the cells
    public int x; //x coordinate of the cells
    public int y; //y coordinate of the cells
    public int type; //type of the cells
    public Cell() {} //empty constructor to avoid null pointer

    Cell(int x, int y, int type) { //constructor to use in main code
        this.x = x; //x coordinate of the cell
        this.y = y; //y coordinate of the cell
        this.type = type; //type of the cell
    }

    /**
     * draws the cells
     */
    public void draw() {
        if (this.type==0) { //checks if the cell is empty
            StdDraw.setPenColor(Color.darkGray);
        }
        else if (this.type == 1) { //checks if the cell is wall
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        }
        else if (this.type == 2) { //checks if the cell is sea
            StdDraw.setPenColor(Color.blue);
        }
        else if (this.type == 3 ) { //checks if the cell is food
            StdDraw.setPenColor(Color.PINK);
        }

        double xCell = x * cellSize + cellSize / 2; //computes the x coordinate of cell
        double yCell = y * cellSize + cellSize / 2; //computes the y coordinate of cell
        StdDraw.filledRectangle(xCell, yCell, cellSize/2, cellSize/2); //draws the cells
    }
}