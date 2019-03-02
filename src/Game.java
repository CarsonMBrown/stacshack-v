import java.util.ArrayList;

import gameObjects.GameObject;
import gameObjects.Player;

public class Game {
	
	private ArrayList<GameObject> objects;
	private Player player;
	
	public void updatePosition(ArrayList<String> input) {
		if (input.contains("LEFT")) {
			for (GameObject o : objects) {
				o.addVelocity(-1, 0);
			}
		}
		
		if (input.contains("RIGHT")) {
			for (GameObject o : objects) {
				o.addVelocity(1, 0);
			}
		}
	}
	
	public void updateScreen(double t) {
		for (GameObject o : objects) {
			o.update(t);
			//Once an object touches lava, it gets deleted.
			//If player deleted, game ends.
		}
	}
	
	public void addObject(GameObject o) {
		objects.add(o);
	}
}