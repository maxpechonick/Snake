package Snake.GameStates;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * This abstract class created for easiely work with menu
 */
public abstract class State {

    protected GameStateManager gsm;

    public abstract void update();
    public abstract void draw(GraphicsContext g);
    public abstract void init();
    public abstract void keyPressed(KeyCode k);
    public abstract void keyReleased(KeyCode k);
}
