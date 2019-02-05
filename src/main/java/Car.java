package main.java;

import java.awt.*;

public class Car extends Position {

    public Car(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(getX(), getY(), getWidth(), getWidth());
    }
}
