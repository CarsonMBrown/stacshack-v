package gameObjects;

import gameWindow.Main;
import javafx.scene.image.Image;

public class Lava extends GameObject {

    private static final int START_X = 0;
    private static final int START_Y = 0;
    private static final int WIDTH = 300;
    private static final int HEIGHT = Main.SCREEN_HEIGHT;

    public Lava() {
        super(START_X, START_Y, WIDTH, HEIGHT, new Image("assets/lava/lava1.png", WIDTH, HEIGHT, false, false));
    }

}
