package net.cubic_control.CCMaze.Entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import net.cubic_control.CCMaze.Check.Check;
import net.cubic_control.CCMaze.GameLoop.GameLoop;
import net.cubic_control.CCMaze.Main.Main;
import cubic_control.GameOperatingSystem.Vector2F;

public class Entity extends EntityBase{

	public Entity(int x, int y) {
		super(x, y);
		this.pos = new Vector2F((float)(Main.width / 2 - this.width / 2), (float)(Main.height / 2 - this.height / 2));
	}

	@Override
	public void init() {
		System.out.println("[System]:Initializing Entities");
        render = new Rectangle(
        		(int)(this.pos.xPos - this.pos.getWorldLocation().xPos + this.pos.xPos - 
        				(float)(this.renderDistanceW * 32 / 2) + (float)(this.width / 2)), 
        		(int)(this.pos.yPos - this.pos.getWorldLocation().yPos + this.pos.yPos - 
        				(float)(this.renderDistanceH * 32 / 2) + (float)(this.height / 2)), this.renderDistanceW * 32, this.renderDistanceH * 32);
	}

	@Override
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
        
        this.idle.runAnimation();
        
        if (up) {
            if (!Check.CollisionPlayerBlock((Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos), (int)(this.pos.yPos + GameLoop.map.yPos - moveAmountU)), (Point)new Point((int)(this.pos.xPos + GameLoop.map.xPos + (float)this.width), (int)(this.pos.yPos + GameLoop.map.yPos - moveAmountU)))) {
                this.speedUp = this.speedUp < this.maxSpeed ? (this.speedUp += this.slowdown) : this.maxSpeed;
                this.pos.yPos -= moveAmountU;
            } else {
                this.speedUp = 0.0f;
            }
            this.walkUp.runAnimation();
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
            this.walkDown.runAnimation();
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
            this.walkLeft.runAnimation();
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
            this.walkRight.runAnimation();
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
	}

	@Override
	public void render(Graphics2D g) {
		if (up && !(down || left || right)) {
            this.walkUp.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (down && !(up || left || right)) {
        	this.walkDown.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (left && !right) {
        	this.walkLeft.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (right && !left) {
        	this.walkRight.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (!(up || down || left || right)) {
            this.idle.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (right && left && !(up || down)) {
        	this.idle.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (up && down && !(left || right)) {
        	this.idle.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (down && left && right && !up) {
        	this.walkDown.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }else if (up && left && right && !down) {
        	this.walkUp.drawAnimation(g, (int)this.pos.xPos - this.width, (int)this.pos.yPos - this.height * 2, this.width * 3, this.height * 3);
        }
	}
	
	public float getX(){
    	return pos.xPos;
    }
    
    public float getY(){
    	return pos.yPos;
    }

}
