package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

/**
 * Abstract class representing any given Level. Able to set up a configuration of bricks, check if the level
 * is completed, check a brick collision, and return a user score.
 */
public abstract class Level extends Sprite {

    //look at duplication in LevelOne and LevelTwo and see if there is any way to remove it
    //maybe make createConfiguration NOT abstract and implement it here because the file will eventually
    //indicate what type of Brick object to create based on a number

    protected final int BRICK_HEIGHT = 20;
    protected final int ROW_SEPARATION = 2;
    protected int brickTracker = 0;
    protected int SCORE = 0;
    protected List<String> configurations;
    protected List<Brick> bricksLevel;
    private Brick brick;
    protected int avoidBricks;
    private int MAX_ROWS = 7;



    /**
     * Constructor to set up a level with random brick configurations
     * @param file = brick configuration
     */
    public Level(String file) {
        configurations = new ArrayList<>();
        Scanner input = new Scanner(Level.class.getClassLoader().getResourceAsStream(file));

//        while (input.hasNext()) {
//            configurations.add(input.nextLine());
//        }
        List templist = new ArrayList<>();
        //Scanner scan = new Scanner(Level.class.getClassLoader().getResourceAsStream("overall.txt"));
        while (input.hasNext()){
            templist.add(input.nextLine());
        }
        int LINES=templist.size();
        Random rand = new Random();
        int randnum = rand.nextInt(LINES);
       // System.out.println("I'm here");
        while(!(MAX_ROWS ==0)){
            Collections.shuffle(templist);
            String s = (String) templist.get(randnum);
            configurations.add(s);
            MAX_ROWS-=1;

        }
    }

    /**
     * Determines number of rows/columns based on configurations (list of each line of the file passed to Level)
     * Loops through each row (i.e. line of data) and creates a Brick object based on the number in the row
     * Adds 1 to brickTracker to determine total number of bricks
     * @return bricksLevelOne = list of Brick objects
     */
    public abstract List <Brick> createConfiguration();

    /**
     * Checks if ball hits bricks, updates score, and subtracts from brickTracker
     * @param ball
     * @param elapsedTime
     */
    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<Brick> iter = bricksLevel.iterator();
        while (iter.hasNext()) {
            Brick brick = iter.next();
            if (brick.checkBreak(ball)) {
                int[] update = brick.update();
                SCORE += update[0];
                brickTracker += update[1];
                ball.bounce(elapsedTime);
            }
        }
    }

    /**
     * Checks if all bricks needed to be hit to beat a level are cleared.
     * @return true/false
     */
    public boolean checkBricksClear() {
        return brickTracker == 0;
    }

    /**
     * @return user's score (number of bricks hit)
     */
    public int getScore() {
        return SCORE;
    }

    /**
     * @return level "1", "2", etc.
     */
    public abstract String getLevel();

    /**
     * Sets brickTracker back to 0 between levels so that when a new level is started, the brickTracker can
     * be updated correctly.
     */
    public void resetBrickTracker() {
        brickTracker = 0;
    }

}
