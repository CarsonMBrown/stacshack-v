package gameObjects;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

public class Player extends GameObject {
    private static final int START_X = 100, START_Y = 100;
    private static final int WIDTH = 40, HEIGHT = 50;

    private static final double FRICTION = .99;

    private static final double MAX_VELOCITY_AIR_X = 20;
    private static final double MAX_VELOCITY_AIR_Y = 100;
    private static final double MAX_VELOCITY_X = 10;
    private static final double MAX_VELOCITY_Y = 1;

    private boolean isInAir = true;

    private double distanceTraveled = 0;

    private HashMap<String, Image> images = new HashMap<String, Image>();
//    private String currentImage = "idle";

    public Player() {
        super(START_X, START_Y, WIDTH, HEIGHT, "assets/player/idle.png");

        images.put("run0", new Image("assets/player/run0.png", WIDTH, HEIGHT, false, false));
        images.put("run1", new Image("assets/player/run1.png", WIDTH, HEIGHT, false, false));
        images.put("fall", new Image("assets/player/fall.png", WIDTH, HEIGHT, false, false));
        images.put("idle", new Image("assets/player/idle.png", WIDTH, HEIGHT, false, false));
        images.put("rise", new Image("assets/player/rise.png", WIDTH, HEIGHT, false, false));
    }

    public void handleInput(ArrayList<String> input) {
        if (input.contains("LEFT")) {
            addVelocity(-1.1, 0);
        } else if (input.contains("RIGHT")) {
            addVelocity(1.1, 0);
        } else if (input.contains("DOWN")) {
            addVelocity(0, 1);
        } else if (input.contains("UP")) {
            addVelocity(0, -1);
        }

        printVelocity();
        if (isInAir) {
            if (getVelocityX() > MAX_VELOCITY_AIR_X) {
                setVelocity(Math.min(getVelocityX(), MAX_VELOCITY_AIR_X), getVelocityY());
            } else if (getVelocityX() < -MAX_VELOCITY_AIR_X) {
                setVelocity(Math.max(getVelocityX(), -MAX_VELOCITY_AIR_X), getVelocityY());
            }
            if (getVelocityY() > MAX_VELOCITY_AIR_Y) {
                setVelocity(getVelocityX(), Math.min(getVelocityY(), MAX_VELOCITY_AIR_Y));
            } else if (getVelocityY() < -MAX_VELOCITY_AIR_Y) {
                setVelocity(getVelocityX(), Math.max(getVelocityY(), -MAX_VELOCITY_AIR_Y));
            }
        } else {
            if (getVelocityX() > MAX_VELOCITY_X) {
                setVelocity(Math.min(getVelocityX(), MAX_VELOCITY_X), 0);
            } else if (getVelocityY() < -MAX_VELOCITY_X) {
                setVelocity(Math.max(getVelocityX(), -MAX_VELOCITY_X), 0);
            }
        }
        distanceTraveled += Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2));
    }

    public void animate(double t) {
        final double duration = 0.7d / getVelocityX();

        if (isInAir) {
            if (getVelocityY() > 0) {
                setImage(images.get("fall"));
            } else {
                setImage(images.get("rise"));
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
//        addVelocity(0, .005);
        double d = getY() + t * getVelocityY();
        degradeVelocityX();
        setY(d);
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void printVelocity() {
        System.out.println(getVelocityX() + " | " + getVelocityY());
    }

    public boolean isInAir() {
        return isInAir;
    }

    public void setInAir(boolean isInAir) {
        this.isInAir = isInAir;
    }

    public void degradeVelocityX() {
//        if (Math.abs(getVelocityX()) < .2) {
//            setVelocity(0, getVelocityY());
//        } else {
//            setVelocity(getVelocityX() * FRICTION, getVelocityY());
//        }
    }
}
