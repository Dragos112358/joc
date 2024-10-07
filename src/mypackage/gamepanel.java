package mypackage;

import entity.*;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static entity.Player.*;

public class gamepanel extends JPanel implements Runnable, MouseListener {
    public static final int originaltilesize=16;
    private boolean showInstructions = true;
    static final int scale=3;
    public boolean first_time =true;
    public static boolean isPaused = true;
    public static final int tilesize= originaltilesize*scale; //48*48
    boolean showDifficultySelection = true;  // Inițial, utilizatorul trebuie să selecteze dificultatea
    boolean difficultySelected = false;  // Dificultatea selectată
    public static String selectedDifficulty = "Easy";  // Variabila care va stoca dificultatea selectată
    public final int maxScreenCol=16;
    public final int maxScreenRows=12;
    public final int screenWidth=tilesize*maxScreenCol; //768
    public final int screenHeight=tilesize*maxScreenRows; //576
    private boolean showingHighScores = false;
    private int ignoreMouseClickCounter = 0;  // Numărăm cadrele
    private boolean ignoreMouseClick = false;
    //World coordinates
    public static final int maxWorldCol= 988;
    public static final int maxWorldRow=940;
    public static final int WorldWidth= tilesize*maxWorldCol;
    public static final int WorldHeight=tilesize*maxWorldRow;

    // setez coordonatele jucatorului
    public static int  FPS=40; //pot sa reglez in cati FPS merge jocul XD
    TileManager tileM = new TileManager(this);

    int playerX=100;
    int playerY=100;
    int Speed=4;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    public Player player = new Player(this,keyH,mouseH);
    JButton shopButton;
    private boolean showingDifficultyMenu = false;

    public gamepanel () throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        //setupShopButton();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                showInstructions = false;
                repaint();
                Player.flag_desenare_punct = false;
                Player.flag_desenare_punct2 = false;
            }
        });
        this.addMouseListener(this);
    }
    public void startGameThread() {
        gameThread=new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {
        double delta = 0;
        double timer = 0;
        int drawCount = 0;
        double drawInterval = 1000000000 / FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            if (!isPaused) { // Rulează jocul doar dacă nu este în pauză
                delta += (currentTime - lastTime) / drawInterval;
                timer += currentTime - lastTime;
                lastTime = currentTime;

                if (delta >= 1) {
                    update();   // Actualizează logica jocului
                    repaint();  // Re-desenează ecranul
                    delta--;
                    drawCount++;
                }

                // Afișează FPS-ul la fiecare secundă, doar dacă este necesar
                if (timer >= 1000000000) {
                    // System.out.println("FPS: " + drawCount);  // Poți folosi asta pentru a monitoriza FPS-ul
                    drawCount = 0;
                    timer = 0;
                }
            } else {
                // Când este pauză, doar actualizează `lastTime` astfel încât timpul să fie sincronizat corect
                lastTime = currentTime;
            }

            try {
                Thread.sleep(10); // Evită utilizarea 100% a procesorului în acest ciclu
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        if(isPaused == false) {
            player.update();
            showingHighScores = false;
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Render the game objects (e.g., player, tiles, bullets, etc.)
        tileM.draw(g2);
        if (mouseH.mouseX>=2*screenX - 40 && mouseH.mouseX<=2*screenX && mouseH.mouseY>=10 && mouseH.mouseY<=50 && mouseH.leftClick == true
                && Player.flag_desenare_punct2==false && Player.flag_desenare_punct == false && Player.flag_desen_upgradeuri == false) {
            isPaused = !isPaused; // Pune pauza sau reia jocul
            first_time = false;
            mouseH.leftClick = false;
           // System.out.println("Game " + (isPaused ? "paused" : "resumed"));
        }
        // Dacă jocul este în pauză și jucătorul a murit, afișează opțiunea "Play Again"
        if (player.hp <= 0 || isPaused == true) {
            // Ignorăm mouse-ul pentru câteva cadre
            if (ignoreMouseClick) {
                ignoreMouseClickCounter++;
                if (ignoreMouseClickCounter > 50) {  // După 10 cadre, reactivăm mouse-ul
                    ignoreMouseClick = false;
                    ignoreMouseClickCounter = 0;
                }
            }
            isPaused = true;
            drawPauseSymbol(g2);
            String playAgainMsg = "Play Again";
            if(player.hp <= 0 && first_time == false)
            {
                playAgainMsg = "Play Again";
            }
            if(first_time == false && player.hp > 0) {
                playAgainMsg = "Continue";
            }
            if(first_time == true)
            {
                playAgainMsg = "Play";
                //first_time = false;
            }
            String highScoresMsg = "High Scores";
            String exitMsg = "Exit";
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            // String gameOverMsg = "Game Over";

            // Centrăm mesajele pe ecran
            FontMetrics fm3 = g2.getFontMetrics();
            //int gameOverWidth = fm.stringWidth(gameOverMsg);
            int playAgainWidth = fm3.stringWidth(playAgainMsg);
            int highScoresWidth = fm3.stringWidth(highScoresMsg);
            int exitWidth = fm3.stringWidth(exitMsg);
            FontMetrics fm2 = g2.getFontMetrics();
            String difficultyMsg = "Select Difficulty";
            int difficultyMsgWidth = fm2.stringWidth(difficultyMsg);
            //g2.drawString(difficultyMsg, (screenWidth - difficultyMsgWidth) / 2, screenHeight / 2 - 100);
            if (showingHighScores == false && showingDifficultyMenu == false) {
                // Setează grosimea liniei pentru dreptunghiuri
                g2.setStroke(new BasicStroke(3));

                // Desenarea mesajului de dificultate
                g2.drawString(difficultyMsg, (screenWidth - difficultyMsgWidth) / 2, screenHeight / 2 - 100);
                // Obține dimensiunea textului pentru a desena dreptunghiul
                FontMetrics fm = g2.getFontMetrics();
                int difficultyMsgHeight = fm.getHeight();
                g2.drawRect((screenWidth - difficultyMsgWidth) / 2 - 5, screenHeight / 2 - 100 - difficultyMsgHeight + 5, difficultyMsgWidth + 10, difficultyMsgHeight + 5);

                // Desenarea mesajului playAgainMsg
                g2.drawString(playAgainMsg, (screenWidth - playAgainWidth) / 2, screenHeight / 2 - 50);
                int playAgainHeight = fm.getHeight();
                g2.drawRect((screenWidth - playAgainWidth) / 2 - 5, screenHeight / 2 - 50 - playAgainHeight + 5, playAgainWidth + 10, playAgainHeight + 5);

                // Desenarea mesajului highScoresMsg
                g2.drawString(highScoresMsg, (screenWidth - highScoresWidth) / 2, screenHeight / 2);
                int highScoresHeight = fm.getHeight();
                g2.drawRect((screenWidth - highScoresWidth) / 2 - 5, screenHeight / 2 - highScoresHeight + 5, highScoresWidth + 10, highScoresHeight + 5);

                // Desenarea mesajului exitMsg
                g2.drawString(exitMsg, (screenWidth - exitWidth) / 2, screenHeight / 2 + 50);
                int exitHeight = fm.getHeight();
                g2.drawRect((screenWidth - exitWidth) / 2 - 5, screenHeight / 2 + 50 - exitHeight + 5, exitWidth + 10, exitHeight + 5);
            }
            // Verificăm dacă jucătorul face click pentru a selecta o opțiune
            if ((mouseH.leftClick == true || showingHighScores == true || showingDifficultyMenu == true) && !ignoreMouseClick) {
                int mouseX = mouseH.mouseX;  // Coordonata X a mouse-ului
                int mouseY = mouseH.mouseY;  // Coordonata Y a mouse-ului
                if (showingHighScores == false && showingDifficultyMenu == false) {
                    // Coordonate pentru "Play Again"
                    int playAgainX = (screenWidth - playAgainWidth) / 2;
                    int playAgainY = screenHeight / 2 - 50;
                    if (mouseX >= playAgainX && mouseX <= playAgainX + playAgainWidth &&
                            mouseY >= playAgainY - 20 && mouseY <= playAgainY && showingHighScores == false) {
                        // System.out.println(totalmoney);
                        if(Player.hp<= 0 ) {
                            saveTotalMoney(totalmoney);
                            resetGame();  // Resetează jocul
                        }
                        else
                        {
                            isPaused = false;
                        }
                        mouseH.leftClick = false;  // Previne re-executarea acestui cod la următorul click
                    }
                }
                int difficultyX = (screenWidth - difficultyMsgWidth) / 2;
                int difficultyY = screenHeight / 2 - 100;

                // Dacă face click pe "Difficulty", afișăm meniul de dificultate
                if (mouseX >= difficultyX && mouseX <= difficultyX + difficultyMsgWidth &&
                        mouseY >= difficultyY - 20 && mouseY <= difficultyY && showingHighScores == false && showingDifficultyMenu == false) {
                    showingDifficultyMenu = true;  // Afișăm meniul de dificultate
                    mouseH.leftClick = false;
                }
                else if (showingDifficultyMenu == true) {
                    String[] difficulties = {"Easy", "Normal", "Hard", "Insane", "God","Back"};
                    String[] describes ={"Mobs and boss are low hp and they move slow","Mobs and boss have more hp than easy (Standard health)",
                        "Mobs and boss have 40% more HP and 40 % more damage","Mobs and boss are very powerful (double HP and damage)",
                            "Good luck, 200% more HP and damage", ""};
                    int yPosition = screenHeight / 2 - 100;
                    // Afișăm fiecare opțiune de dificultate
                    for (int i = 0; i < difficulties.length; i++) {
                        String difficultyOption = difficulties[i];
                        int difficultyOptionWidth = g2.getFontMetrics().stringWidth(difficultyOption);
                        int difficultyOptionWidth2 = g2.getFontMetrics().stringWidth(difficultyOption);
                        int describeX = (screenWidth-difficultyOptionWidth2 -350)/2;
                        int optionX = (screenWidth - difficultyOptionWidth-500) / 2;
                        int optionY = yPosition + (i * 50);  // Între opțiuni este un spațiu de 50px
                        int rectWidth = difficultyOptionWidth + 20; // Adding some padding
                        int rectHeight = 30; // Set the height for the rectangle

                        // Draw the rectangle around the difficulty option
                        g2.setStroke(new BasicStroke(3)); // Set the stroke thickness to 3 pixels
                        g2.drawRect(optionX - 10, optionY - rectHeight + 10, rectWidth, rectHeight); // Adjust position and size for padding
                        g2.setStroke(new BasicStroke(1));
                        g2.drawString(difficultyOption, optionX, optionY);
                        g2.drawString(describes[i],describeX,optionY);
                        if (difficultyOption.equals(selectedDifficulty)) {
                            // Set the position for the circle
                            int circleX = optionX - 40; // Adjust this value as needed for spacing
                            int circleY = optionY - 20; // Adjust this value as needed for centering
                            int circleDiameter = 20; // Diameter of the circle

                            // Draw the filled green circle
                            g2.setColor(Color.GREEN); // Set color for the circle
                            g2.fillOval(circleX, circleY, circleDiameter, circleDiameter); // Fill the circle

                            // Draw the checkmark in white
                            g2.setColor(Color.WHITE); // Set color for the checkmark
                            int checkmarkX = circleX + 5; // Centering the checkmark in the circle
                            int checkmarkY = circleY + 10; // Centering the checkmark in the circle

                            // Draw the checkmark (two lines)
                            g2.drawLine(checkmarkX, checkmarkY, checkmarkX + 5, checkmarkY + 5); // First line
                            g2.drawLine(checkmarkX + 5, checkmarkY + 5, checkmarkX + 10, checkmarkY - 5); // Second line
                        }
                        // Verificăm dacă jucătorul face click pe o opțiune
                        if (mouseX >= optionX && mouseX <= optionX + difficultyOptionWidth &&
                                mouseY >= optionY - 20 && mouseY <= optionY) {

                            if (difficultyOption.equals("Back")) {
                                showingDifficultyMenu = false;  // Revenim la meniul principal
                            } else {
                                selectedDifficulty = difficultyOption;  // Salvăm dificultatea selectată
                              //  System.out.println("Selected Difficulty: " + selectedDifficulty);
                                showingDifficultyMenu = false;  // Revenim la meniul principal
                            }

                            mouseH.leftClick = false;  // Resetăm click-ul pentru a preveni re-executarea codului
                        }
                    }
                }
                // Coordonate pentru "High Scores"
                else if (showingDifficultyMenu == false) {
                    int highScoresX = (screenWidth - highScoresWidth) / 2;
                    int highScoresY = screenHeight / 2;
                    if ((mouseX >= highScoresX && mouseX <= highScoresX + highScoresWidth &&
                            mouseY >= highScoresY - 20 && mouseY <= highScoresY && showingHighScores == false) || showingHighScores == true) {
                        List<String[]> scores = getHighScores();  // Obținem scorurile cu date
                        showingHighScores = true;  // Afișăm scorurile maxime
                        int yPosition = 100;  // Poziția inițială de pe ecran pentru afișarea scorurilor

                        // Afișarea scorurilor și a datelor
                        g2.setFont(new Font("Arial", Font.BOLD, 20));
                        for (int i = 0; i < Math.min(10, scores.size()); i++) {
                            String score = scores.get(i)[0];  // Scorul
                            String date = scores.get(i)[1];   // Data
                            String scoreLine = (i + 1) + ". " + score + " (" + date + ")";

                            // Setăm culoarea în funcție de poziție
                            if (i == 0) {
                                g2.setColor(Color.YELLOW);  // Locul 1 - Galben
                            } else if (i == 1) {
                                g2.setColor(new Color(192, 192, 192));  // Locul 2 - Argintiu (RGB pentru argintiu)
                            } else if (i == 2) {
                                g2.setColor(new Color(205, 127, 50));  // Locul 3 - Bronz (RGB pentru bronz)
                            } else {
                                g2.setColor(Color.WHITE);  // Restul pozițiilor - Alb
                            }

                            // Afișăm scorul și data pe ecran
                            g2.drawString(scoreLine, screenWidth / 2 - 50, yPosition);
                            yPosition += 30;  // Mărim coordonata Y pentru următorul scor
                        }

                        // Desenăm butonul "Back"
                        String backMsg = "Back";
                        g2.setFont(new Font("Arial", Font.BOLD, 20));
                        int backWidth = g2.getFontMetrics().stringWidth(backMsg);
                        int backX = (screenWidth - backWidth) / 2;
                        int backY = yPosition + 50;  // Poziționează butonul "Back" sub scoruri
                        g2.setColor(Color.WHITE);
                        g2.drawString(backMsg, backX, backY);
                        // Verificăm dacă utilizatorul face click pe butonul "Back"
                        if (mouseH.leftClick == true && !ignoreMouseClick) {
                            int mouseX1 = mouseH.mouseX;  // Coordonata X a mouse-ului
                            int mouseY1 = mouseH.mouseY;  // Coordonata Y a mouse-ului

                            // Verificăm dacă mouse-ul se află în zona butonului "Back"
                            if (mouseX1 >= backX && mouseX1 <= backX + backWidth &&
                                    mouseY1 >= backY - 20 && mouseY1 <= backY) {
                                showingHighScores = false;  // Revenim la meniul principal
                                mouseH.leftClick = false;  // Resetăm click-ul pentru a preveni execuția continuă
                            }
                        }
                    }

                    mouseH.leftClick = false;  // Previne re-executarea acestui cod la următorul click
                }


                // Coordonate pentru "Exit"
                if (showingDifficultyMenu == false && showingHighScores == false) {
                    int exitX = (screenWidth - exitWidth) / 2;
                    int exitY = screenHeight / 2 + 50;
                    if (mouseX >= exitX && mouseX <= exitX + exitWidth &&
                            mouseY >= exitY - 20 && mouseY <= exitY && showingHighScores == false) {
                        // System.out.println(totalmoney);
                        saveTotalMoney(totalmoney);  // Salvează totalmoney în fișier
                        System.exit(0);  // Închide aplicația
                    }
                }
            }
        } else {
            if (player != null) {
                player.draw(g2);
            }

            // Conditionally draw instructions
            if (showInstructions) {
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.PLAIN, 18));

                String[] instructions = {
                        "Instructions:",
                        "R - Reload",
                        "Space - Shoot",
                        "Z - Rocket",
                        "W, A, S, D or Arrows - Move",
                        "N - Nuclear Bomb",
                        "Left click: make banks that produce money",
                        "Right click: place defenses, cars and shops"
                };

                FontMetrics fm = g2.getFontMetrics();
                int panelWidth = this.getWidth();
                int panelHeight = this.getHeight();
                int lineHeight = fm.getHeight();
                int startY = (panelHeight - instructions.length * lineHeight) / 2;

                for (int i = 0; i < instructions.length; i++) {
                    int stringWidth = fm.stringWidth(instructions[i]);
                    int startX = (panelWidth - stringWidth) / 2;
                    g2.drawString(instructions[i], startX, startY + (i * lineHeight));
                }
            }
        }

        // Dispose of the graphics context
        g2.dispose();
    }
    public static void drawPauseSymbol(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        int pauseWidth = 10;  // Lățimea fiecărei bare a simbolului de pauză
        int pauseHeight = 30; // Înălțimea simbolului de pauză
        int pauseSpacing = 10; // Spațiul dintre cele două bare

        // Calculăm poziția în colțul dreapta sus
        int pauseX = 2*screenX - 2 * pauseWidth - pauseSpacing - 10; // 10 pixeli de la margine
        int pauseY = 10; // 10 pixeli de la partea de sus

        // Desenăm cele două bare verticale ale simbolului de pauză
        g2.fillRect(pauseX, pauseY, pauseWidth, pauseHeight);               // Prima bară
        g2.fillRect(pauseX + pauseWidth + pauseSpacing, pauseY, pauseWidth, pauseHeight); // A doua bară
    }
    public void resetGame() {
        player.hp =100;  // Resetează sănătatea jucătorului (sau orice valoare dorești)
        player.worldX = 100;  // Resetează poziția jucătorului
        player.worldY = 100;
        player.maxHp=100;
        player.speed=240/FPS;
        Player.carb = false;
        Player.chest_cost = 0;
        //System.out.println("Face constructor");
        //this.gp = gp;
        //addMouseListener(gp);
        this.keyH = keyH;
        this.mouseH=mouseH;
        Player.money = 0;
        Player.bullets = new ArrayList<Bullet>(); // Inițializare lista bullets
        Player.rockets = new ArrayList<Rocket>(); //initializare lista rockets
        mobs = new ArrayList<Mob>(); // Initialize mobs list
        Player.nuclears = new ArrayList<Nuclear>(); // initializare bombe nucleare
        Player.chests = new ArrayList<Chest>();
        cars = new ArrayList<Car>();
        Player.towers = new ArrayList<Tower>();
        Player.tower_bullets=new ArrayList<Bullet>();
        Player.bosses=new ArrayList<Boss>();
        Player.portals = new ArrayList<PortalAnimation>();
        Player.rocket_shops = new ArrayList<Rocket_Shop>();
        Player.nuclear_shops = new ArrayList<Nuclear_Shop>();
        Player.banks = new ArrayList<Bank>();
        Player.bani= new ArrayList<Money>();

        // Add a mob for demonstration (you can add more or use a loop to add multiple)
       // mobs.add(new Mob(200, 200, 120/FPS, player)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(300, 300, 120/FPS, player)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(350, 300, 120/FPS, player)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(400, 300, 120/FPS, player)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(500, 300, 120/FPS, player)); // Initial position (200, 200) and speed 2
       // mobs.add(new RangedMob(600, 400, 120/FPS, player));
       // mobs.add(new RangedMob(400, 600, 120/FPS, player));
        cars.add(new Car(200, 200, 1200,350*736,player));
        cars.add(new Car(700, 900, 1200,800*736,player));
        chests.add(new Chest(400,400));
        chests.add(new Chest(1000,1000));
        chests.add(new Chest(1000,1600));
        chests.add(new Chest(1200,1000));
        chests.add(new Chest(600,1000));
        chests.add(new Chest(300,2000));
        chests.add(new Chest(100,1000));
        Player.totalmoney = 0;
        // banks.add(new Bank(1600,1600,1));
        rocket_shops.add(new Rocket_Shop(1300,1300));
        //nuclear_shops.add(new Nuclear_Shop(1000,1000));
        int distance = 3000;
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                rocket_shops.add(new Rocket_Shop(i * distance + 100, j * distance + 100));
            }
        }
        //chests.add(new Chest(2000,1600));
        // chests.add(new Chest(3000,2000));
        chests.add(new Chest(1600,2000));
        int gridSize = 1000; // Distanța dintre punctele din grilă
        for (int i = 0; i < 42; i++) {
            for (int j = 0; j < 42; j++) {
                chests.add(new Chest(i * gridSize + 100, j * gridSize + 100));
            }
        }

        towers.add(new Tower(100,400,300,20,400, 300,true));
        towers.add(new Tower(500,400,300,20,400, 600,false));
        bosses.add(new Boss(1000,1100,1000,3,player));
        messageFrequency = new HashMap<>();
        Player.speed = 240/FPS;


        for(Boss boss2 : bosses)
        {
            switch(selectedDifficulty)
            {
                case "Easy": // Ușor
                    boss2.currentHp = (int)(0.8*(double)(boss2.currentHp)/(double) (boss2.maxHp)*(double)(Player.hpboss));
                    boss2.maxHp= (int)(0.8*(double)(hpboss));
                    boss2.damage=(int)(0.8*(double)(damageboss));
                    break;
                case "Normal": // Mediu
                    boss2.currentHp = (int)(1*(double)(hpboss));
                    boss2.maxHp= (int)(1*(double)(hpboss));
                    boss2.damage=(int)(1*(double)(damageboss));
                    break;
                case "Hard": // Greu
                    boss2.currentHp = (int)(1.4*(double)(hpboss));
                    boss2.maxHp= (int)(1.4*(double)(hpboss));
                    boss2.damage=(int)(1.4*(double)(damageboss));
                    break;
                case "Insane": // Greu
                    boss2.currentHp = (int)(2*(double)(hpboss));
                    boss2.maxHp= (int)(2*(double)(hpboss));
                    boss2.damage=(int)(2*(double)(damageboss));
                    break;
                case "God": // Greu
                    boss2.currentHp = (int)(3*(double)(hpboss));
                    boss2.maxHp= (int)(3*(double)(hpboss));
                    boss2.damage=(int)(3*(double)(damageboss));
                    break;
                default:
                    boss2.currentHp = (int)(2*(double)(hpboss));
                    boss2.maxHp= (int)(2*(double)(hpboss));
                    boss2.damage=(int)(2*(double)(damageboss));
                    break;
            }
        }
        portals.add(new PortalAnimation(1100,2000,2000,2000));
        portals.add(new PortalAnimation(800,800,3000,2500));

        int minDistance = 2000; // Distanța minimă între portaluri
        int portalCount = 30;   // Numărul total de portaluri dorit

        // Generarea de portaluri noi
        Random rand = new Random();
        while (portals.size() < portalCount) {
            int x1 = rand.nextInt(40000); // Interval de valori pentru coordonatele sursă (poți ajusta intervalul)
            int y1 = rand.nextInt(40000);
            int x2 = rand.nextInt(40000); // Interval de valori pentru coordonatele destinație
            int y2 = rand.nextInt(40000);

            // Verifică dacă noul portal este suficient de departe de cele existente
            boolean isValid = true;
            for (PortalAnimation portal : portals) {
                double distance1 = Math.sqrt(Math.pow(portal.xstart - x1, 2) + Math.pow(portal.ystart - y1, 2));
                double distance2 = Math.sqrt(Math.pow(portal.xstop - x2, 2) + Math.pow(portal.ystop - y2, 2));

                if (distance1 < minDistance || distance2 < minDistance) {
                    isValid = false;
                    break;
                }
            }

            // Dacă distanța e validă, adaugă noul portal
            if (isValid) {
                portals.add(new PortalAnimation(x1, y1, x2, y2));
            }
        }
        Player.generateTowersWithDistance(50,48*maxWorldCol,48*maxWorldRow, 600);
        Player.generateNuclearShopsWithDistance(200,48*maxWorldCol,48*maxWorldCol,150);
        //initializeButton();
        //initializeZoomSlider(); // Initializează slider-ul pentru zoom
        //gp.add(shopButton);
        //gp.add(zoomSlider);
        //shopButton.setVisible(true);
        //zoomSlider.setVisible(true);
        isPaused = false;  // Reia jocul
        // Resetează și alte componente ale jocului, dacă este necesar (scor, obiecte, etc.)
    }
    public List<String[]> getHighScores() {
        List<String[]> scores = new ArrayList<>();
        String filePath = "C:/Users/gbonc/OneDrive/Desktop/POO anul 2 semestrul 1/joc/Animatii/high_scores.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    // Fiecare linie este în formatul "score,data"
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        int score = Integer.parseInt(parts[0].trim());
                        String date = parts[1].trim();
                        scores.add(new String[] { String.valueOf(score), date });
                    }
                } catch (NumberFormatException e) {
                    // Dacă există o linie invalidă, o ignorăm
                  //  System.out.println("Linie invalidă: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Gestionează eventualele erori
        }

        // Sortăm scorurile în ordine descrescătoare după punctaj
        scores.sort((a, b) -> Integer.compare(Integer.parseInt(b[0]), Integer.parseInt(a[0])));
        return scores;
    }

    public void saveTotalMoney(int totalmoney) {
        String filePath = "../joc/Animatii/high_scores.txt";

        // Obținem data curentă în formatul dorit
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        double value =1.0;
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {  // true = append mode
            fileWriter.write(totalmoney + "," + currentDate + "\n");  // Scriem scorul și data
        } catch (IOException e) {
            e.printStackTrace();  // Gestionează eventualele erori
        }
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")");
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse pressed at: (" + e.getX() + ", " + e.getY() + ")");
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released at: (" + e.getX() + ", " + e.getY() + ")");
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // System.out.println("Mouse entered component at: (" + e.getX() + ", " + e.getY() + ")");
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5)); // Adaugă o margine galbenă când mouse-ul intră în componentă
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited component at: (" + e.getX() + ", " + e.getY() + ")");
        setBorder(BorderFactory.createEmptyBorder()); // Îndepărtează marginea când mouse-ul părăsește componenta
    }
}
