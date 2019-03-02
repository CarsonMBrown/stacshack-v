package gameObjects;

import javafx.scene.image.Image;
import terrain.Generator;

public class Block extends GameObject {
    private static Image i = new Image("assets/terrain/block.png", Generator.BLOCK_SIZE, Generator.BLOCK_SIZE, false, false);

    public Block(int x, int y) {
        super(x, y, Generator.BLOCK_SIZE, Generator.BLOCK_SIZE, i);
    }
}
