package Snake.Entity;

/**
 * This class created a bodypart of snake
 */
public class BodyPart
{
    private int x;
    private int y;

    /**
     * Constructor of bodypart
     * @param x coordinate for bodypart
     * @param y coordinate for bodypart
     */
    public BodyPart(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    /**
     * Method which return x coordinate
     * @return x coordinate
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Method which set x coordinate
     * @param x coordinate
     */
    public void setX(int x)
    {
        this.x=x;
    }

    /**
     * Method which return y coordinate
     * @return y coordinate
     */
    public int getY()
    {
        return this.y;
    }

    /**
     * Method which set y coordinate
     * @param y coordinate
     */
    public void setY(int y)
    {
        this.y=y;
    }


}
