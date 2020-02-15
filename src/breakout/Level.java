package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

public abstract class Level extends Sprite {

    protected final int BRICK_HEIGHT = 20;
    protected final int ROW_SEPARATION = 2;
    protected int brickTracker = 0;
    protected int SCORE = 0;

    protected List<String> configurations;

    public Level(String file) {
        configurations = new ArrayList<>();
        Scanner input = new Scanner(Level.class.getClassLoader().getResourceAsStream(file));

        while (input.hasNext()) {
            configurations.add(input.nextLine());
        }
    }

    public abstract void createConfiguration();

    public abstract List<Brick> getBricks();

    public abstract void checkBrickCollision(Ball ball, double elapsedTime);

    public boolean checkBricksClear() {
        return brickTracker == 0;
    }

    public int getScore() {
        return SCORE;
    }


}
