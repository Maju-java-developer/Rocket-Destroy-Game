package EnemyRocket;

import core.Defaults;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class enemiesBullet {

    public int posX;
    public int posY;
    public int bulletSpeed;
    
    public Dimension bulletSize = new Dimension(15,35); 
    public ImageIcon bulletIcon = new ImageIcon("");
    
    EnemyRocket enemyRocket = null;
    public enemiesBullet() {
    }

    public enemiesBullet(EnemyRocket enemyRocket ,int bulletSpeed) {
        this.posX = (enemyRocket.posX + 55);
        this.posY = (enemyRocket.posY + 75);
        this.bulletSpeed = bulletSpeed;
        this.enemyRocket = enemyRocket;
        this.bulletSize = bulletSize;
    
        bulletIcon = core.Defaults.rescaleImage(bulletSize.width, bulletSize.height, Defaults.getPath("bullet_down", "png"));
    }
    
    public void enemiesRocketBulletUpdate(){
        posY += bulletSpeed; 
    }
    
}
