package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Iterator;
import java.util.List;

public abstract class Brick extends Sprite {

    protected Rectangle myShape;


    public Brick(double x, double y, double width, double height, Paint color) {
        myShape = new Rectangle(x, y, width, height);
        myShape.setFill(color);
    }

    public Rectangle getShape() {
        return myShape;
    }

    public void removeBrick() {
        myShape.setFill(null);
    }

    public abstract boolean checkBreak(Ball ball);

    public boolean brick_canbreak(boolean BREAKABLE){return BREAKABLE;}


  public int decreaseScore(int score) {
      score -= 1;
      return score;
  }



}
