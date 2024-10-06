package entity;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Nuclear {
    int x;
    public int y;
    public static int speed = 4;
    private String direction;
    private BufferedImage image;
    private boolean active;
    private int explosionRadius;

    public Nuclear(int x, int y, String direction, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.direction = "down";
        this.image = image;
        this.speed = 10; // Nuclear missiles are usually slower
        this.active = true;
        this.explosionRadius = 50; // Set explosion radius for the nuclear missile
    }

    public void update() {
        switch (direction) {
            case "down":
                y += speed;
                break;
            default:
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

    public int getExplosionRadius() {
        return explosionRadius;
    }

    public void setExplosionRadius(int explosionRadius) {
        this.explosionRadius = explosionRadius;
    }

    public void explode() {
        // Logic to handle the explosion effect
        // For example, you might deactivate the nuclear and apply damage in the radius
        this.active = false;
        // Apply damage to all entities within the explosion radius
    }
}
