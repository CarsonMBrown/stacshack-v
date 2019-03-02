package gameWindow;

import java.util.ArrayList;

import Terrain.Generator;
import gameObjects.Block;
import gameObjects.GameObject;
import gameObjects.Player;
import javafx.scene.canvas.GraphicsContext;

public class Game {

    private ArrayList<GameObject> objects;
    private Player player;
    private ArrayList<Block> blocks;
    private static int displacement;
    private int score = 0;
    private static final double SCORE_MULTIPLIER = 1.0 / 2000;

    public Game() {
        objects = new ArrayList<>();
        player = new Player();
        blocks = new ArrayList<>();

        blocks.add(new Block(100, 300));
    }

    // Called in updateScreen
    public void updatePosition(ArrayList<String> input) {
        player.handleInput(input);

    }

    public boolean countBlocks(double t) {
        displacement += player.getVelocityX() * t;
        System.out.println(displacement);
        if (displacement >= Generator.BLOCK_SIZE) {
            displacement -= Generator.BLOCK_SIZE;
            return true;
        }
        return false;
    }

    public void updateScreen(double t) {
        for (GameObject o : objects) {
            o.update(t);
            // Once an object touches lava, it gets deleted.
            // If player deleted, game ends.
            if (countBlocks(t)) {
                Generator.addCol(this, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
            }
        }
        player.update(t);
    }

    public void addObject(GameObject o) {
        objects.add(o);
    }

    public void addBlock(Block b) {
        blocks.add(b);
    }

    public void renderGame(GraphicsContext gc, double t) {
        gc.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        player.render(gc);
        player.animate(t);
        for (GameObject o : objects) {
            System.out.println("AAAAA");
            o.render(gc);
        }
        gc.fillText(Long.toString(Math.round((player.getDistanceTraveled() * SCORE_MULTIPLIER))), 100, 100);
        for (Block b : blocks) {
            b.render(gc);
        }

    }

    public Player getPlayer() {
        return player;
    }

    public void calculateCollisions() {
        ArrayList<GameObject> collidedObjects = new ArrayList<GameObject>();
        for (GameObject o : blocks) {
            if (o.intersects(player)) {
                System.out.println("Collision");
                collidedObjects.add(o);
            }
        }
        for (GameObject o : collidedObjects) {
            int xDif = (int) Math.abs(o.getX() - player.getX());
            int yDif = (int) Math.abs(o.getY() - player.getY());

            if (xDif > yDif) {
                if (o.getX() < player.getX()) {
                    player.setX(o.getX() - player.getWidth());
                } else if (o.getX() > player.getX()) {
                    player.setX(o.getX() + o.getWidth());
                }
                player.setVelocity(0, player.getVelocityY());
            } else {
                if (o.getY() > player.getY()) {
                    player.setY(o.getY() - player.getHeight());
                    player.setInAir(false);
                } else if (o.getY() < player.getY()) {
                    player.setY(o.getY() + o.getHeight());
                }
                player.setVelocity(player.getVelocityX(), 0);
            }
        }
    }
}
