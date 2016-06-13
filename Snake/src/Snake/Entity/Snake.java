package Snake.Entity;

import Snake.Enum.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class using for initializing snake and do all actions under snake
 */
public class Snake {
    private List<BodyPart> body;
    private Direction direction;

    /**
     * This is constructor for snake
     * @param x this coordinate for add new body part to snake
     * @param y this coordinate for add new body part to snake
     * @param direction in which direction snake going in this moment
     */
    public Snake(int x, int y, Direction direction) {
        this.direction = direction;
        body = new ArrayList<BodyPart>();
        body.add(new BodyPart(x, y));
        body.add(new BodyPart(x - direction.deltaX(), y - direction.deltaY()));
        body.add(new BodyPart(x - direction.deltaX() * 2, y - direction.deltaY() * 2));
    }
    /**
     * Method for move our snake ( head and bode of it)
     */
    public void move() {
        moveBody();
        moveHead();
    }

    /**
     * Method for move body of snake
     */
    private void moveBody() {
        for (int i = body.size() - 1; i > 0; i--) {
            BodyPart current = body.get(i);
            BodyPart previous = body.get(i - 1);
            current.setX(previous.getX());
            current.setY(previous.getY());
        }
    }

    /**
     * method for move head of snake
     */
    private void moveHead() {
        head().setX(head().getX() + direction.deltaX());
        head().setY(head().getY() + direction.deltaY());
    }

    /**
     * Method that return body
     * @return body
     */
    public BodyPart head()
    {
        return body.get(0);
    }

    /**
     * This method return list of body
     * @return listof body
     */
    public List<BodyPart> getBody() {
        return body;
    }

    /**
     * Method that return direction of snake
     * @return direction of snake
     */
    public Direction getDirection() {return direction;}

    /**
     * This method set direction of our snake (in which key we press)
     * @param direction we get previous direction of snake
     */
    public void setDirection(Direction direction) {
        switch (this.direction){
            case RIGHT: {
                if(direction!=Direction.LEFT){
                    this.direction=direction;
                }
                break;
            }
            case LEFT: {
                if(direction!=Direction.RIGHT){
                    this.direction=direction;
                }
                break;
            }
            case UP: {
                if(direction!=Direction.DOWN){
                    this.direction=direction;
                }
                break;
            }
            case DOWN: {
                if(direction!=Direction.UP){
                    this.direction=direction;
                }
                break;
            }
        }
    }
}
