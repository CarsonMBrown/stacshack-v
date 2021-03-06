package gameWindow;

import java.util.ArrayList;

import gameObjects.Block;
import gameObjects.GameObject;
import gameObjects.Lava;
import gameObjects.Player;
import gameObjects.Spider;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import terrain.Generator;

public class Game {

    private ArrayList<GameObject> objects;
    private Player player;
    private ArrayList<Block> blocks;
    private static int updateCycle;
    private int score = 0;
    private static final double SCORE_MULTIPLIER = 1.0 / 2000;
    private static Lava lava;
    private ArrayList<Spider> enemies;

    public Game() {
        blocks = new ArrayList<>();
        blocks.add(new Block(100, 300));
        objects = new ArrayList<>();
        player = new Player();
//        lava = new Lava();
        enemies = new ArrayList<Spider>();
        

    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // Called in updateScreen
    public void updatePosition(ArrayList<String> input) {
        if (input.size() > 0) {
            String s = input.get(0);
            if (s.contains(",")) {
                double x = Double.parseDouble(s.substring(s.indexOf("(") + 1, s.indexOf(",")));
                double y = Double.parseDouble(s.substring(s.indexOf(",") + 1, s.indexOf(")")));
                input.clear();
                input.add("Click");
                for (Block b : blocks) {
                    if (b.getBoundary().contains(new Point2D(x, y))) {
                        player.handleInput(b);
                        return;
                    }
                }
            } else {
                player.handleInput();
            }
        } else {
            player.handleInput(null);
        }
    }

    public void updateScreen(double t) {
        for (GameObject o : objects) {
            o.update(t);
            // Once an object touches lava, it gets deleted.
            // If player deleted, game ends.

        }
        while (blocks.get(blocks.size() - 1).getX() < Main.SCREEN_WIDTH + 1.5 * Generator.BLOCK_SIZE) {
            Generator.addCol(this, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        }
        if (lava != null) {
            while (blocks.get(0).getX() < lava.getX() + lava.getWidth() * .7) {
                blocks.remove(blocks.get(0));
            }
        }
        for (Block b : blocks) {
            b.setVelocity(-player.getVelocityX(), 0);
            b.update(t);
           
            if ((int) (Math.random() * 1000000) == 8) {
            	System.out.println("Jorts");
            	enemies.add(new Spider(1600, (int)b.getY() + 200 + (int)(Math.random() * 20), b));
            }
        }
        if (lava != null) {
            lava.setVelocity(-player.getVelocityX() + 1, 0);
            lava.update(t);
        }
        player.update(t);
        if (player.getY() > 500){
            gameOver();
        }
        
        if (enemies.size() > 0) {
        	for (Spider s : enemies) {
        		s.update(t);
        	}
        }
        for (Spider s : enemies) {
        	s.setVelocity(-player.getVelocityX(), 0);
        }
        
        
    }
    
    private void gameOver() {
        objects.remove(player);
        
    }

    public void addObject(GameObject o) {
        objects.add(o);
    }

    public void addBlock(Block b) {
        blocks.add(b);
    }

    public void renderGame(GraphicsContext gc, double t) {
        player.render(gc);
        player.animate(t);
        for (GameObject o : objects) {
            o.render(gc);
        }

        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block b = blocks.get(i);
            if (b.getX() > -b.getWidth()) {
                b.render(gc);
            }
        }
        if (lava != null) {
            lava.render(gc);
        }
        gc.fillText("Score: " + Math.round((player.getDistanceTraveled() * SCORE_MULTIPLIER)), 850, 30);
        if (enemies.size() > 0) {
        	for (Spider s : enemies) {
        		
        		double startX = s.getY();
        		gc.strokeLine(s.getX() + s.getWidth() / 2, s.getY() + s.getHeight() / 2, s.getX() + s.getWidth() / 2, -10);
        		s.render(gc);
        	}
        }
        
        for (Spider s : enemies) {
        	if (s.intersects(player)) {
        		gameOver(gc);
        	}
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void calculateCollisions() {
        ArrayList<GameObject> collidedObjects = new ArrayList<GameObject>();
        for (GameObject o : blocks) {
            if (o.intersects(player)) {
                collidedObjects.add(o);
            }
        }
        

        if (collidedObjects.size() > 0) {
//            player.setVelocity(-player.getVelocityX(), -player.getVelocityY());
            for (GameObject o : collidedObjects) {
                int xDif = (int) Math.abs(o.getX() - player.getX());
                int yDif = (int) Math.abs(o.getY() - player.getY());

                player.setY(o.getY() + o.getHeight());
                player.setVelocity(player.getVelocityX(), 0);
//
//                for (GameObject ob : blocks) {
//                    if (o.getX() > player.getX()) {
//                        ob.setX(ob.getX() + 5);
//                    } else {
//                        ob.setX(ob.getX() - 5);
//                    }
//                }
//                player.setVelocity(player.getVelocityX(), -player.getVelocityY());
            }

//            player.setInAir(false);
//            for (GameObject o : collidedObjects) {
//                if (o.getX() > player.getX()) {
//                    player.setVelocity(-player.getVelocityX() * .9, player.getVelocityY());
//                } else {
//                    player.setVelocity(-player.getVelocityX() * .9, player.getVelocityY());
//                }
//            }
            // for (GameObject o : collidedObjects) {
//            int xDif = (int) Math.abs(o.getX() - player.getX());
//            int yDif = (int) Math.abs(o.getY() - player.getY());
//
//            if (xDif < yDif && player.getVelocityX() < player.getVelocityY()) {
//                // object is left of player
//                if (o.getX() < player.getX()) {
//                    while (player.intersects(o)) {
//                        for (Block b : blocks) {
//                            b.setVelocity(player.getVelocityX(), 0);
//                            b.update(.1);
//                            b.setVelocity(0, 0);
//                        }
//                    }
//                } // object is right of player
//                if (o.getX() > player.getX()) {
//                    while (player.intersects(o)) {
//                        for (Block b : blocks) {
//                            b.setVelocity(-player.getVelocityX(), 0);
//                            b.update(.1);
//                            b.setVelocity(0, 0);
//                        }
//                    }
//                }
//                player.setVelocity(0, player.getVelocityY());
//            } else {
//                if (o.getY() > player.getY()) {
//                    player.setY(o.getY() - player.getHeight());
//                    player.setInAir(false);
//                } else if (o.getY() < player.getY()) {
//                    player.setY(o.getY() + o.getHeight());
//                }
//                player.setInAir(false);
//                player.setVelocity(player.getVelocityX(), 0);
//            }
            System.out.println("the big dead");
        } else {
            player.setInAir(true);
        }
    }
}
