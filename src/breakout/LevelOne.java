package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelOne extends Level {

    //might need to revisit brickTracker (b/c it's in Level I'm afraid it won't know when bricks of a level are gone)
    //maybe make it a private variable in each subclass

    private List<Brick> bricksLevelOne;
    private Brick brick;

    public LevelOne(String file) {
        super(file);
    }

    @Override
    public boolean checkBricksClear() {
        return brickTracker == 0;
    }

    @Override
    public String getLevel() {
        return "1";
    }
}
