package gameObjects;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends GameObject {
    private static final int START_X = 100, START_Y = 100;
    private static final int WIDTH = 40, HEIGHT = 50;

    private static final double FRICTION_X = .99;
    private static final double FRICTION_Y = .99;

    private static final double MAX_VELOCITY_AIR_X = 100;
    private static final double MAX_VELOCITY_AIR_Y = 100;
    private static final double MAX_VELOCITY_X = 10;
    private static final double MAX_VELOCITY_Y = 1;

    private boolean isInAir = true;

    private double distanceTraveled = 0;

    private HashMap<String, Image> images = new HashMap<String, Image>();

    private Block tetheredTo;
//    private String currentImage = "idle";

    public Player() {
        super(START_X, START_Y, WIDTH, HEIGHT, "assets/player/idle.png");

        images.put("run0", new Image("assets/player/run0.png", WIDTH, HEIGHT, false, false));
        images.put("run1", new Image("assets/player/run1.png", WIDTH, HEIGHT, false, false));
        images.put("fall", new Image("assets/player/fall.png", WIDTH, HEIGHT, false, false));
        images.put("idle", new Image("assets/player/idle.png", WIDTH, HEIGHT, false, false));
        images.put("rise", new Image("assets/player/rise.png", WIDTH, HEIGHT, false, false));
    }

    public void handleInput(Block b) {
        tetheredTo = b;
        handleInput();
    }

    public void handleInput() {
        if (tetheredTo != null) {
            double dx = tetheredTo.getX() + tetheredTo.getWidth() / 2 - getX() - getWidth() / 2;
            double dy = tetheredTo.getY() + tetheredTo.getHeight() / 2 - getY() - getHeight() / 2;

            double angle = Math.atan2(dy, dx);
            double dist = Math.sqrt((dx - getX()) * (dx - getX()) + (dy - getY()) * (dy - getY()));

            if (Math.sin(-angle) < 1) {
                addVelocity(dist * 2 * Math.sin(-angle), dist * 2 * Math.cos(-angle));
            } else {
                addVelocity(dist * 2 * Math.sin(angle), dist * 2 * Math.cos(-angle));
            }
        }

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
                setVelocity(Math.min(getVelocityX(), MAX_VELOCITY_X), getVelocityY());
            } else if (getVelocityX() < -MAX_VELOCITY_X) {
                setVelocity(Math.max(getVelocityX(), -MAX_VELOCITY_X), getVelocityY());
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
                // The Best Code
                Image[] walkImages = new Image[2];
                int imageIndex;
                if (Math.random() * MAX_VELOCITY_X < Math.abs(getVelocityX())) {
                    imageIndex = (int) (Math.random() * 2);
                    setImage(images.get("run" + Math.abs(imageIndex)));
                }
            }
        }

        if (getVelocityX() == 0 && getVelocityY() == 0) {
            setImage(images.get("idle"));
        }
    }

    @Override
    public void update(double t) {
        if (isInAir) {
            addVelocity(0, .1);
        }

        degradeVelocityX();

        double d = getY() + t * getVelocityY();
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
        if (Math.abs(getVelocityX()) < .2) {
            setVelocity(0, getVelocityY() * FRICTION_Y);
        } else {
            setVelocity(getVelocityX() * FRICTION_X, getVelocityY() * FRICTION_Y);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (tetheredTo != null) {
            gc.strokeLine(getX() + getWidth() / 2, getY() + getHeight() / 2, tetheredTo.getX() + tetheredTo.getWidth() / 2,
                    tetheredTo.getY() + tetheredTo.getHeight() / 2);
        }
        super.render(gc);
    }
}
