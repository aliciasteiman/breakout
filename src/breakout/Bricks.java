package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bricks extends Game {
    public static final String DATA_FILE = "line_config_small.txt";
    //public static final int NUM_ROWS = 20;
    //public static final int NUM_COLUMNS = 30;
    public static final int BRICK_HEIGHT = 20;
    //public static final int NUM_BRICKS_PER_ROW=20;
    public static final int COL_SEPARATION = 5;
    public static final int ROW_SEPARATION = 5;
    //public static final int BRICK_WIDTH=(WIDTH/NUM_BRICKS_PER_ROW)-(NUM_BRICKS_PER_ROW-1)*BRICK_SEPARATION;
    //public static final int NUM_TURNS= 3;
    private static Rectangle brick;
    public static ArrayList<Bricks> myBricks;
    private Rectangle myShape;

    public Bricks(int x, int y, int width, int height, Paint color) {
        myShape = new Rectangle(x, y, width, height);
        myShape.setFill(color);
    }

    public Rectangle getShape() {
        return myShape;
    }


    public static ArrayList<Bricks> drawBricks(){
        List<String> configurations = new ArrayList<>();
        myBricks = new ArrayList<>();
        Scanner input = new Scanner(Bricks.class.getClassLoader().getResourceAsStream(DATA_FILE));

        while (input.hasNext()){
            configurations.add(input.nextLine());
        }

        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            int BRICK_WIDTH = ((WIDTH - COL_SEPARATION * NUM_COLUMNS) / NUM_COLUMNS);
            Paint color = null;
            for (int column = 0; NUM_COLUMNS > column; column++) {
                if (holder.charAt(column) == '0') {
                    color = Color.DARKSALMON;
                }
                else if (holder.charAt(column) == '1') {
                    color = Color.BLACK;
                }
                else if (holder.charAt(column) == '2') {
                    color = Color.BEIGE;
                }
                else if (holder.charAt(column) == '3') {
                    color = Color.CYAN;
                }
                else if (holder.charAt(column) == '4') {
                    color = Color.CHOCOLATE;
                }
                else if (holder.charAt(column) == '5') {
                    color = Color.CRIMSON;
                }
                Bricks brick = new Bricks(column * (BRICK_WIDTH + COL_SEPARATION), row  * (BRICK_HEIGHT + COL_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                myBricks.add(brick);
            }
        }
        return myBricks;
    }

}
