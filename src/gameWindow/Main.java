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
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Cave Adventure");

        Group root = new Group();
        Scene scene = new Scene(root);
        theStage.setScene(scene);

        Canvas canvas = new Canvas(1000, 500);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        // Font timesNewRoman = Font.font("Times New Roman", FontWeight.BOLD, 48);

        BackgroundRenderer br = new BackgroundRenderer();

        // Time program was started at
        final long startNanoTime = System.nanoTime();

        ArrayList<String> input = new ArrayList<>();

        Game game = new Game();
        
        
        // These add/remove pressed buttons from the input list.
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
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
        
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // Time to base everything on.
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                for (String i : input) {
                	System.out.println(i);
                }
                // Clears canvas
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                br.moveBackGround(3);
                

                // TODO add game logic here!
                
                
                //Handles input vector changing
                game.updatePosition(input);
                //Renders
                game.updateScreen(t);

                // TODO Draw everything here!
                br.drawBackGround(gc);
                game.renderGame(gc);
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}