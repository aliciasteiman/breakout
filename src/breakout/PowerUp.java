package breakout;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerUp extends Sprite {
    private List<Brick> myPowerUps;
    //private List<Brick> bricks;
    private int NUM_POWERUPS;

    public PowerUp() {
        myPowerUps = new ArrayList<>();
        List<Brick> bricks = myBricks.getBricks();
        Collections.shuffle(bricks);
        for (int i = 0; i < NUM_POWERUPS; i++) {
            Rectangle brick = bricks.get(i).getShape();
            Brick powerUp = new Brick(brick.getX(), brick.getY(), 50, 20, Color.BLACK);
            myPowerUps.add(powerUp);
        }
    }

    public List<Brick> getPowerUps() {
        return myPowerUps;
    }


    public String getPower() {
        ArrayList<String> allPowers = new ArrayList<>();
        allPowers.add("longerPaddle");
        allPowers.add("shorterPaddle");
        allPowers.add("multipleBalls");
        allPowers.add("ballSpeedUp");
        allPowers.add("ballSlowDown");
        NUM_POWERUPS = allPowers.size();
        Collections.shuffle(allPowers);
        return allPowers.get(0);
    }

    public String setPower(){
        ArrayList<String> powersList = new ArrayList<>();
        powersList.add("longerpaddle");
        powersList.add("shorterpaddle");
        powersList.add("multipleballs");
        powersList.add("ballspeedup");
        powersList.add("ballslowdown");
        Collections.shuffle(powersList);
        NUM_POWERUPS = powersList.size();
       return powersList.get(0);
    }

    public void longerPaddle(Paddle myPaddle){
        myPaddle.getShape().setWidth(PADDLE_WIDTH * 2);
    }

    public void shorterPaddle(Paddle myPaddle) {
        myPaddle.getShape().setWidth(PADDLE_WIDTH/2);
    }

    public void ballSpeedUp(Ball myBall){
        int fastSpeed = myBall.BALL_SPEED * 3;
        myBall.BALL_SPEED = fastSpeed;
    }

    public void ballslowdown(Ball myBall){
        int slowSpeed= myBall.BALL_SPEED/3;
        myBall.BALL_SPEED = slowSpeed;
    }

}
