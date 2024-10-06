package entity;

public class Tower {
    private int x;
    private int y;
    private int hp;
    private int damage;
    private int range;
    private int attack_speed = 1000; // un atac la 1 secunda
    private long lastAttackTime = 0; // Timpul ultimului atac în milisecunde
    public  boolean is_ally;
    public int max_hp;
    public boolean is_bombed= false;
    public int level_damage = 1;
    public int level_attack_speed = 1;
    public int level_range=1;
    public int damage_cost = 1000;
    public int attack_speed_cost = 1000;
    public int range_cost = 500;
    public int hp_cost = 500;
    public int level_hp = 1;


    // Constructor pentru inițializare
    public Tower(int x, int y, int hp, int damage, int range,int attack_speed, boolean is_ally) {
        this.attack_speed = attack_speed;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.max_hp=this.hp;
        this.damage = damage;
        this.range = range;
        this.is_ally=is_ally;
    }

    // Getter pentru lastAttackTime
    public long getLastAttackTime() {
        return lastAttackTime;
    }
    public int getAttack_speed()
    {
        return this.attack_speed;
    }
    public void setAttack_speed(int attack_speed)
    {
        this.attack_speed=attack_speed;
    }

    // Setter pentru lastAttackTime
    public void setLastAttackTime(long lastAttackTime) {
        this.lastAttackTime = lastAttackTime;
    }

    // Getter și setter pentru coordonate, damage, etc.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return this.damage;
    }
    public void setDamage(int damage_nou)
    {
        this.damage=damage_nou;
    }

    public int getRange() {
        return range;
    }
    public void setRange(int range)
    {
        this.range=range;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    // Alte metode dacă este necesar...
}
