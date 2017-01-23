package net.cubic_control.CCMaze.GameState;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import net.cubic_control.CCMaze.Managers.Mousemanager;
import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.resources.assets.Assets;

@SuppressWarnings("serial")
public class GameStateButton extends Rectangle{

	private Vector2F pos = new Vector2F();
	private GameState gamestate;
	@SuppressWarnings("unused")
	private GameStateManager gsm;
	private boolean isHeldOver;
	private int width = 32*6;
	private int height = 64;
	private BufferedImage defaultImage;
	private String buttonMessage;

	public GameStateButton(float xpos, float ypos, GameState gamestate, GameStateManager gsm,String buttonMessage) {
		this.gamestate = gamestate;
		this.gsm = gsm;
		this.pos.xPos = xpos;
		this.pos.yPos = ypos;
		this.buttonMessage = buttonMessage;
		setBounds((int)pos.xPos,(int) pos.yPos, width, height);
		defaultImage = Assets.getButton_notover;
	}

	public GameStateButton(float xpos, float ypos,String buttonMessage) {
		this.pos.xPos = xpos;
		this.pos.yPos = ypos;
		this.buttonMessage = buttonMessage;
		setBounds((int)pos.xPos,(int) pos.yPos, width, height);
		defaultImage = Assets.getButton_notover;
	}
	
	public void tick(){
		setBounds((int)pos.xPos,(int) pos.yPos, width, height);	
		
		if(Mousemanager.mouse != null){
			if(getBounds().contains(Mousemanager.mouse)){
				isHeldOver = true;
			}else{
				isHeldOver = false;
			}
			
		}
		
		if(isHeldOver){
			if(defaultImage != Assets.getButton_heldover){
				defaultImage = Assets.getButton_heldover;
			}
		}else{
			if(defaultImage != Assets.getButton_notover){
				defaultImage = Assets.getButton_notover;
			}
		}
		
		if(gamestate != null){
			if(isHeldOver){
				if(isPressed()){
					GameStateManager.states.push(gamestate);
					GameStateManager.states.peek().init();
					isHeldOver = false;
					Mousemanager.pressed = false;
				}
			}
		}
	}
	
	Font font = new Font("arial",10,30);
	
	public void render(Graphics2D g){
		g.drawImage(defaultImage, (int)pos.xPos, (int)pos.yPos,width,height,null);
		
		g.setFont(font);
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int tw = (int) font.getStringBounds(buttonMessage, frc).getWidth();
		g.drawString(buttonMessage, pos.xPos + width / 2 - tw / 2, pos.yPos + height / 2 + 8);

	}
	
	public boolean isHeldOver(){
		return isHeldOver;
	}
	
	public boolean isPressed(){
		return Mousemanager.pressed;
	}
	
	
}