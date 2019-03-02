package gameObjects;

public abstract class GameObject {
	private double x, y, vX, vY, width, height;
	
	
	protected abstract void updatePosition();
	
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public double getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	
	
}
