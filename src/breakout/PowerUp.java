package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

public abstract class PowerUp extends Sprite {

    protected Ellipse myShape;
    private int POWERUP_SPEED = 100;

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
}
