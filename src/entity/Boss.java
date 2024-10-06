package entity;

import mypackage.gamepanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boss {
    int x;
    int y;
    public int maxHp;
    public int currentHp;
    private int speed;
    private String direction;
    private long lastDamageTime = 0;
    public int bossnotmovecontor = 0;
    public int bossdying = 0;
    protected Player player;
    public boolean ismoving;
    public boolean isattacking;
    public boolean is_bombed = false;
    long lastBombedTime=0;
    public BufferedImage imageboss;
    public int damage = 10;
    public long attackStartTime = 0; // Timpul când atacul a început
    public long lastAttackTime = 0; // Ultima dată când a fost aplicat damage-ul
    public static final long ATTACK_DURATION = 1200; // Durata atacului în milisecunde (1.2 secunde)

    // Constructorul clasei Boss
    public Boss(int x, int y, int maxHp, int speed, Player player) {
        this.x = x;
        this.y = y;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.speed = speed;
        this.direction = "down"; // sau orice direcție inițială
        this.player=player;
    }

    // Metodă pentru a actualiza mișcarea și comportamentul boss-ului
    public void update(ArrayList<Mob> mobs) {
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
                    ismoving=true;
                } else {
                    x -= speed;
                    direction = "left";
                    ismoving=true;
                }
            } else {
                // Mișcare prin marginea hărții
                if (x < playerX) {
                    x -= speed;
                    direction = "left";
                    ismoving=true;
                } else {
                    x += speed;
                    direction = "right";
                    ismoving=true;
                }
            }
        }

// Determinarea direcției pe axa Y
        if (distY > 30) {
            if (distY < distYThroughMap) {
                if (y < playerY) {
                    y += speed;
                    direction = "down";
                    ismoving=true;
                } else {
                    y -= speed;
                    direction = "up";
                    ismoving=true;
                }
            } else {
                // Mișcare prin marginea hărții
                if (y < playerY) {
                    y -= speed;
                    direction = "up";
                    ismoving=true;
                } else {
                    y += speed;
                    direction = "down";
                    ismoving=true;
                }
            }
        }

        // Verifică coliziunile cu alți mobi
        for (Mob other : mobs) {
            if (isColliding(other)) {
                // Anulează mișcarea prin revenirea la poziția anterioară
                x = prevX;
                y = prevY;
                ismoving=false;
                break; // Oprește dacă detectezi o coliziune
            }
        }

    }

    // Metodă pentru verificarea coliziunii cu alți mobi
    public boolean isColliding(Mob other) {
        return Math.abs(x - other.x) < 48 && Math.abs(y - other.y) < 72;
    }

    // Getteri și setteri (dacă e necesar)
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCurrentHp(int hp) {
        this.currentHp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
}
