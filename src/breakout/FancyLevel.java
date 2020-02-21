package breakout;

public class FancyLevel extends Level {
    /**
     * Constructor to set up a level
     *
     * @param file = brick configuration
     */
    public FancyLevel(String file) {
        super(file);
    }

    @Override
    public String getLevel() {
        return "4";
    }
}
