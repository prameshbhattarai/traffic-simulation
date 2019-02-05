package main.java;

import java.awt.*;

public abstract class Position {

    private int x, y;
    private final int width = SimulationConstants.CAR_WIDTH;
    private final int speed = SimulationConstants.CAR_SPEED;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected abstract void draw(Graphics graphics);
}
