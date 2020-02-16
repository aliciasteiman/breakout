package breakout;


import javafx.scene.shape.Shape;


public abstract class Sprite { //think about if this class is really necessary

    protected int PADDLE_WIDTH = 120;
    protected int PADDLE_HEIGHT = 15;
    protected int PADDLE_SPEED = 30;


    protected int dx = 1;//move to ball
    protected int dy = 1;
    protected int BALL_SPEED = 100;


    //public abstract void updatePosition(double elapsedTime);

    public boolean checkCollision(Shape a, Shape b) {
        return (Shape.intersect(a, b).getBoundsInLocal().getWidth() != -1);
    }

}
