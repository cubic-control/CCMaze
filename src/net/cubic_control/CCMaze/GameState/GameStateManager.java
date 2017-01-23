package net.cubic_control.CCMaze.GameState;

import java.awt.Graphics2D;
import java.util.Stack;

import net.cubic_control.CCMaze.GameStates.MenuState;
import net.cubic_control.CCMaze.Managers.AudioManager;

public class GameStateManager {
	public static AudioManager audio;

	public static Stack<GameState> states;
	
	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new MenuState(this));
		
		audio = new AudioManager("/assets/sounds/music/menu.wav", true);
	}
	
	public void tick(double deltaTime){
		states.peek().tick(deltaTime);
	}
	
	public void render(Graphics2D g){
		states.peek().render(g);
	}

	public void init() {
		System.out.println("[System][INFO]:Initializing GameStateManager");
		states.peek().init();
		
		audio.play();
	}
	
}
