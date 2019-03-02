package gameObjects;

import terrain.Generator;

public class Block extends GameObject {
	public Block(int x, int y) {
		super(x, y, Generator.BLOCK_SIZE, Generator.BLOCK_SIZE, "assets/terrain/Block.xcf");
	}
}
