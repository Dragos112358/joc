package entity;

import java.util.Random;

public class MapGenerator {
    private static final int SIZE = 300; // Smaller size for labyrinth
    private static final int WALL = 1;
    private static final int PATH = 0;
    private static final int HEART = 11;
    private static final int NUM_HEARTS = 1000;
    private static final int FIELD_SIZE = 1;

    public static void main(String[] args) {
        System.out.println("Start mapa");
        int[][] map = generateMap(SIZE);
        printMap(map);
    }

    private static int[][] generateMap(int size) {
        int[][] map = new int[size][size];

        // Initialize the map with walls
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                map[x][y] = 2;
            }
        }

        // Generate labyrinth
        generateLabyrinth(map, size);

        // Create large fields
        createFields(map, size);

        // Place hearts
        placeHearts(map, size, NUM_HEARTS);

        return map;
    }

    private static void generateLabyrinth(int[][] map, int size) {
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);

        // Start from a random position
        map[x][y] = PATH;

        for (int i = 0; i < size * size / 4; i++) {
            switch (rand.nextInt(4)) {
                case 0 -> x = (x + 2) % size;
                case 1 -> x = (x - 2 + size) % size;
                case 2 -> y = (y + 2) % size;
                case 3 -> y = (y - 2 + size) % size;
            }
            map[x][y] = PATH;

            // Ensure the adjacent cell in the direction of movement is also a path
            switch (rand.nextInt(4)) {
                case 0 -> map[(x + 1) % size][y] = PATH;
                case 1 -> map[(x - 1 + size) % size][y] = PATH;
                case 2 -> map[x][(y + 1) % size] = PATH;
                case 3 -> map[x][(y - 1 + size) % size] = PATH;
            }
        }
    }

    private static void createFields(int[][] map, int size) {
        Random rand = new Random();
        int numFields = 4;

        for (int i = 0; i < numFields; i++) {
            int startX = rand.nextInt(size - FIELD_SIZE);
            int startY = rand.nextInt(size - FIELD_SIZE);

            for (int x = startX; x < startX + FIELD_SIZE && x < size; x++) {
                for (int y = startY; y < startY + FIELD_SIZE && y < size; y++) {
                    map[x][y] = PATH;
                }
            }
        }
    }

    private static void placeHearts(int[][] map, int size, int numHearts) {
        Random rand = new Random();
        int placedHearts = 0;

        while (placedHearts < numHearts) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);

            if (map[x][y] == PATH) {
                map[x][y] = HEART;
                placedHearts++;
            }
        }
    }

    private static void printMap(int[][] map) {
        for (int[] row : map) {
            for (int tile : row) {
                if (tile == WALL) {
                    System.out.print("5 "); // Wall
                } else if (tile == PATH) {
                    System.out.print("0 "); // Path
                } else if (tile == HEART) {
                    System.out.print("11 "); // Heart
                }
            }
            //System.out.println();
        }
    }
}
