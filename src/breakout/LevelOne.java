package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelOne extends Level {

    public LevelOne(String file) {
        super(file);
    }

    @Override
    public String getLevel() {
        return "1";
    }
}
