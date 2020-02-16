package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelThree extends Level{
    private List<Brick> bricksLevelThree;
    private Brick brick;

    public LevelThree(String file) {
        super(file);
    }

    @Override
    public List<Brick> createConfiguration() {
        bricksLevelThree = new ArrayList<>();
        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) 500 / NUM_COLUMNS;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                Random rand = new Random();
                int red = rand.nextInt(255);
                int green = rand.nextInt(255);
                int blue = rand.nextInt(255);
                Paint color = Color.rgb(red, green, blue);
                if(holder.charAt(column)=='1'){
                brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                bricksLevelThree.add(brick);
                brickTracker += 1;
                }
                else if(holder.charAt(column) == '2'){
                    brick = new PowerUpBrick(column * BRICK_WIDTH/2, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                    bricksLevelThree.add(brick);
                    brickTracker += 1;
                }
                else if(holder.charAt(column)=='3'){
                    brick = new MultipleHitsBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                    bricksLevelThree.add(brick);
                    brickTracker += 1;
                }

            }
        }
        return bricksLevelThree;
    }

    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<Brick> iter = bricksLevelThree.iterator();
        while (iter.hasNext()) {
            boolean BREAKABLE=true;
            Brick brick = iter.next();
            if(brick)
            if (brick.checkBreak(ball)) {
                ball.bounce(elapsedTime);
                brickTracker -= 1;
                SCORE += 1;
            }
        }
    }

    @Override
    public String getLevel() {
        return "3";
    }
}
