package terrain;

import gameObjects.Block;
import gameWindow.Game;
import gameWindow.Main;

public class Generator {
	public static final int BLOCK_SIZE = 15;
	private static int colNumber = 0;

	public static void addCol(Game g, int height, int width) {
		/*
		 * double lastBlockX = g.getBlocks().get(g.getBlocks().size() - 1).getX(); int
		 * blockHeight = (int) Math.ceil(height/BLOCK_SIZE ); int blockGap = 0; int mp =
		 * (int)((blockHeight / 4) * Math.sin(colNumber * Math.PI/100)); //The ceiling
		 * height in blocks
		 */

		double lastBlockX = g.getBlocks().get(g.getBlocks().size() - 1).getX();
		int blocksInCol = (int) Math.ceil(height / BLOCK_SIZE);
		int midBlock = (int) ((blocksInCol / 16) * Math.sin(colNumber * Math.PI / 50) + (blocksInCol / 4));
		int gap = 10;
		int randomness = 2;

		for (int i = 0; i < blocksInCol; i++) {
			int lb = (int) (midBlock - gap - Math.random() * randomness);
			int ub = (int) (midBlock + gap + Math.random() * randomness);
			if (i < lb || i > ub) {
				g.addBlock(new Block((int) lastBlockX + BLOCK_SIZE, i * BLOCK_SIZE));
			}
		}
		g.addBlock(new Block((int) lastBlockX + BLOCK_SIZE, 0));
		g.addBlock(new Block((int) lastBlockX + BLOCK_SIZE, Main.SCREEN_HEIGHT - BLOCK_SIZE));
		colNumber++;
	}
}
