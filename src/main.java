import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**Program to animate a randomly moving cat in a world
 * @author Hakan Utus
 * @since Date: 08.01.2021
 */

public class main {
    public static void main(String args[]) throws IOException { //throws an expection
        Random rNum = new Random(); //random to use later
        Cell[][] worldArray = new Cell[40][40]; //2D array to store the world
        String worldData = "world.txt"; //necessary string for reading
        Path worldFile = Paths.get(worldData); //use path to get the text file
        List<String> lines = Files.readAllLines(worldFile, StandardCharsets.UTF_8); //read the file into arraylist

        if (lines.size() < 40) { //check if the boundry is correct
            throw new IllegalArgumentException("There are only "
                    + lines.size()
                    + " lines of the 40 needed."); //If not exception
        }
        for (int i = 0; i < worldArray.length; i++) { //for loop to take the contents into an array
            String line = lines.get(i); //take the whole content as string
            String[] cells = line.split(";"); //split the string and store it in an array
            if (cells.length != 40) { //check if they are correctly passed
                throw new IllegalArgumentException("There are " + i
                        + " cells instead of the 40 needed."); //If not exception
            }
            for (int j = 0; j < cells.length; j++) {
                worldArray[i][j] = new Cell(); //Empty initialization to avoid null pointer exception
                worldArray[i][j].type = Integer.parseInt(cells[j]); //initialize the types of cells
                worldArray[i][j] = new Cell(j, i, worldArray[i][j].type); //Create the cells
            }
        }
        //Drawing setup
        int canvasWidth = 600; //width of canvas
        int canvasHeight = 600; //height of canvas
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0,canvasWidth); //x scale
        StdDraw.setYscale(0,canvasHeight); //y scale
        StdDraw.enableDoubleBuffering(); //library method to show animation
        int pauseTime = 250; //pause time
        int cellSize = 15; //an integer to adapt cell size with canvas sizes'
        Cell.cellSize = cellSize;
        for (int i = 0; i < worldArray.length; i++)
            for ( int j = 0; j < worldArray[0].length; j++)
                worldArray[i][j].draw(); //draw the world first time
        int locationCat;
        do {
            locationCat = rNum.nextInt(40); //random number for cat to enter

        }
        while (worldArray[1][locationCat].type != 0); //enter the world while the cell is empty
        Cat cat = new Cat(locationCat, 0, cellSize); //create the cat
        cat.draw(); //draw the cat
        StdDraw.show(); //show the cat
        StdDraw.pause(pauseTime);
        int iterationNum0 = 5000; //necessary integers to measure iteration number
        int iterationNum1 = 10000;
        int iterationNum2 = 20000;
        int counter = 0;
        //main animation loop
        while (counter <= iterationNum0) {
            cat.move(worldArray); //move the cat
            StdDraw.clear(StdDraw.WHITE); //clear the screen per every iteration
            for (int i = 0; i < worldArray.length; i++) {
                for (int j = 0; j < worldArray[0].length; j++) {
                    worldArray[i][j].draw();  //draw the screen again
                    counter++;
                }
            }
            //show the drawing again
            cat.draw(); //draw the cat again
            StdDraw.show(); //show the world again
            StdDraw.pause(pauseTime);
        }
    }
}
