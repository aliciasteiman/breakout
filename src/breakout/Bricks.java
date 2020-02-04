package breakout;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bricks extends Game {
    public static final String DATA_FILE = "data1/line_config.txt";
    //public static final int NUM_ROWS=20;
    //public static final int NUM_COLUMNS=30;
    public static final int BRICK_HEIGHT=10;
    //public static final int NUM_BRICKS_PER_ROW=20;
    public static final int BRICK_SEPARATION=5;
    //public static final int BRICK_WIDTH=(WIDTH/NUM_BRICKS_PER_ROW)-(NUM_BRICKS_PER_ROW-1)*BRICK_SEPARATION;
    //public static final int NUM_TURNS= 3;
    private static Rectangle brick;

    public static void drawbricks(){
        List<String> configurations = new ArrayList<>();
        Scanner input = new Scanner(Main.class.getClassLoader().getResourceAsStream(DATA_FILE));
        while (input.hasNext()){
            configurations.add(input.nextLine());
        }
        int NUM_ROWS= configurations.size();
        for (int row=0;row<NUM_ROWS;row++){
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s","");
            int NUM_COLUMNS=holder.length();
            int BRICK_WIDTH = (WIDTH/NUM_COLUMNS)- (NUM_COLUMNS-1*BRICK_SEPARATION);
            for(int column = 0; NUM_COLUMNS > column; column++){
                if(holder.charAt(column) == 0){
                    Rectangle brick= new Rectangle(WIDTH-(BRICK_WIDTH*column+1)-(BRICK_SEPARATION*column),HEIGHT/2-(BRICK_HEIGHT*(row+1))-(BRICK_SEPARATION*row), BRICK_WIDTH,BRICK_HEIGHT);
                    //add(brick);
                    //Game.setUpScene.root.getChildren().add(brick);
                    brick.setFill(Color.DARKSALMON);
                }
                if(holder.charAt(column) == 1){
                    Rectangle brick= new Rectangle(WIDTH-(BRICK_WIDTH*column+1)-(BRICK_SEPARATION*column),HEIGHT/2-(BRICK_HEIGHT*(row+1))-(BRICK_SEPARATION*row), BRICK_WIDTH,BRICK_HEIGHT);
                    brick.setFill(Color.BLACK);
                }
                if(holder.charAt(column) == 2){
                    Rectangle brick= new Rectangle(WIDTH-(BRICK_WIDTH*column+1)-(BRICK_SEPARATION*column),HEIGHT/2-(BRICK_HEIGHT*(row+1))-(BRICK_SEPARATION*row), BRICK_WIDTH,BRICK_HEIGHT);
                    brick.setFill(Color.BEIGE);
                }
                if(holder.charAt(column) == 3){
                    Rectangle brick= new Rectangle(WIDTH-(BRICK_WIDTH*column+1)-(BRICK_SEPARATION*column),HEIGHT/2-(BRICK_HEIGHT*(row+1))-(BRICK_SEPARATION*row), BRICK_WIDTH,BRICK_HEIGHT);
                    brick.setFill(Color.CYAN);
                }
                if(holder.charAt(column) == 4){
                    Rectangle brick= new Rectangle(WIDTH-(BRICK_WIDTH*column+1)-(BRICK_SEPARATION*column),HEIGHT/2-(BRICK_HEIGHT*(row+1))-(BRICK_SEPARATION*row), BRICK_WIDTH,BRICK_HEIGHT);
                    brick.setFill(Color.CHOCOLATE);
                }
                if(holder.charAt(column) == 5){
                    Rectangle brick= new Rectangle(WIDTH-(BRICK_WIDTH*column+1)-(BRICK_SEPARATION*column),HEIGHT/2-(BRICK_HEIGHT*(row+1))-(BRICK_SEPARATION*row), BRICK_WIDTH,BRICK_HEIGHT);
                    brick.setFill(Color.CRIMSON);
                }
            }
        }



    }

    public Rectangle getbrick(){return brick;}
}
