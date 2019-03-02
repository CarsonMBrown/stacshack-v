package gameObjects;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class Player extends GameObject {
    private static final int MAX_VELOCITY_AIR_X = 20;
    private static final int MAX_VELOCITY_AIR_Y = 5;
    private static final int MAX_VELOCITY_X = 20;
    private static final int MAX_VELOCITY_Y = 5;

    private boolean isInAir = true;

    private double distanceTraveled = 0;

    private HashMap<String, Image> images = new HashMap<String, Image>();
//    private String currentImage = "idle";

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
        } else if (input.contains("RIGHT")) {
            addVelocity(1, 0);
        } else if (input.contains("DOWN")) {
            addVelocity(0, 1);
        } else if (input.contains("UP")) {
            addVelocity(0, -1);
        }

        if (isInAir) {
            if (getVelocityX() > 18) {
                setVelocity(Math.min(getVelocityX(), MAX_VELOCITY_AIR_X), getVelocityY());
            } else if (getVelocityX() < -18) {
                setVelocity(Math.max(getVelocityX(), -MAX_VELOCITY_AIR_X), getVelocityY());
            }
            if (getVelocityY() > 18) {
                setVelocity(getVelocityX(), Math.min(getVelocityY(), MAX_VELOCITY_AIR_Y));
            } else if (getVelocityX() < -18) {
                setVelocity(getVelocityX(), Math.max(getVelocityY(), -MAX_VELOCITY_AIR_Y));
            }
        } else {
            if (getVelocityX() > 9) {
                setVelocity(Math.min(getVelocityX(), MAX_VELOCITY_X), 0);
            } else if (getVelocityY() < -9) {
                setVelocity(Math.max(getVelocityX(), -MAX_VELOCITY_X), 0);
            }
        }
        distanceTraveled += Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2));
    }

    public void animate(double t) {
        final double duration = 0.7d / getVelocityX();

        if (isInAir) {
            if (getVelocityY() > 0) {
                setImage(images.get("rise"));
            } else {
                setImage(images.get("fall"));
            }
        } else {
            if (getVelocityX() == 0) {
                setImage(images.get("idle"));
            } else {
                Image[] walkImages = new Image[2];
                int imageIndex = (int) ((t % (walkImages.length * duration)) / duration);
                setImage(images.get("run" + Math.abs(imageIndex)));
            }
        }

        if (getVelocityX() == 0 && getVelocityY() == 0) {
            setImage(images.get("idle"));
        }
    }

    @Override
    public void update(double t) {
        addVelocity(0, .05);
        setY(getY() + t * getVelocityY());
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void printVelocity() {
        System.out.println(getVelocityX() + " | " + getVelocityY());
    }
}
