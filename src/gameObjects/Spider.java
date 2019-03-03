package gameObjects;

import javafx.scene.image.Image;

public class Spider extends GameObject {

	private Block tetheredTo;
	
	public Spider(int x, int y, Block b) {
		super(x, y, 75, 75, "assets/enemies/spider.png");
		tetheredTo = b;
	}
	
	public void swing() {
		if (tetheredTo != null) {
	        double dx = tetheredTo.getX() + tetheredTo.getWidth() / 2 - getX() - getWidth() / 2;
	        double dy = tetheredTo.getY() + tetheredTo.getHeight() / 2 - getY() - getHeight() / 2;

	        double angle = Math.atan2(dy, dx);
	        double dist = Math.sqrt((dx - getX()) * (dx - getX()) + (dy - getY()) * (dy - getY()));

	        addVelocity(dist * Math.sin(-angle), dist * Math.cos(-angle));
	        if (dist > 50) {
	            addVelocity(dist * dx / 10000.0, dist * dy / 10000.0);
	        }
	    }
	}
    

}
