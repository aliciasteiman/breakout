package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class AddLife extends PowerUp {

    private int lives;

    public AddLife(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    @Override
    public void revertPowerUp() {
        return;
    }

    @Override
    public void applyPowerUp(Paddle paddle, Ball ball) {
        lives = ball.getLives();
        ball.updateLives(1);
    }

}
