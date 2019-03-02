package terrain;

import gameObjects.Block;
import gameWindow.Game;

public class Generator {
	public static final int BLOCK_SIZE = 50;
	private static int colNumber = 0;
	
	
	public void addCol(Game g, int height, int width) {
		int blockHeight = (int) Math.ceil(height/BLOCK_SIZE);
		int blockGap = (int) (Math.random() * 2 + 2);
		int mp = (int)(blockHeight * Math.sin(colNumber * Math.PI / 10));
		//The ceiling height in blocks
		for (int i = 0; i < blockHeight; i++) {
			if (i != Math.abs(mp - blockGap)) {
				g.addObject(new Block(colNumber * 10, i * BLOCK_SIZE));
			}
		}
		colNumber++;
	}
}
