package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.*;

public class BrickConfiguration extends Sprite {

    private final String DATA_FILE = "line_config_small.txt";
    private final int BRICK_HEIGHT = 20;
    private final int ROW_SEPARATION = 2;
    private int brickTracker = 0;

    private List<Brick> allBricks;

    public BrickConfiguration(String file) {
        List<String> configurations = new ArrayList<>();
        allBricks = new ArrayList<>();
        Scanner input = new Scanner(BrickConfiguration.class.getClassLoader().getResourceAsStream(file));

        while (input.hasNext()){
            configurations.add(input.nextLine());
        }

        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) WIDTH / NUM_COLUMNS;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                Random rand = new Random();
                int red = rand.nextInt(255);
                int green = rand.nextInt(255);
                int blue = rand.nextInt(255);
                Paint color = Color.rgb(red, green, blue);
                Brick brick = new Brick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                allBricks.add(brick);
                brickTracker += 1;
            }
        }
    }



    /*
    public List<Brick> drawBricks(){
        List<String> configurations = new ArrayList<>();
        allBricks = new ArrayList<>();
        Scanner input = new Scanner(BrickConfiguration.class.getClassLoader().getResourceAsStream(DATA_FILE));

        while (input.hasNext()){
            configurations.add(input.nextLine());
        }

        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) WIDTH / NUM_COLUMNS;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                Random rand = new Random();
                int red = rand.nextInt(255);
                int green = rand.nextInt(255);
                int blue = rand.nextInt(255);
                Paint color = Color.rgb(red, green, blue);
                Brick brick = new Brick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                allBricks.add(brick);
                brickTracker += 1;
            }
        }
        return allBricks;
    }

     */

    public List<Brick> getBricks() {
        return allBricks;
    }

    public void removeBrick(Brick brick) {
        brick.getShape().setFill(null);
    }

    public void checkBricks(Circle ball) {
        Iterator<Brick> iter = allBricks.iterator();
        while (iter.hasNext()) {
            Brick brick = iter.next();
            if (Shape.intersect(ball, brick.getShape()).getBoundsInLocal().getWidth() != -1) {
                //if (brick.hasPowerUp(powerup) then call dropPowerUp()
                dy *= -1;
                removeBrick(brick);
                brickTracker -= 1;
                SCORE += 1;
            }
            if (brickTracker == 0) {
                winningText.setVisible(true);
                //Game.myAnimation.stop();
            }
        }
    }


}
