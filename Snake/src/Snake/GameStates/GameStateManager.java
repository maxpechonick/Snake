package Snake.GameStates;

import Snake.Entity.Notation;
import Snake.Statistic.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

/**
 *In this class we choose our states
 */
public class GameStateManager {

    private ArrayList<State> gameStates;
    private int currentState;

    public static boolean autoPlay;
    public static boolean replay;

    public static final int MENUSTATE = 0;
    public static final int GAMESTATE = 1;
    public static final int GAMEOVERSTATE = 2;
    public static final int DIFFICULTSTATE = 3;

    /**
     * In this method we created list of our states
     */
    public GameStateManager() {

        gameStates = new ArrayList<State>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new GameState(this));
        gameStates.add(new GameOverState(this));
        gameStates.add(new DifficultState(this));
    }

    /**
     * This method initialized state that we choose
     * @param state this parameter
     */
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    /**
     * This method update state that we choose
     */
    public void update() {
        gameStates.get(currentState).update();
    }

    /**
     * This method draw state that we choose
     * @param g
     */
    public void draw(GraphicsContext g) {
        gameStates.get(currentState).draw(g);
    }

    /**
     * This method return key,that we pressed
     * @param k
     */
    public void keyPressed(KeyCode k) {
        if (k == KeyCode.F1){
            scalaSort();
        }else if (k == KeyCode.F2){
            javaSort();
        }else if (k == KeyCode.F3){
            scalaStatistic();
        }else if (k == KeyCode.F4){
            notationTransform();
        }else{
            gameStates.get(currentState).keyPressed(k);
        }
    }

    /**
     * This method return when we released key
     * @param k
     */
    public void keyReleased(KeyCode k) {
        gameStates.get(currentState).keyReleased(k);
    }
    private void scalaSort(){
        GameState.initAllNotations();
        Notation[] notations = new Notation[GameState.notations.size()];
        for (int i = 0; i < GameState.notations.size(); i++) {
            notations[i] = GameState.notations.get(i);
        }

        ScalaSort scalaSort = new ScalaSort();

        long time = System.currentTimeMillis();

        scalaSort.sort(notations);

        time = System.currentTimeMillis() - time;
        new SortDialog("Scala", notations, Long.toString(time));
    }

    private void javaSort(){
        GameState.initAllNotations();
        Notation[] notations = new Notation[GameState.notations.size()];
        for (int i = 0; i < GameState.notations.size(); i++) {
            notations[i] = GameState.notations.get(i);
        }

        JavaSort javaSort = new JavaSort();

        long time = System.currentTimeMillis();

        javaSort.qSort(notations,0,notations.length-1);

        time = System.currentTimeMillis() - time;
        new SortDialog("Java", notations, Long.toString(time));
    }

    private void scalaStatistic(){
        GameState.initAllNotations();
        Notation[] notations = new Notation[GameState.notations.size()];
        for (int i = 0; i < GameState.notations.size(); i++) {
            notations[i] = GameState.notations.get(i);
        }

        ScalaStatistic scalaStatistic = new ScalaStatistic();

        scalaStatistic.getStatistic(notations);
    }

    private void notationTransform(){
        GameState.initAllNotations();
        NotationTransformer transformer = new NotationTransformer();
        int size = 0;
        System.out.println("New botgame is started.");
        for (Notation notation : GameState.notations) {
            size++;
            System.out.println(transformer.parse(notation));
        }
        System.out.println(transformer.parse(size));
    }
}

