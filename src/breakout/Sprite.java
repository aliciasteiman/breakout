package breakout;


import javafx.scene.shape.Shape;


public abstract class Sprite {

    protected int dx = 1;
    protected int dy = 1;

    //public abstract void updatePosition(double elapsedTime);

    public boolean checkCollision(Shape a, Shape b) {
        return (Shape.intersect(a, b).getBoundsInLocal().getWidth() != -1);
    }
}
