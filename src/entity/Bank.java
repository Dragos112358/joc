package entity;

import java.util.ArrayList;
import java.util.List;

import static mypackage.gamepanel.FPS;

public class Bank {
    private int x;  // coordonata x
    private int y;  // coordonata y
    private int bankType;  // tipul băncii (de la 1 la 4)
    public int value;
    public int contor_banca = 0;
    public int countdown_bani = 8*FPS;
    public List<Money> bani = new ArrayList<Money>();
    public long displayAmountTime = -1; // Variabilă pentru a ține evidența timpului de afișare a sumei
    public int receivedMoney = 0; // Suma primită la colectare
    // Constructor
    public Bank(int x, int y, int bankType) {
        this.x = x;
        this.y = y;
        this.bankType = bankType;
        if(bankType == 1)
        {
            value = 400;
        }
        if(bankType == 2)
        {
            value = 1600;
        }
        if(bankType == 3)
        {
            value = 3200;
        }
        if(bankType == 4)
        {
            value = 10800;
        }
    }

    // Getter pentru coordonata x
    public int getX() {
        return x;
    }

    // Getter pentru coordonata y
    public int getY() {
        return y;
    }

    // Getter pentru tipul băncii
    public int getBankType() {
        return bankType;
    }

    // Metodă pentru calcularea producției pe baza tipului de bancă
    public double calculateProduction() {
        switch (bankType) {
            case 1:
                return 1000.0;  // Producția pentru tipul 1
            case 2:
                return 2000.0;  // Producția pentru tipul 2
            case 3:
                return 3000.0;  // Producția pentru tipul 3
            case 4:
                return 4000.0;  // Producția pentru tipul 4
            default:
                return 0.0;  // Nu ar trebui să se întâmple
        }
    }

    // Afișarea informațiilor despre bancă
    @Override
    public String toString() {
        return "Bank [x=" + x + ", y=" + y + ", bankType=" + bankType + ", production=" + calculateProduction() + "]";
    }
}

