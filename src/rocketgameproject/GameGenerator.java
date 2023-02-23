package rocketgameproject;

import Constants.Constants;
import EnemyRocket.*;
import core.Defaults;
import core.RandomRange;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameGenerator extends JPanel{
            
    Dimension size;

    public GameGenerator(){}
    public GameGenerator(Dimension size) {
        this.size = size;
    }
    
    ArrayList<EnemyRocket> enemyList = new ArrayList<EnemyRocket>();
    ArrayList<BigEnemyRocket> bigEnemyRocketList = new ArrayList<BigEnemyRocket>();
    
    Timer updateTimer = null;
    int delay = 10;
    
    MyRocket myRocket = null;

    int bigEnemyCreateTimeLimit = 200;
    public void createBigEnemyRocket(){
        bigEnemyCreateTimeLimit --;

        if (bigEnemyCreateTimeLimit < 0) {
            BigEnemyRocket bigEnemyRocket = new BigEnemyRocket(
            RandomRange.randomInt(1, 1100),
            - RandomRange.randomInt(200, 400), 
            new Dimension(120, 90), 
            RandomRange.randomFloat(3.5f, 4.5f)
        );
            bigEnemyCreateTimeLimit = 200;
            bigEnemyRocketList.add(bigEnemyRocket);
        }
    }

    int enemyCreateTimeLimit = 150;
    public void createEnemyRocket(){
        enemyCreateTimeLimit --;

        if (enemyCreateTimeLimit < 0) {
            EnemyRocket enemyRocket = new EnemyRocket(
            RandomRange.randomInt(1, 1100),
            - 250, 
            new Dimension(120, 90), 
            RandomRange.randomFloat(3.5f, 4.5f)
        );
            enemyCreateTimeLimit = 150;
            enemyList.add(enemyRocket);
        }
    }
    
    public void intazile(){
        for (int i = 0; i < 3; i++) {
            EnemyRocket enemyRocket = new EnemyRocket(
                RandomRange.randomInt(1, 1100),
                - 250, 
                new Dimension(120, 90), 
                RandomRange.randomFloat(2.5f, 3.5f)
            );
            enemyList.add(enemyRocket);
        }
        
        intiComponends();
        gameSetup();
        updateTimer();
        System.out.println("EnemySize: " +enemyList.size());
    }
    
    public void gameSetup(){
        myRocket = new MyRocket(600, 500 , new Dimension(150, 150) , new Dimension(getWidth(), getHeight()));
    }

    public void updateTimer(){
        updateTimer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Creating New Enemy
                createBigEnemyRocket();
                createEnemyRocket();
                // Creating New Enemy 
                
                // Updating Enemies List 
                for (int i = 0; i < enemyList.size(); i++) {
                    enemyList.get(i).updateBullet();
                    enemyList.get(i).EnemyRocketupdate();
                    
                    for (int j = 0; j < enemyList.get(i).enenmiesBulletList.size(); j++) {
                        enemyList.get(i).enenmiesBulletList.get(j).enemiesRocketBulletUpdate();
                    }
                }

                for (int i = 0; i < bigEnemyRocketList.size(); i++) {
                    bigEnemyRocketList.get(i).bigEnemyRocketupdate();
                    bigEnemyRocketList.get(i).bigUpdateBullet();
                    
                    for (int j = 0; j < bigEnemyRocketList.get(i).bigEnenmiesBulletList.size(); j++) {
                        bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bigEnemiesRocketBulletUpdate();
                    }
                }
                // Updating Enemies List 

                // Updating My Rocket Postion XYZ
                myRocket.rocketUpdate();
                // Updating My Rocket Postion XYZ

                // Updating My Rocket bullet List
                for (int i = 0; i < myRocket.bulletList.size(); i++) {
                    myRocket.bulletList.get(i).myRocketBulletUpdate();
                    repaint();
                    revalidate();
                }
                // Updating My Rocket bullet List
                
                repaint();
                revalidate();
            }
        });
        updateTimer.start();
    }
    
    public void intiComponends(){
        setBounds(0, 0, size.width, size.height);
//        setBackground(Color.BLACK);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        final Graphics2D graphics2D = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.addRenderingHints(hints);
                
        // Instances: 
        Ellipse2D myRocketBullet = null;
        Ellipse2D enemyRocketBullet = null;
        Ellipse2D bigEnemyRocketBullet = null;
        Ellipse2D bigEnemyRocketRightBullet = null;
        Ellipse2D bigEnemyRocketLeftBullet = null;
        
        Rectangle2D myRocketRec = null;
        Rectangle2D enemyRocketRec = null;
        Rectangle2D bigEnemyRocketRec = null;
        // Instances: 

        // Draw BackgroundImage:
        ImageIcon backgroundIcon = new ImageIcon(Defaults.getPath("backgroundIcon", "jpg"));
//        graphics2D.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
        // Draw BackgroundImage:
        
        graphics2D.drawString("Score : " + Constants.score, 10, 30);
        graphics2D.setFont(new Font("Souge UI Light", 0, 10));

        graphics2D.drawString("Made By Majid Hussain Qutrio : ", getHeight() / 2  , getWidth() - 2);
        graphics2D.setFont(new Font("Souge UI Light", 0, 40));
        graphics2D.setColor(Color.WHITE);

        ImageIcon explosionImage = new ImageIcon("");
        explosionImage = core.Defaults.rescaleImage(50, 50, core.Defaults.getPath("explosion", "png"));

        // Generate Fuel For My Rocket!
        if (Constants.fuel > 60) {
            graphics2D.setColor(new Color(40, 200, 50));
        }else if (Constants.fuel > 30) {
            graphics2D.setColor(new Color(0, 255, 255));
        }else{
            graphics2D.setColor(Color.RED);
        }

        for (int i = 0; i < Constants.fuel; i++) {
//            graphics2D.setColor(new Color(40, 200, 50));
            graphics2D.drawRect((400 + (i * 4)), 20, 1, 20);
        }
        // Generate Fuel For My Rocket!
 
        // Checking Fuel if is Fuel > 0 while game run otherwise stoped! 
        if (Constants.fuel > 0) {

        // DrawMyRocket;
        graphics2D.drawImage(
                myRocket.rocketIcon.getImage(), 
                myRocket.posX,
                myRocket.posY,
                myRocket.rocketSize.width,
                myRocket.rocketSize.height,
                null
        );
        
        myRocketRec = new Rectangle.Float(
                (int)myRocket.posX + 45, 
                (int)myRocket.posY, 
                (int)myRocket.rocketSize.width - 100, 
                (int)myRocket.rocketSize.height 
        );
        // DrawMyRocket;
        
        // DrawMy Rocket Bullets:
        for (int i = 0; i < myRocket.bulletList.size(); i++) {
            graphics2D.drawImage(
                    myRocket.bulletList.get(i).BulletIcon.getImage(),
                    myRocket.bulletList.get(i).posX,
                    myRocket.bulletList.get(i).posY,
                    myRocket.bulletList.get(i).bulletSize.width,
                    myRocket.bulletList.get(i).bulletSize.height,
                    null
            );

            myRocketBullet = new Ellipse2D.Float(
                (int)myRocket.bulletList.get(i).posX, 
                (int)myRocket.bulletList.get(i).posY, 
                (int)myRocket.bulletList.get(i).bulletSize.width, 
                (int)myRocket.bulletList.get(i).bulletSize.height 
            );
            
            // Removing unuseless Bullet!
            if (myRocket.bulletList.get(i).posY < (-myRocket.rocketIcon.getIconHeight()) ) {
                myRocket.bulletList.remove(i);
            }

        }
        // DrawMy Rocket Bullets:
        
        //Draw Simple Enemies Rocket:
        for (int i = 0; i < enemyList.size(); i++) {
            graphics2D.drawImage(
                enemyList.get(i).rocketIcon.getImage(), 
                enemyList.get(i).posX, 
                enemyList.get(i).posY, 
                enemyList.get(i).rocketSize.width,
                enemyList.get(i).rocketSize.height,
                null
            );
            
            enemyRocketRec = new Rectangle2D.Float(
                enemyList.get(i).posX + 30, 
                enemyList.get(i).posY, 
                enemyList.get(i).rocketSize.width - 60,  
                enemyList.get(i).rocketSize.height 
            );
            
            // Removing Index  
            if (enemyList.get(i).posY > size.height) {
                enemyList.remove(i);
            }
            
            // Drawing Enemies Bullet
            for (int j = 0; j < enemyList.get(i).enenmiesBulletList.size(); j++) {
            graphics2D.drawImage(
                enemyList.get(i).enenmiesBulletList.get(j).bulletIcon.getImage(), 
                enemyList.get(i).enenmiesBulletList.get(j).posX, 
                enemyList.get(i).enenmiesBulletList.get(j).posY, 
                enemyList.get(i).enenmiesBulletList.get(j).bulletSize.width,
                enemyList.get(i).enenmiesBulletList.get(j).bulletSize.height,
                null
            );
            
            enemyRocketBullet = new Ellipse2D.Float(
                enemyList.get(i).enenmiesBulletList.get(j).posX, 
                enemyList.get(i).enenmiesBulletList.get(j).posY, 
                enemyList.get(i).enenmiesBulletList.get(j).bulletSize.width,  
                enemyList.get(i).enenmiesBulletList.get(j).bulletSize.height + 5 
            );

            // intersects Between Enemy Rocket Bullet With My Rocket!
            if (enemyRocketBullet.intersects(myRocketRec)) {
                graphics2D.drawImage(explosionImage.getImage(), myRocket.posX + 40, myRocket.posY, explosionImage.getIconWidth(), explosionImage.getIconHeight(),null);
                enemyList.get(i).enenmiesBulletList.remove(j);
                Constants.fuel -= RandomRange.randomInt(4, 10);
                Constants.score += RandomRange.randomInt(5, 10);
            }
            // intersects Between Enemy Rocket Bullet With My Rocket!
            
            // Drawing Enemies Bullet
            }

            // Doing Intesects Between My Rocket Bullet To Enenmies Rocket!
            for (int j = 0; j < myRocket.bulletList.size(); j++) {
                myRocketBullet = new Ellipse2D.Float(
                    (int)myRocket.bulletList.get(j).posX,
                    (int)myRocket.bulletList.get(j).posY,
                    (int)myRocket.bulletList.get(j).bulletSize.width,
                    (int)myRocket.bulletList.get(j).bulletSize.height
                );
                
                if (myRocketBullet.intersects(enemyRocketRec)) {
                    graphics2D.drawImage(explosionImage.getImage(), enemyList.get(i).posX + 40, enemyList.get(i).posY, explosionImage.getIconWidth(), explosionImage.getIconHeight(),null);
                    enemyList.remove(i);
                    myRocket.bulletList.remove(j);
                }
            }
            // Doing Intesects Between My Rocket Bullet To Enenmies Rocket!
        }
        //Draw Simple Enemies Rocket:
        
        //Draw Big Enemies Rocket:
        for (int i = 0; i < bigEnemyRocketList.size(); i++) {
            graphics2D.drawImage(
                bigEnemyRocketList.get(i).bigRocketIcon.getImage(), 
                bigEnemyRocketList.get(i).posX, 
                bigEnemyRocketList.get(i).posY, 
                bigEnemyRocketList.get(i).rocketSize.width,
                bigEnemyRocketList.get(i).rocketSize.height,
                null
            );

            bigEnemyRocketRec = new Rectangle2D.Float(
                bigEnemyRocketList.get(i).posX + 20, 
                bigEnemyRocketList.get(i).posY, 
                bigEnemyRocketList.get(i).rocketSize.width - 40,  
                bigEnemyRocketList.get(i).rocketSize.height 
            );

            // Removing Index  
            if (bigEnemyRocketList.get(i).posY > size.height) {
                bigEnemyRocketList.remove(i);
            }
            
            // Creating Big Enimies Fuel
            for (int j = 0; j < bigEnemyRocketList.get(i).enemyFuel; j++) {
                graphics2D.setColor(new Color(40, 200, 50));
                graphics2D.drawRect((40 + (bigEnemyRocketList.get(i).posX + (j * 5))), (bigEnemyRocketList.get(i).posY - 25) , 1, 20);
            }
            // Creating Big Enimies Fuel

            // Drawing Big Enemies Bullet
            for (int j = 0; j < bigEnemyRocketList.get(i).bigEnenmiesBulletList.size(); j++) {
                // Drawing Bullet Image Straght
                graphics2D.drawImage(
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletIcon.getImage(), 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posX, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posY, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.width,
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.height,
                    null
                );

                // Drawing Bullet Image Right!
                graphics2D.drawImage(
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).rightBulletIcon.getImage(), 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).rightposX, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posY, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.width,
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.height,
                    null
                );

                // Drawing Bullet Image Left!
                graphics2D.drawImage(
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).leftBulletIcon.getImage(), 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).leftposX, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posY, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.width,
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.height,
                    null
                );
                
                bigEnemyRocketBullet = new Ellipse2D.Float(
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posX, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posY, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.width,  
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.height + 5 
                );

                bigEnemyRocketRightBullet = new Ellipse2D.Float(
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).rightposX, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posY, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.width,  
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.height + 5 
                );

                bigEnemyRocketLeftBullet = new Ellipse2D.Float(
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).leftposX, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).posY, 
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.width,  
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.get(j).bulletSize.height + 5 
                );
                
                if (bigEnemyRocketBullet.intersects(myRocketRec) || bigEnemyRocketLeftBullet.intersects(myRocketRec) || bigEnemyRocketRightBullet.intersects(myRocketRec)) {
                    graphics2D.drawImage(explosionImage.getImage(), myRocket.posX + 40, myRocket.posY, explosionImage.getIconWidth(), explosionImage.getIconHeight(),null);
                    bigEnemyRocketList.get(i).bigEnenmiesBulletList.remove(j);
                    Constants.fuel -= RandomRange.randomInt(4, 8);
                    Constants.score += RandomRange.randomInt(5, 10);
                }
            // Drawing Big Enemies Bullet!
            }

            // We Are Intesects Between My Rocket Bullet To Big Enenmies Rocket!
            for (int j = 0; j < myRocket.bulletList.size(); j++) {
                myRocketBullet = new Ellipse2D.Float(
                    (int)myRocket.bulletList.get(j).posX,
                    (int)myRocket.bulletList.get(j).posY,
                    (int)myRocket.bulletList.get(j).bulletSize.width,
                    (int)myRocket.bulletList.get(j).bulletSize.height
                );
                
                if (myRocketBullet.intersects(bigEnemyRocketRec)) {
                    graphics2D.drawImage(explosionImage.getImage(), bigEnemyRocketList.get(i).posX + 40, bigEnemyRocketList.get(i).posY, explosionImage.getIconWidth(), explosionImage.getIconHeight(),null);
                    myRocket.bulletList.remove(j);
                    bigEnemyRocketList.get(i).enemyFuel -= RandomRange.randomInt(3, 6);
                    
                    if (bigEnemyRocketList.get(i).enemyFuel < 0) {
                        graphics2D.drawImage(explosionImage.getImage(), bigEnemyRocketList.get(i).posX + 40, bigEnemyRocketList.get(i).posY, (explosionImage.getIconWidth() + 20), (explosionImage.getIconHeight() +20),null);
                        bigEnemyRocketList.remove(i);
                    }
                }
            }
            // We Are Intesects Between My Rocket Bullet To Big Enenmies Rocket!
//            graphics2D.setColor(Color.BLUE);
//            graphics2D.draw(bigEnemyRocketRec);
        }
        //Draw Big Enemies Rocket:
        
        }else {
            updateTimer.stop();
        }
    }
}
