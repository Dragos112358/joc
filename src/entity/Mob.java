package entity;

import mypackage.gamepanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class Mob {
    public int x, y;
    double hp, maxhp;
    public int speed;
    private BufferedImage mobImage;
    protected Player player;
    public long lastDamageTime;
    public String direction;
    public int spriteNum;
    public int damage;
    public boolean is_bombed = false;
    public int contor_bomb = 0;


    public Mob(int x, int y, int speed, Player player) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.player = player;
        this.lastDamageTime = System.currentTimeMillis(); // Inițializează cu timpul curent
        this.maxhp=200;
        this.hp=maxhp;
        this.direction = "down"; // Direcția implicită
        this.spriteNum = 1; // Sprite-ul implicit
        // loadMobImage();
    }

    public Mob() {
    }

    private void loadMobImage() {
        try {
            mobImage = read(new File("C:/Users/gbonc/OneDrive/Desktop/POO anul 2 semestrul 1/joc/Animatii/mob2.png")); // Replace with actual path to your mob image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void takeDamage(double damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            // Mob is dead, remove it from the game or mark as inactive
            player.mobs.remove(this);
        }
    }
    public static Point generateUniquePosition(HashSet<Point> occupiedPositions, int width, int height) {
        Random rand = new Random();
        Point position;

        do {
            int x = rand.nextInt(width - 48);
            int y = rand.nextInt(height - 48);
            position = new Point(x, y);
        } while (occupiedPositions.contains(position));

        occupiedPositions.add(position);
        return position;
    }

    public void update(ArrayList<Mob> mobs,ArrayList<Boss> bosses) {
        int playerX = player.worldX;
        int playerY = player.worldY;
        //System.out.printf("%d %d\n",playerX,playerY);
        // Mișcare spre jucător
        int prevX = x;
        int prevY = y;
        if(x<0)
            x= x+gamepanel.maxWorldCol*48;
        if(y<0)
            y = y+gamepanel.maxWorldRow*48;
        if(x>=gamepanel.maxWorldCol*48)
            x=x%(gamepanel.maxWorldCol*48);
        if(y>=gamepanel.maxWorldRow*48)
            y=y%(gamepanel.maxWorldRow*48);
        int mapWidth = gamepanel.maxWorldCol*48;
        int mapHeight = gamepanel.maxWorldRow*48;
// Calcularea distanței pe axa X
        int distX = Math.abs(x - playerX);
        int distXThroughMap = mapWidth - distX; // Distanța dacă treci prin marginea hărții

// Calcularea distanței pe axa Y
        int distY = Math.abs(y - playerY);
        int distYThroughMap = mapHeight - distY; // Distanța dacă treci prin marginea hărții
        //System.out.printf("%d %d\n",x,y);
        if(x<0)
            x= x+gamepanel.maxWorldCol*48;
        if(y<0)
            y = y+gamepanel.maxWorldRow*48;
// Determinarea direcției pe axa X
        if (distX > 30) {
            if (distX < distXThroughMap) {
                if (x < playerX) {
                    x += speed;
                    direction = "right";
                } else {
                    x -= speed;
                    direction = "left";
                }
            } else {
                // Mișcare prin marginea hărții
                if (x < playerX) {
                    x -= speed;
                    direction = "left";

                } else {
                    x += speed;
                    direction = "right";
                }
            }
        }

// Determinarea direcției pe axa Y
        if (distY > 30) {
            if (distY < distYThroughMap) {
                if (y < playerY) {
                    y += speed;
                    direction = "down";
                } else {
                    y -= speed;
                    direction = "up";
                }
            } else {
                // Mișcare prin marginea hărții
                if (y < playerY) {
                    y -= speed;
                    direction = "up";
                } else {
                    y += speed;
                    direction = "down";
                }
            }
        }

        // Verifică coliziunile cu alți mobi
        for (Mob other : mobs) {
            if (other != this && isColliding(other)) {
                // Anulează mișcarea prin revenirea la poziția anterioară
                x = prevX;
                y = prevY;
                break; // Oprește dacă detectezi o coliziune
            }
        }
        for(Boss boss : bosses)
        {
            if(Math.abs(x-boss.x)<48 && Math.abs(y-boss.y)<72)
            {
                x=prevX;
                y=prevY;
                break;
            }
        }

        // Verifică dacă trebuie să facă damage jucătorului
        if (Math.abs(x - playerX) < 48 && Math.abs(y - playerY) < 48) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDamageTime >= 1000) {
                player.hp -= this.damage;
                if (player.hp < 0) {
                    player.hp = 0;
                }
                lastDamageTime = currentTime;
            }
        }
    }


    public boolean isColliding(Mob other) {
        return Math.abs(x - other.x) < 48 && Math.abs(y - other.y) < 48;
    }
    public boolean isCollidingbullet(Bullet bullet)
    {
        int screenx = x - Player.worldX + Player.screenX;
        int screeny = y - Player.worldY + Player.screenY;
        //System.out.printf("bullet: x=%d y=%d\n",bullet.x, bullet.y );
        return Math.abs(x - bullet.x) < 48 && Math.abs(y - bullet.y) < 48;
    }

    public boolean isCollidingRocket(Rocket rocket)
    {
        int screenx = x - Player.worldX + Player.screenX;
        int screeny = y - Player.worldY + Player.screenY;
        //System.out.printf("bullet: x=%d y=%d\n",bullet.x, bullet.y );
        return Math.abs(x - rocket.x) < 48 && Math.abs(y - rocket.y) < 48;
    }
    public boolean isCollidingNuclear(Nuclear nuc)
    {
        int screenx = x - Player.worldX + Player.screenX;
        int screeny = y - Player.worldY + Player.screenY;
        //System.out.printf("bullet: x=%d y=%d\n",bullet.x, bullet.y );
        return Math.abs(screenx - nuc.x) < 100 && Math.abs(screeny - nuc.y) < 100;
    }

    private void revertMovement() {
        // Reverti mișcarea - implementează cum dorești (de exemplu, nu mișca mobul)
    }


    public void draw(Graphics2D g2) {
        if (mobImage != null) {
            g2.drawImage(mobImage, x, y, null);
        }
    }
}
