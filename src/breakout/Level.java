package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

public class Level extends Sprite {

    private final int BRICK_HEIGHT = 20;
    private final int ROW_SEPARATION = 2;
    private int brickTracker = 0;
    private int SCORE = 0;

    private List<Brick> bricks;

    public Level(String file) {
        List<String> configurations = new ArrayList<>();
        bricks = new ArrayList<>();
        Scanner input = new Scanner(Level.class.getClassLoader().getResourceAsStream(file));

        while (input.hasNext()){
            configurations.add(input.nextLine());
        }

        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) 500 / NUM_COLUMNS;
/**
 * 1 is for a regular brick
 * 2 is a small brick
 * 3 is a multiple hit brick
 * 6 is a no hit brick
 * 4 is a specific hit brick(must be hit a couple of times then becomes no hit)
 * 5 is a multiple hit brick too
 */
            for (int column = 0; NUM_COLUMNS > column; column++) {
                Random rand = new Random();
                int red = rand.nextInt(255);
                int green = rand.nextInt(255);
                int blue = rand.nextInt(255);
                Paint color = Color.rgb(red, green, blue);
                Brick brick = new Brick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                bricks.add(brick);
                brickTracker += 1;
            }
        }
        //bricks.setId("bricks");
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<Brick> iter = bricks.iterator();
        while (iter.hasNext()) {
            Brick brick = iter.next();
            if (checkCollision(ball.getShape(), brick.getShape())) {
                //if (brick.hasPowerUp(powerup) then call dropPowerUp()
                ball.bounce(elapsedTime);
                brick.removeBrick();
                brickTracker -= 1;
                SCORE += 1;
            }
        }
    }

    public boolean checkBricksClear() {
        return brickTracker == 0;
    }

    public int getScore() {
        return SCORE;
    }


}
