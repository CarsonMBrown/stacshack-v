package gameWindow;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    public final static int SCREEN_WIDTH = 1000;
    public final static int SCREEN_HEIGHT = 500;

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

        Game game = new Game();

        new AnimationTimer() {
            long nanoTimeLastFrame = 0;

            @Override
            public void handle(long currentNanoTime) {
                // Time to base everything on.
                double t = (currentNanoTime - nanoTimeLastFrame) / 100000000.0;
                nanoTimeLastFrame = currentNanoTime;

                // Clears canvas
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                br.moveBackGround((int) game.getPlayer().getVelocityX(), t);

                // TODO add game logic here!

                // Handles input vector changing
                game.updatePosition(input);
                // Renders
                game.updateScreen(t);

                game.calculateCollisions();

                // TODO Draw everything here!
                br.drawBackGround(gc);
                game.renderGame(gc, t);

                input.clear();
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}