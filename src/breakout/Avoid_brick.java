package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import breakout.Level;

import java.util.List;


public class Avoid_brick extends Brick{

        private boolean BOOL = true;

        public Avoid_brick(double x, double y, double width, double height, Paint color) {
            super(x, y, width, height, color);

        }

        @Override
        public boolean checkBreak(Ball ball) {
            if (checkCollision(ball.getShape(), myShape)) {
               brick_canbreak(BOOL);
            }
            return false;
        }

    }

