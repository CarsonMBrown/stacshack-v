package gameWindow;

import java.util.ArrayList;

public class BackgroundRenderer {
    ArrayList<Double> backgroundLocationX = new ArrayList<Double>();

    public BackgroundRenderer() {
        for (int i = 0; i < 4; i++) {
            backgroundLocationX.add(0.0);
        }
    }

    public void moveBackGround(int vX) {
        for (int i = 0; i < 4; i++) {
            backgroundLocationX.set(i, backgroundLocationX.get(i) + vX * Math.pow(2, -i));
        }
    }
}