package cubic_control.cc_game.GameStates;

import java.awt.Graphics2D;

import cubic_control.cc_game.GameState.GameState;
import cubic_control.cc_game.GameState.GameStateButton;
import cubic_control.cc_game.GameState.GameStateManager;
import cubic_control.cc_game.Main.Main;
import cubic_control.cc_game.Managers.Mousemanager;

public class LevelSelectState extends GameState{
	
	GameStateButton hubButton;
	GameStateButton tutorialButton;
	GameStateButton level1Button;
	GameStateButton level2Button;
	GameStateButton level3Button;
	GameStateButton level4Button;
	GameStateButton returnButton;
	Mousemanager mm;
	
	private int WIDTH = Main.width / 4 - 100;

	public LevelSelectState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		System.out.println("[System][INFO]:Initializing LevelSelectState");
		mm = new Mousemanager();
		hubButton = new GameStateButton(WIDTH, 100, new LevelLoader(gsm, "hub"), gsm, "Play Hub");
		tutorialButton = new GameStateButton(WIDTH, 200, new LevelLoader(gsm, "tutorial"), gsm, "Play Tutorial");
		level1Button = new GameStateButton(WIDTH, 300, new LevelLoader(gsm, "level1"), gsm, "Play Level1");
		level2Button = new GameStateButton(WIDTH, 400, new LevelLoader(gsm, "level2"), gsm, "Play Level2");
		level3Button = new GameStateButton(WIDTH, 500, new LevelLoader(gsm, "level3"), gsm, "Play Level3");
		level4Button = new GameStateButton(WIDTH, 600, new LevelLoader(gsm, "level4"), gsm, "Play Level4");
		returnButton = new GameStateButton(WIDTH, 700, "Return to Menu");
	}

	@Override
	public void tick(double deltaTime) {
		mm.tick();
		hubButton.tick();
		tutorialButton.tick();
		level1Button.tick();
		level2Button.tick();
		level3Button.tick();
		level4Button.tick();
		returnButton.tick();
		
		if(returnButton.isHeldOver()){
			if(returnButton.isPressed()){
				GameStateManager.states.push(new MenuState(gsm));
				GameStateManager.states.peek().init();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		hubButton.render(g);
		tutorialButton.render(g);
		level1Button.render(g);
		level2Button.render(g);
		level3Button.render(g);
		level4Button.render(g);
		returnButton.render(g);
		
		mm.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
	}

}
