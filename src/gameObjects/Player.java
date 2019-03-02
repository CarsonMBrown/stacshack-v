package gameObjects;

import java.util.ArrayList;

public class Player extends GameObject {
    private static final int MAX_VELOCITY_X = 10;
    private static final int MAX_VELOCITY_Y = 10;

    boolean isInAir = false;

    public Player() {
        super(100, 100, 50, 50, "assets/player/idle.png");
        // TODO Auto-generated constructor stub
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
}
