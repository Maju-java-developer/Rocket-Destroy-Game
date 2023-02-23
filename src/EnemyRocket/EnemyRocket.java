package EnemyRocket;

import core.Defaults;
import core.RandomRange;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyRocket {
    
    public int posX, posY,fireLimit = 3;
    public Dimension rocketSize;
    
    public ImageIcon rocketIcon = new ImageIcon("");
    public float rocketspeed;
    
    public ArrayList<enemiesBullet> enenmiesBulletList = new ArrayList<enemiesBullet>();
    
    int timeTofire = 100;
    int timeTofireRest = 250;
    
    public void updateBullet(){
        
        timeTofire--;
       
       if(timeTofire < 1){
            for (int i = 0; i < fireLimit; i++) {
                enemiesBullet bullet =  new enemiesBullet(this,RandomRange.randomInt(4, 8));
                enenmiesBulletList.add(bullet);
                
                bullet.bulletSpeed = RandomRange.randomInt(7, 12);
                timeTofire = RandomRange.randomInt(100, timeTofireRest);
            }
       }
    }
    
    public EnemyRocket() {
    }

    public EnemyRocket(int posX, int posY, Dimension rocketSize, float rocketspeed) {
        this.posX = posX;
        this.posY = posY;
        this.rocketSize = rocketSize;
        this.rocketspeed = rocketspeed;
        
        rocketIcon = Defaults.rescaleImage(
                rocketSize.width, 
                rocketSize.height, 
                Defaults.getPath("rocket_icon1", "png")
        );
    }
    
    int createbulletLimitTime = 500;
    
    public void EnemyRocketupdate(){
        posY += rocketspeed;
    }
    
}
