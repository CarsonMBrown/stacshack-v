package gameObjects;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Player {
	private int x, y, vX, vY;
	
	public void handleInput(String keyCode) {
		
	}
	
	private void updatePosition(ArrayList<String> input) {
		if (input.contains("LEFT")) {
			//Increase left velocity vector
		}
		
		if (input.contains("RIGHT")) {
			//Increase right velocity vector
		}
	}
	private int x;
	private int y;
	
	
	
	public int getX() {
		return x;
	}
	private int x;
	private int y;
	
	
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}
	
	
	
}
