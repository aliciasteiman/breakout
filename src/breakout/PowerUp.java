package breakout;
import java.util.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Paint.*;


public class PowerUp extends Game{
    private ArrayList<PowerUp> myPowerups;
    public static ArrayList<Bricks> myBricks;
    //private Brick brick;
    public int NUM_POWERUPS;





    public void createpowerup(ArrayList<Bricks> myBricks){
        myBricks = Bricks.myBricks;
        Collections.shuffle(myBricks);
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
        myPaddle.WIDTH=myPaddle.WIDTH*2;
    }

    public void shorterpaddle(Paddle myPaddle){
        myPaddle.WIDTH=myPaddle.WIDTH/2;
    }

    public void ballspeedup(Ball myBall){
        myBall.BALL_SPEED=myBall.BALL_SPEED*3;
    }

    public void ballslowdown(Ball myBall){
        myBall.BALL_SPEED=myBall.BALL_SPEED/3;
    }
}
