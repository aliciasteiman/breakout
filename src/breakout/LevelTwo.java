package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelTwo extends Level {
    private Brick brick;

    public LevelTwo(String file) {
        super(file);
    }
    /**
     * overrides create configuration to generate a random configuration of bricks
     * everytime the level is run while respecting level difficulty
     * @return random configuration
     */
    @Override
    public List<Brick> createConfiguration() {
        bricksLevel = new ArrayList<>();
        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) 500 / NUM_COLUMNS;
            int num =0;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                if (holder.charAt(column) == '0') {
                    brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, null);
                    brick.getShape().setVisible(false);
                    brickTracker -= 1;
                }
                else if (num%2==0) {
                    brick = new PowerUpBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, Color.GOLD);

                }
                 else if(num%2==1) {
                    brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, Color.SEAGREEN);

                }
                 num+=1;


                brickTracker += 1;
                bricksLevel.add(brick);
            }
        }
        return bricksLevel;
    }

    @Override
    public String getLevel() {
        return "2";
    }

}
