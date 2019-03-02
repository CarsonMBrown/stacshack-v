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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Cave Adventure");

        Group root = new Group();
        Scene scene = new Scene(root);
        theStage.setScene(scene);

        Canvas canvas = new Canvas(1000, 1000);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font timesNewRoman = Font.font("Times New Roman", FontWeight.BOLD, 48);

        // Time program was started at
        final long startNanoTime = System.nanoTime();

        ArrayList<String> input = new ArrayList<>();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Time to base everything on.
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                // Clears canvas
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // TODO add game logic here!
                // These add/remove pressed buttons from the input list.
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // Only add pressed button once
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                });
                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

                // TODO Draw everything here!

            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}