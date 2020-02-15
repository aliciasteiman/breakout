package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelTwo extends Level {

    private List<MultipleHitsBrick> bricksLevelTwo;
    private MultipleHitsBrick brickMH;

    public LevelTwo(String file) {
        super(file);
    }

    @Override
    public void createConfiguration() {
        bricksLevelTwo = new ArrayList<>();
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
                brickMH = new MultipleHitsBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                bricksLevelTwo.add(brickMH);
                brickTracker += 1;
            }
        }
        //bricks.setId("bricks");
    }

    @Override
    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<MultipleHitsBrick> iter = bricksLevelTwo.iterator();
        while (iter.hasNext()) {
            MultipleHitsBrick brick = iter.next();
            if (brick.checkBreak(ball)) {
                ball.bounce(elapsedTime);
                SCORE += 1;
            }
        }
    }

    //@Override
    public List<MultipleHitsBrick> getBricksMH() {
        createConfiguration();
        return bricksLevelTwo;
    }

}
