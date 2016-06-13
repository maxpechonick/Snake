package Snake.GameStates;

import Snake.Main.GameCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 *This is class for create difficult state
 */
public class DifficultState extends State {

    private int currentChoice;

    private String[] options = {
            "Easy",
            "Normal",
            "Nightmare"
    };

    /**
     * This is constructor for this class
     * @param gsm we give in this constructor our common gamestatemanager
     */
    public DifficultState(GameStateManager gsm) {
        this.gsm = gsm;
        currentChoice = 0;
    }

    /**
     * this method initialized state
     */
    @Override
    public void init() {

    }

    /**
     * this method update state
     */
    @Override
    public void update() {

    }

    /**
     * This method draw our state
     * @param g Graphicks Context
     */
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.GOLDENROD);
        g.fillRect(0, 0, GameCanvas.WIDTH * GameCanvas.CELL_SIZE, GameCanvas.HEIGHT * GameCanvas.CELL_SIZE);

        //draw menu
        g.setLineWidth(1);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setStroke(Color.GREEN);
            } else {
                g.setStroke(Color.BLUE);
            }
            g.strokeText(options[i], 530, 140 + i * 40,300);
        }
    }

    /**
     * This method for choose , which difficult we want to choose
     * @param k Key code
     */
    @Override
    public void keyPressed(KeyCode k) {
        if (k == KeyCode.UP) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = options.length - 1;
            }
        } else if (k == KeyCode.DOWN) {
            currentChoice++;
            if (currentChoice > options.length - 1) {
                currentChoice = 0;
            }
        } else if (k == KeyCode.ENTER) {
            select();
        } else if (k == KeyCode.ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

    /**
     * this method for see when we released key
     * @param k Key code
     */
    @Override
    public void keyReleased(KeyCode k) {

    }

    /**
     * in this method we change speed of our snake
     */
    private void select() {

        if (currentChoice==0){
            GameCanvas.setFPS(10);
            gsm.setState(GameStateManager.MENUSTATE);
        }else if (currentChoice==1){
            GameCanvas.setFPS(15);
            gsm.setState(GameStateManager.MENUSTATE);
        }else if (currentChoice==2){
            GameCanvas.setFPS(20);
            gsm.setState(GameStateManager.MENUSTATE);
        }

    }

}
