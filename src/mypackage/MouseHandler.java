package mypackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    public int mouseX, mouseY;
    public boolean leftClick, rightClick;

    @Override
    public void mousePressed(MouseEvent e) {
        // Coordonatele clic-ului
        mouseX = e.getX();
        mouseY = e.getY();

        // Verifică dacă a fost un clic pe butonul stâng sau drept al mouse-ului
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = true;
           // System.out.println("Left click at (" + mouseX + ", " + mouseY + ")");
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick = true;
           // System.out.println("Right click at (" + mouseX + ", " + mouseY + ")");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Resetează starea când butonul mouse-ului este eliberat
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick = false;
        }
    }
}
