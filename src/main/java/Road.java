package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Road extends JPanel {

    private List<Car> verticalCars = new ArrayList<>();
    private List<Car> horizontalCars = new ArrayList<>();

    public Road() {
        super();
    }

    public void addCar() {
        int i = (int)(Math.random() * 10 + 1);
        if(i == 1) {
            if(horizontalCars.size() < 8) addHorizontalCar();
        } else if(i == 2) {
            if(verticalCars.size() < 5) addVerticalCar();
        }
    }

    private void addVerticalCar() {
        verticalCars.add(new Car(SimulationConstants.V_START_X, SimulationConstants.V_START_Y));
    }

    private void addHorizontalCar() {
        horizontalCars.add(new Car(SimulationConstants.H_START_X, SimulationConstants.H_START_Y));
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawRoads(graphics);
        drawCars(graphics);
    }

    private void drawRoads(Graphics graphics) {
        graphics.setColor(Color.BLACK);

        graphics.drawLine(getWidth()/2-SimulationConstants.LANE_SIZE,0,getWidth()/2-SimulationConstants.LANE_SIZE, getHeight()/2-SimulationConstants.LANE_SIZE);
        graphics.drawLine(getWidth()/2+SimulationConstants.LANE_SIZE,0,getWidth()/2+SimulationConstants.LANE_SIZE, getHeight()/2-SimulationConstants.LANE_SIZE);

        graphics.drawLine(0,getHeight()/2-SimulationConstants.LANE_SIZE,getWidth()/2-SimulationConstants.LANE_SIZE, getHeight()/2-SimulationConstants.LANE_SIZE);
        graphics.drawLine(getWidth()/2+SimulationConstants.LANE_SIZE,getHeight()/2-SimulationConstants.LANE_SIZE, getWidth(), getHeight()/2-SimulationConstants.LANE_SIZE);

        graphics.drawLine(0,getHeight()/2+SimulationConstants.LANE_SIZE,getWidth()/2-SimulationConstants.LANE_SIZE, getHeight()/2+SimulationConstants.LANE_SIZE);
        graphics.drawLine(getWidth()/2+SimulationConstants.LANE_SIZE,getHeight()/2+SimulationConstants.LANE_SIZE, getWidth(), getHeight()/2+SimulationConstants.LANE_SIZE);

        graphics.drawLine(getWidth()/2-SimulationConstants.LANE_SIZE, getHeight()/2+SimulationConstants.LANE_SIZE,getWidth()/2-SimulationConstants.LANE_SIZE, getHeight());
        graphics.drawLine(getWidth()/2+SimulationConstants.LANE_SIZE, getHeight()/2+SimulationConstants.LANE_SIZE,getWidth()/2+SimulationConstants.LANE_SIZE, getHeight());
    }


    private void drawCars(Graphics graphics) {
        verticalCars.stream().forEach(car -> car.draw(graphics));
        horizontalCars.stream().forEach(car -> car.draw(graphics));
    }

    private void verticalMove() {
        verticalCars.stream().forEach(car -> {
            if(car.getY() > SimulationConstants.FRAME_HEIGHT) {
                car.setY(SimulationConstants.V_START_Y);
            }
            car.setY(car.getY()+car.getSpeed());
        });
    }

    private void horizontalMove() {
        horizontalCars.stream().forEach(car -> {
            if(car.getX() > SimulationConstants.FRAME_WIDTH) {
                car.setX(SimulationConstants.H_START_X);
            }
            car.setX(car.getX()+car.getSpeed());
        });
    }

    public void moveCars() {
        if(!isAnyCarInHorizontalIntersection() || !isAnyCarInVerticalIntersection()) {
            verticalMove();
        }
        horizontalMove();
    }

    private boolean isAnyCarInHorizontalIntersection() {
        for (Car car : horizontalCars) {
            if (car.getX() + SimulationConstants.CAR_WIDTH >= getWidth() / 2 - SimulationConstants.LANE_SIZE &&
                    car.getX() + SimulationConstants.CAR_WIDTH <= getWidth() / 2 + SimulationConstants.LANE_SIZE) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyCarInVerticalIntersection() {
        for (Car car : verticalCars) {
            if (car.getY() + (SimulationConstants.CAR_WIDTH * 3) >= getHeight() / 2 - SimulationConstants.LANE_SIZE &&
                    car.getY() + (SimulationConstants.CAR_WIDTH * 3) <= getHeight() / 2 + SimulationConstants.LANE_SIZE) {
                return true;
            }
        }
        return false;
    }

}
