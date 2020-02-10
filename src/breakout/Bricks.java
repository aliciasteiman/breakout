package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Bricks extends Game {
    private static Scene myScene;
    public static final String DATA_FILE = "line_config_small.txt";
    public static final int BRICK_HEIGHT = 20;
    public static final int ROW_SEPARATION = 2;
    public static ArrayList<Bricks> myBricks;
    private Rectangle myShape;
    public static final double WIDTH = 500.0;
    public static final double HEIGHT = 500.0;
    public static int Bricktracker=0;


    public Bricks(double x, double y, double width, double height, Paint color) {
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
            double BRICK_WIDTH = (double) WIDTH / NUM_COLUMNS;
            Paint color = null;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                if (holder.charAt(column) == '0') {
                    color = Color.DARKSALMON;
                }
                else if (holder.charAt(column) == '1') {
                    color = Color.DARKCYAN;
                }
                else if (holder.charAt(column) == '2') {
                    color = Color.ORANGE;
                }
                else if (holder.charAt(column) == '3') {
                    color = Color.FORESTGREEN;
                }
                else if (holder.charAt(column) == '4') {
                    color = Color.CHOCOLATE;
                }
                else if (holder.charAt(column) == '5') {
                    color = Color.CRIMSON;
                }
                Bricks brick = new Bricks(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                myBricks.add(brick);
                Bricktracker+=1;
            }
        }
        return myBricks;
    }

    public static void removeBrick(Bricks brick) {
        brick.getShape().setFill(null);
    }

    public static boolean checkBricks(Circle ball) {
        Iterator<Bricks> iter = myBricks.iterator();
        while (iter.hasNext()) {
            Bricks brick = iter.next();
            if (Shape.intersect(ball, brick.getShape()).getBoundsInLocal().getWidth() != -1) {
                removeBrick(brick);
                Bricktracker-=1;
                return true;
            }
        }
        return false;
    }

    public static void checkgameover(){

        if(Bricktracker==0) {
            Text text = new Text();
            text.setText("You won. Awesome!!!");
            gameovertext(text);
        }
         if(LIVES==0){
            Text text = new Text();
            text.setText("You lost. Better luck next time");
             gameovertext(text);
         }

    }

    public static void gameovertext(Text text) {
        myAnimation.pause();
        Stage stage = new Stage();
        text.setX(0);
        text.setY(HEIGHT / 2);
        text.setFont(Font.font("elephant", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
        text.setFill(Color.BLUEVIOLET);
        Group root = new Group(text);
        myScene = new Scene(root, Game.WIDTH, Game.HEIGHT, Color.TAN);
        stage.setScene(myScene);
        stage.show();
    }

    public void getcheats(KeyCode code){
        if (code == KeyCode.L) {
            LIVES+=1;
        }
//        if (code == KeyCode.P) {
//            cheatpaddlelength();
//        }
        if (code == KeyCode.COLON) {
            LIVES+=1;
        }
    }


//    public void cheatpaddlelength(){
//        int cheatPADDLEWIDTH=PADDLE_WIDTH*2;
//        Paddle myPaddle = new Paddle(Game.WIDTH/2 - Game.PADDLE_WIDTH/2, Game.HEIGHT - Game.PADDLE_HEIGHT, cheatPADDLEWIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
//    }

}
