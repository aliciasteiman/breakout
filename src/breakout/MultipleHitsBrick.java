package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MultipleHitsBrick extends Brick {

    private int NUM_HITS = 3;
    //private Rectangle myShape;

    public MultipleHitsBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
        //myShape = new Rectangle(x, y, width, height);
        //myShape.setFill(color);
    }

    @Override
    public boolean checkBreak(Ball ball) { //executing three times? why?
        if (checkCollision(ball.getShape(), myShape)) {
            NUM_HITS -= 1;
            double opacity = myShape.getOpacity();
            opacity -= 1.0/3;
            myShape.setOpacity(opacity);
            System.out.println("hits level 2");
            System.out.println(NUM_HITS);
            System.out.println(myShape.getOpacity());
            if (NUM_HITS == 0) {
                removeBrick();
                return true;
            }
        }
        return false;
    }

}
