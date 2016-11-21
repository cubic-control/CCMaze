package cubic_control.cc_game.Managers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cubic_control.cc_game.Player.Player;

public class InputManager implements KeyListener {
	
	public static int keyUP = KeyEvent.VK_W;
    public static int keyDOWN = KeyEvent.VK_S;
    public static int keyLEFT = KeyEvent.VK_A;
    public static int keyRIGHT = KeyEvent.VK_D;
    public static int keySPRINT = KeyEvent.VK_SHIFT;
    public static int keyEXIT = KeyEvent.VK_ESCAPE;
    public static int keySCREENSHOT = KeyEvent.VK_F2;

    public InputManager() {
    	
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == keyUP) {
            Player.up = true;
        }
        if (key == keyDOWN) {
        	Player.down = true;
        }
        if (key == keyLEFT) {
        	Player.left = true;
        }
        if (key == keyRIGHT) {
        	Player.right = true;
        }
        if (key == keySPRINT) {
        	Player.sprinting = true;
        }
        if (key == keyEXIT) {
        	System.exit(1);
        }
        if (key == keySCREENSHOT) {
        	try {
                Robot robot = new Robot();
                String format = "png";
                String fileName = "Screenshot." + format;
                 
                Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
                ImageIO.write(screenFullImage, format, new File(fileName));
                 
                System.out.println("[System]:Screenshot Saved");
            } catch (AWTException | IOException ex) {
                System.err.println(ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == keyUP) {
        	Player.up = false;
        }
        if (key == keyDOWN) {
        	Player.down = false;
        }
        if (key == keyLEFT) {
        	Player.left = false;
        }
        if (key == keyRIGHT) {
        	Player.right = false;
        }
        if (key == keySPRINT) {
        	Player.sprinting = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}