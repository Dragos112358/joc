package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class ImageToMatrix {
    public static void main(String[] args) {
        try {
            // Input file containing color values
            File inputFile = new File("C:/Users/gbonc/OneDrive/Desktop/POO anul 2 semestrul 1/joc/Animatii/europe_map.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Read the entire file content into a single String
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim()); // Collect all lines into a single line
            }
            reader.close();

            // Remove commas and any non-hexadecimal characters, including the `0xff` prefix
            String data = sb.toString().replaceAll("0xff", ""); // Remove '0xff' prefix
            data = data.replaceAll("[^0-9a-fA-F]", ""); // Keep only hex characters

            // Determine the width and height of the matrix
            int width = 940; // Replace with actual width
            int height = 988; // Each color is 8 hex digits

            // Create a matrix to store color classifications
            int[][] classificationMatrix = new int[height][width];

            // Iterate through the data and fill the matrix
            int index = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Extract color
                    if (index + 8 <= data.length()) {
                        String hex = data.substring(index, index + 8);
                        index += 8;

                        // Debugging: Print the hex value
                        System.out.println("Processing hex value: " + hex);

                        try {
                            // Parse RGB from hex
                            long rgb = Long.parseLong(hex, 16); // Use Long.parseLong instead of Integer.parseInt
                            int red = (int)((rgb >> 16) & 0xFF);
                            int green = (int)((rgb >> 8) & 0xFF);
                            int blue = (int)(rgb & 0xFF);

                            // Debugging: Print RGB values
                            System.out.println("Red: " + red + ", Green: " + green + ", Blue: " + blue);

                            // Classification
                            if (red == 0 && green == 0 && blue == 0) {
                                classificationMatrix[y][x] = 2; // Black color
                            } else {
                                classificationMatrix[y][x] = 0; // Other colors
                            }
                        } catch (NumberFormatException e) {
                            // Enhanced error handling
                            System.out.println("Failed to parse hex value: " + hex);
                            System.out.println("Exception message: " + e.getMessage());
                            System.out.println("Ensure the hex value is exactly 8 characters long and contains only valid hexadecimal digits.");
                            classificationMatrix[y][x] = 0; // Mark invalid data
                        }
                    } else {
                        classificationMatrix[y][x] = 0; // Mark invalid data
                    }
                }
            }

            // Output file to save the matrix
            String outputFilePath = "C:/Users/gbonc/OneDrive/Desktop/POO anul 2 semestrul 1/joc/Animatii/map.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            // Write the classification matrix to the file
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    writer.write(classificationMatrix[y][x] + " ");
                }
                //writer.newLine(); // Go to the next line after each row
            }

            writer.close();
            System.out.println("Matrix has been written to the file: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
