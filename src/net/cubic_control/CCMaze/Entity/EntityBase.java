package net.cubic_control.CCMaze.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import net.cubic_control.CCMaze.Main.Main;
import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.resources.assets.Animator;

public abstract class EntityBase {
	Vector2F pos;
	protected int xPos, yPos, width, height;
	protected boolean removed = false;
	public static Rectangle render;
	
	protected int renderDistanceW = Main.width / 32;
    protected int renderDistanceH = Main.height / 32;
    
    protected Animator idle;
    protected Animator walkUp;
    protected Animator walkDown;
    protected Animator walkLeft;
    protected Animator walkRight;
    
    protected boolean up, down, left, right;
    
    protected float maxSpeed = 85.0f;
    protected float speedUp = 0.0f;
    protected float speedDown = 0.0f;
    protected float speedLeft = 0.0f;
    protected float speedRight = 0.0f;
    protected float slowdown = 47.05f;
    
    protected float fixDt = 0.016666668f;

	public EntityBase(int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.width = 42;
		this.height = 42;
	}
	public abstract void init();
	public abstract void tick(double deltaTime);
	public abstract void render(Graphics2D g);
	
	public int getXpos(){return xPos;}
	public int getYpos(){return yPos;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
}
