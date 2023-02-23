package EnemyRocket;

import core.Defaults;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class BigEnemiesBullet {

    public int posX;
    public int rightposX;
    public int leftposX;
    public int posY;
    
    public int bulletSpeed;
    public int velX;
    
    public Dimension bulletSize = new Dimension(15,35);
    
    public ImageIcon bulletIcon = new ImageIcon("");
    public ImageIcon rightBulletIcon = new ImageIcon("");
    public ImageIcon leftBulletIcon = new ImageIcon("");
    
    BigEnemyRocket enemyRocket = null;
    public BigEnemiesBullet() {
    }

    public BigEnemiesBullet(BigEnemyRocket enemyRocket ,int bulletSpeed,int velX) {
        this.posX = (enemyRocket.posX + 55);
        this.posY = (enemyRocket.posY + 75);
        this.velX = velX;
        
        rightposX = posX;
        leftposX = posX;
        
        this.bulletSpeed = bulletSpeed;
        this.enemyRocket = enemyRocket;
        this.bulletSize = bulletSize;
    
        bulletIcon = core.Defaults.rescaleImage(bulletSize.width, bulletSize.height, Defaults.getPath("bullet_down", "png"));
        rightBulletIcon = core.Defaults.rescaleImage(bulletSize.width, bulletSize.height, Defaults.getPath("bullet_down", "png"));
        leftBulletIcon = core.Defaults.rescaleImage(bulletSize.width, bulletSize.height, Defaults.getPath("bullet_down", "png"));
    }
    
    public void bigEnemiesRocketBulletUpdate(){
        posY += bulletSpeed; 
        
        rightposX += velX;
        leftposX -= velX;
        
    }
    
}
