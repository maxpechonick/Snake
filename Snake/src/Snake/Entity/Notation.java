package Snake.Entity;

import Snake.Enum.Direction;


public class Notation {

    private int appleX;
    private int appleY;
    private int headX;
    private int headY;

    //1-left 2-up 3-right 4-down
    private int direction;

    public Notation(int appleX, int appleY, int headX, int headY, Direction direction) {
        this.appleX = appleX;
        this.appleY = appleY;
        this.headX = headX;
        this.headY = headY;
        switch (direction){
            case LEFT:
                this.direction=1;
                break;
            case UP:
                this.direction=2;
                break;
            case RIGHT:
                this.direction=3;
                break;
            case DOWN:
                this.direction=4;
                break;
        }
    }

    public Notation(int appleX, int appleY, int headX, int headY, int direction) {
        this.appleX = appleX;
        this.appleY = appleY;
        this.headX = headX;
        this.headY = headY;
        this.direction = direction;
    }

    public int getAppleX() {
        return appleX;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    public int getHeadX() {
        return headX;
    }

    public void setHeadX(int headX) {
        this.headX = headX;
    }

    public int getHeadY() {
        return headY;
    }

    public void setHeadY(int headY) {
        this.headY = headY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Direction getEnumDirection(){
        switch (direction){
            case 1:
                return Direction.LEFT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.RIGHT;
            case 4:
                return Direction.DOWN;
            default:
                return null;
        }
    }
    public static String getDirectionName(int direction){
        switch (direction){
            case 1:
                return "Left";
            case 2:
                return "Up";
            case 3:
                return "Right";
            case 4:
                return "Down";
            default:
                return null;
        }
    }
}
