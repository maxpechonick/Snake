package Snake.GameStates;

import Snake.Main.GameCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This class write main menu state
 */
public class MenuState extends State {

    private String[] options = {
            "Play",
            "Difficult",
            "Autoplay",
            "Replay",
            "Quit"
    };

    private int currentChoice;

    public MenuState(GameStateManager gsm){
        this.gsm=gsm;
    }

    public void update(){
    }

    /**
     * This method  draw menu
     * @param g
     */
    public void draw(GraphicsContext g){
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
     *this method for initializing
     */
    @Override
    public void init() {

    }

    /**
     * In this method we know what string we select
     */
    private void select(){
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.GAMESTATE);
        }if (currentChoice==1){
            gsm.setState(GameStateManager.DIFFICULTSTATE);
        }if (currentChoice == 2){
            GameStateManager.autoPlay=true;
            GameState.setAutoplay();
            gsm.setState(GameStateManager.GAMESTATE);
        }if (currentChoice == 3){
            GameStateManager.replay=true;
            GameState.setReplay();
            gsm.setState(GameStateManager.GAMESTATE);
        }
        if (currentChoice == 4) {
            System.exit(0);
        }
    }

    /**
     * In this method we moving on menu
     * @param k
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
        }
    }

    /**
     * This method override to know when we released key
     * @param k
     */
    @Override
    public void keyReleased(KeyCode k) {

    }
}
