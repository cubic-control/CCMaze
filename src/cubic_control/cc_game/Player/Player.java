package cubic_control.cc_game.Player;

import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.cc_game.Check.Check;
import cubic_control.cc_game.GameLoop.GameLoop;
import cubic_control.cc_game.Gen.Map;
import cubic_control.cc_game.Main.Main;
import cubic_control.resources.assets.Animator;
import cubic_control.resources.assets.Assets;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements KeyListener {
    Vector2F pos;
    private int width = 42;
    private int height = 42;
    private static boolean up,down,left,right;
    private static boolean sprinting;
    
    private float maxSpeed = 96.0f;
    private float speedUp = 0.0f;
    private float speedDown = 0.0f;
    private float speedLeft = 0.0f;
    private float speedRight = 0.0f;
    private float slowdown = 47.05f;
    
    private float fixDt = 0.016666668f;
    
    public static int playerID = 1;
    
    public static int keyUP = KeyEvent.VK_W;
    public static int keyDOWN = KeyEvent.VK_S;
    public static int keyLEFT = KeyEvent.VK_A;
    public static int keyRIGHT = KeyEvent.VK_D;
    public static int keySPRINT = KeyEvent.VK_SHIFT;
    public static int keyEXIT = KeyEvent.VK_ESCAPE;
    public static int keySCREENSHOT = KeyEvent.VK_F2;
    
    private Animator playerIdle;
    private Animator playerWalkingUp;
    private Animator playerWalkingDown;
    private Animator playerWalkingLeft;
    private Animator playerWalkingRight;
    
    private int pWAS = 20;
    private int pIAS = 40;
    
    public static Player instance;
    
    private int renderDistanceW = Main.width / 32;
    private int renderDistanceH = Main.height / 32;
    public static Rectangle render;
    
    private boolean mapMove = true;

    public Player(Map map) {
        this.pos = new Vector2F((float)(Main.width / 2 - this.width / 2), (float)(Main.height / 2 - this.height / 2));
        
        if (Player.playerID == 1) {
            this.playerIdle = new Animator(pIAS, new BufferedImage[]{Assets.spr_char1_idle1, Assets.spr_char1_idle2});
            this.playerWalkingUp = new Animator(pWAS, new BufferedImage[]{Assets.spr_char1_walk_up1, Assets.spr_char1_walk_up2, Assets.spr_char1_walk_up3, Assets.spr_char1_walk_up4});
            this.playerWalkingDown = new Animator(pWAS, new BufferedImage[]{Assets.spr_char1_walk_down1, Assets.spr_char1_walk_down2, Assets.spr_char1_walk_down3, Assets.spr_char1_walk_down4});
            this.playerWalkingLeft = new Animator(pWAS, new BufferedImage[]{Assets.spr_char1_walk_left1, Assets.spr_char1_walk_left2, Assets.spr_char1_walk_left3, Assets.spr_char1_walk_left4});
            this.playerWalkingRight = new Animator(pWAS, new BufferedImage[]{Assets.spr_char1_walk_right1, Assets.spr_char1_walk_right2, Assets.spr_char1_walk_right3, Assets.spr_char1_walk_right4});
        }
        if (Player.playerID == 2) {
            this.playerIdle = new Animator(pIAS, new BufferedImage[]{Assets.spr_char2_idle1, Assets.spr_char2_idle2});
            this.playerWalkingUp = new Animator(pWAS, new BufferedImage[]{Assets.spr_char2_walk_up1, Assets.spr_char2_walk_up2, Assets.spr_char2_walk_up3, Assets.spr_char2_walk_up4});
            this.playerWalkingDown = new Animator(pWAS, new BufferedImage[]{Assets.spr_char2_walk_down1, Assets.spr_char2_walk_down2, Assets.spr_char2_walk_down3, Assets.spr_char2_walk_down4});
            this.playerWalkingLeft = new Animator(pWAS, new BufferedImage[]{Assets.spr_char2_walk_left1, Assets.spr_char2_walk_left2, Assets.spr_char2_walk_left3, Assets.spr_char2_walk_left4});
            this.playerWalkingRight = new Animator(pWAS, new BufferedImage[]{Assets.spr_char2_walk_right1, Assets.spr_char2_walk_right2, Assets.spr_char2_walk_right3, Assets.spr_char2_walk_right4});
        }
    }

    public void init() {
    	System.out.println("[System]:Initializing Player");
        render = new Rectangle(
        		(int)(this.pos.xPos - this.pos.getWorldLocation().xPos + this.pos.xPos - 
        				(float)(this.renderDistanceW * 32 / 2) + (float)(this.width / 2)), 
        		(int)(this.pos.yPos - this.pos.getWorldLocation().yPos + this.pos.yPos - 
        				(float)(this.renderDistanceH * 32 / 2) + (float)(this.height / 2)), this.renderDistanceW * 32, this.renderDistanceH * 32);
    }

    public void tick(double deltaTime) {
        render = new Rectangle(
        		(int)(this.pos.xPos - this.pos.getWorldLocation().xPos + this.pos.xPos - 
        				(float)(this.renderDistanceW * 32 / 2) + (float)(this.width / 2)), 
        		(int)(this.pos.yPos - this.pos.getWorldLocation().yPos + this.pos.yPos - 
        				(float)(this.renderDistanceH * 32 / 2) + (float)(this.height / 2)), this.renderDistanceW * 32, this.renderDistanceH * 32);
        
        float moveAmountU = this.speedUp * this.fixDt;
        float moveAmountD = this.speedDown * this.fixDt;
        float moveAmountL = this.speedLeft * this.fixDt;
        float moveAmountR = this.speedRight * this.fixDt;
        
        if(sprinting){
        	this.maxSpeed = 150f;
        }else{
        	this.maxSpeed = 96.0f;
        }
        
        this.playerIdle.runAnimation();
        
        if (!this.mapMove) {
            if (up) {
                if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos - moveAmountU)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos - moveAmountU)))) {
                    this.speedUp = this.speedUp < this.maxSpeed ? (this.speedUp += this.slowdown) : this.maxSpeed;
                    this.pos.yPos -= moveAmountU;
                } else {
                    this.speedUp = 0.0f;
                }
                this.playerWalkingUp.runAnimation();
            } else if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos - moveAmountU)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos - moveAmountU)))) {
                if (this.speedUp != 0.0f) {
                    this.speedUp -= this.slowdown;
                    if (this.speedUp < 0.0f) {
                        this.speedUp = 0.0f;
                    }
                }
                this.pos.yPos -= moveAmountU;
            } else {
                this.speedUp = 0.0f;
            }
            if (down) {
                if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + moveAmountD)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + moveAmountD)))) {
                    this.speedDown = this.speedDown < this.maxSpeed ? (this.speedDown += this.slowdown) : this.maxSpeed;
                    this.pos.yPos += moveAmountD;
                } else {
                    this.speedDown = 0.0f;
                }
                this.playerWalkingDown.runAnimation();
            } else if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + moveAmountD)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + moveAmountD)))) {
                if (this.speedDown != 0.0f) {
                    this.speedDown -= this.slowdown;
                    if (this.speedDown < 0.0f) {
                        this.speedDown = 0.0f;
                    }
                }
                this.pos.yPos += moveAmountD;
            } else {
                this.speedDown = 0.0f;
            }
            if (left) {
                if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - moveAmountL), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - moveAmountL), (int)(this.pos.yPos + GameLoop.map.yPos)))) {
                    this.speedLeft = this.speedLeft < this.maxSpeed ? (this.speedLeft += this.slowdown) : this.maxSpeed;
                    this.pos.xPos -= moveAmountL;
                } else {
                    this.speedLeft = 0.0f;
                }
                this.playerWalkingLeft.runAnimation();
            } else if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - moveAmountL), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - moveAmountL), (int)(this.pos.yPos + GameLoop.map.yPos)))) {
                if (this.speedLeft != 0.0f) {
                    this.speedLeft -= this.slowdown;
                    if (this.speedLeft < 0.0f) {
                        this.speedLeft = 0.0f;
                    }
                }
                this.pos.xPos -= moveAmountL;
            } else {
                this.speedLeft = 0.0f;
            }
            if (right) {
                if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + moveAmountR), (int)(this.pos.yPos + GameLoop.map.yPos)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + moveAmountR), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)))) {
                    this.speedRight = this.speedRight < this.maxSpeed ? (this.speedRight += this.slowdown) : this.maxSpeed;
                    this.pos.xPos += moveAmountR;
                } else {
                    this.speedRight = 0.0f;
                }
                this.playerWalkingRight.runAnimation();
            } else if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + moveAmountR), (int)(this.pos.yPos + GameLoop.map.yPos)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + moveAmountR), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)))) {
                if (this.speedRight != 0.0f) {
                    this.speedRight -= this.slowdown;
                    if (this.speedRight < 0.0f) {
                        this.speedRight = 0.0f;
                    }
                }
                this.pos.xPos += moveAmountR;
            } else {
                this.speedRight = 0.0f;
            }
        } else {
            if (up) {
                this.moveMapUp(moveAmountU);
                this.playerWalkingUp.runAnimation();
            } else {
                this.moveMapUpGlide(moveAmountU);
            }
            if (down) {
                this.moveMapDown(moveAmountD);
                this.playerWalkingDown.runAnimation();
            } else {
                this.moveMapDownGlide(moveAmountD);
            }
            if (left) {
                this.moveMapLeft(moveAmountL);
                this.playerWalkingLeft.runAnimation();
            } else {
                this.moveMapLeftGlide(moveAmountL);
            }
            if (right) {
                this.moveMapRight(moveAmountR);
                this.playerWalkingRight.runAnimation();
            } else {
                this.moveMapRightGlide(moveAmountR);
            }
        }
    }

    public void moveMapUp(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos - speed)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos - speed)))) {
            this.speedUp = this.speedUp < this.maxSpeed ? (this.speedUp += this.slowdown) : this.maxSpeed;
            GameLoop.map.yPos -= speed;
        } else {
            this.speedUp = 0.0f;
        }
    }

    public void moveMapUpGlide(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos - speed)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos - speed)))) {
            if (this.speedUp != 0.0f) {
                this.speedUp -= this.slowdown;
                if (this.speedUp < 0.0f) {
                    this.speedUp = 0.0f;
                }
            }
            GameLoop.map.yPos -= speed;
        } else {
            this.speedUp = 0.0f;
        }
    }

    public void moveMapDown(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + speed)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + speed)))) {
            this.speedDown = this.speedDown < this.maxSpeed ? (this.speedDown += this.slowdown) : this.maxSpeed;
            GameLoop.map.yPos += speed;
        } else {
            this.speedDown = 0.0f;
        }
    }

    public void moveMapDownGlide(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + speed)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height + speed)))) {
            if (this.speedDown != 0.0f) {
                this.speedDown -= this.slowdown;
                if (this.speedDown < 0.0f) {
                    this.speedDown = 0.0f;
                }
            }
            GameLoop.map.yPos += speed;
        } else {
            this.speedDown = 0.0f;
        }
    }

    public void moveMapLeft(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - speed), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - speed), (int)(this.pos.yPos + GameLoop.map.yPos)))) {
            this.speedLeft = this.speedLeft < this.maxSpeed ? (this.speedLeft += this.slowdown) : this.maxSpeed;
            GameLoop.map.xPos -= speed;
        } else {
            this.speedLeft = 0.0f;
        }
    }

    public void moveMapLeftGlide(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - speed), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos - speed), (int)(this.pos.yPos + GameLoop.map.yPos)))) {
            if (this.speedLeft != 0.0f) {
                this.speedLeft -= this.slowdown;
                if (this.speedLeft < 0.0f) {
                    this.speedLeft = 0.0f;
                }
            }
            GameLoop.map.xPos -= speed;
        } else {
            this.speedLeft = 0.0f;
        }
    }

    public void moveMapRight(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + speed), (int)(this.pos.yPos + GameLoop.map.yPos)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + speed), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)))) {
            this.speedRight = this.speedRight < this.maxSpeed ? (this.speedRight += this.slowdown) : this.maxSpeed;
            GameLoop.map.xPos += speed;
        } else {
            this.speedRight = 0.0f;
        }
    }

    public void moveMapRightGlide(float speed) {
        if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + speed), (int)(this.pos.yPos + GameLoop.map.yPos)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width + speed), (int)(this.pos.yPos + GameLoop.map.yPos + (float)this.height)))) {
            if (this.speedRight != 0.0f) {
                this.speedRight -= this.slowdown;
                if (this.speedRight < 0.0f) {
                    this.speedRight = 0.0f;
                }
            }
            GameLoop.map.xPos += speed;
        } else {
            this.speedRight = 0.0f;
        }
    }

    public void render(Graphics2D g) {
        if (up && !(down || left || right)) {
            this.playerWalkingUp.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
        if (down && !(up || left || right)) {
            this.playerWalkingDown.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
        if (left && !right) {
        	this.playerWalkingLeft.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
        if (right && !left) {
        	this.playerWalkingRight.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
        if (!(up || down || left || right)) {
            this.playerIdle.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
        if (right && left) {
        	this.playerIdle.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
        if (up && down) {
        	this.playerIdle.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == keyUP) {
            up = true;
        }
        if (key == keyDOWN) {
            down = true;
        }
        if (key == keyLEFT) {
            left = true;
        }
        if (key == keyRIGHT) {
            right = true;
        }
        if (key == keySPRINT) {
            sprinting = true;
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
            up = false;
        }
        if (key == keyDOWN) {
            down = false;
        }
        if (key == keyLEFT) {
            left = false;
        }
        if (key == keyRIGHT) {
            right = false;
        }
        if (key == keySPRINT) {
            sprinting = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}