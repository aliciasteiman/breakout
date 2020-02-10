package breakout;
import java.util.*;
import javafx.scene.*;
import javafx.scene.image.*;


public class PowerUp extends Game{
    private ArrayList<PowerUp> myPowerups;
    public static ArrayList<Bricks> myBricks;

    public void createpowerup(){
        ArrayList<PowerUp> myPowerups = new ArrayList<>();
        Collections.shuffle(myBricks);
        for (int i=0; i<6;i++){
            
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
       return listpowers.get(0);
    }

    public void longerpaddle(ImageView myPaddle){
        myPaddle.setFitWidth(PADDLE_WIDTH*2);
    }

    public void shorterpaddle(ImageView myPaddle){
        myPaddle.setFitWidth(PADDLE_WIDTH/2);
    }
}
