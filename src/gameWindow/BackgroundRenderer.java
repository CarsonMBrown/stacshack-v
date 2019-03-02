package gameWindow;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundRenderer {
    ArrayList<Double> backgroundLocationX = new ArrayList<Double>();
    ArrayList<Image> backgroundImages = new ArrayList<Image>();

    public BackgroundRenderer() {
        for (int i = 0; i < 4; i++) {
            backgroundLocationX.add(0.0);
            backgroundImages.add(new Image("assets/background/background-" + i + ".png", 2500, 500, true, false));
        }
    }

    public void moveBackGround(int vX) {
        for (int i = 0; i < 4; i++) {
            backgroundLocationX.set(i, (backgroundLocationX.get(i) - .02 * vX * Math.pow(2, i)) % backgroundImages.get(i).getWidth());
        }
    }

    public void drawBackGround(GraphicsContext gc) {
        for (int i = 0; i < 4; i++) {
            gc.drawImage(backgroundImages.get(i), backgroundLocationX.get(i) - backgroundImages.get(i).getWidth(), 0);
            gc.drawImage(backgroundImages.get(i), backgroundLocationX.get(i), 0);
            gc.drawImage(backgroundImages.get(i), backgroundLocationX.get(i) + backgroundImages.get(i).getWidth(), 0);
        }
    }
}