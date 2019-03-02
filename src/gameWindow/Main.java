package gameWindow;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Canvas Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(1000, 1000);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("Hello, World!", 60, 50);
        gc.strokeText("Hello, World!", 60, 50);

        Image earth = new Image("doot.jpeg");
        final long startNanoTime = System.nanoTime();
        
        new AnimationTimer() {
        	public void handle(long currentNanoTime) {
        		 double t = (currentNanoTime - startNanoTime) / 1000000000.0;
        		 
        		 //TODO add game logic here!
        	}
        }.start();

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}