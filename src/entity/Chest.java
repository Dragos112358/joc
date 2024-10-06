package entity;

public class Chest {
    protected int x;
    int y;
    public boolean isOpen;
    public boolean flag_chest;

    // Constructor
    public Chest(int x, int y) {
        this.x = x;
        this.y = y;
        this.isOpen = false; // implicit cufărul este închis
    }

    // Getter pentru x
    public int getX() {
        return x;
    }

    // Setter pentru x
    public void setX(int x) {
        this.x = x;
    }

    // Getter pentru y
    public int getY() {
        return y;
    }

    // Setter pentru y
    public void setY(int y) {
        this.y = y;
    }

    // Getter pentru isOpen
    public boolean isOpen() {
        return isOpen;
    }

    // Metodă pentru a deschide cufărul
    public void open() {
        this.isOpen = true;
        System.out.println("Chest is now open.");
    }

    // Metodă pentru a închide cufărul
    public void close() {
        this.isOpen = false;
        System.out.println("Chest is now closed.");
    }

    // Metodă pentru a afișa starea cufărului
    @Override
    public String toString() {
        String status = isOpen ? "open" : "closed";
        return "Chest at (" + x + ", " + y + ") is " + status + ".";
    }
}
