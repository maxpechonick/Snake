package Snake.Entity;

/**
 * This class created apples
 */
public class Apple
{
    private int x;
    private int y;

    /**
     * Constructor for apple
     * @param x coordinate for x
     * @param y coordinate for y
     */
    public Apple(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    /**
     * Method which return x coordinate of apple
     * @return x coordinate
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Method which return y coordinate of apple
     * @return y coordinate
     */
    public int getY()
    {
        return this.y;
    }
}
