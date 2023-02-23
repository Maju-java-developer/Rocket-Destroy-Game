package rocketgameproject;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MainApp extends JFrame {
    public MainApp(){
        intiComponends();
    }
    
    public void intiComponends(){
        setTitle("Rocket Game Project");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameGenerator = new GameGenerator(new Dimension(getWidth(), getHeight()));
        gameGenerator.intazile();
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gameGenerator.myRocket.bullet();
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    gameGenerator.myRocket.moveRight = true;
                    gameGenerator.myRocket.moveLeft = false;
                    gameGenerator.myRocket.moveDown = false;
                    gameGenerator.myRocket.moveUp = false;
                }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    gameGenerator.myRocket.moveRight = false;
                    gameGenerator.myRocket.moveLeft = true;
                    gameGenerator.myRocket.moveDown = false;
                    gameGenerator.myRocket.moveUp = false;
                }else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    gameGenerator.myRocket.moveRight = false;
                    gameGenerator.myRocket.moveLeft = false;
                    gameGenerator.myRocket.moveDown = false;
                    gameGenerator.myRocket.moveUp = true;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    gameGenerator.myRocket.moveRight = false;
                    gameGenerator.myRocket.moveLeft = false;
                    gameGenerator.myRocket.moveDown = true;
                    gameGenerator.myRocket.moveUp = false;
                }
                
                repaint();
                revalidate();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                gameGenerator.myRocket.moveDown = false;
                gameGenerator.myRocket.moveUp = false;
                gameGenerator.myRocket.moveRight = false;
                gameGenerator.myRocket.moveLeft = false;
            }
        });
        add(gameGenerator);
        
    }
    
    GameGenerator gameGenerator;
    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }
    
}
