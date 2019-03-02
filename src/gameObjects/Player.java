package gameObjects;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Player extends GameObject {
	public Player(int x, int y, int width, int height, String imgPath) {
		super(x, y, width, height, imgPath);
		// TODO Auto-generated constructor stub
	}

	public void handleInput(String keyCode) {
		
	}
	
	public void updatePosition(ArrayList<String> input) {
		if (input.contains("LEFT")) {
			//Increase left velocity vector
		}
		
		if (input.contains("RIGHT")) {
			//Increase right velocity vector
		}
	}	
}
