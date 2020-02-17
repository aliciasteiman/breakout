package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public abstract class PowerUp extends Sprite {
    
    protected Ellipse myShape;
    private int POWERUP_SPEED = 100;
    Timeline myAnimation;

    public PowerUp(double xPos, double yPos, double xRad, double yRad, Paint color) {
        myShape = new Ellipse(xPos, yPos, xRad, yRad);
        myShape.setFill(color);
    }

    public Ellipse getShape() {
        return myShape;
    }

    public void dropPowerUp(double elapsedTime) {
        myShape.setCenterY(myShape.getCenterY() + POWERUP_SPEED * elapsedTime);
    }

    public void removePowerUp() {
        myShape.setFill(null);
    }

    public abstract void revertPowerUp();

    public void setPowerUpDrop() {
        KeyFrame frame = new KeyFrame(Duration.seconds(Game.SECOND_DELAY), e -> dropPowerUp(Game.SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    public void setPowerUpLife() {
        KeyFrame frame = new KeyFrame(Duration.seconds(Game.SECOND_DELAY), e -> revertPowerUp());
        myAnimation = new Timeline();
        myAnimation.setCycleCount(1);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.setDelay(Duration.seconds(10));
        myAnimation.play(); //create a helper method to set up a timeline with a cycle count parameter
    }

    public abstract void applyPowerUp(Paddle paddle, Ball ball);

    public boolean checkHitPaddle(Paddle paddle, Ball ball) {
        if (checkCollision(myShape, paddle.getShape())) {
            removePowerUp();
            applyPowerUp(paddle, ball);
            setPowerUpLife();
            return true;
        }
        return false;
    }
}
