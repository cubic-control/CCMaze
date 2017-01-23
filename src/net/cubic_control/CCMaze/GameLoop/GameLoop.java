package net.cubic_control.CCMaze.GameLoop;

import net.cubic_control.CCMaze.GameState.GameStateManager;
import cubic_control.GameOperatingSystem.CCGameLoop;
import cubic_control.GameOperatingSystem.Vector2F;
import cubic_control.resources.assets.Assets;

@SuppressWarnings("serial")
public class GameLoop extends CCGameLoop {
	GameStateManager gsm;
	public static Assets assets = new Assets();
	public static Vector2F map = new Vector2F();

	public GameLoop(int width, int height) {
		super(width, height);
		
	}
	
	@Override
	public void init() {
		System.out.println("[System][INFO]:Initializing GameLoop");
		assets.init();
		Vector2F.setWorldVaribles(map.xPos, map.yPos);
		gsm = new GameStateManager();
		gsm.init();
		super.init();
	}
	
	@Override
	public void tick(double deltaTime) {
		Vector2F.setWorldVaribles(map.xPos, map.yPos);
		gsm.tick(deltaTime);
	}
	
	@Override
	public void render() {
		super.render();
		gsm.render(graphics2D);
		clear();
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
