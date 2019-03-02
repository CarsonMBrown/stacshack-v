package Terrain;

import gameObjects.Block;
import gameWindow.Game;

public class Generator {
	public static final int BLOCK_SIZE = 50;
	private static int colNumber = 0;
	
	
	public static void addCol(Game g, int height, int width) {
		int blockHeight = (int) Math.ceil(height/BLOCK_SIZE);
		int blockGap = (int) (Math.random() * 2 + 2);
		int mp = (int)(blockHeight * Math.sin(colNumber * Math.PI / 400));
		//The ceiling height in blocks
		
		
		for (int i = 0; i < blockHeight; i++) {
			if (i < Math.abs(mp - blockGap)) {
				g.addBlock(new Block(width + 40, i * BLOCK_SIZE));
			}
		}
		colNumber++;
	} 
}
