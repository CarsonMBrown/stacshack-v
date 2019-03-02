package gameObjects;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class Player extends GameObject {
    private static final int MAX_VELOCITY_X = 10;
    private static final int MAX_VELOCITY_Y = 10;

    boolean isInAir = false;

    private HashMap<String, Image> images = new HashMap<String, Image>();
    private String currentImage = "idle";

    public Player() {
        super(100, 100, 50, 50, "assets/player/idle.png");

        images.put("run0", new Image("assets/player/run0.png"));
        images.put("run1", new Image("assets/player/run1.png"));
        images.put("fall", new Image("assets/player/fall.png"));
        images.put("idle", new Image("assets/player/idle.png"));
        images.put("rise", new Image("assets/player/rise.png"));
    }

    public void handleInput(ArrayList<String> input) {
        if (input.contains("LEFT")) {
            addVelocity(-1, 0);
        }

        if (input.contains("RIGHT")) {
            addVelocity(1, 0);
        }

        if (!isInAir) {
            setVelocity(Math.min(getVelocityX(), MAX_VELOCITY_X), Math.min(getVelocityY(), MAX_VELOCITY_Y));
        }
    }

    public void animate(double t) {
        final double duration = 0.7d / getVelocityX();

        if (!isInAir) {
            if (getVelocityX() == 0) {
                setImage(images.get("idle"));
            } else {
                Image[] walkImages = new Image[2];
                int imageIndex = (int) ((t % (walkImages.length * duration)) / duration);
                setImage(images.get("run" + imageIndex));
            }
        }
    }
}
