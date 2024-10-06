package entity;

import java.util.Random;

public class Rocket_Shop {
    public int x;
    public int y;
    public int cost;

    // Constructorul clasei care setează coordonatele și costul randomizat
    public Rocket_Shop(int x, int y) {
        this.x = x;
        this.y = y;
        this.cost = generateRandomCost(160, 240);
    }

    // Metodă privată pentru a genera un cost randomizat
    public static int generateRandomCost(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    // Getters pentru a accesa atributele
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "RocketShop{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                '}';
    }
}
