package breakout;
import java.awt.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.image.*;


public class PowerUp extends Game {
    private ArrayList<PowerUp> myPowerUps;
    private int NUM_POWERUPS;
    public static ArrayList<Bricks> myBricks;


    public void createPowerUps(){
        ArrayList<PowerUp> myPowerUps = new ArrayList<>();
        Collections.shuffle(myBricks);
        for (int i = 0; i < NUM_POWERUPS; i++){
            //put power ups in bricks
            //add power ups to myPowerUps
        }

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

    public void longerPaddle(Paddle myPaddle){
        myPaddle.getShape().setWidth(PADDLE_WIDTH * 2);
    }

    public void shorterPaddle(Paddle myPaddle) {
        myPaddle.getShape().setWidth(PADDLE_WIDTH/2);
    }
}
