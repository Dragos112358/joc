package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Car {
    // Car properties
    public int x, y;                 // Position of the car
    private int currentSpeed;        // Current speed of the car
    private int acceleration;        // Acceleration rate
    private int currentGear;         // Current gear
    private int maxSpeedPerGear[];   // Max speed per gear
    public boolean isPlayerInCar;   // Flag to check if the player is in the car
    private BufferedImage carImage;  // Car image
    public int greutate;
    public double putere;
    public double putere_initiala;
    public double greutate_initiala;

    // Reference to the player
    private Player player;

    // Constructor to initialize car properties
    public Car(int x, int y, int greutate, double putere, Player player) {
        this.x = x;
        this.y = y;
        this.greutate = greutate;
        this.putere = putere;
        this.putere_initiala=putere;
        this.currentSpeed = 0;
        this.acceleration = 2; // Example acceleration value
        this.currentGear = 1;
        this.maxSpeedPerGear = new int[]{0, 20, 40, 60, 80, 100}; // Example max speeds for each gear
        this.isPlayerInCar = false;
        this.player = player;
        this.isPlayerInCar=false;
        //loadCarImage();
    }

    // Player enters the car
    public void enterCar() {
        if (!isPlayerInCar) {
            isPlayerInCar = true;
            //player.isInCar = true; // Update player's state
            //player.setCar(this); // Reference to the car the player is in
        }
    }

    // Player exits the car
    public void exitCar() {
        if (isPlayerInCar) {
            isPlayerInCar = false;
            //player.isInCar = false; // Update player's state
            //player.setCar(null); // Remove reference to the car
        }
    }

    // Method to handle car acceleration and gear shifting
    public void accelerate() {
        if (isPlayerInCar) {
            currentSpeed += acceleration; // Increase speed by acceleration rate

            // Shift gear if current speed exceeds max speed for the current gear
            if (currentGear < maxSpeedPerGear.length - 1 && currentSpeed > maxSpeedPerGear[currentGear]) {
                currentGear++;
            }

            // Cap the speed at the maximum speed of the highest gear
            if (currentSpeed > maxSpeedPerGear[maxSpeedPerGear.length - 1]) {
                currentSpeed = maxSpeedPerGear[maxSpeedPerGear.length - 1];
            }
        }
    }

    // Update car state (position, speed, etc.)
    public void update() {
        if (isPlayerInCar) {
            accelerate(); // Automatically accelerate if player is in the car

            // Update car position based on current speed
            x += currentSpeed;
            // Logic to update car's y position if needed
        }
    }

    // Draw the car on the screen
    public void draw(Graphics2D g2) {
        if (carImage != null) {
            g2.drawImage(carImage, x, y, null);
        }
    }
}
