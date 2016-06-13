package Snake.GameStates;

import Snake.Entity.Apple;
import Snake.Entity.BodyPart;
import Snake.Entity.Notation;
import Snake.Entity.Snake;
import Snake.Enum.Direction;
import Snake.Main.GameCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Snake.GameStates.GameStateManager.autoPlay;


/**
 * This class do all actions when plaied game
 */
public class GameState extends State {

    private static FileWriter writer;
    static FileReader reader;
    static File file;
    static java.util.List<Notation> notations;


    Snake snake;
    Apple apple;

    /**
     * This constructor create a playing state and initilize beginning place of snake, and create first apple
     *
     * @param gsm
     */
    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        snake = new Snake(GameCanvas.WIDTH/2,
                GameCanvas.HEIGHT/2,
                Direction.RIGHT
        );
    }

    /**
     * this method update our state,snake, and if we take on this turn apple we create new,
     * and also if we already choose autoplay we continue it,
     * and  checked for death and, if game end we crete gameover state
     */
    @Override
    public void update() {

        if (GameStateManager.replay){
            replay();
            return;
        }

        if(GameStateManager.autoPlay){
            autoPlay();
        }

        snake.move();

        BodyPart head = snake.head();
        checkCollisions(head);

        if (head.getX() == apple.getX() && head.getY() == apple.getY())
        {
            List<BodyPart> body = snake.getBody();
            BodyPart lastPart = body.get(body.size() - 1);
            body.add(new BodyPart(lastPart.getX(), lastPart.getY()));
            if (isGameOver())
            {
                gsm.setState(GameStateManager.GAMEOVERSTATE);
                GameStateManager.replay=false;
                GameStateManager.autoPlay=false;
            } else
            {
                placeApple();
            }
        }
        if (isGameOver())
        {
            gsm.setState(GameStateManager.GAMEOVERSTATE);
            GameStateManager.replay=false;
            GameStateManager.autoPlay=false;

        }

        if(autoPlay)
        printNotation(createNotation());
    }


    private void checkCollisions(BodyPart head){
        if (head.getX() < 1)
        {
            head.setX(GameCanvas.WIDTH);
        }
        if (head.getX() > GameCanvas.WIDTH)
        {
            head.setX(1);
        }
        if (head.getY() < 1)
        {
            head.setY(GameCanvas.HEIGHT);
        }
        if (head.getY() > GameCanvas.HEIGHT) {
            head.setY(1);
        }
    }

    /**
     * This method created for autoplay
     */
    private void autoPlay() {
        int offsetX;
        int offsetY;
        if(apple.getX()!=snake.head().getX()){
            offsetX=snake.head().getX()-apple.getX();
            if(offsetX<=0 && Math.abs(offsetX)<=GameCanvas.WIDTH/2){
                snake.setDirection(Direction.RIGHT);
            }else {
                snake.setDirection(Direction.LEFT);
            }
        }else if(apple.getY()!=snake.head().getY()){
            offsetY=snake.head().getY()-apple.getY();
            if(offsetY<=0 && Math.abs(offsetY)<=GameCanvas.WIDTH/2){
                snake.setDirection(Direction.UP);
            }else {
                snake.setDirection(Direction.DOWN);
            }
        }
    }

    /**
     * This method draw state
     *
     * @param g
     */
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.ORANGE);
        g.fillRect(0, 0, GameCanvas.WIDTH * GameCanvas.CELL_SIZE, GameCanvas.HEIGHT * GameCanvas.CELL_SIZE);
        drawApple(g);
        drawSnake(g);
    }

    /**
     * this method initialized our state
     */
    @Override
    public void init() {
        if (!GameStateManager.replay){
            placeApple();
        }else {
            Notation nextNotation = getNextNotation();
            apple = new Apple(nextNotation.getAppleX()-1,nextNotation.getAppleY());
        }
        snake = new Snake(GameCanvas.WIDTH/2,
                GameCanvas.HEIGHT/2,
                Direction.RIGHT
        );
    }

    private boolean isGameOver() {
        if (snake.getBody().size() == GameCanvas.HEIGHT * GameCanvas.WIDTH) {
            snake = new Snake(GameCanvas.WIDTH/2,
                    GameCanvas.HEIGHT/2,
                    Direction.RIGHT
            );
            return true;
        }

        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart != snake.head()
                    && snake.head().getX() == bodyPart.getX()
                    && snake.head().getY() == bodyPart.getY()) {
                snake = new Snake(GameCanvas.WIDTH/2,
                        GameCanvas.HEIGHT/2,
                        Direction.RIGHT
                );
                return true;
            }
        }

        return false;
    }

    /**
     * this method for initialized in which way snake must to go when we play any key, and also it work for autoplay
     *
     * @param k
     */
    @Override
    public void keyPressed(KeyCode k) {
        if (!GameStateManager.autoPlay && !GameStateManager.replay){
            if(k == KeyCode.UP){
                snake.setDirection(Direction.UP);
            }else if (k == KeyCode.RIGHT){
                snake.setDirection(Direction.RIGHT);
            }else if(k == KeyCode.DOWN){
                snake.setDirection(Direction.DOWN);
            }else if(k == KeyCode.LEFT){
                snake.setDirection(Direction.LEFT);
            }
        }
        if(k == KeyCode.ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE);
            GameStateManager.replay=false;
            GameStateManager.autoPlay=false;
        }
    }

    /**
     * this method initialized when we released key
     *
     * @param k
     */
    @Override
    public void keyReleased(KeyCode k) {
    }

    /**
     * this method draw our apple
     *
     * @param g
     */
    private void drawApple(GraphicsContext g) {
        g.setFill(Color.GREEN);
        int x = apple.getX() * GameCanvas.CELL_SIZE - GameCanvas.CELL_SIZE;
        int y = GameCanvas.HEIGHT * GameCanvas.CELL_SIZE - (apple.getY() * GameCanvas.CELL_SIZE);
        g.fillRect(x, y, GameCanvas.CELL_SIZE, GameCanvas.CELL_SIZE);
    }

    /**
     * This method draw our snake, she's head and body
     *
     * @param g
     */
    private void drawSnake(GraphicsContext g) {
        g.setFill(Color.RED);
        for (BodyPart bodyPart : snake.getBody()) {
            g.fillRect(bodyPart.getX() * GameCanvas.CELL_SIZE - GameCanvas.CELL_SIZE, GameCanvas.HEIGHT * GameCanvas.CELL_SIZE - (bodyPart.getY() * GameCanvas.CELL_SIZE), GameCanvas.CELL_SIZE, GameCanvas.CELL_SIZE);
        }
        BodyPart head = snake.head();
        g.setFill(Color.MAGENTA);
        g.fillRect(head.getX() * GameCanvas.CELL_SIZE - GameCanvas.CELL_SIZE, GameCanvas.HEIGHT * GameCanvas.CELL_SIZE - (head.getY() * GameCanvas.CELL_SIZE), GameCanvas.CELL_SIZE, GameCanvas.CELL_SIZE);
    }

    /**
     * This method place apple in  random free cell
     */
    private void placeApple() {
        int x = 1 + (int) (Math.random() * GameCanvas.WIDTH);
        int y = 1 + (int) (Math.random() * GameCanvas.HEIGHT);
        while (!isCellEmpty(x, y)) {
            if (x < GameCanvas.WIDTH) {
                x++;
            } else if (y < GameCanvas.HEIGHT) {
                y++;
                x = 1;
            } else {
                x = 1;
                y = 1;
            }
        }
        apple = new Apple(x, y);
    }

    /**
     * This method watch empty cell or not
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isCellEmpty(int x, int y) {
        for (BodyPart bodyPart : snake.getBody()) {
            if (bodyPart.getX() == x && bodyPart.getY() == y)
                return false;
        }
        return true;
    }

    private void printNotation(Notation notation) {
        try {
            String s =notation.getAppleX() + " " +
                    notation.getAppleY() + " " +
                    notation.getHeadX() + " " +
                    notation.getHeadY() + " " +
                    notation.getDirection() + " ";
            System.out.println(s);
            writer.write(s);
            writer.append('\r');
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Notation createNotation() {
        return new Notation(
                apple.getX(),
                apple.getY(),
                snake.head().getX(),
                snake.head().getY(),
                snake.getDirection()
        );
    }

    private Notation getNextNotation() {
        if (notations.size() > 0) {
            return notations.remove(0);
        }
        return null;
    }

    public static void initAllNotations() {
        file = new File("D:\\notations.txt");
        try {
            reader = new FileReader("D:\\notations.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        char[] buffer = new char[(int) file.length()];
        try {
            reader.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = new String(buffer);
        String[] split = s.split("\r\n");
        notations = new ArrayList<Notation>();
        for (String s1 : split) {
            String[] split1 = s1.split(" ");
            Notation notation = new Notation(
                    Integer.parseInt(split1[0]),//appleX
                    Integer.parseInt(split1[1]),//appleY
                    Integer.parseInt(split1[2]),//headX
                    Integer.parseInt(split1[3]),//headY
                    Integer.parseInt(split1[4])//direction
            );
            notations.add(notation);
        }
    }

    private void replay(){
        Notation nextNotation = getNextNotation();
        if (nextNotation == null){
            gsm.setState(GameStateManager.GAMEOVERSTATE);
            GameStateManager.replay=false;
            GameStateManager.autoPlay=false;
            return;
        }
        snake.setDirection(nextNotation.getEnumDirection());
        snake.move();
        checkCollisions(snake.head());
        if (snake.head().getX() == apple.getX() && snake.head().getY() == apple.getY())
        {
            List<BodyPart> body = snake.getBody();
            BodyPart lastPart = body.get(body.size() - 1);
            body.add(new BodyPart(lastPart.getX(), lastPart.getY()));
            if (isGameOver())
            {
                gsm.setState(GameStateManager.GAMEOVERSTATE);
                GameStateManager.replay=false;
                GameStateManager.autoPlay=false;
            } else
            {
                apple = new Apple(nextNotation.getAppleX()-1,nextNotation.getAppleY());
            }
        }
        if (isGameOver())
        {
            gsm.setState(GameStateManager.GAMEOVERSTATE);
            GameStateManager.replay=false;
            GameStateManager.autoPlay=false;

        }
    }

    public static void setReplay() {
        GameStateManager.replay = true;
        file = new File("D:\\notations.txt");
        try {
            reader = new FileReader("D:\\notations.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        initAllNotations();
    }

    public static void setAutoplay(){
        //initialize FileWriter
        file = new File("D:\\notations.txt");
        try {
            //change to true to switch on append
            writer = new FileWriter("D:\\notations.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
