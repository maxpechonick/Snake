package Snake.Main;

import Snake.GameStates.GameState;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * this is the main calss of app.
 */
public class Main extends Application {
    /**
     * this method for create our stage on
     * @param primaryStage This our mainstage of the game
     * @throws Exception we throw mistake if app not created
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("Snake");
        Canvas canvas = new GameCanvas();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            /**
             * This method for exit from our app
             * @param t
             */
            @Override public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
    }

    /**
     * Entry point
     * @param args string
     */
    public static void main(String[] args) {

        launch(Main.class,args);

    }
}
