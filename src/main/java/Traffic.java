package main.java;

import javax.swing.*;
import java.awt.*;

public class Traffic {

    private JFrame frame = new JFrame("Traffic Simulation");
    private Road road = new Road();
    private boolean running = true;

    public Traffic() {
        frame.setSize(SimulationConstants.FRAME_WIDTH, SimulationConstants.FRAME_HEIGHT);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String... args) {
        Simulate simulate = new Traffic().new Simulate();
        simulate.run();
    }

     class Simulate implements Runnable {

        @Override
        public void run() {
            while(running) {
                step();
                frame.repaint();
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void step() {
            road.moveCars();
            road.addCar();
        }
    }

}
