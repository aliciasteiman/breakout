package breakout;
import java.awt.*;
import java.util.*;
import java.util.List;

import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint.*;


public class PowerUp extends Game {
    private ArrayList<PowerUp> myPowerUps;
    private int NUM_POWERUPS;
    public static ArrayList<Bricks> myBricks;
    public int NUM_POWERUPS;


    public void createPowerUps(){
        ArrayList<PowerUp> myPowerUps = new ArrayList<>();
        Collections.shuffle(myBricks);
        for (int i = 0; i < NUM_POWERUPS; i++){
            //put power ups in bricks
            //add power ups to myPowerUp

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
        List<PowerUp> myPowerups = new ArrayList<>();
        for (int i=0; i<NUM_POWERUPS;i++){
            Rectangle p1= new Rectangle();


        }

    }

    public String setPower(){
        ArrayList<String> listpowers = new ArrayList<>();
        listpowers.add("longerpaddle");
        listpowers.add("shorterpaddle");
        listpowers.add("multipleballs");
        listpowers.add("ballspeedup");
        listpowers.add("ballslowdown");
        //Collections.shuffle(listpowers);
        NUM_POWERUPS=listpowers.size();
       return listpowers.get(0);
    }

    public void longerpaddle(Paddle myPaddle){
        int new_width =myPaddle.WIDTH*2;
        myPaddle.WIDTH=new_width;
    }

    public void shorterpaddle(Paddle myPaddle){
        int new_width= myPaddle.WIDTH/2;
        myPaddle.WIDTH=new_width;
    }

    public void ballspeedup(Ball myBall){
        int new_speed= myBall.BALL_SPEED*3;
        myBall.BALL_SPEED=new_speed;
    }

    public void ballslowdown(Ball myBall){
        int new_speed= myBall.BALL_SPEED/3;
        myBall.BALL_SPEED=new_speed;
    }
}
