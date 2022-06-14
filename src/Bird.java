import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Bird {
    private final Image WING_DOWN_IMAGE = new Image("res/level-0/birdWingDown.png");
    private final Image WING_UP_IMAGE = new Image("res/level-0/birdWingUp.png");
    private final Image WING_DOWN_IMAGE1 = new Image("res/level-1/birdWingDown.png");
    private final Image WING_UP_IMAGE1 = new Image("res/level-1/birdWingUp.png");
    private final double X = 200;
    private final double FLY_SIZE = 6;
    private final double FALL_SIZE = 0.4;
    private final double INITIAL_Y = 350;
    private final double Y_TERMINAL_VELOCITY = 10;
    private final double SWITCH_FRAME = 10;
    private int frameCount = 0;
    private double y;
    private double yVelocity;
    private Rectangle boundingBox;
    private boolean levelUp = false;
    private boolean holdWeapon = false;


    public Bird() {
        y = INITIAL_Y;
        yVelocity = 0;
        boundingBox = WING_DOWN_IMAGE.getBoundingBoxAt(new Point(X, y));
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return X;
    }

    public void setY(double y){
        this.y = y;
    }
    public Rectangle update(Input input) {
        frameCount += 1;
        if (input.wasPressed(Keys.SPACE)) {
            yVelocity = -FLY_SIZE;
            WING_DOWN_IMAGE.draw(X, y);
        }
        else {
            yVelocity = Math.min(yVelocity + FALL_SIZE, Y_TERMINAL_VELOCITY);
            if (frameCount % SWITCH_FRAME == 0) {
                if(levelUp){
                    WING_UP_IMAGE1.draw(X, y);
                    boundingBox = WING_UP_IMAGE1.getBoundingBoxAt(new Point(X, y));
                }else{
                    WING_UP_IMAGE.draw(X, y);
                    boundingBox = WING_UP_IMAGE.getBoundingBoxAt(new Point(X, y));
                }

            }
            else {
                if(levelUp){
                    WING_DOWN_IMAGE1.draw(X, y);
                    boundingBox = WING_DOWN_IMAGE1.getBoundingBoxAt(new Point(X, y));
                }else{
                    WING_DOWN_IMAGE1.draw(X, y);
                    boundingBox = WING_DOWN_IMAGE1.getBoundingBoxAt(new Point(X, y));
                }
            }
        }
        y += yVelocity;

        return boundingBox;
    }
    public Rectangle getBox(){
        return this.boundingBox;
    }

    public void setLevelUp(boolean isLevelUp){
        this.levelUp = isLevelUp;
    }
    public boolean getHoldWeapon(){
        return this.holdWeapon;
    }
    public void setHoldWeapon(boolean holdWeapon){
        this.holdWeapon = holdWeapon;
    }

}
