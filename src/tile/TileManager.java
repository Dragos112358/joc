package tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static javax.imageio.ImageIO.read;
public class TileManager {
    mypackage.gamepanel gp;
    Tile [] tile;
   // int MapTileNum[][];
   public static int[][] MapTileNum ={{2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    public TileManager(mypackage.gamepanel gp)
    {
        //int MapTileNum[][];
        this.gp=gp;
        tile =new tile.Tile[100];
        getTileImage();
        MapTileNum = new int[gp.maxWorldCol+100][gp.maxWorldRow+100]; //aici fac mapa
        /*int i,j;
        for(i=0;i<gp.maxScreenCol;i++) {
            for (j = 0; j < gp.maxScreenRows; j++) {
                if (MapTileNum[i][j] == 0) {
                    Random random = new Random();
                     randnumber = random.nextInt(6);
                    MapTileNum[i][j] = randnumber;
                    System.out.println(randnumber);
                }
            }
        }*/
        loadMap();
        /*Random random = new Random();
        int randnumber1,randnumber2;
        for(i=0;i<gp.maxScreenCol;i++)
        {
            randnumber=random.nextInt(6);
            if(randnumber==5)
            {
                for(j=0;j<gp.maxScreenRows;j++)
                {
                    MapTileNum[i][j]=5;
                }
            }
        }
        randnumber1=random.nextInt(gp.maxScreenCol);
        randnumber2=random.nextInt(gp.maxScreenCol);
*/
    }
    public void getTileImage()
    {
        try
        {
            tile[0]=new Tile();
            tile[0].image = read(new File("../joc/Animatii/Grass.png"));
            tile[1]=new Tile();
            tile[1].image = read(new File("../joc/Animatii/zid.png"));
            tile[2]=new Tile();
            tile[2].image = read(new File("../joc/Animatii/water.png"));
            tile[3]=new Tile();
            tile[3].image = read(new File("../joc/Animatii/brad.png"));
            tile[4]=new Tile();
            tile[4].image = read(new File("../joc/Animatii/earth.png"));
            tile[5]=new Tile();
            tile[5].image = read(new File("../joc/Animatii/road.png"));
            tile[6]=new Tile();
            tile[6].image = read(new File("../joc/Animatii/house.png"));
            tile[7] = new Tile();
            Image originalImage = read(new File("../joc/Animatii/Drum_mare.png"));
            tile[7].image = originalImage.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
            tile[8] = new Tile();
            String path = "../joc/Animatii/casuta.png";
           // System.out.println("Loading image from: " + path);
            File file = new File(path);
            //System.out.println("File exists: " + file.exists());
            tile[8].image = read(file);

            // Verifică dimensiunile imaginii după redimensionare
            //System.out.println("Image 7 width: " + tile[7].image.getWidth(null) + ", height: " + tile[7].image.getHeight(null));
            tile[9]=new Tile();
            tile[9].image = read(new File("../joc/Animatii/empire_state_no_background.png"));
            tile[10]= new Tile();
            tile[10].image = read(new File("../joc/Animatii/key1-1.png"));
            tile[11]= new Tile();
            tile[11].image = read(new File("../joc/Animatii/heart1.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap()
    {
        try {
            // Specify the file path
            String filePath = "../joc/Animatii/map.txt";
            int col=0;
            int row=0;
            // Create a FileReader wrapped in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            // Read the entire file content as a single string
            String line = bufferedReader.readLine();  // Assuming the entire file is one single line

            // Split the string into individual numbers
            String[] numbers = line.split(" ");
            //System.out.println(numbers.length);
            //System.out.println(line);
            // Loop through each number and place it into the correct matrix position
            for (int i = 0; i < gp.maxWorldRow*gp.maxWorldCol; i++) {
                int num = Integer.parseInt(numbers[i]);
                //System.out.printf("%d ",num);
                // Place the number in the matrix
                MapTileNum[col][row] = num;
                col++;

                // If we reach the max column, move to the next row and reset the column counter
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }

                // Ensure you don't exceed the matrix bounds
                if (row == gp.maxWorldRow) {
                    break;
                }
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        // Calculează limitele vizibile pe baza poziției jucătorului
        int leftCol = gp.player.worldX / gp.tilesize - 10;
        int rightCol = (gp.player.worldX + 1700) / gp.tilesize + 1;
        int topRow = gp.player.worldY / gp.tilesize - 10;
        int bottomRow = (gp.player.worldY + 1700) / gp.tilesize + 1;

        // Parcurge toate coloanele și rândurile vizibile pe ecran
        for (int worldCol = leftCol; worldCol <= rightCol; worldCol++) {
            for (int worldRow = topRow; worldRow <= bottomRow; worldRow++) {
                // Folosește modulo pentru a repeta harta
                int modWorldCol = (worldCol + gp.maxWorldCol) % gp.maxWorldCol;
                int modWorldRow = (worldRow + gp.maxWorldRow) % gp.maxWorldRow;

                // Obține numărul tile-ului repetat
                int TileNum = MapTileNum[modWorldCol][modWorldRow];

                // Calculează pozițiile pe ecran
                int WorldX = modWorldCol * gp.tilesize;
                int WorldY = modWorldRow * gp.tilesize;
                int ScreenX = WorldX - gp.player.worldX + gp.player.screenX;
                int ScreenY = WorldY - gp.player.worldY + gp.player.screenY;

                // În acest moment trebuie să avem grijă de tile-urile care ies parțial din ecran și să le repetăm la margine
                if (ScreenX < -gp.tilesize) {
                    ScreenX += gp.maxWorldCol * gp.tilesize;  // Repetă harta pe axa X
                } else if (ScreenX > 1700) {
                    ScreenX -= gp.maxWorldCol * gp.tilesize;  // Repetă harta pe axa X
                }

                if (ScreenY < -gp.tilesize) {
                    ScreenY += gp.maxWorldRow * gp.tilesize;  // Repetă harta pe axa Y
                } else if (ScreenY > 1700) {
                    ScreenY -= gp.maxWorldRow * gp.tilesize;  // Repetă harta pe axa Y
                }

                // Desenează doar dacă tile-ul este vizibil pe ecran
                if (ScreenX + gp.tilesize > 0 && ScreenX < 1700 &&
                        ScreenY + gp.tilesize > 0 && ScreenY < 1700) {

                    g2.drawImage(tile[TileNum].image, ScreenX, ScreenY, gp.tilesize, gp.tilesize, null);

                    // Condiții speciale pentru tile-uri mai mari
                    if (TileNum > 7) {
                        g2.drawImage(tile[0].image, ScreenX, ScreenY, gp.tilesize, gp.tilesize, null);
                    }
                    if (TileNum == 7) {
                        g2.drawImage(tile[7].image, ScreenX, ScreenY, gp.tilesize * 2, gp.tilesize * 2, null);
                    }
                    if (TileNum == 8) {
                        g2.drawImage(tile[8].image, ScreenX, ScreenY, gp.tilesize * 6, gp.tilesize * 6, null);
                    }
                    if (TileNum == 9) {
                        g2.drawImage(tile[9].image, ScreenX, ScreenY, gp.tilesize * 5, gp.tilesize * 15, null);
                    }
                    if (TileNum == 10) {
                        g2.drawImage(tile[10].image, ScreenX, ScreenY, gp.tilesize, gp.tilesize, null);
                    }
                    if (TileNum == 11) {
                        g2.drawImage(tile[11].image, ScreenX, ScreenY, gp.tilesize, gp.tilesize, null);
                    }
                }
            }
        }
    }

}
