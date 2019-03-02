package gameWindow;

import java.util.ArrayList;

import gameObjects.GameObject;
import gameObjects.Player;
import javafx.scene.canvas.GraphicsContext;

public class Game {

    private ArrayList<GameObject> objects;
    private Player player;
    private int score = 0;
    private static final double SCORE_MULTIPLIER = 1.0 / 2000;

    public Game() {
        objects = new ArrayList<>();
        player = new Player();
    }

    // Called in updateScreen
    public void updatePosition(ArrayList<String> input) {
        player.handleInput(input);

    }

    public void updateScreen(double t) {
        for (GameObject o : objects) {
            o.update(t);
            // Once an object touches lava, it gets deleted.
            // If player deleted, game ends.

        }
        player.update(t);
    }

    public void addObject(GameObject o) {
        objects.add(o);
    }

    public void renderGame(GraphicsContext gc, double t) {
        player.render(gc);
        player.animate(t);
        for (GameObject o : objects) {
            o.render(gc);
        }
        gc.fillText(Long.toString(Math.round((player.getDistanceTraveled() * SCORE_MULTIPLIER))), 100, 100);
    }

    public Player getPlayer() {
        return player;
    }
}
