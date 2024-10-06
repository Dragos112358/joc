package mypackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean sus, jos,stanga,dreapta,shot,reload,rocket,nuclear,car,accel,brake,exit,tower;
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code== KeyEvent.VK_W || code==KeyEvent.VK_UP)
        {
            //System.out.println("sus");
            sus=true;
           // System.out.println("sus");
        }
        if(code== KeyEvent.VK_S || code==KeyEvent.VK_DOWN)
        {
           jos=true;
            //System.out.println("jos");
        }
        if(code== KeyEvent.VK_A || code==KeyEvent.VK_LEFT)
        {
            stanga=true;
        }
        if(code== KeyEvent.VK_D || code==KeyEvent.VK_RIGHT)
        {
            dreapta=true;
        }
        if (code == KeyEvent.VK_SPACE) {
            shot = true;
            //System.out.println("shot fired");
        }
        if (code == KeyEvent.VK_R) {
            reload = true;
            //System.out.println("Reload");
        }
        //System.out.println();
        //racheta
        if (code == KeyEvent.VK_Z) {
            rocket = true;
            //System.out.println("Reload");
        }
        if(code == KeyEvent.VK_N)
        {
            nuclear = true;
           // System.out.println("Nuclear");
        }
        if(code == KeyEvent.VK_E)
        {
            car = true;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_G)
        {
            accel = true;
            //System.out.println("Accelereaza");
        }
        if(code == KeyEvent.VK_B)
        {
            brake = true;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_F)
        {
            exit = true;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_T)
        {
            tower = true;
            //System.out.println("Car");
        }

    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code== KeyEvent.VK_W || code==KeyEvent.VK_UP)
        {
            sus=false;
        }
        if(code== KeyEvent.VK_S || code==KeyEvent.VK_DOWN)
        {
            jos=false;
        }
        if(code== KeyEvent.VK_A || code==KeyEvent.VK_LEFT)
        {
            stanga=false;
        }
        if(code== KeyEvent.VK_D || code==KeyEvent.VK_RIGHT)
        {
            dreapta=false;
        }
        if (code == KeyEvent.VK_SPACE) {
            shot = false;
        }
        if (code == KeyEvent.VK_R) {
            reload = false;
            //System.out.println("shot fired");
        }
        if (code == KeyEvent.VK_Z) {
            rocket = false;
            //System.out.println("Reload");
        }
        if(code == KeyEvent.VK_N)
        {
            nuclear = false;
            // System.out.println("Nuclear");
        }
        if(code == KeyEvent.VK_G)
        {
            accel = false;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_B)
        {
            brake = false;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_E)
        {
            car = false;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_F)
        {
            exit = false;
            //System.out.println("Car");
        }
        if(code == KeyEvent.VK_T)
        {
            tower = false;
            //System.out.println("Car");
        }
    }

}
