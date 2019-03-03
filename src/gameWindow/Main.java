package gameWindow;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    public final static int SCREEN_WIDTH = 1000;
    public final static int SCREEN_HEIGHT = 500;

    public static boolean RIGHT_PRESSED = false;
    public static boolean LEFT_PRESSED = false;
    public static boolean UP_PRESSED = false;
    public static boolean DOWN_PRESSED = false;

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Cave");

        Group root = new Group();
        Scene scene = new Scene(root);
        theStage.setScene(scene);

        Canvas canvas = new Canvas(1000, 500);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
        gc.setLineWidth(2);
        Font leto = Font.font("Leto", FontWeight.BOLD, 20);
        gc.setFont(leto);

        BackgroundRenderer br = new BackgroundRenderer();

        // Time program was started at
        final long startNanoTime = System.nanoTime();

        ArrayList<String> input = new ArrayList<>();

        // These add/remove pressed buttons from the input list.
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (code.equals("RIGHT")) {
                    RIGHT_PRESSED = true;
                }
                if (code.equals("LEFT")) {
                    LEFT_PRESSED = true;
                }
                if (code.equals("UP")) {
                    UP_PRESSED = true;
                }
                if (code.equals("DOWN")) {
                    DOWN_PRESSED = true;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (code.equals("RIGHT")) {
                    RIGHT_PRESSED = false;
                }
                if (code.equals("LEFT")) {
                    LEFT_PRESSED = false;
                }
                if (code.equals("UP")) {
                    UP_PRESSED = false;
                }
                if (code.equals("DOWN")) {
                    DOWN_PRESSED = false;
                }
            }
        });
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                input.add("Clicked(" + event.getSceneX() + "," + event.getSceneY() + ")");
            }
        });

        Game game = new Game();

        new AnimationTimer() {
            long nanoTimeLastFrame = 0;

            @Override
            public void handle(long currentNanoTime) {
                if (nanoTimeLastFrame != 0) {
                    // Time to base everything on.
                    double t = (currentNanoTime - nanoTimeLastFrame) / 100000000.0;

                    // Clears canvas
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                    game.updatePosition(input);
                    game.updateScreen(t);
                    game.calculateCollisions();

                    br.moveBackGround((int) game.getPlayer().getVelocityX(), t);
                    br.drawBackGround(gc);
                    game.renderGame(gc, t);

                    input.clear();
                }
                nanoTimeLastFrame = currentNanoTime;
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}