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
    public boolean checkBricksClear() {
        return brickTracker == avoidBricks;
    }

    @Override
    public String getLevel() {
        return "3";
    }
}
