package Snake.GameStates;

import Snake.Main.GameCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This class create gameover state
 */
public class GameOverState extends State {
    /**
     * This constructor for our state
     * @param gsm we give in this constructor our common gamestatemanager
     */
    public GameOverState(GameStateManager gsm){
        this.gsm=gsm;
    }

    /**
     * this method update our state
     */
    @Override
    public void update() {

    }

    /**
     * this method draw our state
     * @param g
     */
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, GameCanvas.WIDTH*GameCanvas.CELL_SIZE, GameCanvas.HEIGHT*GameCanvas.CELL_SIZE);

        g.setLineWidth(7);

        g.setFill(Color.WHITE);

        String message = "Press <Enter> to start new game";
        int messageWidth = 10;
        int messageHeight = 10;

        g.fillText(message,
                GameCanvas.WIDTH * GameCanvas.CELL_SIZE / 2 - messageWidth / 2,
                GameCanvas.HEIGHT * GameCanvas.CELL_SIZE / 2 - messageHeight / 2
        );
    }

    /**
     * this method initialized our state
     */
    @Override
    public void init() {

    }

    /**
     * This method intialized when we press enter
     * @param k
     */
    @Override
    public void keyPressed(KeyCode k) {
        if(k== KeyCode.ENTER){
            gsm.setState(gsm.GAMESTATE);
        }
        if(k== KeyCode.ESCAPE){
            gsm.setState(gsm.MENUSTATE);
        }
    }

    /**
     * This method initialized when we released key
     * @param k
     */
    @Override
    public void keyReleased(KeyCode k) {

    }
}
