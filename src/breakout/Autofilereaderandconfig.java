package breakout;

import java.util.*;

public class Autofilereaderandconfig {
    private int LINES=0;
    private int MAX_ROWS=7;
    Random rand = new Random();
    private int randnum = rand.nextInt(LINES);

    /**
     * method scans file with overall configurations and creates
     * sub configurations randomly for any level
     * @return arraylist with random configurations for a level
     */
    public List create(){
        List randomgenerator = new ArrayList();
        List templist = new ArrayList();
        Scanner scan = new Scanner(Level.class.getClassLoader().getResourceAsStream("overall.txt"));
        while (scan.hasNext()){
            templist.add(scan.next());
        }
        LINES=templist.size();
        while(!(MAX_ROWS ==0)){
            Collections.shuffle(templist);
            randomgenerator.add(templist.get(randnum));
            MAX_ROWS-=1;
        }

      return randomgenerator;
    }
}
