package breakout;
import java.util.*;
import javafx.scene.*;
import javafx.scene.image.*;


public class PowerUp extends Game{
    private ArrayList<PowerUp> myPowerups;
    public static ArrayList<Bricks> myBricks;
    //private Brick brick;





    public void createpowerup(ArrayList<Bricks> myBricks){
        myBricks = Bricks.myBricks;
        Collections.shuffle(myBricks);
        ArrayList<PowerUp> myPowerups = new ArrayList<>();
        for (int i=0; i<6;i++){
//            PowerUp up= new PowerUp(myBricks.get(i));
//            up.PowerUp.setVisible(false);

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
