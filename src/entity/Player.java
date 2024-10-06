package entity;

import mypackage.KeyHandler;
import mypackage.MouseHandler;
import mypackage.gamepanel;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static entity.Boss.ATTACK_DURATION;
import static java.awt.Color.*;
import static javax.imageio.ImageIO.read;
import static mypackage.gamepanel.*;

public class Player extends Entity {
    public JSlider zoomSlider;
    private double zoomLevel = 1.0; // Nivelul de zoom implicit
    BufferedImage[] portalImages = new BufferedImage[24];
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static boolean flag_chest = false;
    JButton shopButton;
    int i = 0;
    gamepanel gp;
    public static int totalmoney = 0;
    double putere = 0; //watt

    KeyHandler keyH;
    MouseHandler mouseH;
    int x = 100;
    int y = 100;
    public static Rectangle pedalAcceleratie;
    Car my_car;
    public static Rectangle pedalFrana;
    int spritemob = 0;
    //aici este partea de hp
    public static int hp = 100;
    boolean showMessage = false; // Variabilă pentru a controla afișarea mesajului
    String message = ""; // Mesajul care va fi afișat
    public static int money = 0;
    public int maxHp = 100;
    public int regen = (int)(maxHp/30); //2 la fiecare 2 secunde
    public int contor_regen = 0;
    public static int worldX = 200;
    public int cheie = 0;
    public static int worldY = 200;
    public JPanel sliderPanel;
    public static double initial_speed = (int)240/ FPS;
    public double initial_velocity = (int) (240/FPS);
    public static double speed = initial_speed;
    public int timp_afisare_buff=2* FPS;
    public int contor_afisare_buff = 0;
    double turatie = 0;
    double max = 8000;
    double acceleratie = 0;
    double k = 1.42;
    int greutate = 0;
    double viteza = 0;
    double u = 0.02;
    int treapta = 1;
    long elapsedTime = 0;
    public static final int screenX = 384;
    public static final int screenY = 288;
    public String direction = "down";
    public static List<Bullet> bullets;
    public static List<Bullet> tower_bullets;
    public static List<Rocket> rockets;
    public static List<Nuclear> nuclears;
    public static List<Bank> banks;
    public int cost_bank1 = 10000;
    public int cost_bank2 = 30000;
    public int cost_bank3 = 50000;
    public int cost_bank4 = 130000;
    double puteremed;
    public static boolean flag_desen_upgradeuri = false;
    public int animationboss=0;
    public int animationbossframe=0;
    public int bossnotmovecontor = 0;
    public static Map<String, Integer> messageFrequency = new HashMap<>();
    int coordX=0;
    int coordY=0;
    public static List<Car> cars;
    public static List<Mob> mobs;
    public static int chest_cost = 0;
    public static List<Tower> towers;

    public static List<Chest> chests;
    public static List<Boss> bosses;
    public static List<PortalAnimation> portals;
    public static List<Rocket_Shop> rocket_shops;
    public static List<Nuclear_Shop> nuclear_shops;
    public static List<Money> bani;
    public static BufferedImage bulletImage;
    double raport = 0;
    double value = 1;
    public static BufferedImage nuclearbomb,upgrade_tower_damage,upgrade_tower_attack_speed;

    private BufferedImage rsus, rjos, rstanga, rdreapta, fire_ring, mobimage, car,chest_closed,chest_opened,defense,europe_map;
    private BufferedImage defense_red;
    private BufferedImage demon_death1,demon_death2,demon_death3,demon_death4,demon_death5,demon_death6,demon_death7,demon_death8,demon_death9,demon_death10,demon_death11,demon_death12,demon_death13;
    private BufferedImage demon_death14, demon_death15, demon_death16, demon_death17, demon_death18, demon_death19, demon_death20, demon_death21, demon_death22;
    private BufferedImage portal1;
    private static int MAGAZINE_SIZE = 30;
    public int flag_portal=0;
    public static int ROCKET_SIZE = 10;
    public static int Nuclear_Size = 5;
    public static boolean flag_desenare_punct = false;
    public static boolean flag_desenare_punct2=false;
    int mobspritecounter = 0;
    public static int rocketsinMagazine = ROCKET_SIZE;
    final int final_speed = 20;
    public boolean flag_car = false;
    double x2 = 0;
    public static int NuclearinMagazine = Nuclear_Size;
    private int bulletsInMagazine = MAGAZINE_SIZE;
    private int copie_nr_bullets = bulletsInMagazine;
    private boolean reloading = false;
    private boolean reload_rocket = false;
    private Random rand = new Random(); // Pentru a genera valori aleatorii
    private boolean nuclear = false;
    private boolean flag_tower = false;
    public static boolean carb = false;
    private boolean flag_rocket= false;
    public int defense_cost = 20000;
    public int dir = 0;
    public int car_cost = 15000;
    public int rocket_shop_cost = 13000;
    public int nuclear_shop_cost = 20000;
    public  int contor_desen = 0;
    int mob_hp_normal = 100;
    int mob_damage_normal = 5;
    int mob_speed_normal = 120/FPS;
    private int reloadTime = 2 * FPS; // Frames required to reload
    private int mobreloadTime = FPS;
    private int mobreloadcounter = 0;
    private int reloadCounter = 0;
    private int nuclearreloadcounter = 0;
    private int nuclearreloadtime = 2 * FPS;
    public boolean flag_colect = false;
    // printf("a=%lf b=%lf c=%lf\n",a,b,c);
    double maximum_speed = 0;
    private static final int ROCKET_COOLDOWN_FRAMES = (int) (0.5 * FPS); // Numărul de frame-uri pentru cooldown între lansări de rachete (90 frame-uri ~ 1.5 secunde la 60 FPS)
    private int rocketCooldownCounter = 0; // Contor pentru numărul curent de frame-uri de la ultima lansare de rachetă
    private BufferedImage mobUp1, mobUp2, mobDown1, mobDown2, mobLeft1, mobLeft2, mobRight1, mobRight2;
    private BufferedImage demonwalk1,demonwalk2,demonwalk3,demonwalk4,demonwalk5,demonwalk6,demonwalk7,demonwalk8,demonwalk9,demonwalk10,demonwalk11,demonwalk12;
    private BufferedImage demon_attack1, demon_attack2, demon_attack3, demon_attack4, demon_attack5, demon_attack6, demon_attack7, demon_attack8, demon_attack9, demon_attack10, demon_attack11, demon_attack12, demon_attack13, demon_attack14, demon_attack15;
    private BufferedImage fdemonwalk1,fdemonwalk2,fdemonwalk3,fdemonwalk4,fdemonwalk5,fdemonwalk6,fdemonwalk7,fdemonwalk8,fdemonwalk9,fdemonwalk10,fdemonwalk11,fdemonwalk12;
    private BufferedImage fdemon_attack1, fdemon_attack2, fdemon_attack3, fdemon_attack4, fdemon_attack5, fdemon_attack6, fdemon_attack7, fdemon_attack8, fdemon_attack9, fdemon_attack10, fdemon_attack11, fdemon_attack12, fdemon_attack13, fdemon_attack14, fdemon_attack15;
    private BufferedImage idle1,idle2,idle3,idle4,idle5,idle6;
    private BufferedImage fidle1,fidle2,fidle3,fidle4,fidle5,fidle6;
    private BufferedImage bank1, bank2, bank3, bank4;
    private BufferedImage rocket_shop,money_image;
    public static int hpboss = 1000;
    public static int damageboss = 10;
    public int bossvalue = 5000;
    private String dificulty=" ";
    private BufferedImage nuclear_shop,upgrade_range_tower,upgrade_tower_hp;
    public Player(gamepanel gp, KeyHandler keyH, MouseHandler mouseH) throws IOException {
        //System.out.println("Face constructor");
        this.gp = gp;
        //addMouseListener(gp);
        this.keyH = keyH;
        this.mouseH=mouseH;
        getPlayerImage();
        getBulletImage();
        this.bullets = new ArrayList<Bullet>(); // Inițializare lista bullets
        this.rockets = new ArrayList<Rocket>(); //initializare lista rockets
        this.mobs = new ArrayList<Mob>(); // Initialize mobs list
        this.nuclears = new ArrayList<Nuclear>(); // initializare bombe nucleare
        this.chests = new ArrayList<Chest>();
        this.cars = new ArrayList<Car>();
        this.towers = new ArrayList<Tower>();
        this.tower_bullets=new ArrayList<Bullet>();
        this.bosses=new ArrayList<Boss>();
        this.portals = new ArrayList<PortalAnimation>();
        this.rocket_shops = new ArrayList<Rocket_Shop>();
        this.nuclear_shops = new ArrayList<Nuclear_Shop>();
        this.banks = new ArrayList<Bank>();
        this.bani= new ArrayList<Money>();
        mobUp1 = ImageUtil.colorize(up1, Color.RED, blue, 200);
        mobUp2 = ImageUtil.colorize(up2, Color.RED, blue, 200);
        mobDown1 = ImageUtil.colorize(down1, Color.RED, blue, 200);
        mobDown2 = ImageUtil.colorize(down2, Color.RED, blue, 200);
        mobLeft1 = ImageUtil.colorize(left1, Color.RED, blue, 200);
        mobLeft2 = ImageUtil.colorize(left2, Color.RED, blue, 200);
        mobRight1 = ImageUtil.colorize(right1, Color.RED, blue, 200);
        mobRight2 = ImageUtil.colorize(right2, Color.RED, blue, 200);
        defense_red=ImageUtil.colorize(defense,Color.RED, blue,140);

        // Add a mob for demonstration (you can add more or use a loop to add multiple)
       mobs.add(new Mob(200, 200, 120/FPS, this)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(300, 300, 120/FPS, this)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(350, 300, 120/FPS, this)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(400, 300, 120/FPS, this)); // Initial position (200, 200) and speed 2
       // mobs.add(new Mob(500, 300, 120/FPS, this)); // Initial position (200, 200) and speed 2
       // mobs.add(new RangedMob(600, 400, 120/FPS, this));
       // mobs.add(new RangedMob(400, 600, 120/FPS, this));
        cars.add(new Car(200, 200, 1200,350*736,this));
        cars.add(new Car(700, 900, 1200,800*736,this));
        chests.add(new Chest(400,400));
        chests.add(new Chest(1000,1000));
        chests.add(new Chest(1000,1600));
        chests.add(new Chest(1200,1000));
        chests.add(new Chest(600,1000));
        chests.add(new Chest(300,2000));
        chests.add(new Chest(100,1000));
       // banks.add(new Bank(1600,1600,1));
        rocket_shops.add(new Rocket_Shop(1300,1300));
        //nuclear_shops.add(new Nuclear_Shop(150,500));
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
        bosses.add(new Boss(1000,1100,1000,3,this));
        portals.add(new PortalAnimation(1100,2000,2000,2000));
        portals.add(new PortalAnimation(800,800,3000,2500));

        int minDistance = 2000; // Distanța minimă între portaluri
        int portalCount = 30;//30   // Numărul total de portaluri dorit

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
        generateTowersWithDistance(50,48*gp.maxWorldCol,48*gp.maxWorldRow, 600);
        generateNuclearShopsWithDistance(200,48*gp.maxWorldCol,48*gp.maxWorldCol,150);
        //initializeButton();
        //initializeZoomSlider(); // Initializează slider-ul pentru zoom
        //gp.add(shopButton);
        //gp.add(zoomSlider);
        //shopButton.setVisible(true);
        //zoomSlider.setVisible(true);
    }
    public BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Crearea unei imagini noi unde vom desena versiunea oglindită
        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Desenarea imaginii oglindite orizontal
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();

        return flippedImage;
    }
    public void getPlayerImage() {
        try {
            up1 = read(new File("../joc/Animatii/test3.png"));
            up2 = read(new File("../joc/Animatii/test4.png"));
            down1 = read(new File("../joc/Animatii/test1.png"));
            down2 = read(new File("../joc/Animatii/test2.png"));
            left1 = read(new File("../joc/Animatii/test5.png"));
            left2 = read(new File("../joc/Animatii/test6.png"));
            right1 = read(new File("../joc/Animatii/test8.png"));
            right2 = read(new File("../joc/Animatii/test7.png"));
            mobimage = read(new File("../joc/Animatii/mob2.png"));
            demonwalk1 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_1.png"));
            demonwalk2 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_2.png"));
            demonwalk3 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_3.png"));
            demonwalk4 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_4.png"));
            demonwalk5 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_5.png"));
            demonwalk6 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_6.png"));
            demonwalk7 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_7.png"));
            demonwalk8 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_8.png"));
            demonwalk9 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_9.png"));
            demonwalk10 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_10.png"));
            demonwalk11 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_11.png"));
            demonwalk12 = read(new File("../joc/Animatii/boss/individual sprites/02_demon_walk/demon_walk_12.png"));
            demon_attack1 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_1.png"));
            demon_attack2 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_2.png"));
            demon_attack3 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_3.png"));
            demon_attack4 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_4.png"));
            demon_attack5 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_5.png"));
            demon_attack6 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_6.png"));
            demon_attack7 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_7.png"));
            demon_attack8 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_8.png"));
            demon_attack9 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_9.png"));
            demon_attack10 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_10.png"));
            demon_attack11 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_11.png"));
            demon_attack12 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_12.png"));
            demon_attack13 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_13.png"));
            demon_attack14 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_14.png"));
            demon_attack15 = read(new File("../joc/Animatii/boss/individual sprites/03_demon_cleave/demon_cleave_15.png"));
            idle1 = read(new File("../joc/Animatii/boss/individual sprites/01_demon_idle/demon_idle_1.png"));
            idle2 = read(new File("../joc/Animatii/boss/individual sprites/01_demon_idle/demon_idle_2.png"));
            idle3 = read(new File("../joc/Animatii/boss/individual sprites/01_demon_idle/demon_idle_3.png"));
            idle4 = read(new File("../joc/Animatii/boss/individual sprites/01_demon_idle/demon_idle_4.png"));
            idle5 = read(new File("../joc/Animatii/boss/individual sprites/01_demon_idle/demon_idle_5.png"));
            idle6 = read(new File("../joc/Animatii/boss/individual sprites/01_demon_idle/demon_idle_6.png"));
            demon_death1 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_1.png"));
            demon_death2 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_2.png"));
            demon_death3 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_3.png"));
            demon_death4 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_4.png"));
            demon_death5 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_5.png"));
            demon_death6 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_6.png"));
            demon_death7 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_7.png"));
            demon_death8 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_8.png"));
            demon_death9 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_9.png"));
            demon_death10 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_10.png"));
            demon_death11 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_11.png"));
            demon_death12 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_12.png"));
            demon_death13 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_13.png"));
            demon_death14 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_14.png"));
            demon_death15 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_15.png"));
            demon_death16 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_16.png"));
            demon_death17 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_17.png"));
            demon_death18 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_18.png"));
            demon_death19 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_19.png"));
            demon_death20 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_20.png"));
            demon_death21 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_21.png"));
            demon_death22 = read(new File("../joc/Animatii/boss/individual sprites/05_demon_death/demon_death_22.png"));
            portal1 = read(new File("../joc/Animatii/portal.png"));
            rocket_shop = read(new File("../joc/Animatii/rocket_shop1_bun.png"));
            nuclear_shop = read(new File("../joc/Animatii/nuclear_shop.png"));
            upgrade_tower_damage = read(new File("../joc/Animatii/upgrade_damage.png"));
            upgrade_tower_attack_speed = read(new File("../joc/Animatii/upgrade_attack_speed_bun.png"));
            upgrade_range_tower= read(new File("../joc/Animatii/upgrade_range.png"));
            upgrade_tower_hp = read(new File("../joc/Animatii/tower_hp_increase.png"));
            bank1 = read(new File("../joc/Animatii/bank1.png"));
            bank2 = read(new File("../joc/Animatii/bank2.png"));
            bank3 = read(new File("../joc/Animatii/bank3.png"));
            bank4 = read(new File("../joc/Animatii/bank4.png"));
            money_image = read(new File("../joc/Animatii/money.png"));
            fidle1 = flipImageHorizontally(idle1);
            fidle2 = flipImageHorizontally(idle2);
            fidle3 = flipImageHorizontally(idle3);
            fidle4 = flipImageHorizontally(idle4);
            fidle5 = flipImageHorizontally(idle5);
            fidle6 = flipImageHorizontally(idle6);
            fdemonwalk1=flipImageHorizontally(demonwalk1);
            fdemonwalk2=flipImageHorizontally(demonwalk2);
            fdemonwalk3=flipImageHorizontally(demonwalk3);
            fdemonwalk4=flipImageHorizontally(demonwalk4);
            fdemonwalk5=flipImageHorizontally(demonwalk5);
            fdemonwalk6=flipImageHorizontally(demonwalk6);
            fdemonwalk7=flipImageHorizontally(demonwalk7);
            fdemonwalk8=flipImageHorizontally(demonwalk8);
            fdemonwalk9=flipImageHorizontally(demonwalk9);
            fdemonwalk10=flipImageHorizontally(demonwalk10);
            fdemonwalk11=flipImageHorizontally(demonwalk11);
            fdemonwalk12=flipImageHorizontally(demonwalk12);
            fdemon_attack1 = flipImageHorizontally(demon_attack1);
            fdemon_attack2 = flipImageHorizontally(demon_attack2);
            fdemon_attack3 = flipImageHorizontally(demon_attack3);
            fdemon_attack4 = flipImageHorizontally(demon_attack4);
            fdemon_attack5 = flipImageHorizontally(demon_attack5);
            fdemon_attack6 = flipImageHorizontally(demon_attack6);
            fdemon_attack7 = flipImageHorizontally(demon_attack7);
            fdemon_attack8  = flipImageHorizontally(demon_attack8);
            fdemon_attack9 = flipImageHorizontally(demon_attack9);
            fdemon_attack10 = flipImageHorizontally(demon_attack10);
            fdemon_attack11 = flipImageHorizontally(demon_attack11);
            fdemon_attack12 = flipImageHorizontally(demon_attack12);
            fdemon_attack13 = flipImageHorizontally(demon_attack13);
            fdemon_attack14 = flipImageHorizontally(demon_attack14);
            fdemon_attack15 = flipImageHorizontally(demon_attack15);
            int width = 2*48;
            int height= 2*48;
            for (int i = 0; i < 24; i++) {
                // Calculăm unghiul de rotație în radiani (0, 15, 30, ... 345)
                double angle = Math.toRadians(i * 15);

                // Creează un BufferedImage pentru a desena imaginea rotită
                BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = rotatedImage.createGraphics();

                // Setează punctul de rotație la centrul imaginii
                double centerX = width / 2.0;
                double centerY = height / 2.0;

                // Creează transformarea de rotație
                AffineTransform transform = new AffineTransform();
                transform.rotate(angle, centerX, centerY); // Rotește imaginea cu `angle` radiani în jurul centrului său

                // Aplică transformarea la Graphics2D
                g2.setTransform(transform);

                // Desenează imaginea rotită
                g2.drawImage(portal1, 0, 0, width, height, null); // Aici specifici imaginea care trebuie rotită
                // Eliberează resursele Graphics2D
                g2.dispose();

                // Stochează imaginea rotită în vector
                portalImages[i] = rotatedImage;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBulletImage() {
        try {
            bulletImage = read(new File("../joc/Animatii/bullet.png")); // Replace with the actual path to your bullet image
            rsus = read(new File("../joc/Animatii/racheta_sus.png"));
            rjos = read(new File("../joc/Animatii/racheta_jos.png"));
            rstanga = read(new File("../joc/Animatii/racheta_stanga.png"));
            rdreapta = read(new File("../joc/Animatii/racheta_dreapta.png"));
            nuclearbomb = read(new File("../joc/Animatii/nuclear_bomb.png"));
            fire_ring = read(new File("../joc/Animatii/fire_ring2.png"));
            car = read(new File("../joc/Animatii/lamborghini.png"));
            chest_closed = read(new File("../joc/Animatii/chest_closed_bun.png"));
            chest_opened = read(new File("../joc/Animatii/chest_opened.png"));
            defense = read(new File("../joc/Animatii/defense.png"));
            europe_map=read(new File("../joc/Animatii/europe_map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update bullets
    private void initializeButton() {
        shopButton = new JButton("Shop");
        shopButton.setBounds(gp.screenWidth - 100, gp.screenHeight - 50, 80, 30); // Setează dimensiunile și poziția
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopWindow shopWindow = new ShopWindow();
                shopWindow.setVisible(true);
            }
        });

        // Adăugați butonul la JPanel-ul tău (presupunând că ai un JPanel numit gamePanel)
        gp.setLayout(null); // Asigură-te că layout-ul este setat corect
    }
    public static void generateNuclearShopsWithDistance(int numShops, int mapWidth, int mapHeight, int minDistance) {
        Random random = new Random();
        ArrayList<Nuclear_Shop> generatedNuclearShops = new ArrayList<>(); // Lista temporară pentru a verifica distanțele

        for (int i = 0; i < numShops; i++) {
            int x, y;
            boolean validPosition;

            // Repetăm generarea de coordonate până când găsim o poziție care respectă distanța minimă
            do {
                validPosition = true;
                x = random.nextInt(mapWidth);   // Coordonata X random între 0 și lățimea hărții
                y = random.nextInt(mapHeight);  // Coordonata Y random între 0 și înălțimea hărții

                // Verifică distanța față de toate turnurile
                for (Tower tower : towers) {
                    int dx = tower.getX() - x;
                    int dy = tower.getY() - y;
                    double distance = Math.sqrt(dx * dx + dy * dy); // Calculăm distanța Euclidiană

                    if (distance < minDistance) {
                        validPosition = false;  // Dacă distanța este prea mică, regenerăm coordonatele
                        break;
                    }
                }

                // Verifică distanța față de toate cuferele
                for (Chest chest : chests) {
                    int dx = chest.x - x;
                    int dy = chest.y - y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance < minDistance) {
                        validPosition = false;
                        break;
                    }
                }

                // Verifică distanța față de toate magazinele de rachete
                for (Rocket_Shop rocketShop : rocket_shops) {
                    int dx = rocketShop.x - x;
                    int dy = rocketShop.y - y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance < minDistance) {
                        validPosition = false;
                        break;
                    }
                }

                // Verifică distanța față de toate magazinele de bombe nucleare deja generate
                for (Nuclear_Shop nuclearShop : generatedNuclearShops) {
                    int dx = nuclearShop.x - x;
                    int dy = nuclearShop.y - y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance < minDistance) {
                        validPosition = false;
                        break;
                    }
                }

            } while (!validPosition);

            // Setează valorile pentru magazinul de bombe nucleare
           // int stock = random.nextInt(51) + 50;  // Stoc random între 50 și 100 de bombe
            boolean isOpen = true; // Magazinul este deschis
            //int price = random.nextInt(501) + 500; // Prețul unei bombe între 500 și 1000

            // Creează magazinul de bombe nucleare și adaugă-l în liste
            Nuclear_Shop newNuclearShop = new Nuclear_Shop(x, y);
            nuclear_shops.add(newNuclearShop);
            generatedNuclearShops.add(newNuclearShop);  // Păstrăm magazinul pentru a verifica distanțele
        }
    }

    public static void generateTowersWithDistance(int numTowers, int mapWidth, int mapHeight, int minDistance) {
        Random random = new Random();
        ArrayList<Tower> generatedTowers = new ArrayList<>(); // Lista temporară pentru a verifica distanțele

        for (int i = 0; i < numTowers; i++) {
            int x, y;
            boolean validPosition;

            // Repetăm generarea de coordonate până când găsim o poziție care respectă distanța minimă
            do {
                validPosition = true;
                x = random.nextInt(mapWidth);   // Coordonata X random între 0 și lățimea hărții
                y = random.nextInt(mapHeight);  // Coordonata Y random între 0 și înălțimea hărții
                if(Math.abs(x-worldX)<500 || Math.abs(y-worldY)<500)
                {
                    validPosition = false;
                }
                if(validPosition == true) {
                    // Verifică distanța față de toate turnurile deja generate
                    for (Tower tower : generatedTowers) {
                        int dx = tower.getX() - x;
                        int dy = tower.getY() - y;
                        double distance = Math.sqrt(dx * dx + dy * dy); // Calculăm distanța Euclidiană

                        if (distance < minDistance) {
                            validPosition = false;  // Dacă distanța este prea mică, regenerăm coordonatele
                            break;
                        }
                    }
                    for(Chest chest : chests)
                    {
                        int dx=chest.x-x;
                        int dy = chest.y-y;
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        if(distance<200)
                        {
                            validPosition = false;
                            break;
                        }
                    }
                    for(Rocket_Shop rocketShop : rocket_shops)
                    {
                        int dx=rocketShop.x-x;
                        int dy = rocketShop.y-y;
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        if(distance<200)
                        {
                            validPosition = false;
                            break;
                        }
                    }
                    for(Nuclear_Shop nuclearShop : nuclear_shops)
                    {
                        int dx=nuclearShop.x-x;
                        int dy = nuclearShop.y-y;
                        double distance = Math.sqrt(dx * dx + dy * dy);
                        if(distance<200)
                        {
                            validPosition = false;
                            break;
                        }
                    }
                }
            } while (!validPosition);

            // Setează valorile pentru turn
            int range = 300;         // Raza de acțiune a turnului
            int damage = 20;         // Daunele turnului
            int fireRate = 400;      // Rata de foc (câte milisecunde între focuri)
            int health = random.nextInt(301) + 100;  // HP random între 100 și 400
            boolean isActive = false; // Status activ/inactiv random

            // Creează turnul și adaugă-l în liste
            Tower newTower = new Tower(x, y, range, damage, fireRate, health, isActive);
            towers.add(newTower);
            generatedTowers.add(newTower);  // Păstrăm turnul pentru a verifica distanțele
        }
    }
    public void update() {
        if(dificulty!=selectedDifficulty || isPaused == true) {
          //  System.out.println("Dificulty changed from " + dificulty +" to " + selectedDifficulty);
            dificulty = selectedDifficulty;
            for(Mob mob : mobs)
            {
                mob.contor_bomb++;
                switch(selectedDifficulty) {
                    case "Easy": // Ușor
                        mob.hp = (int)(0.8*(double)(mob.hp)/(double) (mob.maxhp)*(double)100);
                        mob.maxhp=80;
                        mob.damage=4;
                        value = 1;
                        break;
                    case "Normal": // Mediu
                        mob.hp = (int)(1*(double)(mob.hp)/(double) (mob.maxhp)*(double)100);
                        mob.maxhp=100;
                        mob.damage=5;
                        value = 1.5;
                        break;
                    case "Hard": // Greu
                        mob.hp = (int)(1.4*(double)(mob.hp)/(double) (mob.maxhp)*(double)100);
                        mob.maxhp=140;
                        mob.damage=7;
                        value =2.5;
                        break;
                    case "Insane": // Greu
                        mob.hp = (int)(2*(double)(mob.hp)/(double) (mob.maxhp)*(double)100);
                        mob.maxhp=200;
                        mob.damage=10;
                        value = 4;
                        break;
                    case "God": // Greu
                        mob.hp = (int)(3*(double)(mob.hp)/(double) (mob.maxhp)*(double)100);
                        mob.maxhp=300;
                        mob.damage=15;
                        value = 6;
                        break;
                    default:
                        mob.hp = 100; // Valoare default
                        mob.damage= 5;
                        mob.maxhp = 100;
                        break;
                }
            }
            for(Boss boss2 : bosses)
            {
                switch(selectedDifficulty)
                {
                    case "Easy": // Ușor
                        boss2.currentHp = (int)(0.8*(double)(boss2.currentHp)/(double) (boss2.maxHp)*(double)hpboss);
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
        }
        if (isPaused == false) {
            synchronized (mobs) {
                try {
                    for (Mob mob : mobs) {
                        mob.update((ArrayList<Mob>) mobs, (ArrayList<Boss>) bosses);
                    }
                } catch (ConcurrentModificationException e) {
                    // System.err.println("Concurrent modification detected: " + e.getMessage());
                }
            }
            synchronized (bosses) {
                try {
                    if(isPaused == false) {
                        for (Boss boss : bosses) {
                            boss.update((ArrayList<Mob>) mobs);
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    // System.err.println("Concurrent modification detected: " + e.getMessage());
                }
            }
            if (reload_rocket == true) {
                rocketCooldownCounter++;
                if (rocketCooldownCounter >= ROCKET_COOLDOWN_FRAMES) {
                    reload_rocket = false;
                    rocketCooldownCounter = 0;
                }
            }
            if (reloading == true) {
                reloadCounter++;
                reloading = true;
                if (reloadCounter >= reloadTime) {
                    bulletsInMagazine = MAGAZINE_SIZE;
                    copie_nr_bullets = MAGAZINE_SIZE;
                    reloading = false;
                    reloadCounter = 0;
                }
            }

            // Shooting logic
            //System.out.println(reloading);
            if (keyH.shot && bulletsInMagazine > 0 && reloading == false && isPaused == false) {
                //System.out.println("Trage");
                bullets.add(new Bullet(worldX, worldY, direction, bulletImage));
                bulletsInMagazine--;
                copie_nr_bullets = bulletsInMagazine;
                keyH.shot = false; // Prevent continuous shooting
            }
            nuclearreloadcounter++;
            if (nuclearreloadcounter > (int) (2.5 * FPS)) {
                this.nuclears = new ArrayList<Nuclear>();
            }
            if (keyH.nuclear == true && nuclearreloadcounter > nuclearreloadtime) {
                nuclears.add(new Nuclear(screenX - 48, screenY - 300, "down", nuclearbomb));
                NuclearinMagazine--;
                keyH.nuclear = false;
                nuclearreloadcounter = 0;
            }
            double min = 1000;
            if (keyH.car == true && flag_chest == false && carb == false) {
                for (Car car1 : cars) {
                    int distanceX = Math.abs(car1.x - worldX);
                    int distanceY = Math.abs(car1.y - worldY);
                    if (distanceX < 300 && distanceY < 300) {
                        if (min > Math.sqrt(Math.pow(distanceY, 2) + Math.pow(distanceX, 2))) {
                            my_car = car1;
                            min = Math.sqrt(Math.pow(distanceY, 2) + Math.pow(distanceX, 2));
                        }
                        //System.out.println(carb);
                    } else {
                        car1.isPlayerInCar = false;
                    }
                }
                if (min == 1000) {
                    my_car = null;
                    carb = false;
                }
                if (my_car != null) {
                    my_car.x = worldX;
                    my_car.y = worldY;
                    my_car.isPlayerInCar = true;
                    carb = true;
                    speed = 0;
                    viteza = 0;
                    keyH.car = false;
                    putere = my_car.putere;
                    greutate = my_car.greutate;
                    if (putere <= 150 * 736) {
                        k = 0.7139;
                    } else {
                        k = 1.42;
                    }
                    double b = u * greutate * 9.81;
                    double c = -putere;
                    double a = k * Math.sqrt(greutate);
                    double z = Math.pow(b, 2) - 4 * a * c;
                    x2 = (-b + Math.sqrt(z)) / (2 * a);
                    raport = (x2 / (5 * max)) * 3 / 2;
                    maximum_speed = x2;
                    //  System.out.println(maximum_speed * 3.6);
                }
            }
            //System.out.println(carb);
            if (keyH.exit == true) {
                for (Car car1 : cars) {
                    if (car1.isPlayerInCar == true) {
                        car1.isPlayerInCar = false;
                        carb = false;
                        car1.x = worldX;
                        car1.y = worldY;
                        speed = 0;
                        treapta = 1;
                        turatie = 0;
                    }
                }
                keyH.exit = false;
                // System.out.println("car");
            }
            // Manual reload logic
            if (keyH.reload && !reloading && MAGAZINE_SIZE > bulletsInMagazine) {
                reloading = true;
                keyH.reload = false;
                reloadCounter = 0;
                bulletsInMagazine = 0;
            }
            Iterator<Nuclear> iterator5 = nuclears.iterator();
            while (iterator5.hasNext()) {
                Nuclear nuc = iterator5.next();
                nuc.y = nuc.y + Nuclear.speed;
                if (!nuc.isActive()) {
                    iterator5.remove();
                }
            }
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                bullet.update();
                if (!bullet.isActive()) {
                    iterator.remove();
                }
            }
            Iterator<Rocket> iterator2 = rockets.iterator();
            while (iterator2.hasNext()) {
                Rocket rocket = iterator2.next();
                rocket.update();
                if (!rocket.isActive()) {
                    iterator2.remove();
                }
            }
            mobreloadcounter++;
            if (mobreloadcounter >= mobreloadTime) {
                mobreloadcounter = 0;
                Random rand = new Random();
                int randomValue = rand.nextInt(2); // Generates 0 or 1
                int spawnX = 200;
                int spawnY = 200;

                // Loop to ensure mobs don't spawn at the same position
                boolean positionOccupied;
                do {
                    positionOccupied = false;

                    // Generate new random positions
                    spawnX = rand.nextInt(4000); // Assuming 800 is the width of the area
                    spawnY = rand.nextInt(4000); // Assuming 600 is the height of the area

                    // Check if the position is already occupied by another mob
                    for (Mob mob : mobs) {
                        if (Math.abs(mob.x - spawnX) < 24 || Math.abs(mob.y - spawnY) < 24) {
                            positionOccupied = true;
                            break;
                        }
                    }
                } while (positionOccupied);

                // Spawn the mob at the unoccupied position
                if (randomValue == 0 && mobs.size() <= 80) {
                    mobs.add(new Mob(spawnX, spawnY, 2, this));
                    Mob mob = mobs.getLast(); // Obține ultimul mob din listă

// Setează HP-ul în funcție de dificultate
                    switch(selectedDifficulty) {
                        case "Easy": // Ușor
                            mob.hp = 80;
                            mob.maxhp=80;
                            mob.damage=4;
                            break;
                        case "Normal": // Mediu
                            mob.hp = 100;
                            mob.maxhp=100;
                            mob.damage=5;
                            break;
                        case "Hard": // Greu
                            mob.hp = 140;
                            mob.maxhp=140;
                            mob.damage=7;
                            break;
                        case "Insane": // Greu
                            mob.hp = 200;
                            mob.maxhp=200;
                            mob.damage=10;
                            break;
                        case "God": // Greu
                            mob.hp = 300;
                            mob.maxhp=300;
                            mob.damage=30;
                            break;
                        default:
                            mob.hp = 100; // Valoare default
                            mob.damage= 5;
                            mob.maxhp = 100;
                            break;
                    }
                } else {
                    mobs.add(new RangedMob(spawnX, spawnY, 2, this));
                    Mob mob = mobs.getLast();
                    switch(dificulty) {
                        case "Easy": // Ușor
                            mob.hp = 80;
                            mob.maxhp=80;
                            mob.damage=4;
                            break;
                        case "Normal": // Mediu
                            mob.hp = 100;
                            mob.maxhp=100;
                            mob.damage=5;
                            break;
                        case "Hard": // Greu
                            mob.hp = 140;
                            mob.maxhp=140;
                            mob.damage=7;
                            break;
                        case "Insane": // Greu
                            mob.hp = 200;
                            mob.maxhp=200;
                            mob.damage=10;
                            break;
                        case "God": // Greu
                            mob.hp = 300;
                            mob.maxhp=300;
                            mob.damage=15;
                            break;

                        default:
                            mob.hp = 100; // Valoare default
                            break;
                    }
                }
            }
            if(bosses.size() == 0)
            {
                Random rand = new Random();
                int spawnX = 200;
                int spawnY = 200;

                // Loop to ensure mobs don't spawn at the same position
                boolean positionOccupied;
                do {
                    positionOccupied = false;

                    // Generate new random positions
                    spawnX = rand.nextInt(4000); // Assuming 800 is the width of the area
                    spawnY = rand.nextInt(4000); // Assuming 600 is the height of the area

                    // Check if the position is already occupied by another mob
                    for (Mob mob : mobs) {
                        if (Math.abs(mob.x - spawnX) < 24 || Math.abs(mob.y - spawnY) < 24) {
                            positionOccupied = true;
                            break;
                        }
                    }
                } while (positionOccupied);
                Boss boss2 = new Boss(spawnX,spawnY,hpboss,3,this);
                hpboss= hpboss+500;
                boss2.damage= damageboss;
                damageboss = damageboss + 2;
                switch(selectedDifficulty)
                {
                    case "Easy": // Ușor
                        boss2.currentHp = (int)(0.8*(double)(hpboss));
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
                bosses.add(boss2);
            }


            int player_jos = worldY + 45;
            int player_sus = worldY + 15;
            int player_stanga = worldX + 12;
            int player_dreapta = worldX + 36;
            int bloc_jos = player_jos / gp.tilesize;
            int bloc_up = player_sus / gp.tilesize;
            int bloc_stanga = player_stanga / gp.tilesize;
            int bloc_dreapta = player_dreapta / gp.tilesize;
            int tilenum1, tilenum2;
            if (TileManager.MapTileNum[bloc_stanga][bloc_up] == 10 || TileManager.MapTileNum[bloc_stanga][bloc_jos] == 10
                    || TileManager.MapTileNum[bloc_dreapta][bloc_jos] == 10 || TileManager.MapTileNum[bloc_dreapta][bloc_up] == 10) {
                if (TileManager.MapTileNum[bloc_stanga][bloc_up] == 10) {
                    TileManager.MapTileNum[bloc_stanga][bloc_up] = 0; // Sau poți seta la 0 direct
                }

                // Verificăm și eliminăm cheile în celelalte poziții, dacă există
                if (TileManager.MapTileNum[bloc_stanga][bloc_jos] == 10) {
                    TileManager.MapTileNum[bloc_stanga][bloc_jos] = 0; // Sau poți seta la 0 direct
                }

                if (TileManager.MapTileNum[bloc_dreapta][bloc_jos] == 10) {
                    TileManager.MapTileNum[bloc_dreapta][bloc_jos] = 0; // Sau poți seta la 0 direct
                }

                if (TileManager.MapTileNum[bloc_dreapta][bloc_up] == 10) {
                    TileManager.MapTileNum[bloc_dreapta][bloc_up] = 0; // Sau poți seta la 0 direct
                }

                // Incrementează contorul de chei
                cheie++;
            }
            if ((TileManager.MapTileNum[bloc_stanga][bloc_up] == 11 || TileManager.MapTileNum[bloc_stanga][bloc_jos] == 11
                    || TileManager.MapTileNum[bloc_dreapta][bloc_jos] == 11 || TileManager.MapTileNum[bloc_dreapta][bloc_up] == 11) && hp != maxHp) {
                //System.out.println(hp);
                //System.out.println(maxHp);
                if (TileManager.MapTileNum[bloc_stanga][bloc_up] == 11) {
                    TileManager.MapTileNum[bloc_stanga][bloc_up] = TileManager.MapTileNum[bloc_dreapta][bloc_up]; // Sau poți seta la 0 direct
                }

                // Verificăm și eliminăm cheile în celelalte poziții, dacă există
                if (TileManager.MapTileNum[bloc_stanga][bloc_jos] == 11) {
                    TileManager.MapTileNum[bloc_stanga][bloc_jos] = TileManager.MapTileNum[bloc_dreapta][bloc_jos]; // Sau poți seta la 0 direct
                }

                if (TileManager.MapTileNum[bloc_dreapta][bloc_jos] == 11) {
                    TileManager.MapTileNum[bloc_dreapta][bloc_jos] = TileManager.MapTileNum[bloc_stanga][bloc_jos]; // Sau poți seta la 0 direct
                }

                if (TileManager.MapTileNum[bloc_dreapta][bloc_up] == 11) {
                    TileManager.MapTileNum[bloc_dreapta][bloc_up] = TileManager.MapTileNum[bloc_stanga][bloc_up]; // Sau poți seta la 0 direct
                }
                // Incrementează contorul de hp
                // System.out.println("Trece");
                if (hp + 30 > maxHp)
                    hp = maxHp;
                else
                    hp = hp + 30;
            }
            if ((keyH.dreapta == true || keyH.stanga == true || keyH.sus == true || keyH.jos == true || keyH.shot == true || keyH.reload == true || keyH.rocket == true) && flag_desen_upgradeuri == false || carb ==true) {
                bloc_up = (player_sus) / gp.tilesize;
                tilenum1 = TileManager.MapTileNum[bloc_stanga][bloc_up];
                tilenum2 = TileManager.MapTileNum[bloc_dreapta][bloc_up];
                if(carb == true && speed!=0 && dir!=0)
                {
                 //   System.out.println("I am in car");
                    switch (dir)
                    {
                        case 1:
                            if (worldY - speed >= 0)
                                worldY -= speed;
                            if (worldY - speed < 0 && carb == true)
                            {
                                worldY= (int) (48* maxWorldRow+speed-worldY);
                            }
                            break;
                        case 2:
                            if (worldY + speed <= gamepanel.WorldHeight)
                                worldY += speed;
                            if (worldY + speed > gamepanel.WorldHeight-48 && carb == true)
                            {
                                worldY = (int) ((int)(worldY+speed)%(WorldHeight));
                            }
                            break;
                        case 3:
                            if (worldX - speed >= 0) {
                                worldX -= speed;
                            }
                            if (worldX - speed < 0 && carb == true)
                                worldX= (int) (48* maxWorldCol+speed-worldX);
                            break;
                        case 4:
                            if (worldX + speed <= gamepanel.WorldWidth && carb == true) {
                                worldX += speed;
                            }
                            if (worldX + speed > gamepanel.WorldWidth-48 && carb == true)
                                worldX = (int) ((worldX+speed)%(WorldHeight));
                            break;

                    }
                }
                if (keyH.sus == true && worldY > 0 && ((tilenum1 < 3 || tilenum2 < 3)
                        || (tilenum1 == 10 || tilenum2 == 10)
                        || (tilenum1 == 11 || tilenum2 == 11)
                        || worldY < gp.tilesize * (bloc_up - 1))) {
                    dir = 1;
                    direction = "up";
                    if (worldY - speed >= 0 && carb == false)
                        worldY -= speed;
                    if (worldY - speed < 0 && carb == false)
                    {
                        worldY= (int) (48* maxWorldRow+speed-worldY);
                    }
                    //break;

                }
                bloc_jos = (Math.abs(player_jos - (int) speed)) / gp.tilesize;
                tilenum1 = TileManager.MapTileNum[bloc_stanga][bloc_jos];
                tilenum2 = TileManager.MapTileNum[bloc_dreapta][bloc_jos];
                if (keyH.jos == true && worldY < gp.maxWorldRow * gp.tilesize - 48 && ((tilenum1 < 3 || tilenum2 < 3)
                        || (tilenum1 == 10 || tilenum2 == 10) || (tilenum1 == 11 || tilenum2 == 11) || worldY < gp.tilesize * (bloc_jos - 1))) {
                    //System.out.printf("%d %d\n",tilenum1,tilenum2);
                    direction = "down";
                    dir = 2;
                    if (worldY + speed <= gamepanel.WorldHeight-48 && carb == false)
                        worldY += speed;
                    if (worldY + speed > gamepanel.WorldHeight-48 && carb == false)
                    {
                        worldY = (int) ((int)(worldY+speed-WorldHeight));
                    }
                    //break;
                }
                bloc_stanga = (player_stanga + (int) speed) / gp.tilesize;
                tilenum1 = TileManager.MapTileNum[bloc_stanga][bloc_jos];
                tilenum2 = TileManager.MapTileNum[bloc_stanga][bloc_up];
                if (keyH.stanga == true && worldX > 0 && ((tilenum1 < 3 || tilenum2 < 3)
                        || (tilenum1 == 10 || tilenum2 == 10)
                        || (tilenum1 == 11 || tilenum2 == 11)
                        || worldX < gp.tilesize * (bloc_stanga - 1))) {
                    dir = 3;
                    direction = "left";
                    if (worldX - speed >= 0 && carb == false) {
                        worldX -= speed;
                    }
                    if (worldX - speed < 0 && carb == false)
                        worldX= (int) (48* maxWorldCol+speed-worldX);
                    //break;
                }
                bloc_dreapta = (Math.abs(player_dreapta - (int) speed)) / gp.tilesize;
                tilenum1 = TileManager.MapTileNum[bloc_dreapta][bloc_jos];
                tilenum2 = TileManager.MapTileNum[bloc_dreapta][bloc_up];
                if (keyH.dreapta == true && worldX < gp.maxWorldCol * gp.tilesize - 48 && ((tilenum1 < 3 || tilenum2 < 3)
                        || (tilenum1 == 10 || tilenum2 == 10)
                        || (tilenum1 == 11 || tilenum2 == 11)
                        || worldX < gp.tilesize * (bloc_dreapta - 1))) {
                    direction = "right";
                    dir =4;
                    if (worldX + speed <= gamepanel.WorldWidth-48 && carb == false) {
                        worldX += speed;
                    }
                    if (worldX + speed > gamepanel.WorldWidth-48 && carb == false)
                        worldX = (int) (worldX+speed-WorldWidth);
                    //break;
                }
                if (keyH.shot == true) {
                    if (bulletsInMagazine > 0) {
                        bulletsInMagazine--;
                        copie_nr_bullets = bulletsInMagazine;
                        bullets.add(new Bullet(worldX, worldY, direction, bulletImage));
                        keyH.shot = false; // Set to false to prevent continuous shooting
                    }
                    //System.out.println("Shot");
                }
                if (keyH.reload == true) {
                    keyH.reload = false;
                    bulletsInMagazine = MAGAZINE_SIZE;
                }
                if (keyH.rocket == true) {
                    //keyH.rocket=false;
                    //System.out.println("racheta");
                    if (rocketsinMagazine > 0 && reload_rocket == false) {
                        rocketsinMagazine--;
                        if (direction == "up")
                            rockets.add(new Rocket(worldX, worldY, direction, rsus));
                        if (direction == "down")
                            rockets.add(new Rocket(worldX, worldY, direction, rjos));
                        if (direction == "left")
                            rockets.add(new Rocket(worldX, worldY, direction, rstanga));
                        if (direction == "right")
                            rockets.add(new Rocket(worldX, worldY, direction, rdreapta));
                        keyH.rocket = false;
                        reload_rocket = true;
                    }
                }
                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spritenum == 1) {
                        spritenum = 2;
                    } else if (spritenum == 2) {
                        spritenum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            // System.out.printf("%d %d\n",x,y);
            mobspritecounter++;
            if (mobspritecounter > 20) {
                if (spritemob == 1) {
                    spritemob = 0;
                } else if (spritemob == 0) {
                    spritemob = 1;
                }
                mobspritecounter = 0;
            }
        }
    }
    private Color mixColors(Color color1, Color color2, float ratio) {
        int r = (int) (color1.getRed() * ratio + color2.getRed() * (1 - ratio));
        int g = (int) (color1.getGreen() * ratio + color2.getGreen() * (1 - ratio));
        int b = (int) (color1.getBlue() * ratio + color2.getBlue() * (1 - ratio));
        return new Color(r, g, b);
    }

    public void draw(Graphics2D g2) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
               // System.out.println(x);
               // System.out.println("Trece");
                if (pedalAcceleratie.contains(x, y)) {
                    keyH.accel = true;
                    keyH.brake = false; // Dezactivează frâna
                }
                if (pedalFrana.contains(x, y)) {
                    keyH.brake = true;
                    keyH.accel = false; // Dezactivează accelerația
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                keyH.accel = false;
                keyH.brake = false;
            }
        });
        int drawTileSize = (int) (gp.tilesize * zoomLevel);
        flag_portal++;
        if(flag_portal>=72)
        {
            flag_portal = 0;
        }

        //g2.drawImage(bank4, 100, 100, 5*drawTileSize, 5*drawTileSize, null);
        if(isPaused == false) {
            contor_regen++;
            if (contor_regen >= FPS) {
                contor_regen = 0;
                if (hp + regen <= maxHp) {
                    hp = hp + regen;
                } else
                    hp = maxHp;
            }
        }
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(WHITE);
        for(Bank bank : banks)
        {
            bank.contor_banca++;
            if(mouseH.leftClick == true)
            {
               // System.out.println(Math.abs((mouseH.mouseX-screenX+worldX-bank.getX())%(maxWorldCol*48)));
               // System.out.println(Math.abs((mouseH.mouseY-screenY+worldY-bank.getY())%(maxWorldRow*48)));
            }
            if(mouseH.leftClick == true && Math.abs((mouseH.mouseX-screenX+worldX-bank.getX()+75)%(maxWorldCol*48))<150
            &&  Math.abs((mouseH.mouseY-screenY+worldY-bank.getY())%(maxWorldRow*48))<150 && flag_desen_upgradeuri == false
            && flag_desenare_punct == false && flag_desenare_punct2 == false)
            {
                flag_colect = true;
                mouseH.leftClick = false;
                money = money+bank.bani.size()*bank.value;
                totalmoney = (int)(totalmoney+bank.bani.size()*bank.value*value);
                for(Money bani1 : bank.bani)
                {
                    bani1.displayTime = FPS;
                }
            }
            if(bank.contor_banca >= bank.countdown_bani)
            {
                bank.contor_banca=0;
                int radius = 141;

                // Generează un unghi aleatoriu între 0 și 2*PI
                double angle = rand.nextDouble() * 2 * Math.PI;

                // Calculul coordonatelor pe cerc
                int moneyX = (int) (bank.getX()-20 + radius * Math.cos(angle));
                int moneyY = (int) (bank.getY()-20 + radius * Math.sin(angle));
                int value = 0;
                bank.bani.add(new Money(moneyX,moneyY,value,bank));
                //System.out.println("Adauga bani");
            }
            if(Math.sqrt(Math.pow(bank.getX()-20-worldX, 2) + Math.pow(bank.getY()-20-worldY, 2)) <= 141)
            {
                // Iterează prin fiecare obiect Money pentru colectare
                for(Money bani : bank.bani) {
                    // Dacă este aproape de un ban, colectează-l
                    if(Math.sqrt(Math.pow(bani.x - worldX, 2) + Math.pow(bani.y - worldY, 2)) <= 70 && bani.displayTime == 0) {
                        money += bank.value;  // Adaugă valoarea banului
                        totalmoney +=(int)(bank.value*value);
                        bani.displayTime = FPS;  // Setează timpul de afișare la 1 secundă (în cadre)
                    }
                }
            }

// Desenează banii și afișează sumele colectate pentru fiecare
            Iterator<Money> iterator = bank.bani.iterator();
            while(iterator.hasNext()) {
                Money bani = iterator.next();
                if(bani.displayTime > 0) {
                    // Afișează suma primită pentru acest ban individual
                    g2.drawString("+" + bank.value + " $", bani.x - worldX + screenX, bani.y - worldY + screenY);
                    bani.displayTime--;  // Scade timpul de afișare

                    // Elimină banul doar dacă timpul de afișare a ajuns la 0
                    if(bani.displayTime == 0) {
                        iterator.remove();  // Îndepărtează banul după ce timpul de afișare expiră
                        flag_colect = false;
                    }
                } else {
                    // Desenează banii care nu au fost colectați
                    int towerWorldX = bani.getX();
                    int towerWorldY = bani.getY();
                    // Calculează poziția turnului pe ecran în raport cu jucătorul
                    int screenX = towerWorldX - worldX + gp.player.screenX;
                    int screenY = towerWorldY - worldY + gp.player.screenY;

                    // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                    if (screenX < -500) {
                        screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                    } else if (screenX > gp.screenWidth + 900) {
                        screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                    }

                    // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                    if (screenY < -500) {
                        screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                    } else if (screenY > gp.screenHeight + 900) {
                        screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                    }

                    // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                    // System.out.printf("%d %d\n",screenX,screenY);
                    if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                        g2.drawImage(money_image, screenX,  screenY, drawTileSize, drawTileSize, null);
                    }
                }
            }

            int towerWorldX = bank.getX();
            int towerWorldY = bank.getY();
            // Calculează poziția turnului pe ecran în raport cu jucătorul
            int screenX = towerWorldX - worldX + gp.player.screenX;
            int screenY = towerWorldY - worldY + gp.player.screenY;

            // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
            if (screenX < -500) {
                screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
            } else if (screenX > gp.screenWidth + 900) {
                screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
            }

            // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
            if (screenY < -500) {
                screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
            } else if (screenY > gp.screenHeight + 900) {
                screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
            }

            // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
           // System.out.printf("%d %d\n",screenX,screenY);
            if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                    // System.out.println(bank.getBankType());
                    if(bank.getBankType() == 1) {
                        g2.drawImage(bank1, screenX - (int) (2.5 * drawTileSize), screenY - (int) (2.5 * drawTileSize),
                                5 * drawTileSize, 5 * drawTileSize, null);
                    }
                    if(bank.getBankType() == 2) {
                        g2.drawImage(bank2, screenX - (int) (2.5 * drawTileSize), screenY - (int) (2.5 * drawTileSize),
                                5 * drawTileSize, 5 * drawTileSize, null);
                    }
                    if(bank.getBankType() == 3) {
                        g2.drawImage(bank3, screenX - (int) (2.5 * drawTileSize), screenY - (int) (2.5 * drawTileSize),
                                5 * drawTileSize, 5 * drawTileSize, null);
                    }
                    if(bank.getBankType() == 4) {
                        g2.drawImage(bank4,  screenX - (int) (3.5 * drawTileSize), screenY - (int) (2.5 * drawTileSize),
                                7 * drawTileSize, 5 * drawTileSize, null);
                    }
            }
        }
        g2.setColor(WHITE);
        for(PortalAnimation portal : portals) {
            portal.contor_portal++;
           // System.out.println(portal.contor_portal);
            if(Math.sqrt(Math.pow(portal.xstart+24-worldX,2)+Math.pow(portal.ystart+24-worldY,2))<50 && portal.contor_portal>=5* FPS && carb == false)
            {
                worldX=portal.xstop+24;
                worldY=portal.ystop+24;
                portal.contor_portal=0;
            }
            if(Math.sqrt(Math.pow(portal.xstop+24-worldX,2)+Math.pow(portal.ystop+24-worldY,2))<50 && portal.contor_portal>=5* FPS && carb == false)
            {
                worldX=portal.xstart+24;
                worldY=portal.ystart+24;
                portal.contor_portal = 0;
            }
            //System.out.printf("%d %d\n",(portal.xstart-worldX+screenX)%(gp.maxWorldCol*48),(portal.ystart-worldY+screenY)%(gp.maxWorldRow*48));

            if(portal.contor_portal>=5* FPS)
            {//System.out.println("Trece prin portal");
                g2.drawString("Portal is active",(portal.xstart-worldX+screenX+gp.maxWorldCol*48)%(gp.maxWorldCol*48), (portal.ystart-worldY+screenY+gp.maxWorldRow*48)%(gp.maxWorldRow*48));
                g2.drawString("Portal is active",(portal.xstop-worldX+screenX+gp.maxWorldCol*48)%(gp.maxWorldCol*48), (portal.ystop-worldY+screenY+48*gp.maxWorldRow)%(gp.maxWorldRow*48));

            }
            else
            {
                String message = "Portal will be active in " + (int) (5-portal.contor_portal/ FPS)  + " seconds";

// Desenăm mesajul pe ecran la coordonatele (200, 100)
                if((portal.xstart-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)>=-1600 && (portal.xstart-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)<=1600
                && (portal.ystart-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)<=1600 && (portal.ystart-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)>=-1600) {
                   // System.out.println(portal.xstart - worldX + screenX - 100);
                    g2.drawString(message, (portal.xstart - worldX + screenX - 100+48*gp.maxWorldCol)%(48*gp.maxWorldCol),
                            (portal.ystart - worldY + screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow));
                }
                if((portal.xstop-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)>=-1600 && (portal.xstop-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)<=1600
                        && (portal.ystop-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)<=1600 && (portal.ystop-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)>=-1600) {
                    // System.out.println(portal.xstart - worldX + screenX - 100);
                    g2.drawString(message, (portal.xstop - worldX + screenX - 100+48*gp.maxWorldCol)%(48*gp.maxWorldCol),
                            (portal.ystop - worldY + screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow));
                }
            }
            if((portal.xstart-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)>=-1600 && (portal.xstart-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)<=1600
                    && (portal.ystart-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)<=1600 && (portal.ystart-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)>=-1600) {
                   // System.out.println((portal.xstart-worldX+screenX));
                    g2.drawImage(portalImages[flag_portal / 3], (portal.xstart-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)
                            , (portal.ystart-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow), 2 * drawTileSize, 2 * drawTileSize, null);
            }
            if((portal.xstop-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)>=-1600 && (portal.xstop-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)<=1600
                    && (portal.ystop-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)<=1600 && (portal.ystop-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow)>=-1600) {
                g2.drawImage(portalImages[flag_portal / 3], (portal.xstop-worldX+screenX+48*gp.maxWorldCol)%(48*gp.maxWorldCol)
                        , (portal.ystop-worldY+screenY+48*gp.maxWorldRow)%(48*gp.maxWorldRow), 2 * drawTileSize, 2 * drawTileSize, null);
            }
        }
        BufferedImage image = null;
        int drawX = (int) (screenX * zoomLevel);
        int drawY = (int) (screenY * zoomLevel);
        switch (direction) {
            case "up":
                image = (spritenum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spritenum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spritenum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spritenum == 1) ? right1 : right2;
                break;
        }
        for(Nuclear_Shop nuclear_shop1 : nuclear_shops) {
            int towerWorldX = nuclear_shop1.x;
            int towerWorldY = nuclear_shop1.y;

            // Calculează poziția turnului pe ecran în raport cu jucătorul
            int screenX = towerWorldX - worldX + gp.player.screenX;
            int screenY = towerWorldY - worldY + gp.player.screenY;

            // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
            if (screenX < -500) {
                screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
            } else if (screenX > gp.screenWidth + 900) {
                screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
            }

            // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
            if (screenY < -500) {
                screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
            } else if (screenY > gp.screenHeight + 900) {
                screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
            }

            // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
            if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {

                // Desenăm defensiva pe ecran
                g2.drawImage(nuclear_shop, screenX, screenY, 5 * drawTileSize, 5 * drawTileSize, null);
            }
        }
        for(Rocket_Shop rocketShop : rocket_shops) {
            int towerWorldX = rocketShop.getX();
            int towerWorldY = rocketShop.getY();

            // Calculează poziția turnului pe ecran în raport cu jucătorul
            int screenX = towerWorldX - worldX + gp.player.screenX;
            int screenY = towerWorldY - worldY + gp.player.screenY;

            // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
            if (screenX < -500) {
                screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
            } else if (screenX > gp.screenWidth + 900) {
                screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
            }

            // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
            if (screenY < -500) {
                screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
            } else if (screenY > gp.screenHeight + 900) {
                screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
            }

            // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
            if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {

                // Desenăm defensiva pe ecran
                g2.drawImage(rocket_shop, screenX, screenY, 5 * drawTileSize, 5 * drawTileSize, null);
            }
        }
        if (image != null) {
            g2.drawImage(image, drawX, drawY, drawTileSize, drawTileSize, null);
        }
        //System.out.println("Trece");
        if(RangedMob.bullets!=null) {
            for (Bullet bul : RangedMob.bullets) {
                //System.out.println(bul.x);
                g2.drawImage(bulletImage, bul.x - worldX + screenX, bul.y - worldY + screenY, drawTileSize, drawTileSize, null); // Folosește metoda `draw` din clasa Bullet
                //bul.update();
            }
        }

        Iterator<Mob> iterator3 = mobs.iterator();
        g2.setColor(Color.WHITE);
        try {
            while (iterator3.hasNext()) {
                Mob mob = iterator3.next();
                int screenx2 = (int) ((mob.x - worldX + screenX) * zoomLevel);
                int screeny2 = (int) ((mob.y - worldY + screenY) * zoomLevel);
                BufferedImage image2 = null;

                switch (mob.direction) {
                    case "up":
                        image2 = (spritemob == 0) ? mobUp1 : mobUp2;
                        break;
                    case "down":
                        image2 = (spritemob == 0) ? mobDown1 : mobDown2;
                        break;
                    case "left":
                        image2 = (spritemob == 0) ? mobLeft1 : mobLeft2;
                        break;
                    case "right":
                        image2 = (spritemob == 0) ? mobRight1 : mobRight2;
                        break;
                }

                if (image2 != null) {
                    int towerWorldX = mob.x;
                    int towerWorldY = mob.y;

                    // Calculează poziția turnului pe ecran în raport cu jucătorul
                    int screenX = towerWorldX - worldX + gp.player.screenX;
                    int screenY = towerWorldY - worldY + gp.player.screenY;

                    // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                    if (screenX < -500) {
                        screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                    } else if (screenX > gp.screenWidth + 900) {
                        screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                    }

                    // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                    if (screenY < -500) {
                        screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                    } else if (screenY > gp.screenHeight + 900) {
                        screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                    }

                    // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                    if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {

                        // Desenăm defensiva pe ecran
                        g2.drawImage(image2, screenX, screenY,  drawTileSize,  drawTileSize, null);
                    }

                        // Desenarea barei de HP
                        int hpBarWidth = (int) (30 * zoomLevel);
                        int hpBarHeight = (int) (5 * zoomLevel);
                        int hpBarX = screenX + (drawTileSize - hpBarWidth) / 2;
                        int hpBarY = screenY - (int) (10 * zoomLevel);

                        g2.setColor(Color.RED);
                        g2.fillRect(hpBarX, hpBarY, hpBarWidth, hpBarHeight);

                        float hpPercentage = (float) ((float) mob.hp / mob.maxhp);
                        g2.setColor(Color.GREEN);
                        g2.fillRect(hpBarX, hpBarY, (int) (hpBarWidth * hpPercentage), hpBarHeight);
                        g2.setColor(Color.WHITE);
                }
            }
        } catch(ConcurrentModificationException e) {

        }
        animationbossframe++;
        if(animationbossframe>=48) {
            animationbossframe = 1;
        }
        //System.out.println(animationboss);
        animationboss=animationbossframe/4+1;
        Iterator<Boss> bossIterator = bosses.iterator();
        while(bossIterator.hasNext())
        {
            Boss boss = bossIterator.next();
            int screenx2 = (int) ((boss.x - worldX + screenX) * zoomLevel);
            int screeny2 = (int) ((boss.y - worldY + screenY) * zoomLevel);
            BufferedImage image2 = null;
            if (Math.abs(boss.x - worldX) <= 96 && Math.abs(boss.y- worldY) <= 144) {
                //System.out.println("Trece");
                if (boss.isattacking == false) {
                    // Începe atacul
                    boss.isattacking = true;
                    boss.attackStartTime = System.currentTimeMillis(); // Setează timpul de început al atacului
                    boss.lastAttackTime = boss.attackStartTime; // Inițial, ultima dată când a atacat este momentul de start
                }
            }

            // Dacă boss-ul atacă, verificăm dacă trebuie aplicat damage mob-ului
            if (boss.isattacking == true && boss.bossdying<=0) {
                long currentTime = System.currentTimeMillis();
                if(boss.x>=worldX && isPaused == false) {
                    if (currentTime - boss.lastAttackTime >= 0 && currentTime - boss.lastAttackTime <= ATTACK_DURATION / 15) {
                        image2 = demon_attack1;
                    }
                    if (currentTime - boss.lastAttackTime > ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 2 * ATTACK_DURATION / 15) {
                        image2 = demon_attack2;
                    }
                    if (currentTime - boss.lastAttackTime > 2 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 3 * ATTACK_DURATION / 15) {
                        image2 = demon_attack3;
                    }
                    if (currentTime - boss.lastAttackTime > 3 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 4 * ATTACK_DURATION / 15) {
                        image2 = demon_attack4;
                    }
                    if (currentTime - boss.lastAttackTime > 4 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 5 * ATTACK_DURATION / 15) {
                        image2 = demon_attack5;
                    }
                    if (currentTime - boss.lastAttackTime > 5 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 6 * ATTACK_DURATION / 15) {
                        image2 = demon_attack6;
                    }
                    if (currentTime - boss.lastAttackTime > 6 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 7 * ATTACK_DURATION / 15) {
                        image2 = demon_attack7;
                    }
                    if (currentTime - boss.lastAttackTime > 7 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 8 * ATTACK_DURATION / 15) {
                        image2 = demon_attack8;
                    }
                    if (currentTime - boss.lastAttackTime > 8 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 9 * ATTACK_DURATION / 15) {
                        image2 = demon_attack9;
                    }
                    if (currentTime - boss.lastAttackTime > 9 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 10 * ATTACK_DURATION / 15) {
                        image2 = demon_attack10;
                    }
                    if (currentTime - boss.lastAttackTime > 10 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 11 * ATTACK_DURATION / 15) {
                        image2 = demon_attack11;
                    }
                    if (currentTime - boss.lastAttackTime > 11 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 12 * ATTACK_DURATION / 15) {
                        image2 = demon_attack12;
                    }
                    if (currentTime - boss.lastAttackTime > 12 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 13 * ATTACK_DURATION / 15) {
                        image2 = demon_attack13;
                    }
                    if (currentTime - boss.lastAttackTime > 13 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 14 * ATTACK_DURATION / 15) {
                        image2 = demon_attack14;
                    }
                    if (currentTime - boss.lastAttackTime > 14 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 15 * ATTACK_DURATION / 15) {
                        image2 = demon_attack15;
                    }
                }
                if(boss.x<worldX && isPaused == false)
                {
                    if (currentTime - boss.lastAttackTime >= 0 && currentTime - boss.lastAttackTime <= ATTACK_DURATION / 15) {
                        image2 = fdemon_attack1;
                    }
                    if (currentTime - boss.lastAttackTime > ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 2 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack2;
                    }
                    if (currentTime - boss.lastAttackTime > 2 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 3 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack3;
                    }
                    if (currentTime - boss.lastAttackTime > 3 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 4 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack4;
                    }
                    if (currentTime - boss.lastAttackTime > 4 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 5 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack5;
                    }
                    if (currentTime - boss.lastAttackTime > 5 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 6 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack6;
                    }
                    if (currentTime - boss.lastAttackTime > 6 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 7 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack7;
                    }
                    if (currentTime - boss.lastAttackTime > 7 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 8 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack8;
                    }
                    if (currentTime - boss.lastAttackTime > 8 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 9 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack9;
                    }
                    if (currentTime - boss.lastAttackTime > 9 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 10 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack10;
                    }
                    if (currentTime - boss.lastAttackTime > 10 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 11 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack11;
                    }
                    if (currentTime - boss.lastAttackTime > 11 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 12 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack12;
                    }
                    if (currentTime - boss.lastAttackTime > 12 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 13 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack13;
                    }
                    if (currentTime - boss.lastAttackTime > 13 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 14 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack14;
                    }
                    if (currentTime - boss.lastAttackTime > 14 * ATTACK_DURATION / 15 && currentTime - boss.lastAttackTime <= 15 * ATTACK_DURATION / 15) {
                        image2 = fdemon_attack15;
                    }
                }
                // Dacă au trecut 1.2 secunde de la ultimul atac, aplicăm damage
                if (currentTime - boss.lastAttackTime >= ATTACK_DURATION && isPaused == false) {
                   hp = hp - boss.damage;
                   if(hp <= 0)
                   {
                       hp = 0;
                   }
                    boss.lastAttackTime = currentTime; // Resetăm timpul pentru următorul atac
                }

                // Dacă au trecut 1.2 secunde de la începerea atacului, oprim atacul
                if (currentTime - boss.attackStartTime >= ATTACK_DURATION) {
                    boss.isattacking = false;
                }
                if(image2 == null && boss.x>=worldX)
                    image2=demon_attack1;
                if(image2 == null && boss.x<worldX)
                    image2=fdemon_attack1;
            }
            else {
                if (boss.x-worldX>=worldX-boss.x && boss.bossdying<=0 && isPaused == false) {
                    switch (animationboss) {
                        case 1:
                            image2 = demonwalk1;
                            break;
                        case 2:
                            image2 = demonwalk2;
                            break;
                        case 3:
                            image2 = demonwalk3;
                            break;
                        case 4:
                            image2 = demonwalk4;
                            break;
                        case 5:
                            image2 = demonwalk5;
                            break;
                        case 6:
                            image2 = demonwalk6;
                            break;
                        case 7:
                            image2 = demonwalk7;
                            break;
                        case 8:
                            image2 = demonwalk8;
                            break;
                        case 9:
                            image2 = demonwalk9;
                            break;
                        case 10:
                            image2 = demonwalk10;
                            break;
                        case 11:
                            image2 = demonwalk11;
                            break;
                        case 12:
                            image2 = demonwalk12;
                            break;
                    }
                }
                if(boss.x-worldX<worldX-boss.x && boss.bossdying<=0 && isPaused == false) {
                    switch (animationboss) {
                        case 1:
                            image2 = fdemonwalk1;
                            break;
                        case 2:
                            image2 = fdemonwalk2;
                            break;
                        case 3:
                            image2 = fdemonwalk3;
                            break;
                        case 4:
                            image2 = fdemonwalk4;
                            break;
                        case 5:
                            image2 = fdemonwalk5;
                            break;
                        case 6:
                            image2 = fdemonwalk6;
                            break;
                        case 7:
                            image2 = fdemonwalk7;
                            break;
                        case 8:
                            image2 = fdemonwalk8;
                            break;
                        case 9:
                            image2 = fdemonwalk9;
                            break;
                        case 10:
                            image2 = fdemonwalk10;
                            break;
                        case 11:
                            image2 = fdemonwalk11;
                            break;
                        case 12:
                            image2 = fdemonwalk12;
                            break;
                    }
                }
            }
            if(boss.ismoving == false && boss.bossdying<=0 && isPaused == false)
            {
                //System.out.println("Trece");
                boss.bossnotmovecontor++;
                if(boss.bossnotmovecontor>=40)
                    boss.bossnotmovecontor=0;
                int animation=boss.bossnotmovecontor/8+1;
                //System.out.println(boss.bossnotmovecontor);
                if(boss.x<worldX && isPaused == false)
                {
                    switch(animation)
                    {
                        case 1:
                            image2=fidle1;
                            break;
                        case 2:
                            image2 = fidle2;
                            break;
                        case 3:
                            image2 = fidle3;
                            break;
                        case 4:
                            image2 = fidle4;
                            break;
                        case 5:
                            image2 = fidle5;
                            break;
                        case 6:
                            image2 = fidle6;
                            break;
                    }
                }
                else {
                    switch (animation) {
                        case 1:
                            image2 = idle1;
                            break;
                        case 2:
                            image2 = idle2;
                            break;
                        case 3:
                            image2 = idle3;
                            break;
                        case 4:
                            image2 = idle4;
                            break;
                        case 5:
                            image2 = idle5;
                            break;
                        case 6:
                            image2 = idle6;
                            break;
                    }
                }
                if(image2!=null && boss.bossdying<=0) {
                    int towerWorldX = boss.x;
                    int towerWorldY = boss.y;

                    // Calculează poziția turnului pe ecran în raport cu jucătorul
                    int screenX = towerWorldX - worldX + gp.player.screenX;
                    int screenY = towerWorldY - worldY + gp.player.screenY;

                    // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                    if (screenX < -500) {
                        screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                    } else if (screenX > gp.screenWidth + 900) {
                        screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                    }

                    // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                    if (screenY < -500) {
                        screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                    } else if (screenY > gp.screenHeight + 900) {
                        screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                    }
                    if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                        g2.drawImage(image2, screenX - 4 * drawTileSize,  screenY - (int) (3.5 * drawTileSize), 10 * drawTileSize, 7 * drawTileSize, null);
                    }
                }
            }

            else
            if(image2!=null && boss.bossdying<=0) {
                int towerWorldX = boss.x;
                int towerWorldY = boss.y;

                // Calculează poziția turnului pe ecran în raport cu jucătorul
                int screenX = towerWorldX - worldX + gp.player.screenX;
                int screenY = towerWorldY - worldY + gp.player.screenY;

                // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                if (screenX < -500) {
                    screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                } else if (screenX > gp.screenWidth + 900) {
                    screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                }

                // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                if (screenY < -500) {
                    screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                } else if (screenY > gp.screenHeight + 900) {
                    screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                }

                // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                    g2.drawImage(image2, screenX - 4 * drawTileSize,  screenY - (int) (3.5 * drawTileSize), 10 * drawTileSize, 7 * drawTileSize, null);
                }
            }
        }
        List<Mob> mobsToRemove = new ArrayList<>();
        List<Bullet> bulletsToRemove = new ArrayList<>();

        Iterator<Bullet> bulletIterator = bullets.iterator();
        int ok = 0;
        if (bullets.size() == 0)
            ok = 0;
        while (bulletIterator.hasNext()) {

            Bullet bullet = bulletIterator.next();
            bullet.update();

            int bulletX = (int) (bullet.x * zoomLevel);
            int bulletY = (int) (bullet.y * zoomLevel);

            if (Math.pow(bulletX - worldX, 2) + Math.pow(bulletY - worldY, 2) > 1000000) {
                bulletsToRemove.add(bullet);
            } else {
                if(bulletX-worldX+screenX<gp.screenWidth+400 && bulletY-worldY+screenY < gp.screenHeight+400) {
                    g2.drawImage(bulletImage, bulletX - worldX + screenX, bulletY - worldY + screenY, drawTileSize, drawTileSize, null); //deseneaza bullet
                }
                Iterator<Mob> mobIterator = mobs.iterator();
                try {
                    while (mobIterator.hasNext()) {
                        Mob mob = mobIterator.next();

                        if (mob.isCollidingbullet(bullet)) {
                            mob.takeDamage(10);
                            bulletsToRemove.add(bullet);  // Marchez glonțul pentru eliminare

                            if (mob.hp <= 0) {
                                mobsToRemove.add(mob);   // Marchez mob-ul pentru eliminare
                                money += 1000;           // Adaug bani la eliminarea unui mob
                                totalmoney += (int)(1000*value);
                            }
                        }
                    }
                } catch (ConcurrentModificationException e) {
                   // System.err.println("Concurrent modification detected: " + e.getMessage());
                    // Alte acțiuni sau logica de gestiune a erorii
                } catch (Exception e) {
                    // Gestiune generală a erorilor neprevăzute
                    //System.err.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        }

        //in aceasta parte, vreau sa pot sa fac bossul (demonul) sa fie lovit de gloante, rachete si nucleara
        Iterator<Bullet> bulletIterator3 = bullets.iterator();
        while (bulletIterator3.hasNext()) {
            Bullet bullet = bulletIterator3.next();
            for (Boss boss : bosses) {
                int bossx = boss.x + (int) (1.5 * drawTileSize);
                int bossy = boss.y + 2 * drawTileSize;
                //System.out.printf("Coord boss: %d %d\n",bossx,bossy);
                if (Math.abs(bullet.x - bossx) < 48 && Math.abs(bullet.y - bossy) < 100) {
                    // Aplică damage boss-ului
                    boss.currentHp=boss.currentHp-10;
                    //System.out.println("Trece");
                    // Elimină glonțul din lista bullets
                    bulletIterator3.remove();
                    break; // Ieșim din for-ul de boss, deoarece glonțul a fost eliminat
                }
            }
        }
        //g2.drawImage(portal1, 200, 200, 2 * drawTileSize, 2 * drawTileSize, null);
        Iterator<Rocket> rocketIterator3 = rockets.iterator();
        while (rocketIterator3.hasNext()) {
            Rocket rocket = rocketIterator3.next();
            for (Boss boss : bosses) {
                int bossx = boss.x + (int) (1.5 * drawTileSize);
                int bossy = boss.y + 2 * drawTileSize;
                //System.out.printf("Coord boss: %d %d\n",bossx,bossy);
                if (Math.abs(rocket.x - bossx) < 48 && Math.abs(rocket.y - bossy) < 100) {
                    // Aplică damage boss-ului
                    boss.currentHp=boss.currentHp-50;
                    //System.out.println("Trece");
                    // Elimină glonțul din lista bullets
                    rocketIterator3.remove();
                    break; // Ieșim din for-ul de boss, deoarece glonțul a fost eliminat
                }
            }
        }
        //g2.drawImage(money_image,200,200,(int)(drawTileSize),(int)(drawTileSize),null);
        for(Boss boss : bosses)
        {
            double hpPercentage = (double) boss.currentHp / boss.maxHp;
        // Determinăm culoarea barei de viață în funcție de procentaj
            Color hpColor;
            if (hpPercentage > 0.7) {
                hpColor = Color.GREEN;  // HP mare
            } else if (hpPercentage > 0.3) {
                hpColor = Color.YELLOW; // HP mediu
            } else {
                hpColor = Color.RED;    // HP mic
            }
            // Desenăm bara de viață deasupra boss-ului
            int barWidth = (int) (hpPercentage * 100); // Lățimea barei de viață, 100 pixeli fiind lățimea maximă
            int barHeight = 5; // Înălțimea barei de viață
            int towerWorldX = boss.x;
            int towerWorldY = boss.y;

            // Calculează poziția turnului pe ecran în raport cu jucătorul
            int screenX = towerWorldX - worldX + gp.player.screenX;
            int screenY = towerWorldY - worldY + gp.player.screenY;

            // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
            if (screenX < -500) {
                screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
            } else if (screenX > gp.screenWidth + 900) {
                screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
            }

            // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
            if (screenY < -500) {
                screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
            } else if (screenY > gp.screenHeight + 900) {
                screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
            }

            // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
            if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                int barX = screenX + ((int)(1.5*drawTileSize)) - 50; // Centrat deasupra boss-ului
                int barY = screenY -10; // La 10 pixeli deasupra boss-ului

// Desenăm fundalul barei (un dreptunghi gri pentru fundalul barei de viață)
                g2.setColor(Color.GRAY);
                g2.fillRect(barX, barY, 100, barHeight);

// Desenăm bara de viață cu culoarea corespunzătoare
                g2.setColor(hpColor);
                g2.fillRect(barX, barY, barWidth, barHeight);

// Desenăm bordura barei de viață pentru contrast (opțional)
                g2.setColor(Color.WHITE);
                g2.drawRect(barX, barY, 100, barHeight);
            }
        }
        bullets.removeAll(bulletsToRemove);
        mobs.removeAll(mobsToRemove);
        List<Bullet> bulletsToRemove5 = new ArrayList<>();
        Iterator<Bullet> bulletIterator5 = bullets.iterator();
        while (bulletIterator5.hasNext()) {
            Bullet bullet = bulletIterator5.next();
            int bulletX = (int) (bullet.x);
            int bulletY = (int) (bullet.y);

            try {
                for (Tower tower : towers) {
                    if (tower.is_ally == false) {
                        //System.out.println(tower.is_ally);
                        //System.out.println(Math.abs(tower.getX() + 5 * drawTileSize - bulletX));
                        //System.out.println(tower.getY());
                        //System.out.println(bullet.y);
                        //System.out.println(Math.abs(tower.getY() + (int) (2.5 * drawTileSize) - bulletY));
                    }
                    int flags = 0;
                    if (tower.is_ally == false && Math.abs(tower.getX() + 5 * drawTileSize - bulletX) <= drawTileSize &&
                            Math.abs(tower.getY() + (int) (2.5 * drawTileSize) - bulletY) <= (int) (1.5 * drawTileSize)) {
                        tower.setHp(tower.getHp() - 10);
                        bulletsToRemove5.add(bullet);
                        flags=1;
                    }

                    if (tower.getHp() <= 0 && flags == 0) {
                        tower.is_ally = false;
                        tower.setHp(tower.max_hp);
                        money += 1000;
                        totalmoney += (int)(1000*value);
                    }
                    if (tower.getHp() <= 0 && flags == 1) {
                        tower.is_ally = true;
                        tower.setHp(tower.max_hp);
                        money += 1000;
                        totalmoney += (int)(1000*value);
                    }
                }
            } catch (ConcurrentModificationException e) {
                //System.err.println("Eroare de acces concurent: " + e.getMessage());
            }
        }
        bullets.removeAll(bulletsToRemove5);
        Map<Mob, Long> mobLastDamageTime = new HashMap<>();
        try {
            Iterator<Mob> mobIterator = mobs.iterator();
            long currentTime = System.currentTimeMillis();

            while (mobIterator.hasNext()) {
                Mob mob = mobIterator.next();
                // Get the last damage time for the current mob, default to 0 if not present
                long lastDamageTime = mobLastDamageTime.getOrDefault(mob, 0L);

                if ((Math.abs(worldX - mob.x) < 48 || Math.abs(worldY - mob.y) < 48) && carb == true) {
                    // Check if at least 1000ms (1 second) has passed since the last damage
                    if (currentTime - lastDamageTime >= 1000) {
                        mob.takeDamage(viteza / maximum_speed); // Apply damage
                        mobLastDamageTime.put(mob, currentTime); // Update the last damage time for this mob

                        if (mob.hp <= 0) {
                            //System.out.println("Mob ucis");
                            money = money + 1000;
                            totalmoney += (int)(1000*value);
                            mobIterator.remove(); // Use the iterator's remove method to avoid ConcurrentModificationException
                            mobLastDamageTime.remove(mob); // Clean up the map to avoid memory leaks
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
            //e.printStackTrace(); // Handle the exception, possibly log it or handle it another way
        }
        flag_chest = false;
        for (Chest chest : chests) {
            int distanceX = Math.abs(chest.getX() - worldX);
            int distanceY = Math.abs(chest.getY() - worldY);
            if (chest.isOpen() == false) {
                int towerWorldX = chest.x;
                int towerWorldY = chest.y;

                // Calculează poziția turnului pe ecran în raport cu jucătorul
                int screenX = towerWorldX - worldX + gp.player.screenX;
                int screenY = towerWorldY - worldY + gp.player.screenY;

                // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                if (screenX < -500) {
                    screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                } else if (screenX > gp.screenWidth + 900) {
                    screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                }

                // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                if (screenY < -500) {
                    screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                } else if (screenY > gp.screenHeight + 900) {
                    screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                }

                // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                    g2.drawImage(chest_closed,  screenX, screenY, 2*drawTileSize, drawTileSize, null);
                }
               // g2.drawImage(chest_closed, chest.getX() - worldX + screenX, chest.getY() - worldY + screenY, 2 * drawTileSize, drawTileSize, null);
            }
            if (chest.isOpen == true) {
                int towerWorldX = chest.x;
                int towerWorldY = chest.y;

                // Calculează poziția turnului pe ecran în raport cu jucătorul
                int screenX = towerWorldX - worldX + gp.player.screenX;
                int screenY = towerWorldY - worldY + gp.player.screenY;

                // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                if (screenX < -500) {
                    screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                } else if (screenX > gp.screenWidth + 900) {
                    screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                }

                // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                if (screenY < -500) {
                    screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                } else if (screenY > gp.screenHeight + 900) {
                    screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                }

                // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                    g2.drawImage(chest_opened, screenX,  screenY, (int) (1.6 * drawTileSize), drawTileSize, null);
                }
            }
            if (distanceX < 200 && distanceY < 200 && chest.isOpen() == false && carb == false) {
                g2.drawString("Press E to open chest", screenX + 20, screenY + 20);
                g2.drawString("Chest cost:"+ chest_cost,screenX+20,screenY+40);
                chest.flag_chest = true;
                flag_chest = true;
            } else {
                chest.flag_chest = false;
            }
            //System.out.println(flag_chest);
            if (keyH.car == true && distanceX < 200 && distanceY < 200 && chest.isOpen() == false) {
                if(chest_cost>money)
                {
                    g2.drawString("Not enough money",screenX+20,screenY+60);
                }
                else {
                    money=money-chest_cost;
                    chest.isOpen = true;
                    if(chest_cost<1000)
                    {
                        chest_cost+= 200;
                    }
                    if(chest_cost<3000 && chest_cost >= 1000)
                    {
                        chest_cost+= 500;
                    }
                    if(chest_cost<5000 && chest_cost >= 3000)
                    {
                        chest_cost += 1000;
                    }
                    Random rand = new Random();
                    int randomNumber = rand.nextInt(5) + 1;
                    contor_afisare_buff = 0;
                    switch (randomNumber) {
                        case 1:
                            for (Car car2 : cars) {
                                car2.putere = car2.putere + 0.3*car2.putere_initiala;
                            }
                           // System.out.println("trece 1");
                            message = "Car power +30%";
                            break;
                        case 2:
                            maxHp = maxHp + 20;
                            regen= (int)(maxHp/30);
                            message = "Character HP +20%";
                          //  System.out.println("trece 2");
                            break;
                        case 3:
                            money = money + 5000;
                            totalmoney = totalmoney + (int)(5000*value);
                            message = "Money +5000";
                           // System.out.println("trece 3");
                            break;
                        case 4:
                            initial_speed =  initial_speed+ 0.2*initial_velocity;
                            message = "Speed +20%";
                          //  System.out.println("trece 4");
                            break;
                        case 5:
                            for (Car car2 : cars) {
                                car2.greutate = (int) (0.8 * car2.greutate);
                            }
                            message = "Car weight -20%";
                          //  System.out.println("trece 5");
                            break;
                        default:
                            System.out.println("Numărul nu este între 1 și 5");
                    }
                    if (messageFrequency.containsKey(message)) {
                        messageFrequency.put(message, messageFrequency.get(message) + 1);
                    } else {
                        messageFrequency.put(message, 1);
                    }
                    keyH.car = false;
                }
            }
        }
        for (Chest chest : chests) {
            if (chest.isOpen() == true && contor_afisare_buff < timp_afisare_buff) {
                g2.drawString(message, screenX + 20, screenY + 80);
                contor_afisare_buff++;
            }
        }
        flag_car = false;
        Car car_bun=null;
        double mindist = Math.pow(10,9);
        for (Car car1 : cars) {
            int distanceX = Math.abs(car1.x - worldX);
            int distanceY = Math.abs(car1.y - worldY);
            if (distanceX < 300 && distanceY < 300 && car1.isPlayerInCar == false && flag_chest == false && carb == false
            && Math.sqrt(Math.pow(distanceX,2)+Math.pow(distanceY ,2))<mindist) {
                flag_car = true;
                g2.drawString("Press E to enter car", screenX + 20, screenY + 20);
                car_bun=car1;
                mindist = Math.sqrt(Math.pow(distanceX,2)+Math.pow(distanceY ,2));
            }
            // Draw the car
            //System.out.println(car1.isPlayerInCar);
            //System.out.println(car1.y);
            if (car1.isPlayerInCar == false) {
                int towerWorldX = car1.x;
                int towerWorldY = car1.y;

                // Calculează poziția turnului pe ecran în raport cu jucătorul
                int screenX = towerWorldX - worldX + gp.player.screenX;
                int screenY = towerWorldY - worldY + gp.player.screenY;

                // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                if (screenX < -500) {
                    screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                } else if (screenX > gp.screenWidth + 900) {
                    screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                }

                // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                if (screenY < -500) {
                    screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                } else if (screenY > gp.screenHeight + 900) {
                    screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                }

                // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                    g2.drawImage(car, screenX, screenY, 6 * drawTileSize, (int) 3 * drawTileSize, null);
                }
            } else {
                g2.drawImage(car, screenX - 3 * drawTileSize, screenY - (int) 1.5 * drawTileSize, 6 * drawTileSize, (int) 3 * drawTileSize, null);
            }
        }
        if(car_bun!=null)
        {
            g2.drawString("Power: " + (int)(car_bun.putere / 736) + " HP", screenX + 20, screenY + 40);
            g2.drawString("Weight: " + (int)(car_bun.greutate) + "  kg", screenX + 20, screenY + 60);
        }
        flag_tower = false;
        for (Tower tower : towers) {
            if (flag_chest == false && carb == false && flag_car == false) {
                if (Math.sqrt(Math.pow(tower.getX() + 5 * drawTileSize - worldX, 2) + Math.pow(tower.getY() + (int) (2.5 * drawTileSize) - worldY, 2)) < 200) {
                    g2.drawString("Press T to upgrade tower", screenX + 20, screenY + 20);
                    g2.drawString("Damage per hit: " + String.valueOf(tower.getDamage()), screenX + 20, screenY + 40);
                    g2.drawString("Attacks per second: " + String.format("%.2f", (double) 1000 / (double)(tower.getAttack_speed())), screenX + 20, screenY + 60);
                    g2.drawString("Hitpoints: " + String.format("%d", tower.max_hp), screenX + 20, screenY + 80);
                    flag_tower = true;
                    if(tower.is_ally == true)
                        g2.drawString("Ally tower", screenX+20, screenY+100);
                    else
                        g2.drawString("Enemy tower",screenX+20, screenY+100);
                    if((keyH.tower == true || flag_desen_upgradeuri == true) && tower.is_ally==true && isPaused == false) //trece doar o data, fiindca se afla in range-ul unui singur tower
                    {
                       // System.out.println(i);
                        i++;
                        flag_desen_upgradeuri=true;
                        if(money >= tower.damage_cost) {
                            g2.setColor(Color.GREEN); // Dacă ai destui bani, dreptunghiul va fi verde
                        } else {
                            g2.setColor(Color.RED);   // Altfel, dreptunghiul va fi roșu
                        }

                        // Desenează dreptunghiul pentru upgrade
                        g2.fillRect(0, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize));
                        Composite originalComposite = g2.getComposite();  // Salvează starea originală
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

                        // Desenează imaginea de upgrade peste dreptunghi
                        g2.drawImage(upgrade_tower_damage, 0, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize), null);

                        // Resetează transparența la starea originală
                        g2.setComposite(originalComposite);
                        g2.setColor(WHITE);
                        g2.drawString("Level " + tower.level_damage,20,180);
                        g2.drawString("Price " + tower.damage_cost + " $",20,200);
                        //g2.drawImage(upgrade_tower_damage, 0, 0, (int)(4.5 * drawTileSize), 4 * drawTileSize, null);
                        if(money >= tower.attack_speed_cost) {
                            g2.setColor(Color.GREEN); // Dacă ai destui bani, dreptunghiul va fi verde
                        } else {
                            g2.setColor(Color.RED);   // Altfel, dreptunghiul va fi roșu
                        }
                        g2.fillRect(4*drawTileSize, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize));
                        //Composite originalComposite = g2.getComposite();  // Salvează starea originală
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                        g2.drawImage(upgrade_tower_attack_speed, 4*drawTileSize, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize), null);
                        g2.setComposite(originalComposite);
                        g2.setColor(WHITE);
                        g2.drawString("Level " + tower.level_attack_speed,212,180);
                        g2.drawString("Price " + tower.attack_speed_cost + " $",212,200);
                        if(money >= tower.range_cost) {
                            g2.setColor(Color.GREEN); // Dacă ai destui bani, dreptunghiul va fi verde
                        } else {
                            g2.setColor(Color.RED);   // Altfel, dreptunghiul va fi roșu
                        }
                        g2.fillRect(8*drawTileSize, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize));
                        //Composite originalComposite = g2.getComposite();  // Salvează starea originală
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                        g2.drawImage(upgrade_range_tower, 8*drawTileSize, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize), null);
                        g2.setComposite(originalComposite);
                        g2.setColor(WHITE);
                        g2.drawString("Level " + tower.level_range,404,180);
                        g2.drawString("Price " + tower.range_cost + " $",404,200);


                        if(money >= tower.hp_cost) {
                            g2.setColor(Color.GREEN); // Dacă ai destui bani, dreptunghiul va fi verde
                        } else {
                            g2.setColor(Color.RED);   // Altfel, dreptunghiul va fi roșu
                        }
                        g2.fillRect(12*drawTileSize, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize));
                        //Composite originalComposite = g2.getComposite();  // Salvează starea originală
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                        g2.drawImage(upgrade_tower_hp, 12*drawTileSize, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize), null);
                        g2.setComposite(originalComposite);
                        g2.setColor(WHITE);
                        g2.drawString("Level " + tower.level_hp,596,180);
                        g2.drawString("Price " + tower.hp_cost + " $",596,200);
                        if(mouseH.leftClick == true && mouseH.mouseX>=0 && mouseH.mouseX<=4*drawTileSize
                        && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cresc damage-ul
                        {
                            if(money>tower.damage_cost) {
                                money = money-tower.damage_cost;
                                tower.damage_cost= tower.damage_cost+200;
                                tower.setDamage(tower.getDamage() + 5);
                                tower.level_damage++;
                                mouseH.leftClick = false;
                            }
                        }
                        if(mouseH.leftClick == true && mouseH.mouseX>4*drawTileSize && mouseH.mouseX<=8*drawTileSize
                                && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize)
                        { //cresc attack speed
                            if(money>=tower.attack_speed_cost) {
                                money = money-tower.attack_speed_cost;
                                tower.attack_speed_cost= tower.attack_speed_cost+200;
                                double attack_speed=1000/(double)(tower.getAttack_speed());
                               // System.out.println(attack_speed);
                                tower.setAttack_speed((int)(1000/(attack_speed+0.2)));
                                tower.level_attack_speed++;
                                mouseH.leftClick = false;
                            }
                        }
                        if(mouseH.leftClick == true && mouseH.mouseX>8*drawTileSize && mouseH.mouseX<=12*drawTileSize
                                && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize)
                        { //cresc attack speed
                            if(money>=tower.range_cost) {
                                money = money-tower.range_cost;
                                tower.range_cost= tower.range_cost+100;
                                tower.setRange(tower.getRange()+10);
                                tower.level_range++;
                                mouseH.leftClick = false;
                            }
                        }
                        if(mouseH.leftClick == true && mouseH.mouseX>12*drawTileSize && mouseH.mouseX<=16*drawTileSize
                                && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize)
                        { //cresc hp
                            if(money>=tower.hp_cost) {
                                money = money-tower.hp_cost;
                                tower.hp_cost= tower.hp_cost+100;
                                tower.setHp(tower.getHp()+100);
                                tower.max_hp=tower.max_hp+100;
                                tower.level_hp++;
                                mouseH.leftClick = false;
                            }
                        }
                    }
                }
            }
        }
        if(mouseH.rightClick == true && isPaused == false)
        {
           // System.out.println("right key pressed");
           // mouseH.rightClick = false;
            coordX=mouseH.mouseX-screenX+worldX;
            coordY=mouseH.mouseY-screenY+worldY;
            mouseH.rightClick = false;
            if(coordX>=2*drawTileSize && coordY>=2*drawTileSize) {
                flag_desenare_punct=true;
                //rocket_shops.add(new Rocket_Shop(coordX, coordY));
            }
        }
        if(flag_desenare_punct==true)
        {
            int armLength = 10;
            Stroke oldStroke = g2.getStroke();
            g2.setColor(RED);
            g2.setStroke(new BasicStroke(5));
            //System.out.println("Trece");
            // System.out.printf("%d %d\n",coordX,coordY);
            g2.drawLine(coordX-worldX+screenX - armLength, coordY-worldY+screenY - armLength, coordX-worldX+screenX + armLength, coordY-worldY+screenY + armLength);
            g2.drawLine(coordX-worldX+screenX - armLength, coordY-worldY+screenY + armLength, coordX-worldX+screenX + armLength, coordY-worldY+screenY - armLength);
            g2.setColor(WHITE);
            g2.setStroke(oldStroke);
            g2.setColor(Color.GRAY);
            // Desenăm 4 dreptunghiuri
            int rectWidth = 4 * drawTileSize;   // Lățimea dreptunghiului
            int rectHeight = (int)(4.5 * drawTileSize);  // Înălțimea dreptunghiului
            g2.setColor(BLACK); // Setăm culoarea pentru liniile de delimitare
            for (int i = 0; i <= 4; i++) {
                g2.drawLine(i * rectWidth, 0, i * rectWidth, rectHeight); // Liniile verticale
            }
            // Set a gray color to mix with
            Color gray = new Color(192, 192, 192); // A light gray

// Determine the color based on money
            Color fillColor;
            if (money >= defense_cost) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(0, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(defense, -(int)(1.5 * drawTileSize), -30, (int)(7.13 * drawTileSize), (int)(6 * drawTileSize), null);
            if (money >= car_cost) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(4*drawTileSize, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(car,4*drawTileSize-20,60,5*drawTileSize,(int)(2.5*drawTileSize),null);
            if (money >= rocket_shop_cost) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(8*drawTileSize, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(rocket_shop,8*drawTileSize,0,4*drawTileSize,(int)(4.5*drawTileSize),null);

            if (money >= nuclear_shop_cost) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(12*drawTileSize, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(nuclear_shop,12*drawTileSize,0,4*drawTileSize,(int)(4.5*drawTileSize),null);
            g2.setColor(BLACK); // Setăm culoarea pentru liniile de delimitare
            for (int i = 0; i <= 4; i++) {
                g2.drawLine(i * rectWidth, 0, i * rectWidth, rectHeight); // Liniile verticale
            }
            g2.setColor(WHITE);
            g2.drawString("Price: " + defense_cost,20,180);
            g2.drawString("Price: " + car_cost,20+4*drawTileSize,180);
            g2.drawString("Price: " + rocket_shop_cost,20+8*drawTileSize,180);
            g2.drawString("Price: " + nuclear_shop_cost,20+12*drawTileSize,180);
            if(mouseH.leftClick == true && mouseH.mouseX>=0 && mouseH.mouseX<4*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cresc damage-ul
            {
                if(money>=defense_cost) {
                    money = money-defense_cost;
                    towers.add(new Tower(coordX-(int)(4.5*drawTileSize),coordY-(int)(2.5*drawTileSize),300,20,400,1000,true));
                    mouseH.leftClick = false;
                    flag_desenare_punct = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseX>=4*drawTileSize && mouseH.mouseX<8*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cresc damage-ul
            {
                if(money>=car_cost) {
                    money = money-car_cost;
                    //towers.add(new Tower(coordX-(int)(4.5*drawTileSize),coordY-(int)(2.5*drawTileSize),300,20,400,1000,true));
                    cars.add(new Car(coordX-3*drawTileSize,coordY-(int)(1.5*drawTileSize),1000,750*736,this));
                    mouseH.leftClick = false;
                    flag_desenare_punct = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseX>=8*drawTileSize && mouseH.mouseX<12*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cresc damage-ul
            {
                if(money>=rocket_shop_cost) {
                    money = money-rocket_shop_cost;
                    Rocket_Shop new_rocket = new Rocket_Shop(coordX-(int)(2.5*drawTileSize),coordY-(int)(2.5*drawTileSize));
                    //towers.add(new Tower(coordX-(int)(4.5*drawTileSize),coordY-(int)(2.5*drawTileSize),300,20,400,1000,true));
                    new_rocket.cost = Rocket_Shop.generateRandomCost(160,240)/2;
                    rocket_shops.add(new_rocket);
                    mouseH.leftClick = false;
                    flag_desenare_punct = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseX>=12*drawTileSize && mouseH.mouseX<16*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cresc damage-ul
            {
                if(money>=nuclear_shop_cost) {
                    money = money-nuclear_shop_cost;
                    Nuclear_Shop new_nuclear = new Nuclear_Shop(coordX-(int)(2.5*drawTileSize),coordY-(int)(2.5*drawTileSize));
                    //towers.add(new Tower(coordX-(int)(4.5*drawTileSize),coordY-(int)(2.5*drawTileSize),300,20,400,1000,true));
                    new_nuclear.cost = Rocket_Shop.generateRandomCost(800,1200)/2;
                    nuclear_shops.add(new_nuclear);
                    mouseH.leftClick = false;
                    flag_desenare_punct = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseY> 4*drawTileSize)
            {
               // System.out.println("Trece");
                flag_desenare_punct = false;
                flag_desenare_punct2 = true;
            }
        }
        if(mouseH.leftClick == true && flag_desen_upgradeuri == false && flag_desenare_punct == false && flag_desenare_punct2 == false && isPaused == false)
        {
            if(flag_desenare_punct2 == false) {
                coordX = mouseH.mouseX - screenX + worldX;
                coordY = mouseH.mouseY - screenY + worldY;
            }
            flag_desenare_punct = false;
            flag_desenare_punct2 = false;
            // System.out.println("Trece prin click stanga");
            mouseH.leftClick = false;
            if(coordX>=2*drawTileSize && coordY>=2*drawTileSize) {
                flag_desenare_punct = false;
                flag_desenare_punct2=true;
            }
        }

        //aici incepe partea cu bancile
       // System.out.println(flag_colect);
        if(flag_desenare_punct == false && flag_desenare_punct2 == true && flag_desen_upgradeuri == false && flag_colect == false)
        {
            int armLength = 10;
            Stroke oldStroke = g2.getStroke();
            g2.setColor(BLACK);
            g2.setStroke(new BasicStroke(5));
            //System.out.println("Trece");
            // System.out.printf("%d %d\n",coordX,coordY);
            g2.drawLine(coordX-worldX+screenX - armLength, coordY-worldY+screenY - armLength, coordX-worldX+screenX + armLength, coordY-worldY+screenY + armLength);
            g2.drawLine(coordX-worldX+screenX - armLength, coordY-worldY+screenY + armLength, coordX-worldX+screenX + armLength, coordY-worldY+screenY - armLength);
            g2.setColor(WHITE);
            g2.setStroke(oldStroke);
            g2.setColor(Color.GRAY);
           // System.out.println("Pun banca");
            int rectWidth = 4 * drawTileSize;   // Lățimea dreptunghiului
            int rectHeight = (int)(4.5 * drawTileSize);  // Înălțimea dreptunghiului
            g2.setColor(BLACK); // Setăm culoarea pentru liniile de delimitare
            for (int i = 0; i <= 4; i++) {
                g2.drawLine(i * rectWidth, 0, i * rectWidth, rectHeight); // Liniile verticale
            }
            // Set a gray color to mix with
            Color gray = new Color(192, 192, 192); // A light gray

// Determine the color based on money
            Color fillColor;
            if (money >= cost_bank1) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(0, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(bank1, 0, 0, (int)(4 * drawTileSize), (int)(4.5 * drawTileSize), null);
            if (money >= cost_bank2) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(4*drawTileSize, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(bank2,4*drawTileSize,0,4*drawTileSize,(int)(4.5*drawTileSize),null);
            if (money >= cost_bank3) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(8*drawTileSize, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(bank3,8*drawTileSize,0,4*drawTileSize,(int)(4.5*drawTileSize),null);

            if (money >= cost_bank4) {
                fillColor = mixColors(Color.GREEN, gray, 0.35f); // Mix green with gray
            } else {
                fillColor = mixColors(Color.RED, gray, 0.35f); // Mix red with gray
            }
            g2.setColor(fillColor);
            g2.fillRect(12*drawTileSize, 0, rectWidth, rectHeight); // Desenăm dreptunghiul colorat
            g2.drawImage(bank4,12*drawTileSize,0,4*drawTileSize,(int)(4.5*drawTileSize),null);
            g2.setColor(BLACK); // Setăm culoarea pentru liniile de delimitare
            for (int i = 0; i <= 4; i++) {
                g2.drawLine(i * rectWidth, 0, i * rectWidth, rectHeight); // Liniile verticale
            }
            g2.setColor(WHITE);
            g2.drawString("Price: " + cost_bank1,20,180);
            g2.drawString("Price: " + cost_bank2,20+4*drawTileSize,180);
            g2.drawString("Price: " + cost_bank3,20+8*drawTileSize,180);
            g2.drawString("Price: " + cost_bank4,20+12*drawTileSize,180);
            if(mouseH.leftClick == true && mouseH.mouseX>=0 && mouseH.mouseX<4*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cumpar banca 1
            {
                if(money>=cost_bank1) {
                    money = money-cost_bank1;
                    banks.add(new Bank(coordX,coordY,1));
                    mouseH.leftClick = false;
                    flag_desenare_punct2 = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseX>=4*drawTileSize && mouseH.mouseX<8*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cresc damage-ul
            {
                if(money>=cost_bank2) { //cumpar banca 2
                    money = money-cost_bank2;
                    banks.add(new Bank(coordX,coordY,2));
                    mouseH.leftClick = false;
                    flag_desenare_punct2 = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseX>=8*drawTileSize && mouseH.mouseX<12*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cumpar banca 3
            {
                if(money>=cost_bank3) {
                    money = money-cost_bank3;
                    banks.add(new Bank(coordX,coordY,3));
                    mouseH.leftClick = false;
                    flag_desenare_punct2 = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseX>=12*drawTileSize && mouseH.mouseX<16*drawTileSize
                    && mouseH.mouseY>=0 && mouseH.mouseY<=4*drawTileSize) //cumpar banca 4
            {
                if(money>=cost_bank4) {
                    money = money-cost_bank4;
                    banks.add(new Bank(coordX,coordY,4));
                    mouseH.leftClick = false;
                    flag_desenare_punct2 = false;
                }
            }
            if(mouseH.leftClick == true && mouseH.mouseY> 4*drawTileSize)
            {
                //System.out.println("Trece");
                flag_desenare_punct = false;
                flag_desenare_punct2 = true;
            }
        }
        contor_desen++;
        flag_colect = false;
        if(flag_desen_upgradeuri == true && keyH.tower == true && contor_desen>=FPS)
        {
            flag_desen_upgradeuri = false;
            keyH.tower=false;
            contor_desen= 0;
            i=0;
        }
        flag_rocket=false;
        for(Rocket_Shop rocketShop : rocket_shops) {
            if (Math.sqrt(Math.pow(rocketShop.x + (int) (2.5 * drawTileSize) - worldX, 2) +
                    Math.pow(rocketShop.y + (int) (2.5 * drawTileSize) - worldY, 2)) < 200 && flag_tower == false
            && flag_chest == false && carb == false && flag_car == false) {
                flag_rocket = true;
                if(keyH.tower == false) {
                    g2.drawString("Press T to buy rockets", screenX + 20, screenY + 20);
                    g2.drawString("Rocket cost: " + rocketShop.cost, screenX + 20, screenY + 40);
                }
                if(keyH.tower == true)
                {
                    keyH.tower = false;
                    if(money>=rocketShop.cost) {
                        rocketsinMagazine++;
                        money = money - rocketShop.cost;
                    }
                    else {
                        g2.drawString("Not enough money", screenX + 20, screenY + 20);
                    }
                    if(rocketsinMagazine>ROCKET_SIZE)
                    {
                        ROCKET_SIZE = rocketsinMagazine;
                    }

                }
            }
        }
        for(Nuclear_Shop nuclear_shop1 : nuclear_shops)
        {
            if (Math.sqrt(Math.pow(nuclear_shop1.x + (int) (2.5 * drawTileSize) - worldX, 2) +
                    Math.pow(nuclear_shop1.y + (int) (2.5 * drawTileSize) - worldY, 2)) < 200 && flag_tower == false
                    && flag_chest == false && carb == false && flag_car == false && flag_rocket == false) {
                if(keyH.tower == false) {
                    g2.drawString("Press T to buy nuclears", screenX + 20, screenY + 20);
                    g2.drawString("Nuclear cost: " + nuclear_shop1.cost, screenX + 20, screenY + 40);
                }
                if(keyH.tower == true)
                {
                    keyH.tower = false;
                    if(money>=nuclear_shop1.cost) {
                        NuclearinMagazine++;
                        money = money - nuclear_shop1.cost;
                    }
                    else {
                        g2.drawString("Not enough money", screenX + 20, screenY + 20);
                    }
                    if(NuclearinMagazine>Nuclear_Size)
                    {
                        Nuclear_Size = NuclearinMagazine;
                    }

                }
            }
        }
        //System.out.println(cars.size());
        if (carb == true) {
            if (putere <= 150 * 736) {
                k = 0.7139;
            } else {
                k = 1.42;
            }
            if (x2 == 0) {
                double b = u * greutate * 9.81;
                double c = -putere;
                double a = k * Math.sqrt(greutate);
                double z = Math.pow(b, 2) - 4 * a * c;
                x2 = (-b + Math.sqrt(z)) / (2 * a);
                raport = (x2 / (5 * max)) * 3 / 2;
                maximum_speed = x2;
                //System.out.println(maximum_speed*3.6);
            }
            g2.drawString("Press F to exit car", screenX + 20, screenY + 20);
            if (keyH.exit == true) {
                for (Car car2 : cars) {
                    if (car2.isPlayerInCar == true) {
                        car2.x = worldX;
                        car2.y = worldY;
                        car2.isPlayerInCar = false;
                        speed = 0;
                        viteza = 0;
                        treapta = 1;
                        carb = false;
                    }
                }
                //System.out.println(keyH.car);
            }
        }

        //sliderPanel.paint(g2);
        //zoomSlider.paint(g2);
        g2.setColor(Color.white);

        List<Rocket> rocketsToRemove = new ArrayList<>();
        List<Mob> mobsToRemove2 = new ArrayList<>();
        Iterator<Rocket> iterator2 = rockets.iterator();

        while (iterator2.hasNext()) {
            Rocket rocket = iterator2.next();
            int rocketX = (int) (rocket.x * zoomLevel);
            int rocketY = (int) (rocket.y * zoomLevel);

            if (Math.pow(rocketX - worldX, 2) + Math.pow(rocketY - worldY, 2) > 1000000) {
                rocketsToRemove.add(rocket);
            } else {
                boolean rocketHit = false;
                List<Mob> mobsCopy = new ArrayList<>(mobs);
                for (Mob mob : mobsCopy) {
                    if (mob.isCollidingRocket(rocket)) {
                        mob.takeDamage(50);
                        rocketsToRemove.add(rocket);
                        rocketHit = true;

                        for (Mob nearbyMob : mobsCopy) {
                            double distance = Math.sqrt(Math.pow(nearbyMob.x - rocket.x, 2) + Math.pow(nearbyMob.y - rocket.y, 2));
                            if (distance <= 100 && nearbyMob != mob) {
                                nearbyMob.takeDamage(50);
                                if (nearbyMob.hp <= 0) {
                                    mobsToRemove2.add(nearbyMob);
                                    money += 1000;
                                    totalmoney += (int)(1000*value);
                                }
                            }
                        }

                        if (mob.hp <= 0) {
                            mobsToRemove2.add(mob);
                            money += 1000;
                            totalmoney += (int)(1000*value);
                        }
                        break;
                    }
                }

                if (!rocketHit) {
                    if (rocket.direction.equals("up"))
                        g2.drawImage(rsus, rocketX-worldX+screenX, rocketY-worldY+screenY, drawTileSize, drawTileSize, null);
                    if (rocket.direction.equals("down"))
                        g2.drawImage(rjos, rocketX-worldX+screenX, rocketY-worldY+screenY, drawTileSize, drawTileSize, null);
                    if (rocket.direction.equals("left"))
                        g2.drawImage(rstanga, rocketX-worldX+screenX, rocketY-worldY+screenY, drawTileSize, drawTileSize, null);
                    if (rocket.direction.equals("right"))
                        g2.drawImage(rdreapta, rocketX-worldX+screenX, rocketY-worldY+screenY, drawTileSize, drawTileSize, null);
                    reload_rocket = true;
                }
            }
        }

        rockets.removeAll(rocketsToRemove);
        mobs.removeAll(mobsToRemove2);

        List<Rocket> rocketsToRemove3 = new ArrayList<>();
        Iterator<Rocket> iterator4 = rockets.iterator();
        while (iterator4.hasNext()) {
            Rocket rocket = iterator4.next();
            int rocketX = (int) (rocket.x * zoomLevel);
            int rocketY = (int) (rocket.y * zoomLevel);
            for(Tower tower:towers)
            {
                if(Math.abs(tower.getX()+5*drawTileSize-rocketX)<=(int)(2.5*drawTileSize)
                && Math.abs(tower.getY()+(int)(2.5*drawTileSize)-rocketY)<=(int)(1.5*drawTileSize)
                && tower.is_ally == false)
                {
                    rocketsToRemove3.add(rocket);
                    tower.setHp(tower.getHp()-50);
                    if(tower.getHp()<=0)
                    {
                        tower.is_ally=true;
                        tower.setHp(tower.max_hp);
                    }
                }
            }
        }
        rockets.removeAll(rocketsToRemove3);



            // Maps to track explosion time and size
        Map<Nuclear, Long> nuclearExplosionStartTimes = new HashMap<>();
        Map<Nuclear, Integer> nuclearExplosionSizes = new HashMap<>();
        List<Nuclear> nucleartoremove = new ArrayList<>();
        List<Mob> mobsToRemove3 = new ArrayList<>();
        Iterator<Nuclear> iterator6 = nuclears.iterator();
        while (iterator6.hasNext()) {
            Nuclear nuc = iterator6.next();
            int nuclearx = nuc.x;
            int nucleary = nuc.y;
            int flag = 0;
            // Calculate the distance from the screen center
            if (Math.pow(nuclearx - screenX, 2) + Math.pow(nucleary - screenY, 2) > 1000000) {
                nucleartoremove.add(nuc);
                if (!nuclearExplosionStartTimes.containsKey(nuc)) {
                    long currentTime = System.currentTimeMillis();
                    nuclearExplosionStartTimes.put(nuc, currentTime);
                }
            } else {
                boolean nuclearhit = false;
                List<Mob> mobsCopy = new ArrayList<>(mobs);

                for (Mob mob : mobsCopy) {
                    mob.contor_bomb++;
                    if(mob.contor_bomb>=FPS)
                    {
                        mob.is_bombed = false;
                        mob.contor_bomb = 0;
                    }
                    //System.out.println(mob.contor_bomb);
                    if (mob.isCollidingNuclear(nuc)) {
                        flag = 1;
                        long currentTime = System.currentTimeMillis();
                        nuclearExplosionStartTimes.put(nuc, currentTime);
                        nuclearExplosionSizes.put(nuc, drawTileSize); // Initial size

                        if(mob.contor_bomb>=FPS) {
                            mob.is_bombed = false;
                        }
                        if(mob.is_bombed == false) {
                            mob.takeDamage(100);
                            mob.contor_bomb = 0;
                            mob.is_bombed = true;
                        }
                        nucleartoremove.add(nuc);
                        nuclearhit = true;

                        for (Mob nearbyMob : mobsCopy) {
                            double distance = Math.sqrt(Math.pow(nearbyMob.x - worldX + screenX - nuc.x, 2) + Math.pow(nearbyMob.y - worldY + screenY - nuc.y, 2));
                            if (distance <= 200 && nearbyMob != mob && nearbyMob.is_bombed == false) {
                                nearbyMob.takeDamage(100);
                                nearbyMob.is_bombed = true;
                                if (nearbyMob.hp <= 0) {
                                    mobsToRemove3.add(nearbyMob);
                                    money += 1000;
                                    totalmoney += (int)(1000*value);
                                }
                            }
                        }

                        if (mob.hp <= 0) {
                            mobsToRemove3.add(mob);
                            money += 1000;
                            totalmoney += (int)(1000*value);
                        }
                        break;
                    }
                }
                if (!nuclearhit && nucleary - screenY >= 0) {
                    long currentTime = System.currentTimeMillis();
                    nuclearExplosionStartTimes.put(nuc, currentTime);
                    nuclearExplosionSizes.put(nuc, drawTileSize); // Initial size
                    nucleartoremove.add(nuc);
                    nuc.speed = 0;
                }
            }
        }


// Drawing and updating fire_ring expansion
        Iterator<Map.Entry<Nuclear, Long>> iterator = nuclearExplosionStartTimes.entrySet().iterator();
        //nuclears.removeAll(nucleartoremove);
        for (Nuclear nuc : nucleartoremove) {
            Map.Entry<Nuclear, Long> entry = iterator.next();
            long startTime = entry.getValue();
            //long elapsedTime = System.currentTimeMillis() - startTime;
            //System.out.println(elapsedTime);
            elapsedTime++;
            //System.out.println(elapsedTime);
            if (elapsedTime <= FPS) { // If less than 2 seconds have passed
                // Calculate size based on time, adjust multiplier if needed for desired speed
                int newSize = (int) (drawTileSize + (elapsedTime / 120.0) * 18 * drawTileSize); // Expanding up to 5x size
                nuclearExplosionSizes.put(nuc, newSize);

                // Adjust position to keep the fire ring centered
                drawX = screenX - (newSize - drawTileSize) / 2;
                drawY = screenY - (newSize - drawTileSize) / 2;

                // Draw the explosion at the calculated screen position
                g2.drawImage(fire_ring, drawX, drawY, newSize, newSize, null);
            } else {
                elapsedTime = 0;
                nuclears.remove(nuc);
                for(Tower tower : towers)
                {
                    tower.is_bombed=false;
                }
            }
        }
        for (Nuclear nuc : nuclears) {
            if (!nucleartoremove.contains(nuc)) {
                g2.drawImage(nuclearbomb, nuc.x, nuc.y, 3 * drawTileSize, 3 * drawTileSize, null);
            }
            //System.out.println(nuc.x);
            //System.out.println(nuc.y);
        }
        for(Tower tower : towers)
        {
           for(Nuclear nuclear2 : nuclears)
           {
               double distance = Math.sqrt(Math.pow(tower.getX()+(int)(5*drawTileSize)-worldX+ screenX - nuclear2.x,2)+
                       Math.pow(tower.getY()+(int)(2.5*drawTileSize)-worldY+screenY-nuclear2.y,2));
               if(distance < 200 && tower.is_ally == false && tower.is_bombed == false)
               {
                   tower.is_bombed=true;
                   tower.setHp(tower.getHp()-100);
                   if(tower.getHp() <= 0)
                   {
                       tower.is_ally = true;
                       tower.setHp(tower.max_hp);
                   }
               }
           }
        }
        long currentTime2 = System.currentTimeMillis(); // Timpul curent în milisecunde

        for (Boss boss : bosses) {
            for (Nuclear nuclear2 : nuclears) {
                double distance = Math.sqrt(Math.pow(boss.getX() + (int)(3 * drawTileSize) - worldX + screenX - nuclear2.x, 2) +
                        Math.pow(boss.getY() + (int)(1.5 * drawTileSize) - worldY + screenY - nuclear2.y, 2));

                if (distance < 200 && (currentTime2 - boss.lastBombedTime) >= 1500) { // Verifică dacă a trecut o secundă
                    boss.is_bombed = true; // Poți actualiza dacă mai e necesar sau să ștergi `is_bombed`
                    boss.currentHp -= 100;
                    boss.lastBombedTime = currentTime2; // Actualizează timpul ultimei bombardări
                }
            }
        }

        mobs.removeAll(mobsToRemove3);
        if (carb == true) {
            drawSpeedometer(g2, 20, 350, 200); // Coordonatele și dimensiunea

            // Desenăm turometrul
            drawTachometer(g2, 350, 350, 200); // Coordonatele și dimensiunea
            drawGearbox(g2, 615, 385, 120, 150);
            Font originalFont = g2.getFont(); // Salvează fontul original
            Font smallFont = originalFont.deriveFont(20f); // Font de 10 puncte
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setFont(smallFont); // Aplicăm fontul mic
            drawPedals(g2);
        }
        if (carb == true && keyH.accel == true) {
            if (turatie < 0) {
                turatie = 0;
                speed = 0.01;
            }

            if (turatie < max / 8)
                puteremed = (putere / 8) * (3 / 2);
            if (turatie >= 2 * max / 3)
                //puteremed=putere*(1-(turatie-2*max/3)/(2*max/3));
                puteremed = putere;
            if (turatie > max / 8 && turatie <= 2 * max / 3)
                puteremed = 3 * (putere * turatie) / (2 * max);
            acceleratie = (puteremed - k * Math.sqrt(greutate) * Math.pow(viteza, 2) - u * greutate * 9.81 * viteza) / (greutate * viteza);
            if (acceleratie > 10 && putere / greutate < 736)
                acceleratie = 10;
            if (acceleratie > 50 && putere / greutate < 736 * 5)
                acceleratie = 50;
            if (acceleratie > 200 && putere / greutate < 736 * 200)
                acceleratie = 200;
            if (acceleratie > 500 && putere / greutate < 736 * 1000)
                acceleratie = 500;
            if (acceleratie > 500)
                acceleratie = 500;

            viteza = (viteza + acceleratie / FPS);
            turatie = viteza / (raport * treapta);
            if (turatie < 800)
                turatie = 800;
            //recalculez viteza si acceleratia la 0.01 secunde
            //System.out.printf("viteza=%f putere= %f acceleratie=%f turatie=%f treapta=%d \n", viteza * 3.6, puteremed, acceleratie, turatie, treapta);
            speed = (viteza * drawTileSize / (FPS));
            if (turatie >= 3 * max / 4) {
                //printf("turatie=%lf treapta=%lf max=%lf viteza=%lf\n",turatie,treapta,max,viteza*3.6);
                //timp=timp+valoare/2;
                viteza = viteza +(-k * Math.sqrt(greutate) * Math.pow(viteza, 2) - u * greutate * 9.81 * viteza) / (greutate * viteza) * 0.3 / 2;
                turatie = turatie * treapta / (treapta + 1);
                treapta++;
            }
        }
        if (carb == true && keyH.accel == false && keyH.brake == true && speed > 0 && viteza > 0) {
            acceleratie = (-k * Math.sqrt(greutate) * Math.pow(viteza, 2) - u * greutate * 9.81 * viteza) / (greutate * viteza) - 10;
            viteza = (viteza + acceleratie / FPS);
            turatie = viteza / (raport * treapta);
            //System.out.printf("viteza=%f putere= %f acceleratie=%f turatie=%f treapta=%d \n", viteza * 3.6, puteremed, acceleratie, turatie, treapta);
            speed = (viteza * drawTileSize / (FPS));
            if (turatie < 0) {
                turatie = 0;
                speed = 0.0;
            }
            if (turatie < 800)
                turatie = 800;
            if (speed < 0)
                speed = 0;
            if (turatie <= max / 4 && treapta > 1) {
                viteza = viteza - acceleratie * 0.3 / 2;
                turatie = turatie * treapta / (treapta - 1);
                treapta--;
            }
        }
        if (carb == true && keyH.accel == false && keyH.brake == false && speed > 0 && viteza > 0) {
            acceleratie = (-k * Math.sqrt(greutate) * Math.pow(viteza, 2) - u * greutate * 9.81 * viteza) / (greutate * viteza);
            viteza = (viteza + acceleratie / FPS);
            turatie = viteza / (raport * treapta);
            //System.out.printf("viteza=%f putere= %f acceleratie=%f turatie=%f treapta=%d \n", viteza * 3.6, puteremed, acceleratie, turatie, treapta);
            speed = (viteza * drawTileSize / (FPS));
            if (turatie < 0) {
                turatie = 0;
                speed = 0.0;
            }
            if (turatie < 800)
                turatie = 800;
            if (speed < 0)
                speed = 0;
            if (turatie <= max / 4 && treapta > 1) {
                viteza = viteza - acceleratie * 0.3 / 2;
                turatie = turatie * treapta / (treapta - 1);
                treapta--;
            }
        }
        List<Boss> bosstoeliminate= new ArrayList<Boss>();
        for(Boss boss:bosses)
        {
            if(boss.currentHp<=0 && boss.bossdying<66 && isPaused == false)
            {
                boss.bossdying++;
                int animation = boss.bossdying/3+1;
                BufferedImage image3= null;
                switch (animation)
                {
                    case 1:
                        image3 = demon_death1;
                        break;
                    case 2:
                        image3 = demon_death2;
                        break;
                    case 3:
                        image3 = demon_death3;
                        break;
                    case 4:
                        image3 = demon_death4;
                        break;
                    case 5:
                        image3 = demon_death5;
                        break;
                    case 6:
                        image3 = demon_death6;
                        break;
                    case 7:
                        image3 = demon_death7;
                        break;
                    case 8:
                        image3 = demon_death8;
                        break;
                    case 9:
                        image3 = demon_death9;
                        break;
                    case 10:
                        image3 = demon_death10;
                        break;
                    case 11:
                        image3 = demon_death11;
                        break;
                    case 12:
                        image3 = demon_death12;
                        break;
                    case 13:
                        image3 = demon_death13;
                        break;
                    case 14:
                        image3 = demon_death14;
                        break;
                    case 15:
                        image3 = demon_death15;
                        break;
                    case 16:
                        image3 = demon_death16;
                        break;
                    case 17:
                        image3 = demon_death17;
                        break;
                    case 18:
                        image3 = demon_death18;
                        break;
                        case 19:
                    image3 = demon_death19;
                    break;
                    case 20:
                        image3 = demon_death20;
                        break;
                    case 21:
                        image3 = demon_death21;
                        break;
                    case 22:
                        image3 = demon_death22;
                        break;


                }
                g2.drawImage(image3, boss.x-worldX+screenX-4*drawTileSize, boss.y-worldY+screenY-(int)(3.5*drawTileSize), 10 * drawTileSize, 7 * drawTileSize, null);
            }
            if(boss.currentHp<=0 && boss.bossdying>=66)
            {
                bosstoeliminate.add(boss);
                money = money + bossvalue;
                totalmoney = totalmoney + (int)(bossvalue*value);
                bossvalue = bossvalue+2500;
            }
        }
        bosses.removeAll(bosstoeliminate);
        if (viteza < 0)
            viteza = 0.0001;
        if (carb == false)
            speed = initial_speed;
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString("Bullets: " + copie_nr_bullets + "/" + MAGAZINE_SIZE, 10, 20);
        g2.drawString("Rockets: " + rocketsinMagazine + "/" + ROCKET_SIZE, 10, 60);
        g2.drawString("Money: " + money + "$", 250, 20);
        g2.drawString("Nuclear bombs:" + NuclearinMagazine + "/" + Nuclear_Size, 10, 80);
        int a = 250;
        int b = 60;
        List<Mob> mobtoeliminate = new ArrayList<Mob>();
        long currentTime = System.currentTimeMillis(); // Obține timpul curent
        List<Bullet> towerBullettoeliminate = new ArrayList<Bullet>();
        if(isPaused == false) {
            for (Bullet bullet : tower_bullets) {
                if (bullet.targetTower != null && (bullet.targetTower.is_ally == false || bullet.tower.is_ally == false)) {
                    //System.out.printf("%d %d\n",bullet.targetTower.getX(),bullet.targetTower.getY());
                    double distanceX = bullet.targetTower.getX() + (int) (5 * drawTileSize) - bullet.x;
                    double distanceY = bullet.targetTower.getY() + (int) (2.5 * drawTileSize) - bullet.y;
                    double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                    // Normalizează vectorul direcției
                    double directionX = distanceX / totalDistance;
                    double directionY = distanceY / totalDistance;

                    // Viteza glonțului
                    double speed = 20; // Poți ajusta viteza după cum dorești

                    // Actualizează poziția glonțului
                    bullet.x += directionX * speed;
                    bullet.y += directionY * speed;
                    if (totalDistance < speed) {
                        // Glonțul a lovit ținta, aplică daune și eventual marchează mob-ul ca eliminat
                        bullet.targetTower.setHp(bullet.targetTower.getHp() - bullet.tower.getDamage());
                        // Dacă mob-ul este eliminat, poți adăuga logică pentru eliminarea lui
                        if (bullet.targetTower.getHp() <= 0 && bullet.targetTower.getHp() > -bullet.tower.getDamage()) {
                            bullet.targetTower.is_ally = !bullet.targetTower.is_ally;
                            bullet.targetTower.setHp(bullet.targetTower.max_hp);
                        }

                        // Marchează glonțul pentru eliminare (poți adăuga o listă separată pentru eliminarea glonțului)
                        towerBullettoeliminate.add(bullet);
                    }

                }
                if (bullet.targetMob != null && bullet.targetTower == null) { //daca dau target unui mob
                    double distanceX = bullet.targetMob.x - bullet.x;
                    double distanceY = bullet.targetMob.y - bullet.y;

                    // Calculează distanța totală către țintă (folosește teorema lui Pitagora)
                    double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                    // Normalizează vectorul direcției
                    double directionX = distanceX / totalDistance;
                    double directionY = distanceY / totalDistance;

                    // Viteza glonțului
                    double speed = 20; // Poți ajusta viteza după cum dorești

                    // Actualizează poziția glonțului
                    bullet.x += directionX * speed;
                    bullet.y += directionY * speed;
                    if (totalDistance < speed) {
                        // Glonțul a lovit ținta, aplică daune și eventual marchează mob-ul ca eliminat
                        bullet.targetMob.takeDamage(bullet.tower.getDamage());

                        // Dacă mob-ul este eliminat, poți adăuga logică pentru eliminarea lui
                        if (bullet.targetMob.hp <= 0 && bullet.targetMob.hp > -bullet.tower.getDamage()) {
                            mobs.remove(bullet.targetMob); // sau folosește o listă pentru eliminări întârziate
                            money = money + 1000;
                            totalmoney = totalmoney + (int)(1000*value);
                        }

                        // Marchează glonțul pentru eliminare (poți adăuga o listă separată pentru eliminarea glonțului)
                        towerBullettoeliminate.add(bullet);
                    }
                }
                if (bullet.targetMob == null && bullet.targetBoss == null && bullet.targetTower == null) //daca turnul este inamic si trage in mine (player)
                {
                    double distanceX = worldX - bullet.x;
                    double distanceY = worldY - bullet.y;

                    // Calculează distanța totală către țintă (folosește teorema lui Pitagora)
                    double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                    // Normalizează vectorul direcției
                    double directionX = distanceX / totalDistance;
                    double directionY = distanceY / totalDistance;

                    // Viteza glonțului
                    double speed = 10; // Poți ajusta viteza după cum dorești

                    // Actualizează poziția glonțului
                    bullet.x += directionX * speed;
                    bullet.y += directionY * speed;
                    if (totalDistance < speed) {
                        // Glonțul a lovit ținta, aplică daune și eventual marchează mob-ul ca eliminat
                        hp = hp - towers.getFirst().getDamage();
                        if (hp < 0) {
                            hp = 0;
                        }
                        towerBullettoeliminate.add(bullet);
                    }
                }
                if (bullet.targetMob == null && bullet.targetBoss == null && bullet.targetTower != null && bullet.tower.is_ally == true) //daca trage in tower
                {
                    if (bullet.targetTower.getHp() <= 0 || bullet.targetTower.is_ally == true) {
                        towerBullettoeliminate.add(bullet);
                    }
                }
                if (bullet.targetMob == null && bullet.targetBoss != null) //daca trage in boss
                {
                    double distancex = bullet.targetBoss.x + (int) (0.5 * drawTileSize) - bullet.x;
                    double distancey = bullet.targetBoss.y + (int) (0.5 * drawTileSize) - bullet.y;
                    double totalDistance = Math.sqrt(distancex * distancex + distancey * distancey);
                    double directionX = distancex / totalDistance;
                    double directionY = distancey / totalDistance;
                    double speed = 20; // Poți ajusta viteza după cum dorești
                    bullet.x += directionX * speed;
                    bullet.y += directionY * speed;
                    if (totalDistance < speed) {
                        // Glonțul a lovit ținta, aplică daune și eventual marchează mob-ul ca eliminat
                        bullet.targetBoss.currentHp = bullet.targetBoss.currentHp - towers.getFirst().getDamage();
                        if (hp < 0) {
                            hp = 0;
                        }
                        towerBullettoeliminate.add(bullet);
                    }
                    // System.out.println("Trece");

                }
                int towerWorldX = bullet.x;
                int towerWorldY = bullet.y;
                // Calculează poziția turnului pe ecran în raport cu jucătorul
                int screenX = towerWorldX - worldX + gp.player.screenX;
                int screenY = towerWorldY - worldY + gp.player.screenY;

                // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                if (screenX < -500) {
                    screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                } else if (screenX > gp.screenWidth + 900) {
                    screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                }

                // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                if (screenY < -500) {
                    screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                } else if (screenY > gp.screenHeight + 900) {
                    screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                }

                // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                // System.out.printf("%d %d\n",screenX,screenY);
                if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900)
                    g2.drawImage(bulletImage, screenX, screenY, drawTileSize, drawTileSize, null);
            }
        }
        tower_bullets.removeAll(towerBullettoeliminate);
        //System.out.println(towers.getFirst().getX());
        for (Tower tower : towers) {
            // Desenăm turnul
            if (tower.is_ally == true) {
                // Calculează poziția turnului în coordonate mondiale
                int towerWorldX = tower.getX();
                int towerWorldY = tower.getY();

                // Calculează poziția turnului pe ecran în raport cu jucătorul
                int screenX = towerWorldX - worldX + gp.player.screenX;
                int screenY = towerWorldY - worldY + gp.player.screenY;
                //System.out.printf("%d %d\n",gp.player.screenX,gp.player.screenY);
                // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                if (screenX < -500) {
                    screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                } else if (screenX > gp.screenWidth+900) {
                    screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                }

                // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                if (screenY < -500) {
                    screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                } else if (screenY > gp.screenHeight+900) {
                    screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                }

                // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                //System.out.printf("%d %d\n",screenX,screenY);
                if ( screenX < gp.screenWidth + 1500 && screenY < gp.screenHeight + 1500) {

                    // Desenăm defensiva pe ecran
                    g2.drawImage(defense, screenX, screenY, 10 * drawTileSize, 5 * drawTileSize, null);
                }
            }
            else {
                if (tower.is_ally == false) {
                    // Calculează poziția turnului în coordonate mondiale
                    int towerWorldX = tower.getX();
                    int towerWorldY = tower.getY();

                    // Calculează poziția turnului pe ecran în raport cu jucătorul
                    int screenX = towerWorldX - worldX + gp.player.screenX;
                    int screenY = towerWorldY - worldY + gp.player.screenY;

                    // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
                    if (screenX < -500) {
                        screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
                    } else if (screenX > gp.screenWidth + 900) {
                        screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
                    }

                    // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
                    if (screenY < -500) {
                        screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
                    } else if (screenY > gp.screenHeight + 900) {
                        screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
                    }

                    // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
                    if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {

                        // Desenăm defensiva pe ecran
                        g2.drawImage(defense_red, screenX, screenY, 10 * drawTileSize, 5 * drawTileSize, null);
                    }
                }
            }
            // tower.setAttack_speed(100);
            int radius = (int) tower.getRange(); // Raza cercului
            int diameter = 2 * radius; // Diametrul cercului

            // Calculăm coordonatele cercului (centrat pe turn)
            int centerX = tower.getX() - worldX + screenX + (10 * drawTileSize / 2) - radius;
            int centerY = tower.getY() - worldY + screenY + (5 * drawTileSize / 2) - radius;
            // Setăm culoarea cercului și desenăm cercul
            if(tower.is_ally == true)
                g2.setColor(Color.green);
            else
                g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            if (centerX < -500) {
                centerX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
            } else if (screenX > gp.screenWidth + 1500) {
                centerX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
            }

            // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
            if (centerY < -500) {
                centerY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
            } else if (screenY > gp.screenHeight + 1500) {
                centerY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
            }
            //System.out.printf("%d %d\n",centerX,centerY);
            if ( centerX < gp.screenWidth + 2000 && centerY < gp.screenHeight + 2000) {
                g2.drawOval(centerX, centerY, diameter, diameter);
            }
            g2.setStroke(new BasicStroke(1));

            double hpPercentage = (double) tower.getHp() /tower.max_hp; // Considerăm că HP maxim e 100

            // Determinăm culoarea barei de viață în funcție de procentaj
            Color hpColor;
            if (hpPercentage > 0.7) {
                hpColor = Color.GREEN;  // HP mare
            } else if (hpPercentage > 0.3) {
                hpColor = Color.YELLOW; // HP mediu
            } else {
                hpColor = Color.RED;    // HP mic
            }

            // Desenăm bara de viață deasupra turnului
            int barWidth = (int) (hpPercentage * 100); // Lățimea barei de viață, 100 pixeli fiind lățimea maximă
            int barHeight = 5; // Înălțimea barei de viață
            int towerWorldX = tower.getX();
            int towerWorldY = tower.getY();

            // Calculează poziția turnului pe ecran în raport cu jucătorul
            int screenX = towerWorldX - worldX + gp.player.screenX;
            int screenY = towerWorldY - worldY + gp.player.screenY;

            // Dacă turnul este aproape de marginea stângă sau dreaptă, repetă-l pe cealaltă parte
            if (screenX < -500) {
                screenX += gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în dreapta)
            } else if (screenX > gp.screenWidth + 900) {
                screenX -= gp.maxWorldCol * gp.tilesize;  // Repetare pe axa X (mutare în stânga)
            }

            // Dacă turnul este aproape de marginea de sus sau de jos, repetă-l pe cealaltă parte
            if (screenY < -500) {
                screenY += gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în jos)
            } else if (screenY > gp.screenHeight + 900) {
                screenY -= gp.maxWorldRow * gp.tilesize;  // Repetare pe axa Y (mutare în sus)
            }
            int barX = screenX + (10 * drawTileSize / 2) - 50; // Centrat deasupra turnului
            int barY = screenY+10; // La 10 pixeli deasupra turnului

            // Desenăm fundalul barei (un dreptunghi gri pentru fundalul barei de viață)

            // Desenăm defensiva doar dacă este vizibilă pe ecran (după wrapping)
            if ( screenX < gp.screenWidth + 900 && screenY < gp.screenHeight + 900) {
                g2.setColor(Color.GRAY);
                g2.fillRect(barX, barY, 100, barHeight);

                // Desenăm bara de viață cu culoarea corespunzătoare
                g2.setColor(hpColor);
                g2.fillRect(barX, barY, barWidth, barHeight);
                // Desenăm defensiva pe ecran
                //g2.drawImage(defense_red, screenX, screenY, 10 * drawTileSize, 5 * drawTileSize, null);
            }
            // Poziționarea barei deasupra turnului
            g2.setColor(white);
            Mob targetMob = null;
            Boss targetBoss = null;
            Tower targetTower = null;
            double shortestDistance = Double.MAX_VALUE;
            double turn_inamic = Double.MAX_VALUE;
            // Găsește cel mai apropiat mob în raza turnului
            try {
                int flag = 0;
                for(Tower tower2 : towers) //verific printre towerele aliate daca exista towere inamice
                {
                    double distance = Math.sqrt(Math.pow(tower.getX() - tower2.getX(), 2) + Math.pow(tower.getY()
                            - tower2.getY(), 2));
                    if(tower2!=tower && distance<= tower.getRange() && tower.is_ally== true && tower2.is_ally == false)
                    {
                       // System.out.println(distance);
                        targetTower = tower2;
                        flag = 1;
                        if(distance<=tower2.getRange())
                        {
                            if (currentTime - tower.getLastAttackTime() >= tower2.getAttack_speed()) {
                                Bullet bul = new Bullet(tower2.getX() + (int) (5 * drawTileSize), tower2.getY() + (int) (2.5 * drawTileSize), "down", bulletImage);
                                bul.tower = tower2;
                                bul.targetTower = tower;
                                bul.targetBoss = null;
                                bul.targetMob = null;
                                tower_bullets.add(bul);
                                //System.out.println(bul.tower.getX());
                            }
                        }
                        break;
                    }
                }
                if(flag == 0) {
                    for (Mob mob : mobs) {
                        // Calculating the distance between the tower and the mob
                        double distance = Math.sqrt(Math.pow(tower.getX() + 5 * drawTileSize - mob.x, 2) +
                                Math.pow(tower.getY() + (int) (2.5 * drawTileSize) - mob.y, 2));

                        // Check if the mob is in range, the closest, and alive
                        if (distance < tower.getRange() && distance < shortestDistance && mob.hp > 0 && tower.is_ally == true) {
                            shortestDistance = distance;
                            targetMob = mob;
                        }
                    }
                    for (Boss boss : bosses) {
                        double distance = Math.sqrt(Math.pow(tower.getX() + 5 * drawTileSize - boss.x, 2) +
                                Math.pow(tower.getY() + (int) (2.5 * drawTileSize) - boss.y, 2));
                        if (distance < tower.getRange() && distance < shortestDistance && boss.currentHp > 0 && tower.is_ally == true) {
                            shortestDistance = distance;
                            targetMob = null;
                            targetBoss = boss;
                        }
                    }
                    for(Tower tower2 : towers) //verific printre towerele aliate daca exista towere inamice
                    {
                        double distance = Math.sqrt(Math.pow(tower.getX() - tower2.getX(), 2) + Math.pow(tower.getY()
                                - tower2.getY(), 2));
                        if(tower2!=tower && distance<= tower.getRange() && tower.is_ally== true && tower2.is_ally == false)
                        {
                          //  System.out.println(distance);
                            targetTower = tower;
                            flag = 1;
                            break;
                        }
                    }
                }
            } catch (NullPointerException e) {
            } catch (Exception e) {
            }
           // Player player2=this.gp.player;
            if(tower.is_ally == false)
            {
                //mobs.add(new Mob(worldX,worldY,final_speed,this));
                //mobtoeliminate.add(mobs.getLast());
                double distance = Math.sqrt(Math.pow(tower.getX() + 5 * drawTileSize - worldX, 2) + Math.pow(tower.getY() +
                        (int) (2.5 * drawTileSize) - worldY, 2));
                //System.out.println(distance);
                //System.out.println(tower.getRange());
                if (distance < tower.getRange() && tower.is_ally == false)
                {
                    targetMob = null;
                    turn_inamic = distance;
                }
                //System.out.println(turn_inamic);
            }

            // Dacă există un mob în raza turnului
            if(targetTower!=null)
            {
                if (currentTime - tower.getLastAttackTime() >= tower.getAttack_speed()) {
                    // Atacă towerul-ul țintă
                    //targetMob.takeDamage(tower.getDamage());
                    tower_bullets.add(new Bullet(tower.getX() + 5 * drawTileSize, tower.getY() + (int) (1.5 * drawTileSize), "down", bulletImage));
                    Bullet bul = tower_bullets.getLast();
                    bul.tower = tower;
                    bul.targetTower = targetTower;
                    bul.targetMob = null;
                    if (targetTower.getHp() <= 0) {
                       targetTower.is_ally = !targetTower.is_ally;
                       targetTower.setHp(targetTower.max_hp);
                        //money = money + 1000;
                    }
                    // Actualizează timpul ultimului atac
                    tower.setLastAttackTime(currentTime);
                }
            }
            if (targetMob != null && tower.is_ally == true && targetBoss == null) {
                // Verifică dacă a trecut 1 secundă de la ultimul atac
                if (currentTime - tower.getLastAttackTime() >= tower.getAttack_speed()) {
                    // Atacă mob-ul țintă
                    //targetMob.takeDamage(tower.getDamage());
                    tower_bullets.add(new Bullet(tower.getX() + 5 * drawTileSize, tower.getY() + (int) (1.5 * drawTileSize), "down", bulletImage));
                    Bullet bul = tower_bullets.getLast();
                    bul.tower = tower;
                    bul.targetMob = targetMob;
                    if (targetMob.hp <= 0) {
                        mobtoeliminate.add(targetMob);
                        //money = money + 1000;
                    }
                    // Actualizează timpul ultimului atac
                    tower.setLastAttackTime(currentTime);
                }
            }
            if(targetMob == null && tower.is_ally == false && turn_inamic<tower.getRange())
            {
                if (currentTime - tower.getLastAttackTime() >= tower.getAttack_speed()) {
                    tower_bullets.add(new Bullet(tower.getX() + 5 * drawTileSize, tower.getY() + (int) (1.5 * drawTileSize), "down", bulletImage));
                    Bullet bul = tower_bullets.getLast();
                    bul.targetMob = null;
                    tower.setLastAttackTime(currentTime);
                }
            }
            if(tower.is_ally == true && targetMob == null && targetBoss!=null)
            {
                // Verifică dacă a trecut 1 secundă de la ultimul atac
                if (currentTime - tower.getLastAttackTime() >= tower.getAttack_speed()) {
                    // Atacă mob-ul țintă
                    //targetMob.takeDamage(tower.getDamage());
                    tower_bullets.add(new Bullet(tower.getX() + 5 * drawTileSize, tower.getY() + (int) (1.5 * drawTileSize), "down", bulletImage));
                    Bullet bul = tower_bullets.getLast();
                    bul.targetBoss = targetBoss;
                    bul.targetMob= null;
                    // Actualizează timpul ultimului atac
                    tower.setLastAttackTime(currentTime);
                }
            }
        }
        //System.out.println(mobtoeliminate);
        // money = money + mobtoeliminate.size()*1000;
        mobs.removeAll(mobtoeliminate);

        g2.drawString("Score: " + totalmoney + " (x" + value + ")",a,b-20);
        for (Map.Entry<String, Integer> entry : messageFrequency.entrySet()) {
            String message = entry.getKey();
            int frequency = entry.getValue();

            // Mesaj inițial de afișat (mesaj + frecvență)
            String messageToDisplay = message + " (x" + frequency + ")";

            double percentage = 0;
            String percentageDisplay = "";

            // Pattern pentru extragerea numărului de după '+' sau '-'
            Pattern pattern = Pattern.compile("([+-])(\\d+\\.?\\d*)%");
            Matcher matcher = pattern.matcher(message);
            double modifier = 1.0; // Valoare implicită
            if (message.contains("Money")) {
                messageToDisplay += String.format(" %d", 5000 * frequency);
                // System.out.println("Trece");
            } else {
                if (matcher.find()) {
                    String sign = matcher.group(1);  // Extrage semnul '+' sau '-'
                    double value = Double.parseDouble(matcher.group(2)); // Extrage numărul după semn
                    //System.out.println(value);
                    //System.out.println(sign);
                    // Conversie la factor multiplicator (ex: +30% -> 1.3, -20% -> 0.8)
                    modifier = (sign.equals("+")) ? 1.0 + value / 100 : 1.0 - value / 100;
                    //System.out.println(1.0+value/100);
                }

                if (message.contains("+")) {
                    // Calcul pentru buff (+)
                    //percentage = 100 * Math.pow(modifier, frequency) - 100;
                    percentage =100 * (modifier-1.0)*frequency;
                    messageToDisplay += " + ";
                    messageToDisplay += String.format(" %.2f%%", percentage);

                } else if (message.contains("-")) {
                    // Calcul pentru nerf (-)
                    percentage = 100 - 100 * Math.pow(modifier, frequency);
                    messageToDisplay += " - ";
                    messageToDisplay += String.format(" %.2f%%", percentage);
                }
            }

            // Adăugăm procentul la mesajul final de afișat

            // Desenează mesajul cu procentul pe ecran
            if(flag_desen_upgradeuri == false) {
                g2.drawString(messageToDisplay, a, b); // Desenează mesajul la coordonatele (a, b)
            }

            // Mărește coordonata Y pentru următorul mesaj
            b += 20;
        }
        if (carb == true) {
            g2.drawString("Speed: " + String.format("%.3f", speed * FPS / drawTileSize * 3.6) + " km/h", 10, 100);
        }
        if (reloading) {
            g2.drawString("Reloading...", 10, 40);
        }
        int buttonX = (int) ((gp.screenWidth - 100) * zoomLevel);
        int buttonY = (int) ((gp.screenHeight - 50) * zoomLevel);
        int buttonWidth = (int) (80 * zoomLevel);
        int buttonHeight = (int) (30 * zoomLevel);

        g2.setColor(Color.GREEN);
        g2.fillRect(buttonX, buttonY, buttonWidth, buttonHeight);
        g2.setColor(Color.WHITE);
        g2.drawString("Shop", buttonX + 25, buttonY + 20);
        String coordinates = "X: " + gp.player.worldX + " Y: " + gp.player.worldY;
        FontMetrics fm2 = g2.getFontMetrics();
        String dif = selectedDifficulty;
        int stringWidth2 = fm2.stringWidth(dif);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        gamepanel. drawPauseSymbol(g2);
        // Get the width of the string to position it correctly
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(coordinates);

        // Position the string in the top right corner
        int x = gp.screenWidth - stringWidth - 10;  // 10 pixels padding from the right
        int y = 100;  // 20 pixels from the top
        g2.drawString(coordinates, x, y);
        x = gp.screenWidth - stringWidth2 - 90;
        g2.drawString("Difficulty:" + dif,x,120);
        // Configurări pentru bara de HP
        int hpBarWidth = 200;
        int hpBarHeight = 20;
        int hpBarX = gp.screenWidth - hpBarWidth - 10;  // 10 pixeli de la marginea dreaptă
        int hpBarY = 60;  // Sub textul coordonatelor

        // Determină culoarea barei de HP în funcție de procentul de viață
        float hpPercentage = (float) gp.player.hp / gp.player.maxHp;
        Color hpColor;
        if (hpPercentage > 0.5) {
            hpColor = Color.GREEN;
        } else if (hpPercentage > 0.25) {
            hpColor = Color.YELLOW;
        } else {
            hpColor = Color.RED;
        }

        // Desenează fundalul barei de HP
        g2.setColor(Color.GRAY);
        g2.fillRect(hpBarX, hpBarY, hpBarWidth, hpBarHeight);

        // Desenează bara de HP propriu-zisă
        g2.setColor(hpColor);
        g2.fillRect(hpBarX, hpBarY, (int) (hpBarWidth * hpPercentage), hpBarHeight);

        // Desenează marginea barei de HP
        g2.setColor(Color.WHITE);
        g2.drawRect(hpBarX, hpBarY, hpBarWidth, hpBarHeight);

        // Desenează textul HP
        String hpText = gp.player.hp + "/" + gp.player.maxHp;
        int hpTextWidth = fm.stringWidth(hpText);
        int hpTextX = hpBarX + (hpBarWidth - hpTextWidth) / 2;
        int hpTextY = hpBarY + hpBarHeight - 5;  // Ajustează pentru centrul vertical
        g2.drawString(hpText, hpTextX, hpTextY);
        //g2.draw((Shape) zoomSlider);
        //gp.add(shopButton);
        //gp.add(zoomSlider);
        }

    private void drawSpeedometer(Graphics2D g2, int x, int y, int size) {
        g2.setStroke(new BasicStroke(3));
        Font originalFont = g2.getFont();
        Font smallFont = originalFont.deriveFont(15f);
        g2.setFont(smallFont);
        g2.setColor(Color.BLACK);
        g2.drawArc(x, y, size, size, 135, 270);
        for (int i = 0; i <= 10; i++) {
            double angle = Math.toRadians(135 + i * 27);
            int x1 = (int) (x + size / 2 + (size / 2 - 10) * Math.cos(angle));
            int y1 = (int) (y + size / 2 + (size / 2 - 10) * Math.sin(angle));
            int x2 = (int) (x + size / 2 + (size / 2 - 20) * Math.cos(angle));
            int y2 = (int) (y + size / 2 + (size / 2 - 20) * Math.sin(angle));
            g2.drawLine(x1, y1, x2, y2);
            int speedValue = i * (int) (maximum_speed*3.6/10);
            int textX = (int) (x + size / 2 + (size / 2 - 35) * Math.cos(angle));
            int textY = (int) (y + size / 2 + (size / 2 - 35) * Math.sin(angle));
            g2.drawString(String.valueOf(speedValue), textX - 10, textY + 5);
        }
        double maxSpeed = maximum_speed*4/5;
        double speedAngle = Math.toRadians(135 + ((speed / maxSpeed) * 270)*2/3); // Ajustăm factorul la 265 în loc de 270
        int x3 = (int) (x + size / 2 + (size / 2 - 30) * Math.cos(speedAngle));
        int y3 = (int) (y + size / 2 + (size / 2 - 30) * Math.sin(speedAngle));
        g2.setColor(Color.RED);
        g2.drawLine(x + size / 2, y + size / 2, x3, y3);
        g2.setFont(originalFont);
        Font gearFont = originalFont.deriveFont(Font.BOLD, 30f);
        g2.setFont(gearFont);
        g2.setColor(Color.BLACK);
        int gearX = x + size / 2 - 10; // Ajustăm poziția X pentru centrare
        int gearY = y + size / 2 + 30; // Ajustăm poziția Y pentru centrare
        g2.drawString(String.valueOf(treapta), gearX, gearY);
        g2.setFont(originalFont);
    }
    private void drawGearbox(Graphics2D g2, int x, int y, int width, int height) {
        // Desenăm conturul cutiei de viteze
        g2.setColor(Color.BLACK);

        // Definim padding și radius pentru gear-uri
        int padding = 40;
        int gearRadius = 15;

        // Definim pozițiile treptelor
        int rX = x - width * 1 / 6;
        int rY = y + padding;

        int gear1X = x + width * 1 / 6;
        int gear1Y = y + padding;

        int gear3X = x + width * 3 / 6;
        int gear3Y = y  + padding;

        int gear5X = x + width * 5 / 6;  // Modificat pentru a fi în dreapta sus
        int gear5Y = y + padding;        // Modificat pentru a fi în dreapta sus

        int gear2X = x + width * 1 / 6;
        int gear2Y = y + height - padding;

        int gear4X = x + width * 3 / 6;
        int gear4Y = y + height - padding;

        // Desenăm gear-urile ca cercuri
        g2.fillOval(rX - gearRadius, rY - gearRadius, gearRadius * 2, gearRadius * 2);
        g2.fillOval(gear1X - gearRadius, gear1Y - gearRadius, gearRadius * 2, gearRadius * 2);
        g2.fillOval(gear2X - gearRadius, gear2Y - gearRadius, gearRadius * 2, gearRadius * 2);
        g2.fillOval(gear3X - gearRadius, gear3Y - gearRadius, gearRadius * 2, gearRadius * 2);
        g2.fillOval(gear4X - gearRadius, gear4Y - gearRadius, gearRadius * 2, gearRadius * 2);
        g2.fillOval(gear5X - gearRadius, gear5Y - gearRadius, gearRadius * 2, gearRadius * 2);

        // Desenăm etichetele pentru gear-uri
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("R", rX - 4, rY + 4);
        g2.drawString("1", gear1X - 4, gear1Y + 4);
        g2.drawString("2", gear2X - 4, gear2Y + 4);
        g2.drawString("3", gear3X - 4, gear3Y + 4);
        g2.drawString("4", gear4X - 4, gear4Y + 4);
        g2.drawString("5", gear5X - 4, gear5Y + 4);

        // Desenăm liniile de shift (conectăm gear-urile)
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(3));
        // Trasee de shift:
        g2.drawLine(rX, rY + 15, rX, (gear1Y+gear2Y)/2); // R -> jos
        g2.drawLine(gear1X, gear1Y + 15, gear1X, (gear2Y+gear1Y)/2); // 1 ->jos
        g2.drawLine(gear3X,gear3Y + 15,gear3X, (gear1Y+gear2Y)/2); // 3 -> jos
        g2.drawLine(gear5X, gear5Y + 15, gear5X,(gear1Y+gear2Y)/2); // 5 ->jos
        g2.drawLine(rX,(gear1Y+gear2Y)/2, gear5X,(gear1Y+gear2Y)/2); // linia transversala
        g2.drawLine(gear2X,gear2Y - 15,gear2X,(gear1Y+gear2Y)/2); //de la 2 la linie
        g2.drawLine(gear4X,gear4Y - 15,gear4X,(gear1Y+gear2Y)/2); //de la 2 la linie


        // Desenăm maneta în poziția treptei curente
        int manetaX = gear3X;
        int manetaY = gear3Y;

        switch (treapta) {
            case 1:
                manetaX = gear1X;
                manetaY = gear1Y;
                break;
            case 2:
                manetaX = gear2X;
                manetaY = gear2Y;
                break;
            case 3:
                manetaX = gear3X;
                manetaY = gear3Y;
                break;
            case 4:
                manetaX = gear4X;
                manetaY = gear4Y;
                break;
            case 5:
                manetaX = gear5X;
                manetaY = gear5Y;
                break;
            case 6: // Marșarier (R)
                manetaX = rX;
                manetaY = rY;
                break;
            default:
                break;
        }

        // Desenăm maneta
        g2.setColor(Color.RED);
        int manetaRadius = 7;
        g2.fillOval(manetaX - manetaRadius, manetaY - manetaRadius, manetaRadius * 2, manetaRadius * 2);
    }





    private void drawTachometer(Graphics2D g2, int x, int y, int size) {
        // Setăm grosimea cadranului
        g2.setStroke(new BasicStroke(3)); // Grosimea liniei de 3 pixeli
        Font originalFont = g2.getFont(); // Salvează fontul original
        Font smallFont = originalFont.deriveFont(15f); // Font de 10 puncte
        g2.setFont(smallFont); // Aplicăm fontul mic

        // Desenăm cadranul
        g2.setColor(Color.BLACK);
        g2.drawArc(x, y, size, size, 135, 270); // Cadran de 270 de grade de la 135° la 45°

        // Desenăm marcajele
        for (int i = 0; i <= 10; i++) {
            double angle = Math.toRadians(135 + i * 27); // Interval de 27 de grade pentru 10 marcaje
            int x1 = (int) (x + size / 2 + (size / 2 - 10) * Math.cos(angle));
            int y1 = (int) (y + size / 2 + (size / 2 - 10) * Math.sin(angle));
            int x2 = (int) (x + size / 2 + (size / 2 - 20) * Math.cos(angle));
            int y2 = (int) (y + size / 2 + (size / 2 - 20) * Math.sin(angle));
            g2.drawLine(x1, y1, x2, y2);
            int rpmValue = i * 800; // Notări de la 0 la 8000 RPM
            int textX = (int) (x + size / 2 + (size / 2 - 35) * Math.cos(angle));
            int textY = (int) (y + size / 2 + (size / 2 - 35) * Math.sin(angle));
            g2.drawString(String.valueOf(rpmValue), textX - 10, textY + 5); // Ajustare pentru centrul textului
        }

        // Calculăm unghiul pentru limba turometrului
        int turX = x + size / 2 - 25; // Ajustăm poziția X pentru centrare
        int turY = y + size / 2 + 30; // Ajustăm poziția Y pentru centrare
        Font bigFont = originalFont.deriveFont(25f); // Font de 10 puncte
        g2.setFont(bigFont); // Aplicăm fontul mic
        g2.drawString(String.valueOf((int )turatie), turX, turY);
        double rpmAngle = Math.toRadians(135 + (turatie / 8000.0) * 270); // 8000 rot/min turația maximă
        int x3 = (int) (x + size / 2 + (size / 2 - 30) * Math.cos(rpmAngle));
        int y3 = (int) (y + size / 2 + (size / 2 - 30) * Math.sin(rpmAngle));

        // Desenăm limba turometrului
        g2.setColor(Color.BLUE);
        g2.drawLine(x + size / 2, y + size / 2, x3, y3);
    }
    public void drawPedals(Graphics2D g2) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
               // System.out.println(x);
               // System.out.println("Trece");
                if (Player.pedalAcceleratie.contains(x, y)) {
                    keyH.accel = true;
                    keyH.brake = false; // Dezactivează frâna
                }
                if (Player.pedalFrana.contains(x, y)) {
                    keyH.brake = true;
                    keyH.accel = false; // Dezactivează accelerația
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                keyH.accel = false;
                keyH.brake = false;
            }
        });
        // Definește pozițiile și dimensiunile pedalelor
        int pedalWidth = 40;
        int pedalHeight = 60;
        int pedalSpacing = 20; // Spațiu între pedale
        // Poziționează pedalele pe ecran (jos, centru)
        int baseX = gp.screenWidth / 2 - pedalWidth - pedalSpacing / 2 - 100;
        int baseY = gp.screenHeight - pedalHeight - 50;

        // Definește rectangluri pentru pedale
        pedalAcceleratie = new Rectangle(baseX + pedalWidth + pedalSpacing, baseY, pedalWidth, pedalHeight);
        pedalFrana = new Rectangle(baseX, baseY, pedalWidth, pedalHeight);

        // Desenează pedala de acceleratie
        if (keyH.accel) {
            g2.setColor(Color.GREEN); // Schimbă culoarea când este apăsată
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.fill(pedalAcceleratie);

        // Desenează pedala de frână
        if (keyH.brake) {
            g2.setColor(Color.RED); // Schimbă culoarea când este apăsată
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.fill(pedalFrana);

        // Schițează marginile pedalelor
        g2.setColor(Color.BLACK);
        g2.draw(pedalAcceleratie);
        g2.draw(pedalFrana);
    }
}

