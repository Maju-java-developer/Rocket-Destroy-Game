package rocketgameproject;

import core.Defaults;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class MyRocket {
    
    int posX, posY;
    Dimension rocketSize;
    Dimension windowSize;
    
    ImageIcon rocketIcon = new ImageIcon("");
    int rocketSpeed = 5;
    
    MyRocketBullet rocketBullet;
    ArrayList<MyRocketBullet> bulletList = new ArrayList<>();
    
    boolean moveUp = false,
            moveDown = false, 
            moveLeft = false,
            moveRight = false;
    
    public MyRocket() {}
    public MyRocket(int posX, int posY , Dimension rocketSize, Dimension windowSize) {
        this.posX = posX;
        this.posY = posY;
        this.rocketSize = rocketSize;
        this.windowSize = windowSize;
        
        String rootpath = Defaults.getPath("rocket_icon", "png");
        rocketIcon = Defaults.rescaleImage(rocketSize.width, rocketSize.height, rootpath);
        rocketBullet = new MyRocketBullet(new Dimension(15, 35), this);
    }
    
    public void bullet(){
        MyRocketBullet bullet = new MyRocketBullet(new Dimension(15, 35), this);
        bulletList.add(bullet);
    }
    
    public void rocketUpdate(){
        if (moveRight) {
            if ((posX + rocketSize.width - 30) < windowSize.width) {
                posX += rocketSpeed;
            }
        }
        if (moveLeft) {
            if (posX > - 30) {
                posX -= rocketSpeed;
            }
        }
        if (moveUp) {
            if (posY > 0) {
                posY -= rocketSpeed;
            }
        }
        if (moveDown) {
            if ((posY + rocketSize.height + 35) < windowSize.height) {
                posY += rocketSpeed;
            }
        }
    }
}