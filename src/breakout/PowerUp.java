package breakout;
import java.util.*;
import javafx.scene.*;
import javafx.scene.image.*;


public class PowerUp extends Game{
    private ArrayList<PowerUp> myPowerups;

    

    public void createpowerup(){

    }

    public String setPower(){
        ArrayList<String> listpowers = new ArrayList<>();
        listpowers.add("longerpaddle");
        listpowers.add("shorterpaddle");
        listpowers.add("multipleballs");
        listpowers.add("ballspeedup");
        listpowers.add("ballslowdown");
        Collections.shuffle(listpowers);
       return listpowers.get(0);
    }
}
