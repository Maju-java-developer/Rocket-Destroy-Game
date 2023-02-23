package EnemyRocket;

import core.Defaults;
import core.RandomRange;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class BigEnemyRocket {
    
    public int posX, posY;
    public Dimension rocketSize;
    
    public ImageIcon bigRocketIcon = new ImageIcon("");
    public float bigRocketspeed;
    public int enemyFuel = RandomRange.randomInt(10, 15);
    
    public ArrayList<BigEnemiesBullet> bigEnenmiesBulletList = new ArrayList<BigEnemiesBullet>();
    
    int timeTofire = 100;
    int timeTofireRest = 250;
    int fireLimit = 3;
    
    public void bigUpdateBullet(){
        timeTofire--;
       
       if(timeTofire < 1){
            for (int i = 0; i < fireLimit; i++) {
                BigEnemiesBullet bigBullet =  new BigEnemiesBullet(this,RandomRange.randomInt(4, 8), RandomRange.randomInt(2, 5));
                bigEnenmiesBulletList.add(bigBullet);
                
                bigBullet.bulletSpeed = RandomRange.randomInt(4, 8);
                bigBullet.velX = RandomRange.randomInt(2, 5);
                
                timeTofire = RandomRange.randomInt(100, timeTofireRest);
            }
       }
       
    }
    
    public BigEnemyRocket() {
    }

    public BigEnemyRocket(int posX, int posY, Dimension rocketSize, float bigRocketspeed) {
        this.posX = posX;
        this.posY = posY;
        this.rocketSize = rocketSize;
        this.bigRocketspeed = bigRocketspeed;
        
        bigRocketIcon = Defaults.rescaleImage(
                rocketSize.width, 
                rocketSize.height, 
                Defaults.getPath("BigEnenyRocekt", "png")
        );
        
    }
    
    public void bigEnemyRocketupdate(){
        posY += bigRocketspeed;
    }
    
}
