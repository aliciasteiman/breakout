package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelTwo extends Level {

    private List<Brick> bricksLevelTwo;
    private Brick brick;

    public LevelTwo(String file) {
        super(file);
    }

    @Override
    public List<Brick> createConfiguration() {
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
                brick = new MultipleHitsBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                bricksLevelTwo.add(brick);
                brickTracker += 1;
            }
        }
        return bricksLevelTwo;
    }

    @Override
    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<Brick> iter = bricksLevelTwo.iterator();
        while (iter.hasNext()) {
            Brick brick = iter.next();
            if (brick.checkBreak(ball)) {
                ball.bounce(elapsedTime);
                SCORE += 1;
            }
        }
    }

    @Override
    public String getLevel() {
        return "2";
    }

}
