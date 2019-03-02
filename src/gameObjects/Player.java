package gameObjects;

import java.util.ArrayList;

public class Player extends GameObject {
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
    }
}
