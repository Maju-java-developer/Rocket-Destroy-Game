package rocketgameproject;

import core.Defaults;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class MyRocketBullet {
    
    int posX, posY;
    int bulletSpeed = 8;
    
    Dimension bulletSize;
    ImageIcon BulletIcon = new ImageIcon("");

    public MyRocketBullet() {}
    public MyRocketBullet(Dimension bulletSize ,MyRocket myRocket) {
        this.posX = (myRocket.posX + 65);
        this.posY = (myRocket.posY - 30);
        this.bulletSize = bulletSize;
    
        BulletIcon = core.Defaults.rescaleImage(bulletSize.width, bulletSize.height, Defaults.getPath("bullet_up", "png"));
    }
    
    public void myRocketBulletUpdate(){
        posY -= bulletSpeed; 
    }
    
}