package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MultipleHitsBrick extends Brick {

    private int NUM_HITS = 3;
    private Rectangle myShapeMH;

    public MultipleHitsBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
        myShapeMH = new Rectangle(x, y, width, height);
        myShapeMH.setFill(color);
    }

    @Override
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShapeMH)) {
            System.out.println("hits");
            NUM_HITS -=1;
            double opacity = myShapeMH.getOpacity();
            opacity -= 1.0/3;
            myShapeMH.setOpacity(opacity);
            System.out.println(NUM_HITS);
            System.out.println(myShapeMH.getOpacity());
            if (NUM_HITS == 0) {
                removeBrick();
                return true;
            }
        }
        return false;
    }

}
