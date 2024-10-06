package entity;

public class Money {
    public int x;       // Coordonata x
    public int y;       // Coordonata y
    public double value; // Valoarea banilor
    public Bank bank;  // Numele băncii
    public int displayTime = 0;

    // Constructor
    public Money(int x, int y, double value, Bank bank) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.bank = bank;
    }

    // Getters și Setters pentru fiecare atribut
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    // Suprascrierea metodei toString pentru o afișare frumoasă a obiectului
    @Override
    public String toString() {
        return "Money from bank: " + bank + ", value: " + value +
                ", at coordinates: (" + x + ", " + y + ")";
    }
}

