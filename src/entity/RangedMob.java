package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static entity.Player.*;
import static javax.imageio.ImageIO.read;

public class RangedMob extends Mob {
    private BufferedImage mobImage; // Only declare what is new or different from Mob
    public static List<Bullet> bullets;
    public List<Bullet> bulletstoremove;
    //private Player player;
    //private BufferedImage bulletImage;


    public RangedMob(int x, int y, int speed, Player player) {
        super(x, y, speed, player); // Initialize the parent class fields
        //this.player=player;
        this.lastDamageTime = System.currentTimeMillis();
        this.maxhp = 100;
        this.hp = maxhp;
        this.direction = "down";
        this.spriteNum = 1;
        this.bullets=new ArrayList<Bullet>();
        this.bulletstoremove=new ArrayList<Bullet>();
        loadMobImage();
    }

    private void loadMobImage() {
        try {
            mobImage = read(new File("C:/Users/gbonc/OneDrive/Desktop/POO anul 2 semestrul 1/joc/Animatii/mob2.png")); // Replace with actual path to your mob image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(ArrayList<Mob> mobs) {
        int playerX = worldX;
        int playerY = worldY;

        // Mișcare spre jucător
        int prevX = x;
        int prevY = y;

        if (x < playerX && (Math.abs(x - playerX) > 30 || Math.abs(y - playerY) > 30)) {
            x += speed;
            direction="right";
        } else if (x > playerX && (Math.abs(x - playerX) > 30 || Math.abs(y - playerY) > 30)) {
            x -= speed;
            direction= "left";
        }

        if (y < playerY && (Math.abs(x - playerX) > 30 || Math.abs(y - playerY) > 30)) {
            y += speed;
            direction= "down";
        } else if (y > playerY && (Math.abs(x - playerX) > 30 || Math.abs(y - playerY) > 30)) {
            y -= speed;
            direction="up";
        }
        // spriteNum = (spriteNum == 1) ? 2 : 1;

        // Verifică coliziunile cu alți mobi
        for (Mob other : mobs) {
            if (other != this && isColliding(other)) {
                // Anulează mișcarea prin revenirea la poziția anterioară
                x = prevX;
                y = prevY;
                break; // Oprește dacă detectezi o coliziune
            }
        }
        // Verifică dacă trebuie să facă damage jucătorului
        //System.out.println(x-playerX);
        if (((x - playerX < 20 && x - playerX > -20)  || (y - playerY < 20 && y - playerY >- 20))
            && Math.sqrt(Math.pow((x-playerX),2)+Math.pow((y-playerY),2)) < 1000) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDamageTime >= 1000) {
                //player.hp -= 5;
                bullets.add(new Bullet(x, y, direction, bulletImage));
                if (player.hp < 0) {
                    player.hp = 0;
                }
                lastDamageTime = currentTime;
            }
        }
        bulletstoremove.clear();
        //System.out.println("Number of bullets: " + bullets.size());
        for(Bullet bullet : bullets)
        {
            bullet.update();
            if(Math.abs(bullet.x-playerX)<24 && Math.abs(bullet.y-playerY)<24) {
                player.hp -= 5;
                if (player.hp < 0) {
                    player.hp = 0;
                }
                bulletstoremove.add(bullet);
            }
            if(Math.abs(bullet.x-x)>10000 || Math.abs(bullet.y-y)>10000)
            {
                //System.out.println("Trece");
                bulletstoremove.add(bullet);
            }
        }
        bullets.removeAll(bulletstoremove);
    }

    @Override
    public void draw(Graphics2D g2) {
        if (mobImage != null) {
            g2.drawImage(mobImage, x, y, null);
        }
    }

    // Ensure you're not redeclaring methods or fields unless necessary.
    // The collision methods from Mob should handle interaction with bullets and rockets.
}
