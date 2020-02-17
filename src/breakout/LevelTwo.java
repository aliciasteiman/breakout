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
    public boolean checkBricksClear() {
        return brickTracker == 0;
    }

    @Override
    public String getLevel() {
        return "2";
    }

}
