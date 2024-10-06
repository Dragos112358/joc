package mypackage;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window =new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(400,400);
        window.setTitle("Treasure Hunt");
        gamepanel gamePanel= new gamepanel() ;
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //apoi, sa spawneze mobi constant //done
        //de modifiaawwacat mapa (sa faca cuceriri) //done
        //sa pot sa dau click dreapta pe harta si in acel punct sa pot sa pun 3 towere cu hp si damage sau o masina sau o fabrica de rachete/nucleare
        //sa pot sa cumpar cu 5000 de cash buildinguri aliate //done
        //portale de adaugatsde //done
        //am adaugat damage //done
        //mai am de adaugat attack_speed,range si hp //done
        //TODO:
        //de facut harta ca lumea
        //towerele sa poata trage intre ele //done
        //de facut bancile cu bani //done
        //de facut harta ca lumea
        //de facut parte de endgame/restart done
        //de facut pauza //done
        //de facut bossi de venit in valuri //done
        //de facut meniu ca lumea cand mor cu play again, exit si high scores //done
        //la meniu, adaug si o descriere la fiecare dificultate. //done
        //de adaugat la masina sa mearga incontinuu, chiar daca nu apas sagetile //done
        //de adaugat mouse listener pe pedalele cu rosu si negru
        //de adaugat harta repetitiva desen //done



        gamePanel.startGameThread();
    }
}