package Snake.Enum;

/**
 * This class choose in what direction snake are going
 */
public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * In this method we watch in what direction we going right now and return change of snake head on x coordinate
     * @return change of snake head on x coordinate
     */
    public int deltaX() {
        switch (this) {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * In this method we watch in what direction we going right now and return change of snake head on y coordinate
     * @return change of snake head on y coordinate
     */
    public int deltaY() {
        switch (this) {
            case UP:
                return 1;
            case DOWN:
                return -1;
            default:
                return 0;
        }
    }
}
