package gameObjects;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    private double x, y, vX, vY, width, height;
    private Image image;
    private String imagePath;

    public GameObject(int x, int y, int width, int height, String imgPath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        image = new Image(imgPath, width, height, false, false);
    }

    public void update(double t) {
        x += vX * t;
        y += vY * t;
    }

    public void setVelocity(double vX, double vY) {
        this.vX = vX;
        this.vY = vY;
    }

    public void addVelocity(double vX, double vY) {
        setVelocity(vX + this.vX, vY + this.vY);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    public boolean intersects(GameObject o) {
        return o.getBoundary().intersects(this.getBoundary());
    }

    public void setImage(String imgPath) {
        this.image = new Image(imgPath);
        this.imagePath = imgPath;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getVelocityX() {
        return (int) vX;
    }

    public int getVelocityY() {
        return (int) vY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}
