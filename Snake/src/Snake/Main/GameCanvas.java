package Snake.Main;

import Snake.GameStates.GameStateManager;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * This clase create our scene
 */
public class GameCanvas extends javafx.scene.canvas.Canvas
        implements Runnable{

    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static final int CELL_SIZE=15;

    // game state manager
    private GameStateManager gsm;

    // image
    GraphicsContext g;

    // game thread
    private Thread thread;
    private boolean running;
    public static int FPS = 20;

    /**
     * this method set fps of our scene
     * @param fps  FPS of game
     */
    public static void setFPS(int fps){
        FPS=fps;
    }

    /**
     * This is constructor for our class
     */
    public GameCanvas() {
        super(WIDTH*CELL_SIZE,HEIGHT*CELL_SIZE);
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
            setOnKeyPressed(new EventHandler<KeyEvent>() {
                /**
                 * method for use key when they pressed
                 * @param event for key
                 */
                @Override
                public void handle(KeyEvent event) {
                    gsm.keyPressed(event.getCode());
                }
            });
            setOnKeyReleased(new EventHandler<KeyEvent>() {
                /**
                 * method for release key
                 * @param event for key
                 */
                @Override
                public void handle(KeyEvent event) {
                    gsm.keyReleased(event.getCode());
                }
            });
        }
    }

    /**
     * method that initialized gamestatemanager
     */
    private void init() {
        g = getGraphicsContext2D();

        running = true;

        gsm = new GameStateManager();

    }

    /**
     * method for update gsm
     */
    private void update() {
        gsm.update();
    }

    /**
     * method for draw gsm
     */
    private void draw() {
        gsm.draw(g);
    }

    /**
     * This is method for run our game
     */
    @Override
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while(running) {

            start = System.nanoTime();

            update();
            draw();

            long targetTime = 1000 / FPS;

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        }
    }
}
