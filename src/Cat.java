import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
/** Cat class to move and draw the cat
 * @author Hakan Utus
 * @since Date: 08.01.2021
 */
public class Cat {
    public int x; //X coordinate of the cat
    public int y; //Y coordinate of the cat
    public Color color; //color of the cat
    public int foodCount; //number of foods eaten by the cat
    public int cellSize; //cellsize of the cat

    public Cat(int x, int y, int cellSize) { //necessary constructor
        this.x = x; //this x equals data field
        this.y = y; //this y equals data field
        this.color = color; //this color equals data field
        this.cellSize = cellSize;
    }

    /**
     * Moves the cat throughout the world
     * @param world array that holds the world in it
     */
    public void move(Cell[][] world) {
        ArrayList<Cell> Foods = new ArrayList<>(); //Arraylist to hold the foods
        ArrayList<Cell> neighbors = new ArrayList<>(); //Arraylist to hold the empty neighbors
        int numRows = world.length; //weight of the world
        int numCols = world[0].length; //height of the world
        if (world[y][x].type == 3) //check if food
            Foods.add(world[y][x]); //add to the list
        if (y - 1 >= 0) { //check if the down cell is at the bottom
            if (world[y - 1][x].type == 3) { //check if the down cell is food
                y = y - 1; //if so eat and move
                world[y][x].type = 0; //erase the food
                this.foodCount += 1; //increment the food counter
                return;
            }
            if (world[y - 1][x].type == 0) //check if the down cell is empty
                neighbors.add(world[y - 1][x]); //if so add to the empty neighbors list
        }


        if (y + 1 < numRows) { //check if the upper cell is at the top
            if (world[y + 1][x].type == 3) { //check if the upper cell is food
                y = y + 1; //if so eat and move
                world[y][x].type = 0; //erase the food
                this.foodCount += 1; //increment the food count
                return;
            }
            if (world[y + 1][x].type == 0) //check if the upper cell is empty
                neighbors.add(world[y + 1][x]); //if so add to the empty neighbors list
        }
        if (x-1 >= 0) { //check if the one step left cell is in the world
            if (world[y][x - 1].type == 3) { //check if the cell is food
                x = x - 1; //if so eat and mode
                world[y][x].type = 0; //erase the food
                this.foodCount += 1; //increment the food count
                return;
            }
            if (world[y][x - 1].type == 0) //check if the left cell is empty
                neighbors.add(world[y][x - 1]); //if so add to the empty neighbors list
        }
        if (x+1 < numCols) { //check if the right cell is in the world
            if (world[y][x + 1].type == 3) { //check if the right cell is food
                x = x + 1; //if so eat and move
                world[y][x].type = 0; //erase the food
                this.foodCount += 1; //increment the food counter
                return;
            }
            if (world[y][x + 1].type == 0) //check if the right cell is empty
                neighbors.add(world[y][x + 1]); //if so add to the empty neighbors list
        }
        //random cell for cat to enter the world
        Random randomGen = new Random();
        int catLocation = randomGen.nextInt(neighbors.size()); //bounds the random with empty cells
        x = neighbors.get(catLocation).x; //sets the cats' x coordinate
        y = neighbors.get(catLocation).y; //sets the cats' y coordinate
        for (int i = 0; i <= Foods.size()-1; i++){ //for loop to iterate through foods list
            if ((Foods.get(i).x - neighbors.get(catLocation).x) <= 1){ //check if one of the neighbors is food
                x = Foods.get(i).x; //if so move there
                y = Foods.get(i).y;
            }


        }




    }

    /** draws the cat
     */
    public void draw() {
        double catX = x * cellSize + cellSize / 2; //sets the cats' center location
        double catY = y * cellSize + cellSize / 2;
        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        StdDraw.filledCircle(catX,catY,cellSize/4); //draws the cat
    }


}