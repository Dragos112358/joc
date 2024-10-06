package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rocket {
    public int x, y;
    private int speed;
    public String direction;
    private BufferedImage image;
    private boolean active;

    public Rocket(int x, int y, String direction, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.image = image;
        this.speed = 12; // Set rocket speed (adjust as needed)
        this.active = true; // Initially rockets are active
    }

    public void update() {
        switch (direction) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
        }
    }

    public void draw(Graphics2D g2) {
        if (active) {
            g2.drawImage(image, x, y, null);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
}
